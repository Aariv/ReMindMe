/**
 * 
 */
package com.ariv.remind.service;

import java.util.List;

import com.ariv.remind.model.Problem;
import com.ariv.remind.model.ProblemDto;

/**
 * @author al
 *
 */
public interface ProblemService {

	public Boolean saveProblem(Problem problem);

	public List<Problem> problems();
	
	public ProblemDto getProblem(Integer id);
}
