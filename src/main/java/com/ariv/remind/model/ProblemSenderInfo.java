package com.ariv.remind.model;

/**
 * @author zakir
 *
 */
public class ProblemSenderInfo {
    private String problem;
    private String feedback;
    private ProblemType problemType;

    public ProblemType getProblemType() {
        return problemType;
    }

    public void setProblemType(ProblemType problemType) {
        this.problemType = problemType;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
