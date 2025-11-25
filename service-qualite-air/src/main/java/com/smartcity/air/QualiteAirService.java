package com.smartcity.air;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class QualiteAirService {

    private static final Map<String, Integer> indicesAqi = Map.of(
            "Centre", 55,
            "Nord", 42,
            "Sud", 68
    );

    public int getAqi(String zone) {
        return indicesAqi.getOrDefault(zone, -1);
    }
}