/**
 * 
 */
package com.ariv.remind.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.ariv.remind.model.Problem;
import com.ariv.remind.model.ProblemSenderInfo;

/**
 * @author al
 *
 */
public interface ProblemService {

	public Boolean saveProblem(Problem problem);

	public List<Problem> problems();
	
}
