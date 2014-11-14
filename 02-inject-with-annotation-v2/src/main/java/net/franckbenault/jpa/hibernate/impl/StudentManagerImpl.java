package net.franckbenault.jpa.hibernate.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.franckbenault.jpa.hibernate.Student;
import net.franckbenault.jpa.hibernate.StudentManager;
import net.franckbenault.jpa.hibernate.StudentQuery;
import net.franckbenault.jpa.hibernate.exception.Constraint;
import net.franckbenault.jpa.hibernate.exception.ConstraintViolatedException;
import net.franckbenault.jpa.hibernate.exception.NotFoundException;


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
	public Student createStudent(Student student ) throws ConstraintViolatedException {
		
		//name not null ?
		List<Constraint> constraints = student.check();
		
		if(!constraints.isEmpty()) {
			throw new ConstraintViolatedException(constraints);
		}
			
		
	    Student student2 = studentQuery.findByName(student.getName());
		if(student2!=null) {
			List<Constraint> constraints2 = new ArrayList<Constraint>();
			Constraint constraint = new Constraint("duplicate Name");
			constraints2.add(constraint);
			throw new ConstraintViolatedException(constraints2);
		}
	    
	    em.getTransaction().begin();
	    em.persist(student);
	    em.getTransaction().commit();
	    return student;
	}

	@Override
	public void removeStudent(String studentName) throws NotFoundException {
	    Student student = studentQuery.findByName(studentName);
	    
	    if(student==null)
	    	throw new NotFoundException("not found "+studentName);
	    
	    em.getTransaction().begin();
	    em.remove(student);
	    em.getTransaction().commit();
		
	}

}
