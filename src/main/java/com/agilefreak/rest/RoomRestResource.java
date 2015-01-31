package com.agilefreak.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.agilefreak.helper.CardKeeper;

@Path("/checkusers")
public class RoomRestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkService(){
        return "Hello From Rest!!!";
    }
    
    @GET
    @Path("/check/{roomnumber}")
    @Produces(MediaType.TEXT_PLAIN)
    public String loggedIn(@PathParam ("roomnumber") String roomnumber) {
        return CardKeeper.getParticipants(Integer.parseInt(roomnumber)).toString();
    }
    
    /*@GET
    @Path("/extimate/{size}")
    public String estimate(@PathParam ("size") String size) {
        CardKeeper.getParticipants(Integer.parseInt(size)).toString();
    }*/
}
