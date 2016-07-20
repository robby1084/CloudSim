package org.iti.coursequery.service;

import java.io.Serializable;
import java.util.Map;

import org.iti.coursequery.entity.PhoneCourseTable.Course;

public interface ICourseTableService extends Serializable {

	public Course LoadSpecificCourse(String userCode, Integer weekNum,
			Integer week, Integer sequence);

	public Map<Integer, Course[]> LoadCourseWeekView(String userCode,
			Integer weekNum);
}
