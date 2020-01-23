/**
 * 
 */
package com.ariv.remind.repository;

import com.ariv.remind.model.Problem;
import com.ariv.remind.model.SpacedReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author zakir
 *
 */
@Repository
public interface SpacedReminderRepository extends JpaRepository<SpacedReminder, Integer> {

    Optional<List<SpacedReminder>> findAllByDateLessThanEqualAndIsRevisedFalse(LocalDate date);

}
