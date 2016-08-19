package org.iti.exam.wangyi;

public class Key {

	private String province;

	public Key(String province){
		this.province = province;
	}
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Override
	public int hashCode(){
		/*if (this.province.equals("河北省")) {
			return 39;
		}
		if(this.province.equals("天津市")){
			return 42;
		}
		return 43;*/
		int hash = this.province.hashCode();
		return hash;
	}
}
