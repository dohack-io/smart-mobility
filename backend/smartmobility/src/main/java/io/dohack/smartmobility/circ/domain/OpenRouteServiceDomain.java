package io.dohack.smartmobility.circ.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.catalog.CatalogFeatures;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenRouteServiceDomain {
    ArrayList<Feature> features;

    @Override
    public String toString() {
        return "OpenRouteServiceDomain{" +
                "features=" + features +
                '}';
    }

    public OpenRouteServiceDomain() {
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }
}
