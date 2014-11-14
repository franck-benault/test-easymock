package net.franckbenault.jpa.hibernate.exception;

import java.util.ArrayList;
import java.util.List;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Constraint> constraints = new ArrayList<Constraint>();
	
	public NotFoundException(List<Constraint> constraints) {
		this.constraints.addAll(constraints);
	}
}
