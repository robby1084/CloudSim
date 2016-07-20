package org.iti.framework.hebut.testAction;

import java.util.Map;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.common.util.JsonUtil;
import org.iti.coursequery.entity.PhoneCourseTable.Course;
import org.iti.coursequery.service.ICourseTableService;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("CourseTableRMITestAction")
@Scope("prototype")
public class CourseTableRMITestAction extends AbstractHttpInterfaceAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3131338742845590760L;

	@Override
	public String defaultExecute() throws Throwable {

		Map<Integer, Course[]> map = loadCTService().LoadCourseWeekView("03029", 3);
		
		this.responResult = JsonUtil.toJson(map);
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

	public ICourseTableService loadCTService(){
		return (ICourseTableService) BeanFactory.getBean("CourseTableRMIServiceClient");
	}
}
