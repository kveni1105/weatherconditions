package com.kce.wether.dto;

public class WeatherResponseDTO {

    private String conds;
    private Double tempm;
    private Double hum;
    private Double pressurem;

    public WeatherResponseDTO(String conds, Double tempm, Double hum, Double pressurem) {
        this.conds = conds;
        this.tempm = tempm;
        this.hum = hum;
        this.pressurem = pressurem;
    }

    public String getConds() {
        return conds;
    }

    public Double getTempm() {
        return tempm;
    }

    public Double getHum() {
        return hum;
    }

    public Double getPressurem() {
        return pressurem;
    }
}