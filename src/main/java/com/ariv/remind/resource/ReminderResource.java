/**
 * 
 */
package com.ariv.remind.resource;

import java.util.List;

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

	/**
	 * 
	 */
	public ReminderResource(ProblemService problemService) {
		this.problemService = problemService;
	}

	@GetMapping("/health")
	public ResponseData getHelloWorld() {
		return new ResponseData(true, "", "Hello World!");
	}

	@PostMapping
	public ResponseData addToReminder(@RequestBody Problem problem) {
		boolean result = problemService.addToReminder(problem);
		if (result)
			return new ResponseData(true, "", "Succcess");
		else
			return new ResponseData(false, "", "Failed");
	}

	@GetMapping
	public ResponseData getAllProblems() {
		List<Problem> problems = problemService.problems();
		return new ResponseData(true, problems, "Success");
	}
}
