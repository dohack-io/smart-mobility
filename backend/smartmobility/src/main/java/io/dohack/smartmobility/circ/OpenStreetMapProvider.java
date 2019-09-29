package io.dohack.smartmobility.circ;

import io.dohack.smartmobility.circ.domain.OpenRouteServiceDomain;
import io.dohack.smartmobility.circ.model.GpsLocation;
import io.dohack.smartmobility.circ.model.Journey;
import io.dohack.smartmobility.circ.model.MovementProfile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class OpenStreetMapProvider {
    private static final String OPEN_ROUTING_SERVICE_SCOOTER_URL = "https://api.openrouteservice.org/v2/directions/cycling-road";
    private static final String OPEN_ROUTING_SERVICE_WALK_URL = "https://api.openrouteservice.org/v2/directions/foot-walking";
    private static final String API_KEY = "5b3ce3597851110001cf624835e7fc3e03c24f9d95be709be802c86f&";


    public static Journey getRoute(GpsLocation startsAt, GpsLocation endsAt, MovementProfile movementProfile) {

        RestTemplate restTemplate = new RestTemplate();
        String start = startsAt.getLongitude() + "," + startsAt.getLatitude();
        String end = endsAt.getLongitude() + "," + endsAt.getLatitude();
        String url = "";
        switch (movementProfile) {
            case WALK:
                url = OPEN_ROUTING_SERVICE_WALK_URL;
                break;
            case SCOOTER:
                url = OPEN_ROUTING_SERVICE_SCOOTER_URL;
                break;
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api_key", API_KEY)
                .queryParam("start", start)
                .queryParam("end", end);

        OpenRouteServiceDomain openRouteServiceDomain = restTemplate.getForObject(builder.toUriString(), OpenRouteServiceDomain.class);
        double distance = openRouteServiceDomain.getFeatures().get(0).getProperties().getSegments().get(0).getDistance();
        System.out.println(openRouteServiceDomain.getFeatures().get(0).getProperties().getSegments().get(0).getDuration());
        int duration = (int) Math.round(openRouteServiceDomain.getFeatures().get(0).getProperties().getSegments().get(0).getDuration());

        return new Journey(distance, duration);

    }
}
