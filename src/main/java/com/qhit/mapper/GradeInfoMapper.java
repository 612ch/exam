package com.qhit.mapper;

import com.qhit.pojo.GradeInfo;
import com.qhit.pojo.StudentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface GradeInfoMapper {
    /*
     * 获取所有班级
     * */
    List<GradeInfo> SelectSelective(Map map);

    GradeInfo selectById(Integer gradeInfoid);

    int deleteByPrimaryKey(Integer gradeid);

    int insert(GradeInfo record);

    int insertSelective(GradeInfo record);

    GradeInfo selectByPrimaryKey(Integer gradeid);

    int updateByPrimaryKeySelective(GradeInfo record);

    int updateByPrimaryKey(GradeInfo record);

    int add(GradeInfo gradeInfo);

    int updateGradeInfo(GradeInfo gradeInfo);
    //根据ID删除年级
    int deleteGradeInfo(int id);
}