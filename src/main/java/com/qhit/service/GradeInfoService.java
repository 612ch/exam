package com.qhit.service;

import com.qhit.pojo.CourseInfo;
import com.qhit.pojo.GradeInfo;

import java.util.List;

public interface GradeInfoService {

    /*
    * 获取所有年级
    * */
    List<GradeInfo> getGradeList();

    GradeInfo selectById(Integer gradeInfoid);

    int deleteByPrimaryKey(Integer gradeid);

    int insert(GradeInfo record);

    int insertSelective(GradeInfo record);

    GradeInfo selectByPrimaryKey(Integer gradeid);

    int updateByPrimaryKeySelective(GradeInfo record);

    int updateByPrimaryKey(GradeInfo record);
    //添加年级
    int add(GradeInfo gradeInfo);
    //修改年级
    int updateGradeInfo(GradeInfo gradeInfo);
    //删除年级
    int deleteGradeInfo(int id);

}
