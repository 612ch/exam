package com.qhit.service;

import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.ExamPlanInfo;

import java.util.List;
import java.util.Map;

public interface ExamPlanInfoService {
    public List<ExamPlanInfo> getAll();
    public ExamPlanInfo getExamPlanIdByexamPlanId(Integer examPlanId);
    public int addExamPlanInfo(ExamPlanInfo examPlanInfo);
    public int delExamPlanInfo(int examPlanId);
    public int updateExamPlanInfo(ExamPlanInfo examPlanInfo);

    /*
     * @Author demon
     * @Date 23:30 2019/5/27
     * @Description 查看学生将要考试的信息
     * @MethodName getStudentWillExam
     * @Param [map]
     * @return java.util.List<com.qhit.pojo.ExamPlanInfo>
     **/
    public List<ExamPlanInfo> getStudentWillExam(Map<String, Object> map);
}
