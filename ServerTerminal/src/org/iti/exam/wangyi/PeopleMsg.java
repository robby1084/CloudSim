package org.iti.exam.wangyi;

public class PeopleMsg implements Cloneable{
	
	private String count;
	
	private String sex;
	
	private String phone;
	
	private String province;

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Override
	protected PeopleMsg clone() throws CloneNotSupportedException{
		return (PeopleMsg) super.clone();
	} 
	
	@Override
	public String toString(){
		return this.count+","+this.province;
	}
}
