package net.franckbenault.jpa.hibernate.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
		
		TypedQuery<Student> query = em.createQuery(
	            "select s from Student s where s.name=:name", Student.class);
		query.setParameter("name", name);
		
		try  {
			return query.getSingleResult();
		
		} catch (NoResultException e ) {
			return null;
		}

	}
	
	@Override
	public List<Student> findAllStudents() {
	    return em.createQuery(
	            "select s from Student s", Student.class).getResultList();
	}

}
