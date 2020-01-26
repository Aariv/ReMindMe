/**
 * 
 */
package com.ariv.remind.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
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

    Optional<List<SpacedReminder>> findAllByDateLessThanEqualAndIsRevisedTrueAndProblem(LocalDate date, Problem problem);
}
