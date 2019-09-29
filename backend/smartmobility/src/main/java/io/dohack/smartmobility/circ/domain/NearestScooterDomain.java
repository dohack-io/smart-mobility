package io.dohack.smartmobility.circ.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NearestScooterDomain {
    ArrayList<Vehicle> vehicles;
    int total;

    public NearestScooterDomain() {
    }

    @Override
    public String toString() {
        return "NearestScooterDomain{" +
                "vehicles=" + vehicles +
                ", total=" + total +
                '}';
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

