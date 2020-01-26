/**
 * 
 */
package com.ariv.remind.model;

/**
 * @author zentere
 *
 */
public class PreviousFeedback {

	private String feedback;
	
	public PreviousFeedback() {
	}

	public PreviousFeedback(String feedback) {
		super();
		this.feedback = feedback;
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
