package com.cigouyun.ebiz.edi.zhuozhi.beans;

/*
 * data bean for 
 * 3.8	电商交易订单接口
 */
public class Good {

		private String goodId;
  	    public static final int GOODID_SIZE=20;

		private int amount;
		public static final int AMOUNT_MAX=1000;
		public static final int AMOUNT_MIN=1;
		
		private double price;
		public static final double PRICE_MAX=10000000.00;
		public static final double PRICE_MIN=1.00;
		
		public String getGoodId() {
			return goodId;
		}
		public void setGoodId(String goodId) {
			this.goodId = goodId;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		
}
