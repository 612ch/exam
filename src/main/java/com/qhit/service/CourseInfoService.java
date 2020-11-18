package com.qhit.service;

import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.CourseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseInfoService {
    List<ClassInfo> getAllCourseInfo();

    int deleteByPrimaryKey(Integer courseid);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    CourseInfo selectByPrimaryKey(Integer courseid);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);
    //添加科目
    int courseadd(CourseInfo courseInfo);

    int selectByCourseId(int courseId);
    //删除科目
    int deleteCourseInfo(int d);
    //修改科目i
    int updateCourseInfo(CourseInfo courseInfo);
}
