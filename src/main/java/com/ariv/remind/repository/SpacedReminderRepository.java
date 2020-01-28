/**
 * 
 */
package com.ariv.remind.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ariv.remind.model.Problem;
import com.ariv.remind.model.SpacedReminder;

/**
 * @author zakir
 *
 */
@Repository
public interface SpacedReminderRepository extends JpaRepository<SpacedReminder, Integer> {

	Optional<List<SpacedReminder>> findAllByDateLessThanEqualAndIsRevisedFalse(LocalDate date);

//	@Query("FROM SpacedReminder sr WHERE sr.problem.user.id = :userId")
//	List<SpacedReminder> findAllByUserIdAndLocalDate(@Param("userId") Integer userId, LocalDate date);

	@Query("select s from SpacedReminder s where s.problem.user.id = ?1 and s.date <= ?2 and s.isRevised = false")
	List<SpacedReminder> findAllByUserIdAndLocalDate(Integer userId, LocalDate date);
	
	@Query("select s from SpacedReminder s where s.problem =?1 and s.problem.user.id = ?2 and s.date <= ?3 and s.isRevised = true")
	List<SpacedReminder> findAllByUserIdAndLocalDateAndProblem(Problem problem, Integer userId, LocalDate date);
	
	@Query("select s from SpacedReminder s where s.problem.user.id = ?1")
	List<SpacedReminder> findAllByUserId(Integer userId);

	Optional<List<SpacedReminder>> findAllByDateLessThanEqualAndIsRevisedTrueAndProblem(LocalDate date,
			Problem problem);
}
