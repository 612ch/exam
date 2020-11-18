package com.qhit.service;

import com.qhit.pojo.ExamSubjectMiddleInfo;



import java.util.List;
import java.util.Map;


public interface ExamSubjectMiddleInfoService {

    List<ExamSubjectMiddleInfo> getExamPaperWithSubject(ExamSubjectMiddleInfo esm);
    public Integer getEsmByExamIdWithSubjectId(ExamSubjectMiddleInfo esm);
    public int isAddESM(Map<String, Object> map);
    public int removeSubjectWithExamPaper(Map<String, Object> map);
}
