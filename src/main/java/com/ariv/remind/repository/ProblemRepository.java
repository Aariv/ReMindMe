/**
 * 
 */
package com.ariv.remind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ariv.remind.model.Problem;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author al
 *
 */
@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {
    Problem findByNumber(String problemNumber);
}
