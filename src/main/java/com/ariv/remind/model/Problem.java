/**
 * 
 */
package com.ariv.remind.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

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

	private String name;

	@Column(unique = true)
	private String number;

	private ProblemType type;

	private String feedback;

	private LocalDate date;


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
	public Problem(String name, String number, ProblemType type, String feedback, LocalDate date) {
		super();
		this.name = name;
		this.number = number;
		this.type = type;
		this.feedback = feedback;
		this.date = date;
	}
	
	/**
	 * @param name
	 * @param number
	 * @param type
	 * @param feedback
	 */
	public Problem(String name, String number, ProblemType type, String feedback) {
		super();
		this.name = name;
		this.number = number;
		this.type = type;
		this.feedback = feedback;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the type
	 */
	public ProblemType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ProblemType type) {
		this.type = type;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
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
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
