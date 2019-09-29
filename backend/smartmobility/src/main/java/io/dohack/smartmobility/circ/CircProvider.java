package io.dohack.smartmobility.circ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dohack.smartmobility.circ.domain.*;
import io.dohack.smartmobility.circ.model.GPSLocation;
import io.dohack.smartmobility.circ.model.Journey;
import io.dohack.smartmobility.circ.model.MovementProfile;
import io.dohack.smartmobility.circ.model.ReturningPoint;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CircProvider {
    final static private String BUSINESS_AREA_URL = "https://node.integrations.goflash.com/partner/areas/business";
    final static private String NEAREST_AREA_URL = "https://node.integrations.goflash.com/partner/vehicles/nearest";
    final static private String AUTH_URL = "https://node.integrations.goflash.com/partner/oauth/token";

/*
    public CircProvider() {

    }
*/
    public void circHandler() {
        // Remove Mockdata and return Object for Frontend
        // adesso
        double longitude = 7.5269513432156145;
        double latitude = 51.50435048769515;
        GPSLocation start = new GPSLocation(latitude, longitude);

        // Kampstraße
        double longitude2 = 7.4620800917897725;
        double latitude2 = 51.51491967325967;
        GPSLocation end = new GPSLocation(latitude2, longitude2);


        // Get the nearest available scooter
        ArrayList<Vehicle> vehicles = getNearestScooter(start);
        Vehicle chosenVehicle = vehicles.get(0);

        // Calculate distance and durations from start to scooter
        GPSLocation vehiclePosition = new GPSLocation(chosenVehicle.getLocation().getLatitude(), chosenVehicle.getLocation().getLongitude());
        Journey routeToVehicle = OpenStreetMapProvider.getRoute(start, vehiclePosition, MovementProfile.WALK);

        // find return point for scooter close to desired destination
        // TODO: move returning point around 600 meters closer the destination since the point induces a area not a single point
        ReturningPoint returningPoint = findNearestReturningPoint(end);

        // Calculate distance and durations from scooter pick-up point to returning point (driven with scooter)
        Journey routeToReturnPoint = OpenStreetMapProvider.getRoute(vehiclePosition, returningPoint.getGpsLocation(), MovementProfile.SCOOTER);

        // Calculate distance and durations from returning point (driven with scooter) to desired destination
        Journey routeToDestination = OpenStreetMapProvider.getRoute(returningPoint.getGpsLocation(), end, MovementProfile.WALK);


        System.out.println("Return: " + returningPoint.getGpsLocation());
        System.out.println("Weg 1 (Distanz): " + routeToVehicle.getDistance());
        System.out.println("Weg 2 (Distanz): " + routeToReturnPoint.getDistance() + " Duration: " +  routeToReturnPoint.getDuration());
        System.out.println("Weg 3 (Distanz): " + routeToDestination.getDistance());
        int duration = routeToVehicle.getDuration() + routeToReturnPoint.getDuration() + routeToDestination.getDuration();
        System.out.println("Dauer: " + duration);
        double price = 1 + 0.15 * Math.round(routeToReturnPoint.getDuration()/60);
        System.out.println("Price:  " + price);

    }


    static private String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();
        parameters.add("client_id", "dortstdt.EK97HTcwyQdB");
        parameters.add("client_secret", "4bdgEVcP76TeUGc4");
        parameters.add("grant_type", "client_credentials");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String,String>> entity =
                new HttpEntity<MultiValueMap<String, String>>(parameters, headers);

        String tokenString = restTemplate.postForObject(AUTH_URL, entity, String.class);


        try {

            JsonNode root = new ObjectMapper().readTree(tokenString);
            String token = root.path("access_token").asText();
            return token;

        }catch(Exception e) {
            System.out.println("JSON convert failed");
            return "";
        }
    }

    static ReturningPoint findNearestReturningPoint(GPSLocation destination) {
        List<BusinessArea> businessAreas = getBusinesAreas(destination);
        double minDist = -1;
        GPSLocation nearestReturningPoint = new GPSLocation(0,0);
        Iterator<BusinessArea> businessAreaIterator = businessAreas.iterator();
        while(businessAreaIterator.hasNext()) {
            BusinessArea businessArea = businessAreaIterator.next();
            ArrayList<Vertex> vertices = businessArea.getVertices();
            for (Vertex vertex : vertices) {
                double distance = distance(vertex.getLatitude(),
                        destination.getLatitude(),
                        vertex.getLongitude(),
                        destination.getLongitude(),
                        1,
                        1);
                if (minDist == -1 || distance < minDist) {
                    minDist = distance;
                    System.out.println(minDist);
                    nearestReturningPoint = new GPSLocation(vertex.getLatitude(), vertex.getLongitude());
                }
            }
        }
        return new ReturningPoint(minDist, nearestReturningPoint);
        }


    static public ArrayList<Vehicle> getNearestScooter(GPSLocation gpsLocation) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(NEAREST_AREA_URL)
                .queryParam("lat", gpsLocation.getLatitude())
                .queryParam("count", 5)
                .queryParam("radius", 500)
                .queryParam("long", gpsLocation.getLongitude());

        ResponseEntity<NearestScooterDomain> res = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                NearestScooterDomain.class);
        NearestScooterDomain nearestScooterDomain =  res.getBody();
        ArrayList<Vehicle> vehicles = nearestScooterDomain.getVehicles();

        return vehicles;


    }



    public static List<BusinessArea> getBusinesAreas(GPSLocation gpsLocation) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BUSINESS_AREA_URL)
                .queryParam("lat", gpsLocation.getLatitude())
                .queryParam("long", gpsLocation.getLongitude());

        ResponseEntity<BusinessArea[]> ret = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                BusinessArea[].class);
        List<BusinessArea> list =  Arrays.asList(ret.getBody());

        return list;
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ getToken());
        headers.set("Api-Version", "1.0");
        return headers;
    }


    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}
