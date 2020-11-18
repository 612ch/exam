package com.qhit.service.impl;

import com.qhit.mapper.CourseInfoMapper;
import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.CourseInfo;
import com.qhit.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoServiceImpl implements CourseInfoService {

    @Autowired
    private CourseInfoMapper courseInfoMapper;

    @Override
    public List<ClassInfo> getAllCourseInfo() {
        return courseInfoMapper.getAllCourseInfo();
    }

    @Override
    public int deleteByPrimaryKey(Integer courseid) {
        return courseInfoMapper.deleteByPrimaryKey(courseid);
    }

    @Override
    public int insert(CourseInfo record) {
        return courseInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(CourseInfo record) {
        return courseInfoMapper.insertSelective(record);
    }

    @Override
    public CourseInfo selectByPrimaryKey(Integer courseid) {
        return courseInfoMapper.selectByPrimaryKey(courseid);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseInfo record) {
        return courseInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseInfo record) {
        return courseInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int courseadd(CourseInfo courseInfo) {
        return courseInfoMapper.courseadd(courseInfo);
    }

    @Override
    public int selectByCourseId(int courseId) {
        return courseInfoMapper.selectByCourseId(courseId);
    }

    @Override
    public int deleteCourseInfo(int id) {
        return courseInfoMapper.deleteCourseInfo(id);
    }

    @Override
    public int updateCourseInfo(CourseInfo courseInfo) {
        return courseInfoMapper.updateCourseInfo(courseInfo);
    }
}