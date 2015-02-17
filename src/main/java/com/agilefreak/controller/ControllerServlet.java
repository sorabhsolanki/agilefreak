package com.agilefreak.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.agilefreak.helper.CardKeeper;
import com.agilefreak.helper.CardKeeper.SessionEstimate;

@WebServlet(name = "roomServlet", urlPatterns = { "/room" })
public class ControllerServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ControllerServlet.class);


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("Hello servlet 3.0");
        
        
        String roomNo = request.getParameter("roomnumber");
        List<SessionEstimate> estimates = CardKeeper.enterRoom(new Integer(roomNo), request.getSession());
        request.setAttribute("myList", estimates);
        request.setAttribute("roomnumber", roomNo);
        request.setAttribute("result", estimates.size());
        
        SessionEstimate sessionEstimate = CardKeeper.getSessionEstimateFromListOfSessionEstimates(estimates, request.getSession());
        request.setAttribute("usercardno", sessionEstimate.getCardNo());
        
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
        
    }

}
