/**
 * 
 */
package com.ariv.remind.model;

/**
 * @author zentere
 *
 */
public class ReminderFeedback {

	public Integer id;
	public Boolean revised;
	public String feedback;

	public ReminderFeedback() {
		// TODO Auto-generated constructor stub
	}

	public ReminderFeedback(Integer id, Boolean revised, String feedback) {
		super();
		this.id = id;
		this.revised = revised;
		this.feedback = feedback;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the revised
	 */
	public Boolean getRevised() {
		return revised;
	}

	/**
	 * @param revised
	 *            the revised to set
	 */
	public void setRevised(Boolean revised) {
		this.revised = revised;
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
