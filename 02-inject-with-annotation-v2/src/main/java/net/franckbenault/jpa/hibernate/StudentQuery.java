package net.franckbenault.jpa.hibernate;

import java.util.List;

public interface StudentQuery {
	
	Student findByName(String Name);
	
	List<Student> findAllStudents();
	
}
