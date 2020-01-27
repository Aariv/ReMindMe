/**
 * 
 */
package com.ariv.remind.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author zakir
 *
 */
@Entity
public class SpacedReminder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate date;

	private Boolean isRevised;

	@ManyToOne
	@JoinColumn(name = "problem_id", nullable = false)
	private Problem problem;

	private String reminderFeedback;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean getRevised() {
		return isRevised;
	}

	public void setRevised(Boolean revised) {
		isRevised = revised;
	}

	/**
	 * @return the isRevised
	 */
	public Boolean getIsRevised() {
		return isRevised;
	}

	/**
	 * @param isRevised
	 *            the isRevised to set
	 */
	public void setIsRevised(Boolean isRevised) {
		this.isRevised = isRevised;
	}

	/**
	 * @return the reminderFeedback
	 */
	public String getReminderFeedback() {
		return reminderFeedback;
	}

	/**
	 * @param reminderFeedback
	 *            the reminderFeedback to set
	 */
	public void setReminderFeedback(String reminderFeedback) {
		this.reminderFeedback = reminderFeedback;
	}

}
