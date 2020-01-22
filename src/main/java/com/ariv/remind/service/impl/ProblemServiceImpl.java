/**
 * 
 */
package com.ariv.remind.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ariv.remind.model.ProblemSenderInfo;
import com.ariv.remind.model.SpacedReminder;
import com.ariv.remind.repository.SpacedReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	SpacedReminderService spacedService;

	/**
	 * 
	 */
	public ProblemServiceImpl(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
	}

	@Override
	public Boolean saveProblem(Problem problem) {
		Problem resultProblem = problemRepository.save(constructProblem(problem));
		if(!spacedService.saveProblemInReminder(resultProblem)){
			throw new IllegalArgumentException("Problem cannot be inserted into the spaced_reminder table");
		}
		return Boolean.TRUE;
	}

	/**
	 * @param problem
	 * @return
	 */
	private Problem constructProblem(Problem problem) {
		problem.setDate(LocalDate.now());
		return problem;
	}

	@Override
	public List<Problem> problems() {
		return problemRepository.findAll();
	}

}
