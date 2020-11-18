package com.qhit.mapper;

import com.qhit.pojo.CourseInfo;
import com.qhit.pojo.ExamChooseInfo;
import com.qhit.pojo.GradeInfo;
import com.qhit.pojo.SubjectInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface SubjectInfoMapper {
    int deleteByPrimaryKey(Integer subjectid);

    int insert(SubjectInfo record);

    int insertSelective(SubjectInfo record);

    SubjectInfo selectByPrimaryKey(Integer subjectid);

    int updateByPrimaryKeySelective(SubjectInfo subjectInfo);

    int updateByPrimaryKey(SubjectInfo record);

    List<SubjectInfo> SelectSelective(HashMap map);

    int getTotalCount();
    public List<CourseInfo> getCourseList(HashMap map);
    public List<GradeInfo> getGradeList(HashMap map);
    public List<ExamChooseInfo> isDelSubject(Integer ID);
    SubjectInfo getSubjectById(Integer subjectId);
    public List<SubjectInfo> getSubjects(Map<String, Object> map);
    public int isAddSubjects(Map<String, Object> map);
    public int isAddSubject(SubjectInfo subject);
}