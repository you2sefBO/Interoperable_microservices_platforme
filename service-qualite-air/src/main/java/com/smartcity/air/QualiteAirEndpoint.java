package com.smartcity.air;

import com.smartcity.air.qualiteair.GetAQIRequest;
import com.smartcity.air.qualiteair.GetAQIResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint 
public class QualiteAirEndpoint {

 private static final String NAMESPACE_URI = "http://www.smartcity.com/air/qualiteair";

 @Autowired
 private QualiteAirService qualiteAirService;

 @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAQIRequest")
 @ResponsePayload 
 public GetAQIResponse handleGetAqiRequest(@RequestPayload GetAQIRequest request) {

     String zone = request.getZone();

     int aqi = qualiteAirService.getAqi(zone);

     GetAQIResponse response = new GetAQIResponse();
     response.setAqi(aqi);

     return response;
 }
}
