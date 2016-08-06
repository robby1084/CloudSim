package org.iti.redis.entity;

import java.io.Serializable;

public class SMSEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5799408620330811825L;
	
	private Integer id;
	private String phone;
	private String content;
	private Integer operator;

	public SMSEntity(){}
	
	public SMSEntity(Integer id,String phone,String content,Integer operator){
		this.id = id;
		this.phone = phone;
		this.content = content;
		this.operator = operator;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	@Override
	public String toString(){
		return this.id+this.content+this.phone+this.operator;
	}
}
