package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java 
//Created for perform Create ,Read,Update,Delete requests the user API.

public class UserEndPoints2 {
	
	static ResourceBundle getUrl() {
		
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes; 
	}
	public static Response createUser(User payload) {
		
		String post_url=getUrl().getString("post_url");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(post_url);
		return response;
	}
    public static Response getUser(String userName) {
		
    	String get_url=getUrl().getString("get_url");
		Response response=given()
				.pathParam("username", userName)
				.when()
				.get(get_url);
		return response;
	}
    public static Response updateUser(String userName,User payload) {
		
    	String put_url=getUrl().getString("put_url");
    	
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", userName)
				.body(payload)
				.when()
				.put(put_url);
		return response;
	}
   public static Response deleteUser(String userName) {
		
	   String delete_url=getUrl().getString("delete_url");
	   
		Response response=given()
				.pathParam("username", userName)
				.when()
				.delete(delete_url);
		return response;
	}

}
