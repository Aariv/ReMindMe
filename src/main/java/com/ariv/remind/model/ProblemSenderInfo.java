package com.ariv.remind.model;

/**
 * @author zakir
 *
 */
public class ProblemSenderInfo {
    private String problem;
    private String feedback;
    private ProblemType problemType;

    public Boolean getRevised() {
        return isRevised;
    }

    public void setRevised(Boolean revised) {
        isRevised = revised;
    }

    private Boolean isRevised;

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
