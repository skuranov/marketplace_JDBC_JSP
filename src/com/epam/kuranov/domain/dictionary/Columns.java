package com.epam.kuranov.domain.dictionary;

public class Columns {

	public static enum Bid{
		BID_ID,
		BIDDER_ID,
		ITEM_ID_BID,
		BID_VALUE;
	}
	
	public static enum Users{
		USER_ID,
		FULL_NAME,
		BILLING_ADRESS,
		LOGIN,
		PASSWORD;
	}
	
	public static enum Items{
		ITEM_ID,
		SELLER_ID,
		TITLE,
		DESCRIPTION,
		START_PRICE,
		TIME_LEFT,
		START_BIDDING_DATE,
		BUY_IT_NOW,
		BID_INCREMENT;
	};
    
}
