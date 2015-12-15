package com.epam.kuranov.domain.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.kuranov.domain.entities.impl.ComplexEntity;

public class DataSetUtils {

	private static ArrayList<ComplexEntity> toArrayList(HashMap<Integer,ComplexEntity> map){
		ArrayList<ComplexEntity> outList = new ArrayList<ComplexEntity>();
		Iterator<Entry<Integer, ComplexEntity>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer,ComplexEntity> pair = (Entry<Integer, ComplexEntity>)it.next();
			outList.add(pair.getValue());
		}
		return outList;
	}
	
	public static ArrayList<ComplexEntity> exceptOldBids(ArrayList<ComplexEntity> complexEntityList){
		HashMap<Integer,ComplexEntity> outMap = new HashMap<Integer, ComplexEntity>();
		for(ComplexEntity entity : complexEntityList){
			Integer tempItemId = entity.getItemId();
			Double tempCurrentBid = entity.getBidValue();
			if(!outMap.containsKey(tempItemId)){
				outMap.put(tempItemId, entity);
			}
			else{
				if(outMap.get(tempItemId).getBidValue()<tempCurrentBid){
					outMap.put(tempItemId, entity);
				}
			}
		}
		return toArrayList(outMap);
	}
	
	
	public static ArrayList<ComplexEntity> exceptUnactualItems(ArrayList<ComplexEntity> complexEntityList){
		ArrayList<ComplexEntity> outList = new ArrayList<>();
		for(ComplexEntity entity : complexEntityList){
			Date stopDate = entity.getStopDate();

			if (stopDate.getTime() < System.currentTimeMillis()){
				continue;
			}
			
			if(!(entity.getBuyItNow() == null) &&
					(entity.getBidValue() == entity.getStartPrice())){
				continue;
			}
			outList.add(entity);
		}
		return outList;	
	}
}
