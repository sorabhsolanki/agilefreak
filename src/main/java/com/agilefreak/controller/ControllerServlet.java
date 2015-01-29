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

@WebServlet(name = "roomServlet", urlPatterns = { "/room" })
public class ControllerServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(ControllerServlet.class);


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("Hello servlet 3.0");
        
        
        String roomNo = request.getParameter("roomnumber");
        List<Integer> lists = CardKeeper.enterRoom(Integer.parseInt(roomNo));
        request.setAttribute("myList", lists);
        request.setAttribute("roomnumber", roomNo);
        
        request.setAttribute("result", lists.size());
        
        
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
        
    }

}
