package com.thinktimetechno.Shrido.endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class User_EndPoints extends BaseEndpoints{

	RequestSpecification requestSpecification;
	public Response result;
	private  String application_ENDPOINT_PATH="";
    public static String inheritedToken;
    public static String token;
    public static String otp_code;
 
    public Response sendPostRequestWithPayload(String jsonFileName) throws IOException {
        switch (jsonFileName) {
            case "user register.json":
                application_ENDPOINT_PATH = "/api/user/register";
                break;

            case "Otp verify.json":
                application_ENDPOINT_PATH = "/api/otp/verify";
                break;

            case "Resend Otp":
                application_ENDPOINT_PATH = "/api/otp/resend_otp";
               
                break;
        }
       
        if (application_ENDPOINT_PATH.equals("/api/otp/resend_otp")) {
        	 requestSpecification = getRequestWithInheritedToken(inheritedToken);
            result = requestSpecification
                    .post(application_ENDPOINT_PATH);
        }
        else {

        String filePath = System.getProperty("user.dir") + "/src/test/resources/Payloads/User/" + jsonFileName;
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(jsonContent);

        if (application_ENDPOINT_PATH.equals("/api/otp/verify")) {
            jsonObject.put("id", token);
            jsonObject.put("otp", otp_code);
        }

        if (application_ENDPOINT_PATH.equals("/api/user/register")) {
            requestSpecification = getRequestWithJSONHeaders();
            String timeComponent = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
            String dynamicMobile = "+611" + timeComponent;
            jsonObject.put("mobile", dynamicMobile);
        } else {
            requestSpecification = getRequestWithInheritedToken(inheritedToken);
        }
        
        result = requestSpecification.body(jsonObject.toString())
                .post(application_ENDPOINT_PATH);
        }

        try {
            if (result.jsonPath().getString("data.token") != null)
                token = result.jsonPath().getString("data.token");

            if (result.jsonPath().getString("data.otp_code") != null)
                otp_code = result.jsonPath().getString("data.otp_code");

            if (result.jsonPath().getString("data.user.JWToken") != null)
                inheritedToken = result.jsonPath().getString("data.user.JWToken");

        } catch (Exception e) {
            System.out.println("ℹ️ Some fields were not present in the response.");
        }
    
		return result;


    }
   
   public Response sendGetRequest(String name) {
	    requestSpecification = getRequestWithInheritedToken(inheritedToken);
	   
	    switch (name) {
	        case "Get Referral History":
	            application_ENDPOINT_PATH = "/api/user/referral-history?page=1";
	            break;
	        case "Get Total Referrals":
	            application_ENDPOINT_PATH = "/api/user/total-referral";
	            break;
	            

	    }

	    result = requestSpecification.get(application_ENDPOINT_PATH);
	    return result;
	}

}