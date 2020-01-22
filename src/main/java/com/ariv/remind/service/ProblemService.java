/**
 * 
 */
package com.ariv.remind.service;

import java.util.List;

import com.ariv.remind.model.Problem;

/**
 * @author al
 *
 */
public interface ProblemService {

	public Boolean addToReminder(Problem problem);

	public List<Problem> problems();
	
}
