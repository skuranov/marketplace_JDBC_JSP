package com.epam.kuranov.domain.searchSequence;


public class SearchByDescription {
	
	private String[] descr;
	
	public SearchByDescription (String Desctiption){
		descr = Desctiption.split(" ");
	}
		
    public String getSequence(int mode){
    	StringBuilder builder = new StringBuilder();
    	for(String subDescr : descr){
    		builder.append(" AND LOWER (Description) LIKE LOWER('%");
    		builder.append(subDescr);
    		builder.append("%')");
    	}
        return builder.toString();
    }
}
	
