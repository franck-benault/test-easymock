package net.franckbenault.jpa.hibernate;


public interface StudentManager {

	Student createStudent(Student student);
	
	void removeStudent(String studentName);
	

	
}
