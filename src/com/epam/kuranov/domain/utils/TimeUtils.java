package com.epam.kuranov.domain.utils;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtils {

//	public static void main(String[] args){
//		System.out.println(getStopDate(new Timestamp(System.currentTimeMillis()),12.0));
//	}
	
	public static Date getSummDate(Timestamp startBiddingDate,double timeLeft) {
		long temp = startBiddingDate.getTime();
		temp = temp + (long)(timeLeft*86400000);
		return new Date(temp);
	}
	

}
