package com.cigouyun.ebiz.ws.demo.server;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
 
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
 
	private static String RESOURCE_PACKAGE="com.cigouyun.ebiz.ws.demo.server";
	
	public static final String host="192.168.0.201";
	
    private static URI getBaseURI() {
         // return UriBuilder.fromUri("http://localhost/").port(CrunchifyRESTService.port).build();
    	return UriBuilder.fromUri("http://"+ host +"/").port(CrunchifyRESTService.port).build();
    }
 
     public static final URI BASE_URI = getBaseURI();
 
     protected static HttpServer startServer() throws IOException {
         System.out.println("Starting grizzly...");
         ResourceConfig rc = new PackagesResourceConfig(RESOURCE_PACKAGE);
         return GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
     }
 
     public static void main(String[] args) throws IOException {
    	 
    	 
         HttpServer httpServer = startServer();
         System.out.println(String.format("Jersey app started with WADL available at "
                 + "%sapplication.wadl\n" + 
                 "Hit enter to stop it...",
                 BASE_URI, BASE_URI));
         System.in.read();
         httpServer.stop();
     }
}

