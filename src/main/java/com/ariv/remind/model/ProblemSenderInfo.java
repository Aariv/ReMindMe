package com.ariv.remind.model;

import java.time.LocalDate;

/**
 * @author zakir
 *
 */
public class ProblemSenderInfo {
	private Integer problemId;
	private String problem;
	private String referenceLink;
	private String feedback;
	private LocalDate date;
	
	public ProblemSenderInfo() {}

	public ProblemSenderInfo(Integer problemId, String problem, String referenceLink, String feedback) {
		super();
		this.problemId = problemId;
		this.problem = problem;
		this.referenceLink = referenceLink;
		this.feedback = feedback;
	}

	/**
	 * @return the problemId
	 */
	public Integer getProblemId() {
		return problemId;
	}

	/**
	 * @param problemId
	 *            the problemId to set
	 */
	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getReferenceLink() {
		return referenceLink;
	}

	public void setReferenceLink(String referenceLink) {
		this.referenceLink = referenceLink;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
