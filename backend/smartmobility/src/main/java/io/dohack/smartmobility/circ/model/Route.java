package io.dohack.smartmobility.circ.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Route {
    private String provider;
    private int duration;
    private double price;
    private ArrayList<Step> steps;
}
