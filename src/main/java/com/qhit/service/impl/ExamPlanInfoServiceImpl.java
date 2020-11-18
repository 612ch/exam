package com.qhit.service.impl;

import com.qhit.mapper.ExamPlanInfoMapper;
import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.ExamPlanInfo;
import com.qhit.service.ExamPlanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamPlanInfoServiceImpl implements ExamPlanInfoService {
    @Autowired
    private ExamPlanInfoMapper examPlanInfoMapper;

    public ExamPlanInfoMapper getExamPlanInfoMapper() {
        return examPlanInfoMapper;
    }

    public void setExamPlanInfoMapper(ExamPlanInfoMapper examPlanInfoMapper) {
        this.examPlanInfoMapper = examPlanInfoMapper;
    }

    @Override
    public List<ExamPlanInfo> getAll() {
        return examPlanInfoMapper.getAll();
    }

    @Override
    public ExamPlanInfo getExamPlanIdByexamPlanId(Integer examPlanId) {
        return examPlanInfoMapper.getExamPlanIdByexamPlanId(examPlanId);
    }


    @Override
    public int addExamPlanInfo(ExamPlanInfo examPlanInfo) {
        return examPlanInfoMapper.addExamPlanInfo(examPlanInfo);
    }

    @Override
    public int delExamPlanInfo(int examPlanId) {
        return examPlanInfoMapper.delExamPlanInfo(examPlanId);
    }

    @Override
    public int updateExamPlanInfo(ExamPlanInfo examPlanInfo) {
        return examPlanInfoMapper.updateExamPlanInfo(examPlanInfo);
    }

    @Override
    public List<ExamPlanInfo> getStudentWillExam(Map<String, Object> map) {
        return examPlanInfoMapper.getStudentWillExam(map);
    }


}
