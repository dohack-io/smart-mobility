package io.dohack.smartmobility.circ;

import io.dohack.smartmobility.circ.domain.NominatimDomain;
import io.dohack.smartmobility.circ.model.Address;
import io.dohack.smartmobility.circ.model.GPSLocation;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;



public class GeoProvider {
    final static private String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search";


    public static Address getCoords(String searchString) {
        searchString.replace('ß', 's');
        searchString.replace('ü', 'u');
        searchString.replace('ö', 'o');
        searchString.replace('ä', 'a');

        RestTemplate restTemplate = new RestTemplate();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(NOMINATIM_URL)
                .queryParam("q", searchString)
                .queryParam("format", "json");
        NominatimDomain[] nominatimDomains = restTemplate.getForObject(builder.toUriString(), NominatimDomain[].class);

        GPSLocation gpsLocation = new GPSLocation(Double.parseDouble(nominatimDomains[0].getLat()), Double.parseDouble(nominatimDomains[0].getLon()));
        Address address = new Address(gpsLocation, nominatimDomains[0].getDisplay_name());

        return address;
    }

}

