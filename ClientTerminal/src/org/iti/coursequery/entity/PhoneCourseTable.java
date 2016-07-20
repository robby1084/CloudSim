package org.iti.coursequery.entity;

import java.io.Serializable;
import java.util.List;

public class PhoneCourseTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5848381110018406930L;

	private List<PhoneCalendar> calendarList;
	private String role;
	private List<Course> courseList;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public static Course newCourseinstance(String name, String code) {
		Course course = new Course();
		return course;
	}

	public static class Course implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4417820035159571399L;

		public enum Type {
			HEBUT, HEBUT_COLLAGE, FRANCE;
		}

		private String campusCode;// У���
		private String classroom;// ����
		private String college;// ѧԺ����
		private String courseCode;// �γ̺�
		private String courseName;// �γ���
		private String courseSequence;// �����
		private String courseStuCount;// �Ͽ�����
		private String courseWeek; // �Ͽ�����,���磺1-16����
		private String lastNum; // �������
		private String sequence; // �ڼ���
		private String week; // �ܼ�
		private String teacherCode;// ��ʦ����
		private String teacherName; // ��ʦ������ֵ��Ϊ��
		private String teachingBuildCode;// ��ѧ¥��
		private String yearAndTerm;// ѧ��ѧ��
		private String allClass; // �Ͽΰ༶��ֻ�н�ʦ�Ĵ��ֶβ���ֵ��ѧ��Ϊ��
		private Type type;// ��һ�������з���

		public String getSequence() {
			return sequence;
		}

		public void setSequence(String sequence) {
			this.sequence = sequence;
		}

		public String getLastNum() {
			return lastNum;
		}

		public void setLastNum(String lastNum) {
			this.lastNum = lastNum;
		}

		public String getCourseWeek() {
			return courseWeek;
		}

		public void setCourseWeek(String courseWeek) {
			this.courseWeek = courseWeek;
		}

		public String getCourseName() {
			return courseName;
		}

		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}

		public String getTeacherName() {
			return teacherName;
		}

		public void setTeacherName(String teacherName) {
			this.teacherName = teacherName;
		}

		public String getWeek() {
			return week;
		}

		public void setWeek(String week) {
			this.week = week;
		}

		public String getAllClass() {
			return allClass;
		}

		public void setAllClass(String allClass) {
			this.allClass = allClass;
		}

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public String getCampusCode() {
			return campusCode;
		}

		public void setCampusCode(String campusCode) {
			this.campusCode = campusCode;
		}

		public String getTeachingBuildCode() {
			return teachingBuildCode;
		}

		public void setTeachingBuildCode(String teachingBuildCode) {
			this.teachingBuildCode = teachingBuildCode;
		}

		public String getClassroom() {
			return classroom;
		}

		public void setClassroom(String classroom) {
			this.classroom = classroom;
		}

		public String getCollege() {
			return college;
		}

		public void setCollege(String college) {
			this.college = college;
		}

		public String getCourseCode() {
			return courseCode;
		}

		public void setCourseCode(String courseCode) {
			this.courseCode = courseCode;
		}

		public String getCourseSequence() {
			return courseSequence;
		}

		public void setCourseSequence(String courseSequence) {
			this.courseSequence = courseSequence;
		}

		public String getCourseStuCount() {
			return courseStuCount;
		}

		public void setCourseStuCount(String courseStuCount) {
			this.courseStuCount = courseStuCount;
		}

		public String getTeacherCode() {
			return teacherCode;
		}

		public void setTeacherCode(String teacherCode) {
			this.teacherCode = teacherCode;
		}

		public String getYearAndTerm() {
			return yearAndTerm;
		}

		public void setYearAndTerm(String yearAndTerm) {
			this.yearAndTerm = yearAndTerm;
		}


	}

	public List<PhoneCalendar> getCalendarList() {
		return calendarList;
	}

	public void setCalendarList(List<PhoneCalendar> calendarList) {
		this.calendarList = calendarList;
	}

	@Override
	public String toString() {
		return "PhoneCourseTable [calendarList=" + calendarList + ", role="
				+ role + ", courseList=" + courseList + "]";
	}

}
