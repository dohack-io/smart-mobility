package io.dohack.smartmobility.circ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dohack.smartmobility.circ.domain.BusinessArea;
import io.dohack.smartmobility.circ.domain.NearestScooterDomain;
import io.dohack.smartmobility.circ.domain.Vehicle;
import io.dohack.smartmobility.circ.domain.Vertex;
import io.dohack.smartmobility.circ.model.*;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CircProvider {
    private static final String BUSINESS_AREA_URL = "https://node.integrations.goflash.com/partner/areas/business";
    private static final String NEAREST_AREA_URL = "https://node.integrations.goflash.com/partner/vehicles/nearest";
    private static final String AUTH_URL = "https://node.integrations.goflash.com/partner/oauth/token";
    
    public static CircTrip generateCircTip(String from, String to) {

        // Remove Mockdata and return Object for Frontend
        // adesso
        double longitude = 7.5269513432156145;
        double latitude = 51.50435048769515;
        GpsLocation start = new GpsLocation(latitude, longitude);

        // Kampstraße
        Address startCoords = GeoProvider.getCoords(from);
        Address endCoords = GeoProvider.getCoords(to);


        // Get the nearest available scooter
        List<Vehicle> vehicles = getNearestScooter(startCoords.getGpsLocation());
        Vehicle chosenVehicle = vehicles.get(0);

        // Calculate distance and durations from start to scooter
        GpsLocation vehiclePosition = new GpsLocation(chosenVehicle.getLocation().getLatitude(), chosenVehicle.getLocation().getLongitude());
        Journey routeToVehicle = OpenStreetMapProvider.getRoute(start, vehiclePosition, MovementProfile.WALK);

        // find return point for scooter close to desired destination
        ReturningPoint returningPoint = findNearestReturningPoint(endCoords.getGpsLocation());

        // Calculate distance and durations from scooter pick-up point to returning point (driven with scooter)
        Journey routeToReturnPoint = OpenStreetMapProvider.getRoute(vehiclePosition, returningPoint.getGpsLocation(), MovementProfile.SCOOTER);

        // Calculate distance and durations from returning point (driven with scooter) to desired destination
        Journey routeToDestination = OpenStreetMapProvider.getRoute(returningPoint.getGpsLocation(), endCoords.getGpsLocation(), MovementProfile.WALK);


        System.out.println("Return: " + returningPoint.getGpsLocation());
        System.out.println("Weg 1 (Distanz): " + routeToVehicle.getDistance());
        System.out.println("Weg 2 (Distanz): " + routeToReturnPoint.getDistance() + " Duration: " + routeToReturnPoint.getDuration());
        System.out.println("Weg 3 (Distanz): " + routeToDestination.getDistance());

        
        int duration = routeToVehicle.getDuration() + routeToReturnPoint.getDuration() + routeToDestination.getDuration();
        duration = Math.round(duration /60);

        double distance = routeToVehicle.getDistance() + routeToReturnPoint.getDistance() + routeToDestination.getDistance();
        double price = 1 + 0.15 * Math.round(routeToReturnPoint.getDuration() / 60);

        CircTrip circTrip = new CircTrip();
        circTrip.setDistance(distance);
        circTrip.setDuration(duration);
        circTrip.setPrice(price);
        circTrip.setWalkToScooterDuration(routeToVehicle.getDuration());
        circTrip.setWalkToScooterDistance(routeToVehicle.getDistance());
        circTrip.setScooterDuration(routeToReturnPoint.getDuration());
        circTrip.setScooterDistance(routeToReturnPoint.getDistance());
        circTrip.setWalkToDestinationDuration(routeToDestination.getDuration());
        circTrip.setWalkToDestinationDistance(routeToDestination.getDistance());
        circTrip.setStart(startCoords);
        circTrip.setEnd(endCoords);
        circTrip.setReturningPoint(returningPoint.getGpsLocation());

        return circTrip;

    }


    private static String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", "dortstdt.EK97HTcwyQdB");
        parameters.add("client_secret", "4bdgEVcP76TeUGc4");
        parameters.add("grant_type", "client_credentials");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity =
                new HttpEntity<>(parameters, headers);

        String tokenString = restTemplate.postForObject(AUTH_URL, entity, String.class);


        try {

            JsonNode root = new ObjectMapper().readTree(tokenString);
            return root.path("access_token").asText();

        } catch (Exception e) {
            System.out.println("JSON convert failed");
            return "";
        }
    }

    private static ReturningPoint findNearestReturningPoint(GpsLocation destination) {
        List<BusinessArea> businessAreas = getBusinesAreas(destination);
        double minDist = -1;
        GpsLocation nearestReturningPoint = new GpsLocation(0, 0);
        for (BusinessArea businessArea : businessAreas) {
            ArrayList<Vertex> vertices = businessArea.getVertices();
            if (areaContains(vertices, destination)){
                return new ReturningPoint(0, destination);
            }
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
                    nearestReturningPoint = new GpsLocation(vertex.getLatitude(), vertex.getLongitude());
                }
            }

        }
        return new ReturningPoint(minDist, nearestReturningPoint);
    }


    public static List<Vehicle> getNearestScooter(GpsLocation gpsLocation) {
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
        NearestScooterDomain nearestScooterDomain = res.getBody();
        if (nearestScooterDomain != null) {
            return nearestScooterDomain.getVehicles();
        }
        return Collections.emptyList();
    }


    public static List<BusinessArea> getBusinesAreas(GpsLocation gpsLocation) {
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

        return Arrays.asList(ret.getBody());
    }


    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getToken());
        headers.set("Api-Version", "1.0");
        return headers;
    }


    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * <p>
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     *
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

    public static boolean areaContains(ArrayList<Vertex> vertices, GpsLocation test) {
        // y = long
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = vertices.size() - 1; i < vertices.size(); j = i++) {
            if ((vertices.get(i).getLongitude() > test.getLongitude()) !=
                    (vertices.get(j).getLongitude() > test.getLongitude()) &&
                    (test.getLatitude() < (vertices.get(j).getLatitude() -
                            vertices.get(i).getLatitude()) *
                            (test.getLongitude() - vertices.get(i).getLongitude()) /
                            (vertices.get(j).getLongitude()-vertices.get(i).getLongitude()) +
                            vertices.get(i).getLatitude() )) {
                result = !result;
            }
        }
        return result;
    }

}
