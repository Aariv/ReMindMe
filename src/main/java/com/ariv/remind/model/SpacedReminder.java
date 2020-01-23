/**
 * 
 */
package com.ariv.remind.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author zakir
 *
 */
@Entity
public class SpacedReminder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String problemNumber;

	private LocalDate date;

	private Boolean isRevised;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProblemNumber() {
		return problemNumber;
	}

	public void setProblemNumber(String problemNumber) {
		this.problemNumber = problemNumber;
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
}
