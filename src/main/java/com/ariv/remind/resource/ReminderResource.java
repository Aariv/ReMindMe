/**
 * 
 */
package com.ariv.remind.resource;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.ariv.remind.model.ProblemSenderInfo;
import com.ariv.remind.model.SpacedReminder;
import com.ariv.remind.service.impl.SpacedReminderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariv.remind.model.Problem;
import com.ariv.remind.service.ProblemService;

/**
 * @author al
 *
 */
@RestController
@RequestMapping("/v1/reminders")
public class ReminderResource {

	final ProblemService problemService;
	final SpacedReminderService spacedReminderService;

	/**
	 * 
	 */
	public ReminderResource(ProblemService problemService, SpacedReminderService spacedReminderService) {
		this.problemService = problemService;
		this.spacedReminderService = spacedReminderService;
	}

	@GetMapping("/health")
	public ResponseData getHelloWorld() {
		return new ResponseData(true, "", "Hello World!");
	}

	@PostMapping
	public ResponseData saveProblem(@RequestBody Problem problem) {
		boolean result = problemService.saveProblem(problem);
		if (result)
			return new ResponseData(true, "", "Succcess");
		else
			return new ResponseData(false, "", "Failed");
	}

	@GetMapping("/all")
	public ResponseData getAllProblems() {
		List<Problem> problems = problemService.problems();
		return new ResponseData(true, problems, "Success");
	}

	@GetMapping("/allReminders")
	public ResponseData getAllProblemReminders() {
		List<SpacedReminder> problems = spacedReminderService.findAll();
		return new ResponseData(true, problems, "Success");
	}

	@GetMapping
	public ResponseData getProblemsByDate(){
		List<ProblemSenderInfo> problems = spacedReminderService.getProblemsByDate();
		return new ResponseData(true, problems, "Success");
	}
}
