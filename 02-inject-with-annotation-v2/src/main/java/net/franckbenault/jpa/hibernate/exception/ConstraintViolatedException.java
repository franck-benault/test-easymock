package net.franckbenault.jpa.hibernate.exception;

import java.util.ArrayList;
import java.util.List;

public class ConstraintViolatedException extends Exception {

	private List<Constraint> constraints = new ArrayList<Constraint>();
	
	public ConstraintViolatedException(List<Constraint> constraints) {
		this.constraints.addAll(constraints);
	}
}
