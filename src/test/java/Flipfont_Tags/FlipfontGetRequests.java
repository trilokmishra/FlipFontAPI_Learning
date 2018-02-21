package Flipfont_Tags;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.monotype.api_utils.logger.extentreportlogger.MTLogger;

import Utility.Token;
import Utility.Utils;
import helper.TokenHelper;


@Test
public class FlipfontGetRequests {

//Test case to verify APP version 
	public void Test_01() 
	{
		Response resp =	given().parameters("version","1.0.0").
		when().get("http://flipfonts-preprod-webapp-elb-100267766.us-east-1.elb.amazonaws.com/v1/apps/version");
		//System.out.println(resp.getStatusCode());
		AssertJUnit.assertEquals(resp.getStatusCode(), 200);
		//System.out.println(resp.asString());		
	}
// Test case to get tag as Popular
	public void Test_02()
	 {
		String expected = "Popular";
		String token = Utils.getToken();
		String resp= given().header("version","1.0.0").header("Authorization", token).
				queryParam("limit","25").queryParam("start","0").
				when().get("http://flipfonts-preprod-webapp-elb-100267766.us-east-1.elb.amazonaws.com/v1/tags").then().
	    		contentType(ContentType.JSON).
	    		extract().path("records[0].name");
				//System.out.println(resp.getStatusCode());	
				//System.out.println(resp);
				 AssertJUnit.assertEquals(resp, expected);
		 }
	
// Test case to get tag as Designer
	public void Test_03()
	{
		
			String expected = "Designer";
			String token = Utils.getToken();
			String resp= given().header("version","1.0.0").header("Authorization", token).
					queryParam("limit","25").queryParam("start","0").
					when().get("http://flipfonts-preprod-webapp-elb-100267766.us-east-1.elb.amazonaws.com/v1/tags").then().
		    		contentType(ContentType.JSON).
		    		extract().path("records[1].name");
					//System.out.println(resp.getStatusCode());	
					//System.out.println(resp);
					 AssertJUnit.assertEquals(resp, expected);
	}
	
// Test case to get tag as New
	public void Test_04()
		{
		String expected = "New";
		String token = Utils.getToken();
		String resp= given().header("version","1.0.0").header("Authorization", token).
				queryParam("limit","25").queryParam("start","0").
				when().get("http://flipfonts-preprod-webapp-elb-100267766.us-east-1.elb.amazonaws.com/v1/tags").then().
	    		contentType(ContentType.JSON).
	    		extract().path("records[2].name");
				//System.out.println(resp.getStatusCode());	
				//System.out.println(resp);
				 AssertJUnit.assertEquals(resp, expected);
				 
	   }
		
// Test case to get tag as Groovy
	@Test
	public void Test_05()
	{
		String expected = "Groovy";
		String token = Utils.getToken();
		String resp= given().header("version","1.0.0").header("Authorization", token).
				queryParam("limit","25").queryParam("start","0").
				when().get("http://flipfonts-preprod-webapp-elb-100267766.us-east-1.elb.amazonaws.com/v1/tags").then().
	    		contentType(ContentType.JSON).
	    		extract().path("records[3].name");
				//System.out.println(resp.getStatusCode());	
				//System.out.println(resp);
				 AssertJUnit.assertEquals(resp, expected);
		}

	//Test case if version name is not correct
	@Test
	public void Test_06() 
	{
		
		String token = Utils.getToken();
	
	Response resp= given().header("version","1.1.1").header("Authorization", token).
			queryParam("limit","25").queryParam("start","0").
			when().get("http://flipfonts-preprod-webapp-elb-100267766.us-east-1.elb.amazonaws.com/v1/tags").then().
    		contentType(ContentType.JSON).
    		extract().response();
			Assert.assertEquals(resp.getStatusCode(),426);
			
	}

	
@Test
	public void Test_00()
 	{
	Response resp=	given().
			body("{\"app_id\":\"7adc85ec69db43d8aa2c844416e8d68b\","+"\"hash\":\"f4cd2888cda01156e337123413253bd5\"}").
			when().
			contentType(ContentType.JSON).
			header("version", "1.0.0").
			post("http://flipfonts-preprod-webapp-elb-100267766.us-east-1.elb.amazonaws.com/v1/apps/authenticate/posts");
		//System.out.println(resp.asString());
	
 }
	
}