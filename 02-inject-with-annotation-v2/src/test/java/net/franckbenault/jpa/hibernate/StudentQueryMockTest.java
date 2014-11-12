package net.franckbenault.jpa.hibernate;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import net.franckbenault.jpa.hibernate.impl.StudentManagerImpl;
import net.franckbenault.jpa.hibernate.impl.StudentQueryImpl;

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
public class StudentQueryMockTest  {
	

	@TestSubject
	private static StudentQuery studentQuery;
	

	@Mock(type = MockType.NICE)
	private EntityManager emMock = EasyMock.createNiceMock(EntityManager.class);
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		studentQuery = new StudentQueryImpl();
	}
		
	@Before
	public void setUp() throws Exception {
		EasyMock.reset(emMock);
	}



	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testByName() {
		
		TypedQuery<Student> queryMock = EasyMock.createNiceMock(TypedQuery.class);		
		EasyMock.expect(queryMock.getSingleResult()).andReturn(new Student());
		EasyMock.expect(emMock.createQuery(EasyMock.isA(String.class),EasyMock.isA(java.lang.Class.class))).andReturn(queryMock);

		EasyMock.replay(emMock);
		EasyMock.replay(queryMock);
		
		Student s = studentQuery.findByName("A name");
		
		assertNotNull(s);
		
	}


	@Test
	public void testByName_ResultNull() {
		
		TypedQuery<Student> queryMock = EasyMock.createNiceMock(TypedQuery.class);		
		EasyMock.expect(queryMock.getSingleResult()).andThrow(new NoResultException());
		EasyMock.expect(emMock.createQuery(EasyMock.isA(String.class),EasyMock.isA(java.lang.Class.class))).andReturn(queryMock);

		EasyMock.replay(emMock);
		EasyMock.replay(queryMock);
		
		Student s = studentQuery.findByName("Is Null name");
		
		assertNull(s);
		
	}	

	@Test
	public void testfindAllStudents()  {

		@SuppressWarnings("unchecked")
		TypedQuery<Student> queryMock = EasyMock.createNiceMock(TypedQuery.class);
		
		List<Student> res = new ArrayList<Student>();
		res.add(new Student());
		
		EasyMock.expect(queryMock.getResultList()).andReturn(res);
		
		EasyMock.expect(emMock.createQuery(EasyMock.isA(String.class),EasyMock.isA(java.lang.Class.class))).andReturn(queryMock);
		
		EasyMock.replay(emMock);
		EasyMock.replay(queryMock);
		
		List<Student> students = studentQuery.findAllStudents();

		assertNotNull(students);
		assertFalse(students.isEmpty());

	}

	
	@Test
	public void testfindAllStudents_isEmpty()  {

		@SuppressWarnings("unchecked")
		TypedQuery<Student> queryMock = EasyMock.createNiceMock(TypedQuery.class);		
		EasyMock.expect(queryMock.getResultList()).andReturn(new ArrayList<Student>());
		
		EasyMock.expect(emMock.createQuery(EasyMock.isA(String.class),EasyMock.isA(java.lang.Class.class))).andReturn(queryMock);
		
		EasyMock.replay(emMock);
		EasyMock.replay(queryMock);
		
		List<Student> students = studentQuery.findAllStudents();

		assertNotNull(students);
		assertTrue(students.isEmpty());

	}
}
