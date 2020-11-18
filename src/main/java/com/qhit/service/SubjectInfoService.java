package com.qhit.service;

import com.qhit.pojo.CourseInfo;
import com.qhit.pojo.ExamChooseInfo;
import com.qhit.pojo.GradeInfo;
import com.qhit.pojo.SubjectInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SubjectInfoService {
    public List<SubjectInfo> SelectSelective(HashMap map);
    public List<CourseInfo> getCourseList(HashMap map);
    public List<GradeInfo> getGradeList(HashMap map);
    public int getTotalCount();
    int deleteByPrimaryKey(Integer ID);
    public List<ExamChooseInfo> isDelSubject(Integer ID);

    int insertSelective(SubjectInfo subjectInfo);

    SubjectInfo getSubjectById(Integer subjectId);
    int updateByPrimaryKeySelective(SubjectInfo subjectInfo);

    public List<SubjectInfo> getSubjects(Map<String, Object> map);
    public int isAddSubjects(Map<String, Object> map);
    public int isAddSubject(SubjectInfo subject);

}
