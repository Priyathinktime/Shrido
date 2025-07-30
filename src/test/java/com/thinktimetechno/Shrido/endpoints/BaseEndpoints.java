package com.thinktimetechno.Shrido.endpoints;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.thinktimetechno.constants.FrameworkConstants;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class BaseEndpoints {
	public static final int SUCCESS_STATUS_CODE = 200;

	public static final int GET_REQUEST = 0;
	public static final int POST_REQUEST = 1;
	public static final int DELETE_REQUEST = 2;
	public static final int PUT_REQUEST = 3;
	public static String tokenkey=null;
	 public static String inheritedToken = null;

		protected final String base_url = "https://perfbkend.solarion.ai";
		
		
	public BaseEndpoints() {
	}
	
	//newly added 
	public void verifyResponseKeyValuePairs(DataTable dataTable, Response response) {
	    List<Map<String, String>> expectedList = dataTable.asMaps(String.class, String.class);
	    for (Map<String, String> row : expectedList) {
	        String key = row.get("Key");
	        String expectedValue = row.get("Value");
	        Object actualValue = response.jsonPath().get(key);

	        if (actualValue instanceof Integer) {
	            Assert.assertEquals(actualValue, Integer.valueOf(expectedValue),
	                    "Mismatch for key: " + key);
	        } else if (actualValue instanceof Boolean) {
	            Assert.assertEquals(actualValue, Boolean.valueOf(expectedValue),
	                    "Mismatch for key: " + key);
	        } else {
	            Assert.assertEquals(String.valueOf(actualValue), expectedValue,
	                    "Mismatch for key: " + key);
	        }
	    }
	}


	public RequestSpecification getRequestWithOnlyTokenHeader(String inheritedToken) {
	    return RestAssured.given().log().all()
	        .header("x-access-token", inheritedToken);  // No Content-Type
	}




	public RequestSpecification getRequestWithInheritedToken(String inheritedToken) {
		RequestSpecification r = RestAssured.given().log().all()
			.header("Content-Type", "application/json")
			.header("x-access-token", inheritedToken);  
		return r;
	}
	public RequestSpecification getRequestWithJSONHeader(String endpointPath) {
	    RequestSpecification r = RestAssured.given();
	    r.header("Content-Type", "application/json");
	    r.header("Accept", "application/json");

	    if (!endpointPath.equals("/api/user/register") && inheritedToken != null) {
	        r.header("x-access-token", inheritedToken);
	    }

	    return r;
	}



	public void extractAndStoreAuthToken(Response response, String jsonPath) {
	    inheritedToken = response.jsonPath().getString(jsonPath);
	    System.out.println("Token inherited: " + inheritedToken);
	}


	
	public void verifyResponseKeyValues(String key, String val, Response r) {
		String keyValue = r.jsonPath().getString(key);
		assertThat(keyValue, is(val));
	}
	public String verifyTokenResponseKeyValues(String key,Response r) {
		tokenkey = r.jsonPath().getString(key);
		return tokenkey;
	}

	public void verifyTrue(boolean val) {
		assertTrue(val);
	}

	public void verifyFalse(boolean val) {
		assertFalse(val);
		;
	}

	public void verifyResponseStatusValue(Response response, int expectedCode) {
		assertThat(response.getStatusCode(), is(expectedCode));
	}

	public String getBaseUrl() {
		return this.base_url;
	}

	public void verifyNestedResponseKeyValues(String nestTedCompnent, String key, String val, Response r) {
		Map<String, String> nestedJSON = r.jsonPath().getMap(nestTedCompnent);
		String actual = String.valueOf(nestedJSON.get(key));
		assertThat(actual, is(val));
	}

	public void verifyNestedArrayValueResponseKeyValues(String nestTedCompnent, String[] val, Response r) {

		ArrayList<Object> nestedArray = (ArrayList<Object>) r.jsonPath().getList(nestTedCompnent);

		String actual;

		for (int i = 0; i < nestedArray.size(); i++) {
			actual = (String) nestedArray.get(i);
			assertThat(actual, is(val[i]));
		}
	}

	public void verifyNestedArrayMapResponseKeyValues(String nestTedCompnent, String key, String[] val, Response r) {
		ArrayList<Object> nestedArray = (ArrayList<Object>) r.jsonPath().getList(nestTedCompnent);

		String actual;
		for (int i = 0; i < nestedArray.size(); i++) {
			Map<String, String> map = (Map<String, String>) nestedArray.get(i);
			actual = String.valueOf(map.get(key));
			assertThat(actual, is(val[i]));
		}
	}
	
	public RequestSpecification getRequestWithJSONHeadersToken() {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/json");
		r.header("Authorization", "Bearer " + FrameworkConstants.STATIC_TOKEN);
		return r;
	}


	public RequestSpecification getRequestWithJSONHeaders(String token) {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/json");
		r.header("Authorization", "Bearer " + token);
//		.contentType(ContentType.JSON)
        
		return r;
	}
	public RequestSpecification getRequestWithJSONHeadersToken(String token) {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/json");
		r.header("Authorization", "Bearer " + token);
//		.contentType(ContentType.JSON)
        
		return r;
	}
	
	
	
	public RequestSpecification getRequestWithJSONHeaders() {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/json");
        
		return r;
	}
	public RequestSpecification getRequestWithJSONHeaders(String P,String S) {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/json");
		r.header(P, S);
		return r;
	}

	public RequestSpecification getRequestWithXMLHeaders() {
		RequestSpecification r = RestAssured.given();
		r.header("Content-Type", "application/xml");
		return r;
	}

	protected JSONObject createJSONPayload(Object pojo) {
		return new JSONObject(pojo);
	}
//	public JSONObject createJSONPayload(Object pojo) {
//		return new JSONObject(pojo);
//	}
	
	
	
	public JSONObject getUpdatedPayload(String filePath) throws IOException {
		   String content = new String(Files.readAllBytes(new File(filePath).toPath()));
		    JSONObject jsonObject = new JSONObject(content);

		    String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		    String updatedName = "TestFolder_" + timeStamp;

		    // ✅ Case 1: Direct "name" key in root object
		    if (jsonObject.has("name")) {
		        jsonObject.put("name", updatedName);
		    }

		    // ✅ Case 2: Inside objects array -> set name for each object
		    if (jsonObject.has("objects")) {
		        JSONArray objects = jsonObject.getJSONArray("objects");
		        for (int i = 0; i < objects.length(); i++) {
		            JSONObject obj = objects.getJSONObject(i);
		            if (obj.has("name")) {
		                obj.put("name", updatedName); // or add "_i" to name if multiple needed
		            }
		        }
		    }

		    return jsonObject;
	  
	}


	public Response sendRequest(RequestSpecification request, int requestType, String url, Object pojo) {
		Response response = null;

		// Add the Json to the body of the request
		//if (null != pojo) {
			//String payload = createJSONPayload(pojo).toString();
			request.body(pojo);
		//}

		// need to add a switch based on request type
		switch (requestType) {
		case BaseEndpoints.GET_REQUEST:
			if (null == request) {
				response = RestAssured.when().get(url);
			} else {
				response = request.get(url);
			}
			break;
		case BaseEndpoints.POST_REQUEST:
			if (null == request) {
				response = RestAssured.when().post(url);
			} else {
				response = request.post(url);
			}
			break;
		case BaseEndpoints.DELETE_REQUEST:
			if (null == request) {
				response = RestAssured.when().delete(url);
			} else {
				response = request.delete(url);
			}
			break;
		case BaseEndpoints.PUT_REQUEST:
			if (null == request) {
				response = RestAssured.when().put(url);
			} else {
				response = request.put(url);
			}
			break;
		default:
			if (null == request) {
				response = RestAssured.when().post(url);
			} else {
				response = request.post(url);
			}
			response = request.post(url);
			break;
		}
		return response;
	}
}
