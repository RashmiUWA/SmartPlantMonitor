package com.example.smartplantmonitor.ui.dto;

public class DevicesDto {

    private String id;
    private String name;
    private String description;
    private Integer condition;
    private Double avgTemp;
    private Double avgHumidity;
    private Double avgLight;
    private Double avgSoilMoisture;

    private String status;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Double getAvgTemp() {
        return avgTemp;
    }

    public void setAvgTemp(Double avgTemp) {
        this.avgTemp = avgTemp;
    }

    public Double getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(Double avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    public Double getAvgLight() {
        return avgLight;
    }

    public void setAvgLight(Double avgLight) {
        this.avgLight = avgLight;
    }

    public Double getAvgSoilMoisture() {
        return avgSoilMoisture;
    }

    public void setAvgSoilMoisture(Double avgSoilMoisture) {
        this.avgSoilMoisture = avgSoilMoisture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
