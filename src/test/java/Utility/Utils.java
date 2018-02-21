package Utility;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;

public class Utils {
	
	public static String getToken() {
		ResponseBody resp = 
				given().
				        //accept(ContentType.JSON).
				        contentType(ContentType.JSON).
				       header("version", "1.0.0").
				        body("{\"app_id\":\"7adc85ec69db43d8aa2c844416e8d68b\","+"\"hash\":\"f4cd2888cda01156e337123413253bd5\"}").
				when().
				        post("http://flipfonts-preprod-webapp-elb-100267766.us-east-1.elb.amazonaws.com/v1/apps/authenticate\r\n" + "").
				        thenReturn().body();
		
			ObjectMapper mapper = new ObjectMapper();
			Token token = null;
			try {
				 token = mapper.readValue(resp.asString(), Token.class);
				return token.getToken();
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
	}

}
