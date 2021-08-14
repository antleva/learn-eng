package com.myproject.learneng.dto;

import com.myproject.learneng.domain.Result;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ResultDto {
    private double totalPhrases;
    private double wrongAnswers;
    private double rightAnswers;
    private double percent;
    private String date;

    public ResultDto(Result result) {
        this.totalPhrases = result.getTotalPhrases();
        this.wrongAnswers = result.getWrongAnswers();
        this.rightAnswers = result.getRightAnswers();
        this.percent = result.getPercent();
        this.date = result.getDate().toString();
    }

    public ResultDto() {}

    public static Result getResultFromDto(ResultDto resultDto){
            Result result = new Result();
            System.out.println(resultDto.getTotalPhrases());
            result.setTotalPhrases(resultDto.getTotalPhrases());
            result.setRightAnswers(resultDto.getRightAnswers());
            result.setWrongAnswers(resultDto.getWrongAnswers());
            result.setPercent(resultDto.getRightAnswers()/resultDto.getTotalPhrases());
            result.setCreatedDate(LocalDateTime.now((ZoneId.of("Europe/Moscow"))).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return result;
    }

    public static String getResultDto(Result result){
        return new ResultDto(result).toString();
    }

    @Override
    public String toString() {
        String p = String.format("%.2f",percent*100.0);
        return  "totalPhrases :   " + (int)totalPhrases +
                "|   wrongAnswers :   " + (int)wrongAnswers +
                "|   rightAnswers :   " + (int)rightAnswers +
                "|   percent  :   " + p +" %"+
                "|   date :   " + date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
