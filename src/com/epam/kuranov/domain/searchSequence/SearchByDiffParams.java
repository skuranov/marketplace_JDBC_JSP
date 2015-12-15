package com.epam.kuranov.domain.searchSequence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SearchByDiffParams {
    private HashMap<String,Object> searchMap;
    private ValueGreater bidValueGreater;
    private ValueLess bidValueLess;
    private String title;
    private String description;
    private Integer userId;
    private String buyItNow;


    private class ValueLess implements SQLConvertable {
        Number value;
        public ValueLess(Number value) {
            this.value = value;
        }
        @Override
        public String getSQLSubString() {
            return " <= " + value;
        }
    }

    private class ValueGreater implements SQLConvertable {
        Number value;
        public ValueGreater(Number value) {
            this.value = value;
        }
        @Override
        public String getSQLSubString() {
            return " >= " + value;
        }
    }


    public SearchByDiffParams() {
        searchMap = new HashMap<>();
    }

    public String getSequence(){
        if (!(bidValueGreater==null)){
            searchMap.put("Bid_Value", bidValueGreater);
        }
        if (!(bidValueLess==null)){
            searchMap.put("Bid_Value", bidValueLess);
        }
        if (!(title==null)){
            searchMap.put("Title", title);
        }
        if (!(description==null)){
            searchMap.put("Description", description);
        }
        if (!(buyItNow==null)){
            searchMap.put("Buy_It_Now",buyItNow);
        }
        if (!(userId==null)){
            searchMap.put("Seller_Id",userId);
        }

        StringBuilder searchSequence = new StringBuilder();

        if (searchMap.size()>0) {
            for (Iterator<Map.Entry<String, Object>> it = searchMap.entrySet().iterator();
                 it.hasNext(); ) {
                Map.Entry<String, Object> entry = it.next();
                
                searchSequence.append(" AND ");
                
                if (entry.getValue()instanceof String)
                {
                    if (entry.getValue().equals("")){
                        searchSequence.append(entry.getKey());
                        searchSequence.append(" IS NULL ");
                    }
                    else{
                        searchSequence.append(" LOWER(");
                        searchSequence.append(entry.getKey());
                        searchSequence.append(") LIKE '%");
                        searchSequence.append(entry.getValue().toString().toLowerCase());
                        searchSequence.append("%'");
                    }
                }
                else if (entry.getValue() instanceof SQLConvertable){
                    searchSequence.append(entry.getKey());
                    searchSequence.append(((SQLConvertable)entry.getValue()).getSQLSubString());
                }
                else{
                    searchSequence.append(entry.getKey());
                    searchSequence.append(" = ");
                    searchSequence.append(entry.getValue());
                }
            }
        }
        return searchSequence.toString();
    }


    public void setBidValueGreater(Number bidValueGreater) {
        this.bidValueGreater = new ValueGreater(bidValueGreater);
    }

    public void setBidValueLess(Number bidValueLess) {
        this.bidValueLess = new ValueLess(bidValueLess);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBuyItNow(String buyItNow) {
        this.buyItNow = buyItNow;
    }

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
}
