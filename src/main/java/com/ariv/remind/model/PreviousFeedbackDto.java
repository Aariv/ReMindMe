/**
 * 
 */
package com.ariv.remind.model;

import java.util.List;

/**
 * @author zentere
 *
 */
public class PreviousFeedbackDto {

	private Problem problem;

	private List<PreviousFeedback> previousFeedbacks;

	public PreviousFeedbackDto() {
	}

	/**
	 * @return the problem
	 */
	public Problem getProblem() {
		return problem;
	}

	/**
	 * @param problem the problem to set
	 */
	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	/**
	 * @return the previousFeedbacks
	 */
	public List<PreviousFeedback> getPreviousFeedbacks() {
		return previousFeedbacks;
	}

	/**
	 * @param previousFeedbacks the previousFeedbacks to set
	 */
	public void setPreviousFeedbacks(List<PreviousFeedback> previousFeedbacks) {
		this.previousFeedbacks = previousFeedbacks;
	}

}
