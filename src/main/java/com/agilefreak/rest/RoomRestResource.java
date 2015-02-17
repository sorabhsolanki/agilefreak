package com.agilefreak.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import com.agilefreak.dto.EstimateDto;
import com.agilefreak.helper.CardKeeper;

@Path("/checkusers")
public class RoomRestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkService() {
        return "Hello From Rest!!!";
    }


    @GET
    @Path("/check/{roomnumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstimateDto> loggedIn(@PathParam("roomnumber") String roomnumber) {
        return CardKeeper.getParticipants(Integer.parseInt(roomnumber));
    }


    @GET
    @Path("/estimate/{estimate}/room/{roomnumber}")
    @Produces(MediaType.TEXT_PLAIN)
    public void setEstimate(@PathParam("estimate") String estimate, @PathParam("roomnumber") String roomnumber,
            @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        CardKeeper.setUserEstimate(Integer.parseInt(roomnumber), estimate, session);
    }

    @GET
    @Path("/showcards")
    public void setShowCardBooleanInREquest(@Context HttpServletRequest request) {
       request.setAttribute("showcards", true); 
    }

    @POST
    @Path("/formsubmission")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String checkFormSubmission(@FormParam("userName") String userName) {
        return "UserName is " + userName;
    }

}
