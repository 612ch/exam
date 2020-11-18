package com.qhit.mapper;

import com.qhit.pojo.ExamSubjectMiddleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExamSubjectMiddleInfoMapper {
    int deleteByPrimaryKey(Integer esmid);

    int insert(ExamSubjectMiddleInfo record);

    int insertSelective(ExamSubjectMiddleInfo record);

    ExamSubjectMiddleInfo selectByPrimaryKey(Integer esmid);

    int updateByPrimaryKeySelective(ExamSubjectMiddleInfo record);

    int updateByPrimaryKey(ExamSubjectMiddleInfo record);

    List<ExamSubjectMiddleInfo> getExamPaperWithSubject(ExamSubjectMiddleInfo esm);

    public Integer getEsmByExamIdWithSubjectId(ExamSubjectMiddleInfo esm);

    public int isAddESM(Map<String, Object> map);

    public int removeSubjectWithExamPaper(Map<String, Object> map);
}