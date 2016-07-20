package org.iti.cascade.action;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.iti.cascade.entity.City;
import org.iti.cascade.entity.CityDetail;
import org.iti.cascade.entity.Province;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;

@Controller("SearchCasCadeAction")
@Scope("prototype")
public class SearchCasCadeAction extends AbstractHttpInterfaceAction{

	/**
	 * 测试更新
	 */
	private static final long serialVersionUID = 1537070189319133004L;

	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;
	
	@Override
	public String defaultExecute() throws Throwable {

		//TODO 无法删除和更新
		//Province province = hibernateTemplate.get(Province.class, 1);
		//province.setId(Long.valueOf("2"));
		//hibernateTemplate.delete(province);
		
		City city = hibernateTemplate.get(City.class, Long.valueOf("1"));
		if(city.getProvince() != null){
			System.out.println("根据City获取的Province"+city.getProvince().getProvinceName());
		}
		if(city.getCityDetail() != null){
			System.out.println("根据City获取的CityDetail"+city.getCityDetail().getDivisionCode());
		}
		
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Province t_province = (Province) session.get(Province.class,(long)1);
		Hibernate.initialize(t_province.getCities());
		tx.commit();
		List<City> cities = t_province.getCities();
		if(cities.size()>=1){
			for(City c : cities){
				if(c != null){
					System.out.println("根据Province获取的city"+c.getCityName());
					if(c.getCityDetail() != null){
						System.out.println("根据Province获取的cityDetail"+c.getCityDetail().getDivisionCode());
					}
				}
			}
		}
		
		CityDetail cityDetail = hibernateTemplate.get(CityDetail.class, Long.valueOf("1"));
		if(cityDetail.getCity() != null){
			System.out.println("根据cityDetail获取的city"+cityDetail.getCity().getCityName());
		}
		if(cityDetail.getCity().getProvince() != null){
			System.out.println("根据cityDetail获取的province"+cityDetail.getCity().getProvince().getProvinceName());
		}
		
		
		return AbstractHttpInterfaceAction.SUCCESS;
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
