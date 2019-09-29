package io.dohack.smartmobility.circ;

import io.dohack.smartmobility.circ.domain.NominatimDomain;
import io.dohack.smartmobility.circ.model.Address;
import io.dohack.smartmobility.circ.model.GpsLocation;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class GeoProvider {
    final static private String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search";


    public static Address getCoords(String searchString) {
        searchString = searchString.replace('ß', 's');
        searchString = searchString.replace('ü', 'u');
        searchString = searchString.replace('ö', 'o');
        searchString = searchString.replace('ä', 'a');

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(NOMINATIM_URL)
                .queryParam("q", searchString)
                .queryParam("format", "json");
        NominatimDomain[] nominatimDomains = restTemplate.getForObject(builder.toUriString(), NominatimDomain[].class);

        GpsLocation gpsLocation = new GpsLocation(Double.parseDouble(nominatimDomains[0].getLat()),
                Double.parseDouble(nominatimDomains[0].getLon()));
        return new Address(gpsLocation, nominatimDomains[0].getDisplay_name());
    }

}

