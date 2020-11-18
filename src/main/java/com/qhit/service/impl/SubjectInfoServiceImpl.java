package com.qhit.service.impl;

import com.qhit.mapper.SubjectInfoMapper;
import com.qhit.pojo.CourseInfo;
import com.qhit.pojo.ExamChooseInfo;
import com.qhit.pojo.GradeInfo;
import com.qhit.pojo.SubjectInfo;
import com.qhit.service.SubjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubjectInfoServiceImpl implements SubjectInfoService {
    @Autowired
    private SubjectInfoMapper subjectInfoMapper;
    @Override
    public List<SubjectInfo> SelectSelective(HashMap map) {
        return subjectInfoMapper.SelectSelective(map);
    }

    @Override
    public List<CourseInfo> getCourseList(HashMap map) {
        return subjectInfoMapper.getCourseList(map);
    }

    @Override
    public List<GradeInfo> getGradeList(HashMap map) {
        return subjectInfoMapper.getGradeList(map);
    }

    @Override
    public int getTotalCount() {
        return subjectInfoMapper.getTotalCount();
    }

    @Override
    public int deleteByPrimaryKey(Integer ID) {
        return subjectInfoMapper.deleteByPrimaryKey(ID);
    }

    @Override
    public List<ExamChooseInfo> isDelSubject(Integer ID) {
        return subjectInfoMapper.isDelSubject(ID);
    }

    @Override
    public int insertSelective(SubjectInfo subjectInfo) {
        return subjectInfoMapper.insertSelective(subjectInfo);
    }

    @Override
    public SubjectInfo getSubjectById(Integer subjectId) {
        return subjectInfoMapper.getSubjectById(subjectId);
    }

    @Override
    public int updateByPrimaryKeySelective(SubjectInfo subjectInfo) {
        return subjectInfoMapper.updateByPrimaryKeySelective(subjectInfo);
    }

    @Override
    public List<SubjectInfo> getSubjects(Map<String, Object> map) {
        return subjectInfoMapper.getSubjects(map);
    }

    @Override
    public int isAddSubjects(Map<String, Object> map) {
        return subjectInfoMapper.isAddSubjects(map);
    }

    @Override
    public int isAddSubject(SubjectInfo subject) {
        return subjectInfoMapper.isAddSubject(subject);
    }


}
