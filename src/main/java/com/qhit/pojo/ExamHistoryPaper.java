package com.qhit.pojo;

/**
 * @ClassName ExamHistoryPaper
 * @Descriotion 考试历史
 * @Author demon
 * @Date 2019/5/28 0:06
 * @Version 1.0
 **/
public class ExamHistoryPaper {

    private int examScore;

    private String beginTime;

    private Integer examPaperId;
    private String examPaperName;
    private int subjectNum;
    private int examPaperScore;

    public int getExamScore() {
        return examScore;
    }

    public void setExamScore(int examScore) {
        this.examScore = examScore;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(Integer examPaperId) {
        this.examPaperId = examPaperId;
    }

    public String getExamPaperName() {
        return examPaperName;
    }

    public void setExamPaperName(String examPaperName) {
        this.examPaperName = examPaperName;
    }

    public int getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(int subjectNum) {
        this.subjectNum = subjectNum;
    }

    public int getExamPaperScore() {
        return examPaperScore;
    }

    public void setExamPaperScore(int examPaperScore) {
        this.examPaperScore = examPaperScore;
    }
}
