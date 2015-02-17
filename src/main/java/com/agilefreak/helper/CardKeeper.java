package com.agilefreak.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.agilefreak.dto.EstimateDto;

public class CardKeeper {

    private static Integer counter = 0;
    // current map will be having roomnumber as key and list of SessionEstimate class obects as estimates and session
    private static final ConcurrentMap<Integer, List<SessionEstimate>> cardMap = new ConcurrentHashMap<Integer, List<SessionEstimate>>();
    
    private static final Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();


    private static List<SessionEstimate> createRoom(final Integer roomNo, final HttpSession session) {
        List<SessionEstimate> estimates = cardMap.putIfAbsent(roomNo, new ArrayList() {
            {
                add(new SessionEstimate(session, null, 1));
            }
        });
        return estimates;
    }


    public static List<SessionEstimate> enterRoom(Integer roomNo, HttpSession session) {

        List<SessionEstimate> estimates = cardMap.get(roomNo);
        if (estimates == null) {
            estimates = createRoom(roomNo, session);
        }
        if (estimates != null) {
            if (!checkIfUserSessionAlreadyPresent(session, estimates)){
                estimates.add(new SessionEstimate(session, null, estimates.size() + 1));
            }
                
        }
        return cardMap.get(roomNo);
    }

    public static void setShowCards(Integer roomnumber, boolean val){
        map.put(roomnumber, val);
    }

    private static boolean checkIfUserSessionAlreadyPresent(HttpSession session, List<SessionEstimate> estimates) {
        SessionEstimate sessionEstimate = getSessionEstimateFromListOfSessionEstimates(estimates, session);
        if(sessionEstimate != null)
            return true;
        return false;
    }


    private static List<SessionEstimate> getSessionEstimates(Integer roomNumber) {
        return cardMap.get(roomNumber);
    }


    public static SessionEstimate getSessionEstimateFromListOfSessionEstimates(List<SessionEstimate> estimates, HttpSession session) {
        Iterator<SessionEstimate> iterator = estimates.iterator();
        while (iterator.hasNext()) {
            SessionEstimate sessionEstimate = iterator.next();
            if (sessionEstimate.getSession().equals(session))
                return sessionEstimate;
        }
        return null;
    }

    public static List<EstimateDto> getParticipants(Integer roomNo) {
        List<SessionEstimate> sessionEstimates = cardMap.get(roomNo);
        Iterator<SessionEstimate> iterator = sessionEstimates.iterator();
        List<EstimateDto> estimatesDto = new ArrayList<EstimateDto>();
        while(iterator.hasNext()){
            String estimate = iterator.next().getEstimate();
            estimatesDto.add(new EstimateDto(estimate));
        }
        return estimatesDto;
    }


    public static boolean setUserEstimate(Integer roomNumber, String estimate, HttpSession session) {
        List<SessionEstimate> sessionEstimates = getSessionEstimates(roomNumber);
        if (sessionEstimates != null) {
            SessionEstimate sessionEstimate = getSessionEstimateFromListOfSessionEstimates(sessionEstimates, session);
            if(sessionEstimate != null){ 
                sessionEstimate.setEstimate(estimate);
                return true;
            }
        }
        return false;
    }

    public static class SessionEstimate {
        HttpSession session;
        String estimate;
        Integer cardNo;


        public SessionEstimate(HttpSession session, String estimate, Integer cardNo) {
            this.session = session;
            this.estimate = estimate;
            this.cardNo = cardNo;
        }


        public HttpSession getSession() {
            return session;
        }


        public String getEstimate() {
            return estimate;
        }


        public void setSession(HttpSession session) {
            this.session = session;
        }


        public void setEstimate(String estimate) {
            this.estimate = estimate;
        }


        public Integer getCardNo() {
            return cardNo;
        }


        public void setCardNo(Integer cardNo) {
            this.cardNo = cardNo;
        }

    }

}
