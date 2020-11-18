package com.qhit.service.impl;

import com.qhit.mapper.StudentInfoMapper;
import com.qhit.pojo.GradeInfo;
import com.qhit.pojo.StudentInfo;
import com.qhit.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    @Override
    public int deleteByPrimaryKey(Integer studentid) {
        return studentInfoMapper.deleteByPrimaryKey(studentid);
    }

    @Override
    public int insert(StudentInfo record) {
        return studentInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(StudentInfo record) {
        return studentInfoMapper.insertSelective(record);
    }

    @Override
    public StudentInfo selectByPrimaryKey(Integer studentid) {
        return studentInfoMapper.selectByPrimaryKey(studentid);
    }

    @Override
    public int updateByPrimaryKeySelective(StudentInfo record) {
        return studentInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(StudentInfo record) {
        return studentInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<StudentInfo> SelectSelective(Map map) {
        return studentInfoMapper.SelectSelective(map);
    }

    @Override
    public int getTotalCount() {
        return studentInfoMapper.getTotalCount();
    }

    @Override

    public List<StudentInfo> getExamStudent(Integer teacherId) {
        return studentInfoMapper.getExamStudent(teacherId);
    }

    @Override
    public List<StudentInfo> getExamCount(Integer teacherId) {
        return studentInfoMapper.getExamCount(teacherId);

    }
    @Override
    public GradeInfo getGradeByClassId(Integer classid) {
        return studentInfoMapper.getGradeByClassId(classid);

    }

    @Override
    public List<StudentInfo> getExamPaperANDexamScore(String studentName) {
        return studentInfoMapper.getExamPaperANDexamScore(studentName);

    }
}
