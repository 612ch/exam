package com.qhit.pojo;

import java.io.Serializable;
import java.util.Date;

public class ExamPlanInfo implements Serializable {
    private Integer examPlanId;

    private Integer courseId;

    private Integer classId;

    private Integer exampaperId;

    private String beginTime;

    private CourseInfo course;

    private ClassInfo clazz;

    private ExamPaperInfo examPaper;

    public CourseInfo getCourse() {
        return course;
    }

    public void setCourse(CourseInfo course) {
        this.course = course;
    }

    public ClassInfo getClazz() {
        return clazz;
    }

    public void setClazz(ClassInfo clazz) {
        this.clazz = clazz;
    }

    public ExamPaperInfo getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(ExamPaperInfo examPaper) {
        this.examPaper = examPaper;
    }

    public Integer getExamPlanId() {
        return examPlanId;
    }

    public void setExamPlanId(Integer examPlanId) {
        this.examPlanId = examPlanId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getExampaperId() {
        return exampaperId;
    }

    public void setExampaperId(Integer exampaperId) {
        this.exampaperId = exampaperId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
}