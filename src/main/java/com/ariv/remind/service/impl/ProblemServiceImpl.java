/**
 * 
 */
package com.ariv.remind.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ariv.remind.model.Problem;
import com.ariv.remind.repository.ProblemRepository;
import com.ariv.remind.service.ProblemService;

/**
 * @author al
 *
 */
@Service
public class ProblemServiceImpl implements ProblemService {

	final ProblemRepository problemRepository;

	/**
	 * 
	 */
	public ProblemServiceImpl(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
	}

	@Override
	public Boolean addToReminder(Problem problem) {
		problemRepository.save(constructProblem(problem));
		return Boolean.TRUE;
	}

	/**
	 * @param problem
	 * @return
	 */
	private Problem constructProblem(Problem problem) {
		problem.setDate(new Date());
		return problem;
	}

	@Override
	public List<Problem> problems() {
		return problemRepository.findAll();
	}

}
