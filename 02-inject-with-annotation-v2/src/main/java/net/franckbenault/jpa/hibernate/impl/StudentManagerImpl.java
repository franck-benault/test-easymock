package net.franckbenault.jpa.hibernate.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.franckbenault.jpa.hibernate.Student;
import net.franckbenault.jpa.hibernate.StudentManager;
import net.franckbenault.jpa.hibernate.StudentQuery;


public class StudentManagerImpl implements StudentManager {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private StudentQuery studentQuery;
	
	public StudentManagerImpl() {

	}
	
	public StudentManagerImpl(String persistenceUnitName) {
	    emf = Persistence.createEntityManagerFactory(persistenceUnitName);
	    em = emf.createEntityManager();		
	}

	@Override
	public Student createStudent(Student student ) {
		
		//name not null ?
		student.check();
		
	    Student student2 = studentQuery.findByName(student.getName());
		
	    em.getTransaction().begin();
	    em.persist(student);
	    em.getTransaction().commit();
	    return student;
	}

	@Override
	public void removeStudent(String studentName) {
	    Student student = studentQuery.findByName(studentName);
	    
	    em.getTransaction().begin();
	    em.remove(student);
	    em.getTransaction().commit();
		
	}

}
