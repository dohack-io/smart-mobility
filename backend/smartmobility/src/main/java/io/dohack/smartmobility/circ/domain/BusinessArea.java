package io.dohack.smartmobility.circ.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessArea {
    String id;
    String type;
    double unlockFee;
    double pricePerMinute;
    String currency;
    int SpeedLimit;
   // ArrayList<Vertex> parkRestriction;
    ArrayList<Vertex> vertices;

    public BusinessArea() {
    }

    @Override
    public String toString() {
        return "BusinessArea{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", unlockFee=" + unlockFee +
                ", pricePerMinute=" + pricePerMinute +
                ", currency='" + currency + '\'' +
                ", SpeedLimit=" + SpeedLimit +
                ", vertices=" + vertices +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getUnlockFee() {
        return unlockFee;
    }

    public void setUnlockFee(double unlockFee) {
        this.unlockFee = unlockFee;
    }

    public double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setPricePerMinute(double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getSpeedLimit() {
        return SpeedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        SpeedLimit = speedLimit;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }
}
