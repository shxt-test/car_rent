package com.shxt.base.model;

import java.math.BigInteger;

public class CharDatas {
	
	private String lable;
	private BigInteger value;
	private String link="<a href='javascript:void(0)' onclick='toSub()'></a>";
	
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public BigInteger getValue() {
		return value;
	}
	public void setValue(BigInteger value) {
		this.value = value;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

}
