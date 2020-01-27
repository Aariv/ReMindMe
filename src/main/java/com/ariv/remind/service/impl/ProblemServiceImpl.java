/**
 * 
 */
package com.ariv.remind.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ariv.remind.model.Problem;
import com.ariv.remind.model.ProblemDto;
import com.ariv.remind.model.ProblemSenderInfo;
import com.ariv.remind.repository.ProblemRepository;
import com.ariv.remind.security.SecurityUtils;
import com.ariv.remind.security.repository.UserRepository;
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
	@Autowired
	private UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(SpacedReminderService.class);

	/**
	 * 
	 */
	public ProblemServiceImpl(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
	}

	@Override
	public Boolean saveProblem(Problem problem) {

		problem.setUser(
				userRepository.findOneWithAuthoritiesByEmailIgnoreCase(SecurityUtils.getCurrentUsername().get()).get());
		Problem resultProblem = problemRepository.save(constructProblem(problem));
		if (!spacedService.saveProblemInReminder(resultProblem)) {
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

	@Override
	public ProblemDto getProblem(Integer id) {
		Problem problem = problemRepository.getOne(id);
		ProblemDto problemDto = new ProblemDto(problem.getName(), problem.getReferenceLink(), problem.getFeedback());
		return problemDto;
	}

	@Override
	public List<ProblemSenderInfo> findAll() {
		List<ProblemSenderInfo> problemSenderInfoList = new ArrayList<>();
		List<Problem> problemList = problemRepository.findAll();
		if (problemList.isEmpty()) {
			LOGGER.debug("Reminder List is not present....Please check....");
			return problemSenderInfoList;
		}
		for (Problem problem : problemList) {
			ProblemSenderInfo problemSenderInfo = new ProblemSenderInfo();
			problemSenderInfo.setProblemId(problem.getId());
			problemSenderInfo.setProblem(problem.getName());
			problemSenderInfo.setReferenceLink(problem.getReferenceLink());
			problemSenderInfo.setFeedback(problem.getFeedback());
			problemSenderInfo.setDate(problem.getDate());
			problemSenderInfoList.add(problemSenderInfo);
		}
		return problemSenderInfoList;
	}

}
