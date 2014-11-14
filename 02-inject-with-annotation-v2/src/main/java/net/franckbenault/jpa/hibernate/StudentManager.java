package net.franckbenault.jpa.hibernate;

import net.franckbenault.jpa.hibernate.exception.ConstraintViolatedException;
import net.franckbenault.jpa.hibernate.exception.NotFoundException;


public interface StudentManager {

	Student createStudent(Student student) throws ConstraintViolatedException;
	
	void removeStudent(String studentName) throws NotFoundException;
	

	
}
