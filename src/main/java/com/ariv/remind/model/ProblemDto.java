package com.ariv.remind.model;

public class ProblemDto {
	private String name;

	private String referenceLink;

	private String feedback;

	public ProblemDto(String name, String referenceLink, String feedback) {
		super();
		this.name = name;
		this.referenceLink = referenceLink;
		this.feedback = feedback;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the referenceLink
	 */
	public String getReferenceLink() {
		return referenceLink;
	}

	/**
	 * @param referenceLink
	 *            the referenceLink to set
	 */
	public void setReferenceLink(String referenceLink) {
		this.referenceLink = referenceLink;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback
	 *            the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}