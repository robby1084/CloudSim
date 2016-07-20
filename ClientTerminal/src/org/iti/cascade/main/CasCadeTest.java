package org.iti.cascade.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.iti.cascade.entity.City;
import org.iti.cascade.entity.CityDetail;
import org.iti.cascade.entity.Province;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;

@Controller("CasCadeTest")
@Scope("prototype")
public class CasCadeTest extends AbstractHttpInterfaceAction{

	/**
	 * 测试级联增删改查_新建实体
	 */
	private static final long serialVersionUID = -8539587014684960037L;

	protected JdbcTemplate jdbcTemplate;
	
	@Resource(name = "hibernateTemplate")
	protected HibernateTemplate hibernateTemplate;
	
	@Override
	public String defaultExecute() throws Throwable {
		
		Long currentTime = Calendar.getInstance().getTime().getTime();
		
		Province province = new Province();
		province.setProvinceCode(String.valueOf(43));
		province.setProvinceName("福建省");
		province.setState(0);
		province.setTimeStamp(currentTime);
		//province.setId(Long.valueOf("1"));
		hibernateTemplate.saveOrUpdate(province);
		
		City city_01 = new City();
		City city_02 = new City();
		City city_03 = new City();
		
		city_01.setCityName("福州市");
		city_01.setProvince(province);
		city_01.setState(0);
		city_01.setTimeStamp(currentTime);
		//city_01.setId(Long.valueOf("1"));
		
		city_02.setCityName("厦门市");
		city_02.setProvince(province);
		city_02.setState(0);
		city_02.setTimeStamp(currentTime);
		//city_02.setId(Long.valueOf("2"));
		
		city_03.setCityName("莆田市");
		city_03.setProvince(province);
		city_03.setState(0);
		city_03.setTimeStamp(currentTime);
		//city_03.setId(Long.valueOf("3"));
		
		hibernateTemplate.saveOrUpdate(city_01);
		hibernateTemplate.saveOrUpdate(city_02);
		hibernateTemplate.saveOrUpdate(city_03);
		//List<City> citise = new ArrayList<City>();
		//citise.add(city_01);
		//citise.add(city_02);
		//citise.add(city_03);
		//province.setCities(citise);
		//hibernateTemplate.saveOrUpdate(province);
		
		CityDetail cityDetail_01 = new CityDetail();
		CityDetail cityDetail_02 = new CityDetail();
		CityDetail cityDetail_03 = new CityDetail();
		
		cityDetail_01.setCity(city_01);
		cityDetail_01.setDivisionCode("0591");
		cityDetail_01.setIsSpecial(false);
		cityDetail_01.setState(0);
		cityDetail_01.setTimeStamp(currentTime);
		//cityDetail_01.setId(Long.valueOf("1"));
		
		cityDetail_02.setCity(city_02);
		cityDetail_02.setDivisionCode("0592");
		cityDetail_02.setIsSpecial(true);
		cityDetail_02.setState(0);
		cityDetail_02.setTimeStamp(currentTime);
		//cityDetail_02.setId(Long.valueOf("2"));
		
		cityDetail_03.setCity(city_03);
		cityDetail_03.setDivisionCode("0594");
		cityDetail_03.setIsSpecial(false);
		cityDetail_03.setState(0);
		cityDetail_03.setTimeStamp(currentTime);
		//cityDetail_03.setId(Long.valueOf("3"));
		
		hibernateTemplate.saveOrUpdate(cityDetail_01);
		hibernateTemplate.saveOrUpdate(cityDetail_02);
		hibernateTemplate.saveOrUpdate(cityDetail_03);
		city_01.setCityDetail(cityDetail_01);
		city_02.setCityDetail(cityDetail_02);
		city_03.setCityDetail(cityDetail_03);
		hibernateTemplate.saveOrUpdate(city_01);
		hibernateTemplate.saveOrUpdate(city_02);
		hibernateTemplate.saveOrUpdate(city_03);
		
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
