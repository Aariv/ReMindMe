/**
 * 
 */
package com.ariv.remind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ariv.remind.model.Problem;

/**
 * @author al
 *
 */
@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

}
