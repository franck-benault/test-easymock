package net.franckbenault.jpa.hibernate;

import net.franckbenault.jpa.hibernate.exception.ConstraintViolatedException;


public interface StudentManager {

	Student createStudent(Student student) throws ConstraintViolatedException;
	
	void removeStudent(String studentName);
	

	
}
