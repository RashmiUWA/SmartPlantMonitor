package com.example.smartplantmonitor.ui.datalist;

import com.example.smartplantmonitor.ui.dto.UserDto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static UserDto getData() {

        Gson gson = new Gson();
        UserDto userDto = gson.fromJson("{\n" +
                "  \"name\": \"Rashmi\",\n" +
                "  \"id\": \"1\",\n" +
                "  \"devices\": [\n" +
                "    {\n" +
                "      \"name\": \"Plant A\",\n" +
                "      \"id\": 1,\n" +
                "      \"description\": \"Plant A at Home\",\n" +
                "      \"condition\": 1,\n" +
                "      \"avgTemp\": 30,\n" +
                "      \"avgHumidity\": 80,\n" +
                "      \"avgLight\": 100,\n" +
                "      \"avgSoilMoisture\": 40\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Plant B\",    \n" +
                "      \"id\": 2,\n" +
                "      \"description\": \"Plant B at Office\",\n" +
                "      \"condition\": 5,\n" +
                "      \"avgTemp\": 30,\n" +
                "      \"avgHumidity\": 80,\n" +
                "      \"avgLight\": 100,\n" +
                "      \"avgSoilMoisture\": 40\n" +
                "    }\n" +
                "  ]\n" +
                "}", UserDto.class);


        return userDto;
    }
}