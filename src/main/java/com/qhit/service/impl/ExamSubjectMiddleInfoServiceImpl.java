package com.qhit.service.impl;

import com.qhit.mapper.ExamSubjectMiddleInfoMapper;
import com.qhit.pojo.ExamSubjectMiddleInfo;
import com.qhit.service.ExamSubjectMiddleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamSubjectMiddleInfoServiceImpl implements ExamSubjectMiddleInfoService {
    @Autowired
    private ExamSubjectMiddleInfoMapper examSubjectMiddleInfoMapper;


    @Override
    public List<ExamSubjectMiddleInfo> getExamPaperWithSubject(ExamSubjectMiddleInfo esm) {
        return examSubjectMiddleInfoMapper.getExamPaperWithSubject(esm);
    }

    @Override
    public Integer getEsmByExamIdWithSubjectId(ExamSubjectMiddleInfo esm) {
        return examSubjectMiddleInfoMapper.getEsmByExamIdWithSubjectId(esm);
    }

    @Override
    public int isAddESM(Map<String, Object> map) {
        return examSubjectMiddleInfoMapper.isAddESM(map);
    }

    @Override
    public int removeSubjectWithExamPaper(Map<String, Object> map) {
        return examSubjectMiddleInfoMapper.removeSubjectWithExamPaper(map);
    }
}
