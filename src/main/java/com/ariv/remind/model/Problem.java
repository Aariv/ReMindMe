/**
 * 
 */
package com.ariv.remind.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author al
 *
 */
@Entity
@Table(name = "problem")
public class Problem {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String name;

	private String referenceLink;

	private String feedback;

	private LocalDate date;

	@OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
	private Set<SpacedReminder> spacedReminders;

	/**
	 * 
	 */
	public Problem() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param number
	 * @param type
	 * @param feedback
	 * @param date
	 */
	public Problem(String name, String referenceLink, String feedback, LocalDate date) {
		super();
		this.name = name;
		this.referenceLink = referenceLink;
		this.feedback = feedback;
		this.date = date;
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
	 * @return the number
	 */
	public String getReferenceLink() {
		return referenceLink;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setReferenceLink(String number) {
		this.referenceLink = number;
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

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the spacedReminders
	 */
	public Set<SpacedReminder> getSpacedReminders() {
		return spacedReminders;
	}

	/**
	 * @param spacedReminders
	 *            the spacedReminders to set
	 */
	public void setSpacedReminders(Set<SpacedReminder> spacedReminders) {
		this.spacedReminders = spacedReminders;
	}

}
