/**
 * 
 */
package com.ariv.remind.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ariv.remind.model.PreviousFeedback;
import com.ariv.remind.model.Problem;
import com.ariv.remind.model.ProblemDto;
import com.ariv.remind.model.ProblemSenderInfo;
import com.ariv.remind.model.ReminderFeedback;
import com.ariv.remind.model.SpacedReminder;
import com.ariv.remind.service.ProblemService;
import com.ariv.remind.service.impl.SpacedReminderService;

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
	
	@PostMapping("/revisedReminder")
	public ResponseData saveRevisedReminder(@RequestBody ReminderFeedback reminderFeedback) {
		SpacedReminder result = spacedReminderService.updateSpacedReminder(reminderFeedback);
		if (result != null)
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
		List<ProblemSenderInfo> problems = spacedReminderService.findAll();
		return new ResponseData(true, problems, "Success");
	}

	@GetMapping("/allConcepts")
	public ResponseData getAllConcepts() {
		List<ProblemSenderInfo> problems = problemService.findAll();
		return new ResponseData(true, problems, "Success");
	}
	
	@GetMapping("/previousFeedback/{id}")
	public ResponseData getAllPreviousFeedback(@PathVariable Integer id) {
		List<PreviousFeedback> feedbacks = spacedReminderService.findByIdAndProblem(id);
		return new ResponseData(true, feedbacks, "Success");
	}
	
	@GetMapping("/{id}")
	public ResponseData getProblemReminder(@PathVariable Integer id) {
		ProblemDto problem = problemService.getProblem(id);
		return new ResponseData(true, problem, "Success");
	}
	
	@GetMapping("/spacedReminders/{id}")
	public ResponseData getReminders(@PathVariable Integer id) {
		ProblemSenderInfo problem = spacedReminderService.findById(id);
		return new ResponseData(true, problem, "Success");
	}

	@GetMapping
	public ResponseData getProblemsByDate(){
		List<ProblemSenderInfo> problems = spacedReminderService.getProblemsByDate();
		if(problems.isEmpty()) {
			return new ResponseData(false, problems, "Failure");
		} else {
			return new ResponseData(true, problems, "Success");
		}
	}
}
