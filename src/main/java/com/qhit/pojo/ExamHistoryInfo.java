package com.qhit.pojo;

import java.io.Serializable;

public class ExamHistoryInfo implements Serializable {
    private Integer historyId;

    private Integer studentId;

    private Integer exampaperId;
    private Integer examPaperTime;

    public Integer getExamPaperTime() {
        return examPaperTime;
    }

    public void setExamPaperTime(Integer examPaperTime) {
        this.examPaperTime = examPaperTime;
    }

    private Integer examScore;
    private StudentInfo student;
    private ExamPaperInfo examPaper;

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getExampaperId() {
        return exampaperId;
    }

    public void setExampaperId(Integer exampaperId) {
        this.exampaperId = exampaperId;
    }

    public Integer getExamScore() {
        return examScore;
    }

    public void setExamScore(Integer examScore) {
        this.examScore = examScore;
    }

    public StudentInfo getStudent() {
        return student;
    }

    public void setStudent(StudentInfo student) {
        this.student = student;
    }

    public ExamPaperInfo getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(ExamPaperInfo examPaper) {
        this.examPaper = examPaper;
    }
}