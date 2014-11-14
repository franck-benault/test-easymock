package net.franckbenault.jpa.hibernate;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import net.franckbenault.jpa.hibernate.exception.ConstraintViolatedException;
import net.franckbenault.jpa.hibernate.exception.NotFoundException;
import net.franckbenault.jpa.hibernate.impl.StudentManagerImpl;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EasyMockRunner.class)
public class StudentManagerMockTest  {
	

	@TestSubject
	private static StudentManager studentManager;
	

	@Mock(type = MockType.NICE)
	private StudentQuery studentQueryMock = EasyMock.createNiceMock(StudentQuery.class);
	
	@Mock(type = MockType.NICE)
	private EntityManager emMock = EasyMock.createNiceMock(EntityManager.class);
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		studentManager = new StudentManagerImpl();
	}
		
	@Before
	public void setUp() throws Exception {
		EasyMock.reset(studentQueryMock);
		EasyMock.reset(emMock);
	}



	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreateStudent() throws ConstraintViolatedException {
		
		EntityTransaction entityTransactionMock = EasyMock.createNiceMock(EntityTransaction.class);
		entityTransactionMock.begin();
		entityTransactionMock.commit();
		EasyMock.expect(emMock.getTransaction()).andReturn(entityTransactionMock).times(2);
		
		EasyMock.expect(studentQueryMock.findByName("newName")).andReturn(null);
		
		EasyMock.replay(emMock);
		EasyMock.replay(studentQueryMock);
		EasyMock.replay(entityTransactionMock);
		
		Student s = new Student("newName");
		
		s = studentManager.createStudent(s);
		
		assertNotNull(s);
		
	}
	
	@Test(expected=ConstraintViolatedException.class)
	public void testCreateStudent_NoName() throws ConstraintViolatedException {
		
		EntityTransaction entityTransactionMock = EasyMock.createNiceMock(EntityTransaction.class);
		entityTransactionMock.begin();
		entityTransactionMock.commit();
		EasyMock.expect(emMock.getTransaction()).andReturn(entityTransactionMock).times(2);
		
		EasyMock.replay(emMock);
		EasyMock.replay(entityTransactionMock);
		
		Student s = new Student();
		
		s = studentManager.createStudent(s);
				
	}
	
	@Test(expected=ConstraintViolatedException.class)
	public void testCreateStudent_DuplicateName() throws ConstraintViolatedException {
		
		Student s = new Student();
		s.setName("duplicateName");
		
		EntityTransaction entityTransactionMock = EasyMock.createNiceMock(EntityTransaction.class);
		entityTransactionMock.begin();
		entityTransactionMock.commit();
		EasyMock.expect(emMock.getTransaction()).andReturn(entityTransactionMock).times(2);
		
		EasyMock.expect(studentQueryMock.findByName(s.getName())).andReturn(s);
		
		EasyMock.replay(emMock);
		EasyMock.replay(studentQueryMock);
		EasyMock.replay(entityTransactionMock);

		
		
		
		s = studentManager.createStudent(s);
				
	}

	@Test
	public void testRemoveStudent() throws NotFoundException {

		Student student = new Student();
		student.setName("exists");
		
		EntityTransaction entityTransactionMock = EasyMock.createNiceMock(EntityTransaction.class);
		entityTransactionMock.begin();
		entityTransactionMock.commit();
		EasyMock.expect(emMock.getTransaction()).andReturn(entityTransactionMock).times(2);
		
		EasyMock.expect(studentQueryMock.findByName(student.getName())).andReturn(student);
		
		EasyMock.replay(emMock);
		EasyMock.replay(studentQueryMock);
		EasyMock.replay(entityTransactionMock);
		

		
		studentManager.removeStudent(student.getName());
	}
	
	@Test(expected=NotFoundException.class)
	public void testRemoveStudent_NotFound() throws NotFoundException {

		Student student = new Student();
		student.setName("exists");
		
		EntityTransaction entityTransactionMock = EasyMock.createNiceMock(EntityTransaction.class);
		entityTransactionMock.begin();
		entityTransactionMock.commit();
		EasyMock.expect(emMock.getTransaction()).andReturn(entityTransactionMock).times(2);
		
		EasyMock.expect(studentQueryMock.findByName(student.getName())).andReturn(null);
		
		EasyMock.replay(emMock);
		EasyMock.replay(studentQueryMock);
		EasyMock.replay(entityTransactionMock);
		

		
		studentManager.removeStudent(student.getName());
	}



}
