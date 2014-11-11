package net.franckbenault.jpa.hibernate.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.franckbenault.jpa.hibernate.Student;
import net.franckbenault.jpa.hibernate.StudentManager;
import net.franckbenault.jpa.hibernate.StudentQuery;


public class StudentQueryImpl implements StudentQuery {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public StudentQueryImpl() {

	}
	
	public StudentQueryImpl(String persistenceUnitName) {
	    emf = Persistence.createEntityManagerFactory(persistenceUnitName);
	    em = emf.createEntityManager();		
	}

	@Override
	public Student findByName(String name) {
	    return null;
	}
	
	@Override
	public List<Student> findAllStudents() {
	    return em.createQuery(
	            "select s from Student s", Student.class).getResultList();
	}

}
