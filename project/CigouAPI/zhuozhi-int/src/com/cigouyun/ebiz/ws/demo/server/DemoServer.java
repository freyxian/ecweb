package com.cigouyun.ebiz.ws.demo.server;

import java.util.Date;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.NotFoundException;

@Path("/users")
public class DemoServer {

//The Java method will process HTTP GET requests

	@Path("/getUser/{username}/{password}")
	@GET
	@Produces("text/plain")
	// @Consumes("text/plain")
	public String getUser( @PathParam("username") String userName, @PathParam("password") String password ) {
		
		String ret = "Name:"+userName+"\n"+
				"Password:"+password;
		return ret;
		
	}
	
	 @POST
	 @Path("{id}")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 
	 // public JSONObject sayPlainTextHello(JSONObject inputJsonObj inputJsonObj) {
	 public JSONObject sayPlainTextHello(@PathParam("id")JSONObject inputJsonObj) throws JSONException {

	        String input = (String) inputJsonObj.get("input");
	        String output="The input you sent is :"+input;
	        JSONObject outputJsonObj = new JSONObject();
	        outputJsonObj.put("output", output);

	        return outputJsonObj;
	    }
}
	
	