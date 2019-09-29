package io.dohack.smartmobility.circ.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessAreaDomain {
    ArrayList<BusinessArea> businessAreas;
    public BusinessAreaDomain() {

    }

    public BusinessAreaDomain(ArrayList<BusinessArea> businessAreas) {
        this.businessAreas = businessAreas;
    }

    public void setBusinessAreas(ArrayList<BusinessArea> businessAreas) {
        this.businessAreas = businessAreas;
    }

    public ArrayList<BusinessArea> getBusinessAreas() {
        return businessAreas;
    }
}
