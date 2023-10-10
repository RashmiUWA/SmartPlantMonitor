package com.example.smartplantmonitor.ui.dto;

import java.util.List;

public class UserDto {

    private Integer id;
    private String name;

    private List<DevicesDto> devices;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        if(name == null){
            return "Unknown Device";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DevicesDto> getDevices() {
        return devices;
    }

    public void setDevices(List<DevicesDto> devices) {
        this.devices = devices;
    }
}
