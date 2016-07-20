package org.iti.cascade.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.iti.cascade.entity.City;
import org.iti.cascade.entity.Province;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;

@Controller("UpdateCasCadeAction")
@Scope("prototype")
public class UpdateCasCadeAction extends AbstractHttpInterfaceAction{

	/**
	 * 更新级联测试
	 */
	private static final long serialVersionUID = 3740460815314930478L;

	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;
	
	@Override
	public String defaultExecute() throws Throwable {

		
		Province t1_province = loadProvince();

		t1_province.setProvinceName("chenjunda");;
		this.hibernateTemplate.saveOrUpdate(t1_province);
		
		System.out.println("---------------------------");
		Province t2_province = loadProvince();
		display(t2_province.getCities());
		
		City city = hibernateTemplate.get(City.class, Long.valueOf("1"));
		Province new_Province = new Province(); 
		new_Province.setProvinceCode("44");
		new_Province.setProvinceName("浙江省");
		new_Province.setState(0);
		new_Province.setTimeStamp(new Date().getTime());
		city.setProvince(new_Province);
		this.hibernateTemplate.saveOrUpdate(city);
		
		System.out.println("---------------------------");
		Province t3_province = loadProvince();
		display(t3_province.getCities());
		
		return super.defaultExecute();
	}
	
	public Province loadProvince(){
		
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Province t_province = (Province) session.get(Province.class,(long)1);
		Hibernate.initialize(t_province.getCities());
		tx.commit();
		return t_province;
	}
	
	public void display(List<City> cities){
		if(cities.size()>=1){
			for(City c : cities){
				if(c != null){
					System.out.println("根据Province获取的city"+c.getCityName());
				}
			}
		}
	}
	
	@Override
	public String getResponState() {
		return responState;
	}

	@Override
	public String getResponResult() {
		return responResult;
	}

}
