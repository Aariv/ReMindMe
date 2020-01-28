/**
 * 
 */
package com.ariv.remind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ariv.remind.model.Problem;
import com.ariv.remind.security.model.User;

/**
 * @author al
 *
 */
@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {
	List<Problem> findAllByUser(User user);
}
