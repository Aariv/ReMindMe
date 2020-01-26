package com.ariv.remind.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ariv.remind.model.Problem;
import com.ariv.remind.model.ProblemSenderInfo;
import com.ariv.remind.model.ReminderFeedback;
import com.ariv.remind.model.SpacedReminder;
import com.ariv.remind.repository.ProblemRepository;
import com.ariv.remind.repository.SpacedReminderRepository;

/**
 * @author zakir
 *
 */
@Service
public class SpacedReminderService {

	final SpacedReminderRepository spacedReminderRepository;
	final ProblemRepository problemRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(SpacedReminderService.class);

	public SpacedReminderService(SpacedReminderRepository spacedReminderRepository,
			ProblemRepository problemRepository) {
		this.spacedReminderRepository = spacedReminderRepository;
		this.problemRepository = problemRepository;
	}

	public Boolean saveProblemInReminder(Problem problem) {
		// TODO:- While saving a 'Problem' in spaced_reminder table follow a proper
		// spaced repetition algorithm
		spacedRepetitionLogic(problem);
		return Boolean.TRUE;
	}

	private void spacedRepetitionLogic(Problem problem) {
		/*
		 * Saving data in a Fibonacci fashion, a proven method to revise problems in a
		 * repetitive way
		 */
		LocalDate problemCompletionDate = problem.getDate();
		int range = 12;
		int numberOne = 1;
		int numberTwo = 2;
		for (int i = 1; i <= range; i++) {
			SpacedReminder spacedReminder = new SpacedReminder();
			int sum = numberOne + numberTwo;
			LocalDate reminderDate = problemCompletionDate.plusDays(sum);
			numberOne = numberTwo;
			numberTwo = sum;
			spacedReminder.setDate(reminderDate);
			spacedReminder.setProblem(problem);
			spacedReminder.setRevised(Boolean.FALSE);
			SpacedReminder result = spacedReminderRepository.save(spacedReminder);
			LOGGER.info("Scheduled problem '{}' on {} ", result.getProblem().getName(), result.getDate());
		}
	}

	public List<ProblemSenderInfo> getProblemsByDate() {
		LocalDate date = LocalDate.of(2020, 1, 29);
		List<ProblemSenderInfo> problemSenderInfoList = new ArrayList<>();
		Optional<List<SpacedReminder>> spacedReminderList = spacedReminderRepository
				.findAllByDateLessThanEqualAndIsRevisedFalse(date);
		if (!spacedReminderList.isPresent()) {
			LOGGER.debug("Reminder List is not present....Please check....");
			return problemSenderInfoList;
		}
		for (SpacedReminder spacedReminder : spacedReminderList.get()) {
			Problem problem = spacedReminder.getProblem();
			ProblemSenderInfo problemSenderInfo = new ProblemSenderInfo();
			problemSenderInfo.setProblemId(spacedReminder.getId());
			problemSenderInfo.setProblem(problem.getName());
			problemSenderInfo.setReferenceLink(problem.getReferenceLink());
			problemSenderInfo.setFeedback(problem.getFeedback());
			problemSenderInfoList.add(problemSenderInfo);
		}
		return problemSenderInfoList;
	}

	public List<SpacedReminder> findAll() {
		return spacedReminderRepository.findAll();
	}

	public ProblemSenderInfo findById(Integer id) {
		SpacedReminder reminder = spacedReminderRepository.getOne(id);
		return new ProblemSenderInfo(reminder.getId(), reminder.getProblem().getName(),
				reminder.getProblem().getReferenceLink(), reminder.getProblem().getFeedback());
	}

	public SpacedReminder updateSpacedReminder(ReminderFeedback reminderFeedback) {
		SpacedReminder resultFromDB = spacedReminderRepository.getOne(reminderFeedback.getId());
		resultFromDB.setIsRevised(reminderFeedback.getRevised());
		resultFromDB.setReminderFeedback(reminderFeedback.getFeedback());
		spacedReminderRepository.save(resultFromDB);
		return resultFromDB;
	}
}
