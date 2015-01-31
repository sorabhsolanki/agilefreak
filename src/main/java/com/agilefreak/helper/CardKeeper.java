package com.agilefreak.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;

public class CardKeeper {

    private static Integer counter = 0;
    // current map will be having roomnumber as key and list of SessionEstimate class obects as estimates and session
	private static final ConcurrentMap<Integer, List<SessionEstimate>> cardMap = new ConcurrentHashMap<Integer, List<SessionEstimate>>();

	private static List<SessionEstimate> createRoom(final Integer roomNo, final HttpSession session) {
	    List<SessionEstimate> estimates = cardMap.putIfAbsent(roomNo, new ArrayList(){{add(new SessionEstimate(session, null));}} );
	    return estimates;
	}
	
    public static List<SessionEstimate> enterRoom(Integer roomNo, HttpSession session) {
		
        List<SessionEstimate> estimates = cardMap.get(roomNo);
		if(estimates == null){
		   estimates = createRoom(roomNo, session);
		}
		if(estimates != null){
		    if(!checkIfUserSessionAlreadyPresent(session, estimates))
		       estimates.add(new SessionEstimate(session, null));
		}
		return cardMap.get(roomNo);
	}
    
    private static boolean checkIfUserSessionAlreadyPresent(HttpSession session, List<SessionEstimate> estimates) {
        
        Iterator<SessionEstimate> iterator = estimates.iterator();
        
        while(iterator.hasNext()){
            SessionEstimate sessionEstimate = iterator.next();
            if(sessionEstimate.getSession().equals(session))
                return true;
        }
        return false;
    }

    public static Integer getParticipants(Integer roomNo){
        return cardMap.get(roomNo).size();
    }
    
    public static boolean setEstimates(Integer roomNumber, String estimate){
        return true;
    }
    
    public static class SessionEstimate{
        HttpSession session;
        String estimate;
        
        public SessionEstimate(HttpSession session, String estimate){
            this.session = session;
            this.estimate = estimate;
        }

        public HttpSession getSession() {
            return session;
        }

        public String getEstimate() {
            return estimate;
        }
        
        
    }
	
}
