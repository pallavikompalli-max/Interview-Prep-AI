
package com.pallavi.interviewprep;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "practice_progress")
public class PracticeProgress {

    @Id
    private Long questionId;

    private boolean practiced;

    public PracticeProgress() {
    }

    public PracticeProgress(Long questionId, boolean practiced) {
        this.questionId = questionId;
        this.practiced = practiced;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public boolean isPracticed() {
        return practiced;
    }

    public void setPracticed(boolean practiced) {
        this.practiced = practiced;
    }
}

