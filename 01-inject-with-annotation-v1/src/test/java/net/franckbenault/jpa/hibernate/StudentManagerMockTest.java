package net.franckbenault.jpa.hibernate;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

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
	private EntityManager emMock = EasyMock.createNiceMock(EntityManager.class);
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		studentManager = new StudentManagerImpl();
	}
		
	@Before
	public void setUp() throws Exception {
		EasyMock.reset(emMock);
	}



	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreateStudent() throws ClassNotFoundException, SQLException {
		
		EntityTransaction entityTransactionMock = EasyMock.createNiceMock(EntityTransaction.class);
		entityTransactionMock.begin();
		entityTransactionMock.commit();
		EasyMock.expect(emMock.getTransaction()).andReturn(entityTransactionMock).times(2);
		
		EasyMock.replay(emMock);
		EasyMock.replay(entityTransactionMock);
		
		Student s = studentManager.createStudent(new Student());
		
		assertNotNull(s);
		
	}

	@Test
	public void testRemoveStudent() throws ClassNotFoundException, SQLException {
		
		EntityTransaction entityTransactionMock = EasyMock.createNiceMock(EntityTransaction.class);
		entityTransactionMock.begin();
		entityTransactionMock.commit();
		EasyMock.expect(emMock.getTransaction()).andReturn(entityTransactionMock).times(2);
		
		EasyMock.replay(emMock);
		EasyMock.replay(entityTransactionMock);

		
		Student student = new Student();
		
		studentManager.removeStudent(student);



	}
	

	@Test
	public void testfindAllStudents() throws ClassNotFoundException, SQLException {

		@SuppressWarnings("unchecked")
		TypedQuery<Student> queryMock = EasyMock.createNiceMock(TypedQuery.class);		
		EasyMock.expect(queryMock.getResultList()).andReturn(new ArrayList<Student>());
		
		EasyMock.expect(emMock.createQuery(EasyMock.isA(String.class),EasyMock.isA(java.lang.Class.class))).andReturn(queryMock);
		
		EasyMock.replay(emMock);
		EasyMock.replay(queryMock);
		
		List<Student> students = studentManager.findAllStudents();

		assertNotNull(students);

	}

}
