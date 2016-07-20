package org.iti.cascade.action;

import java.util.ArrayList;
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

@Controller("DeleteCasCadeAction")
@Scope("prototype")
public class DeleteCasCadeAction extends AbstractHttpInterfaceAction {

	/**
	 * 删除级联操作
	 */
	private static final long serialVersionUID = -8481601611675058916L;

	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;
	
	@Override
	public String defaultExecute() throws Throwable {

		/**
		 * 删除省，获取市
		 */
		Province fujian = loadProvince((long)1);
		this.hibernateTemplate.delete(fujian);
		
		Long[] ids = {(long) 0,(long) 1,(long) 2};
		display(loadCities(ids));
		System.out.println("-------------------");
		/**
		 * 删除市，获取省
		 */
		City fuzhou = loadCities((long)1).get(0);
		this.hibernateTemplate.delete(fuzhou);
		Province zhejiang = loadProvince((long)3);
		System.out.println(zhejiang.getProvinceName());
		display(zhejiang.getCities());
		return super.defaultExecute();
	}

	public List<City> loadCities(Long... ids){
		
		List<City> cities = new ArrayList<City>();
		for(Long id : ids){
			City city = this.hibernateTemplate.get(City.class, id);
			cities.add(city);
		}
		
		return cities;
	}
	
	public Province loadProvince(Long id) {

		Session session = this.hibernateTemplate.getSessionFactory()
				.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Province t_province = (Province) session.get(Province.class, id);
		Hibernate.initialize(t_province.getCities());
		tx.commit();
		return t_province;
	}

	public void display(List<City> cities) {
		if (cities.size() >= 1) {
			for (City c : cities) {
				if (c != null) {
					System.out.println(c.getCityName() + c.getProvince().getProvinceName());
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
