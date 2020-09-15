package com.pagerduty.sendevents;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class PDSender {
	public static void main(String[] args) {
		
		// Initialize the client - once per session
		HttpClient httpClient = new DefaultHttpClient();
		try {
			// Initialize the Post request with the generic endpoint for all events
		    HttpPost request = new HttpPost("https://events.pagerduty.com/v2/enqueue");
		    
		    // Create the payload per https://developer.pagerduty.com/api-reference/reference/events-v2/openapiv3.json/paths/~1enqueue/post
		    String serializedJSON = "{\n" + 
		    		"   \"payload\":{\n" + 
		    		"      \"summary\":\"atl14adoob-01.internal.secureworks.net is Down.\",\n" + 
		    		"      \"source\":\"atl14adoob-01.internal.secureworks.net\",\n" + 
		    		"      \"severity\":\"critical\",\n" + 
		    		"      \"component\":\"Cisco Catalyst 2960-48TC-S\",\n" + 
		    		"      \"custom_details\":{\n" + 
		    		"         \"Description\":\"Cisco IOS Software, C2960 Software (C2960-LANLITEK9-M), Version 12.2(55)SE11, RELEASE SOFTWARE (fc3) Technical Support: http://www.cisco.com/techsupport Copyright (c) 1986-2016 by Cisco Systems, Inc. Compiled Wed 17-Aug-16 13:46 by prod_rel_team\",\n" + 
		    		"         \"Application Name\":\"SolarWinds.Core.Common\",\n" + 
		    		"         \"Node\":\"atl14adoob-01.internal.secureworks.net\",\n" + 
		    		"         \"Node Availability\":\"Node status is Down.\"\n" + 
		    		"      }\n" + 
		    		"   },\n" + 
		    		"   \"routing_key\":\"<your-routing-key>\",\n" + 
		    		"   \"event_action\":\"trigger\",\n" + 
		    		"   \"client\":\"SolarWinds Orion\",\n" + 
		    		"   \"client_url\":\"https://www.solarwinds.com\"\n" + 
		    		"}";
		    
		    StringEntity params = new StringEntity(serializedJSON);
		    
		    // Add JSON header
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    
		    // POST to PagerDuty
		    HttpResponse response = httpClient.execute(request);
		    
		    // Check results
		    System.out.println(response.getStatusLine());
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
}