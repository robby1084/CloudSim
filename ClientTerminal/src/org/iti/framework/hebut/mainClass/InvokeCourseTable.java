package org.iti.framework.hebut.mainClass;

import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.coursequery.entity.PhoneCourseTable.Course;
import org.iti.coursequery.service.ICourseTableService;

public class InvokeCourseTable {

public static void main(String[] args) {
		
		Course name = (Course) loadCTService().LoadCourseWeekView("03029", 3);
		System.out.println(name);
	}
	
	public static ICourseTableService loadCTService(){
		return (ICourseTableService) BeanFactory.getBean("CourseTableRMIServiceClient");
	}
}
