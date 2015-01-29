package com.agilefreak.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CardKeeper {

    private static Integer counter = 0;
	private static final ConcurrentMap<Integer, List<Integer>> cardMap = new ConcurrentHashMap<Integer, List<Integer>>();

	private static List<Integer> createRoom(Integer roomNo) {
	    List<Integer> cards = cardMap.putIfAbsent(roomNo, new ArrayList<Integer>());
	    if(cards == null){
	        return cardMap.get(roomNo);
	    }
	    return cards;
	}
	
    public static List<Integer> enterRoom(Integer roomNo) {
		
		List<Integer> cards = cardMap.get(roomNo);
		if(cards == null){
		   cards = createRoom(roomNo);
		}
		cards.add(++ counter);
		return cards;
	}
    
    public static Integer getParticipants(Integer roomNo){
        return cardMap.get(roomNo).size();
    }
	
}
