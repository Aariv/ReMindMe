package com.ariv.remind.model;

/**
 * @author zakir
 *
 */
public class ProblemSenderInfo {
	private String problem;
	private String referenceLink;
	private String feedback;

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

}
