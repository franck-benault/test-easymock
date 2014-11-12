package net.franckbenault.jpa.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

import net.franckbenault.jpa.hibernate.exception.Constraint;

@Entity
public class Student {
  @Id
  private long id = System.currentTimeMillis();

  //the annotation basic is not needed for Hibernate and EclipseLink
  //@Basic
  private String name;

  //hibernate could accept @Basic here
  //@Basic
  @Temporal(javax.persistence.TemporalType.TIMESTAMP)  
  private Date dateOfBirth = new Date();

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  
  public List<Constraint> check() {
	  List<Constraint> constraints = new ArrayList<Constraint>();
	  
	  if(this.name==null)
		  constraints.add(new Constraint("name"));
	 
	  return constraints;
  }
  
  public Student(String name) {
	  this.name = name;
  }
  
  public Student() {
  }
  
}

