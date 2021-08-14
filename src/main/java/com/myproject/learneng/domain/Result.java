package com.myproject.learneng.domain;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Embeddable
public class Result {
    private double totalPhrases;
    private double wrongAnswers;
    private double rightAnswers;
    private double percent;
    private String createdDate = LocalDateTime.now((ZoneId.of("Europe/Moscow"))).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    public Result() {}

    public Result(double totalPhrases, double wrongAnswers, double rightAnswers, double percent) {
        this.totalPhrases = totalPhrases;
        this.wrongAnswers = wrongAnswers;
        this.rightAnswers = rightAnswers;
        this.percent = percent;
    }

    public double getTotalPhrases() {
        return totalPhrases;
    }

    public void setTotalPhrases(double totalPhrases) {
        this.totalPhrases = totalPhrases;
    }

    public double getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(double wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public double getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(double rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDate() {
        return createdDate;
    }

}
