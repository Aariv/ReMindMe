package com.ariv.remind.service.impl;

import com.ariv.remind.model.Problem;
import com.ariv.remind.model.ProblemSenderInfo;
import com.ariv.remind.model.SpacedReminder;
import com.ariv.remind.repository.ProblemRepository;
import com.ariv.remind.repository.SpacedReminderRepository;
import com.sun.xml.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author zakir
 *
 */
@Service
public class SpacedReminderService {

    final SpacedReminderRepository spacedReminderRepository;
    final ProblemRepository problemRepository;
    private static final Boolean isRevised = Boolean.FALSE;
    private static final Logger LOGGER = LoggerFactory.getLogger(SpacedReminderService.class);

    public SpacedReminderService(SpacedReminderRepository spacedReminderRepository, ProblemRepository problemRepository) {
        this.spacedReminderRepository = spacedReminderRepository;
        this.problemRepository = problemRepository;
    }

    public Boolean saveProblemInReminder(Problem problem) {
        //TODO:- While saving a 'Problem' in spaced_reminder table follow a proper spaced repetition algorithm
        spacedRepetitionLogic(problem);
        return Boolean.TRUE;
    }

    private void spacedRepetitionLogic(Problem problem){
        /* Saving data in a Fibonacci fashion, a proven method to revise problems in a repetitive
        way */
        LocalDate problemCompletionDate = problem.getDate();
        int range = 12;
        int numberOne = 1;
        int numberTwo = 2;
        for(int i = 1; i <= range; i++) {
            SpacedReminder spacedReminder = new SpacedReminder();
            int sum = numberOne + numberTwo;
            LocalDate reminderDate = problemCompletionDate.plusDays(sum);
            numberOne = numberTwo;
            numberTwo = sum;
            spacedReminder.setDate(reminderDate);
            spacedReminder.setProblemNumber(problem.getNumber());
            spacedReminder.setRevised(Boolean.FALSE);
            SpacedReminder result = spacedReminderRepository.save(spacedReminder);
            LOGGER.info("Scheduled problem '{}' on {} ", result.getProblemNumber(), result.getDate());
        }
    }

    public List<ProblemSenderInfo> getProblemsByDate(){
        LocalDate date = LocalDate.of(2020, 1, 26);
        List<ProblemSenderInfo> problemSenderInfoList = new ArrayList<>();
        List<Problem> problemList = new ArrayList<>();
        Optional<List<SpacedReminder>> spacedReminderList = spacedReminderRepository.findAllByDateBeforeAndIsRevisedFalse(date);
        if(!spacedReminderList.isPresent()){
            LOGGER.debug("Reminder List is not present....Please check....");
            return problemSenderInfoList;
        }
        for (SpacedReminder spacedReminder : spacedReminderList.get()) {
            problemList.add(problemRepository.findByNumber(spacedReminder.getProblemNumber()));
        }
        for(Problem problem : problemList){
            ProblemSenderInfo problemSenderInfo = new ProblemSenderInfo();
            problemSenderInfo.setProblemType(problem.getType());
            String problemStr = problem.getNumber() + ". " + problem.getName();
            problemSenderInfo.setProblem(problemStr);
            problemSenderInfo.setFeedback(problem.getFeedback());
            problemSenderInfoList.add(problemSenderInfo);
        }
        return problemSenderInfoList;
    }

    public List<SpacedReminder> findAll() {
        return spacedReminderRepository.findAll();
    }
}
