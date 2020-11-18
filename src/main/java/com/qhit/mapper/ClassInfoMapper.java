package com.qhit.mapper;

import com.qhit.pojo.ClassInfo;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ClassInfoMapper {


    /*
     * @Author demon
     * @Date 8:57 2019/5/21
     * @Description 获取所有班级信息
     * @MethodName getAllClassInfo
     * @Param [map]
     * @return java.util.List<com.qhit.pojo.ClassInfo>
     **/
    List<ClassInfo> getAllClassInfo(Map map);

    ClassInfo getClassByClassId(Integer classid);

    int deleteByPrimaryKey(int classid);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    ClassInfo selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);

    int updateClass(ClassInfo classInfo);
    /*
     * @Author demon
     * @Date 8:53 2019/5/21
     * @Description 获取班级总数
     * @MethodName getCount
     * @Param []
     * @return int
     **/
    int getCount();

    /*
     * @Author demon
     * @Date 9:37 2019/5/28
     * @Description 通过班级获取学生人数
     * @MethodName getStudentCountForClass
     * @Param [gradeId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
/*    @MapKey("className")
    public Map<String, Object> getStudentCountForClass(Integer gradeId);*/
    List<Map<String,Object>> getStudentCountForClass();
    /*
     * @Author demon
     * @Date 19:18 2019/5/21
     * @Description 有选着性的获取班级
     * @MethodName getClassSelective
     * @Param [map]
     * @Key classId,gradeId,className,teacherId
     * @return java.util.List<com.qhit.pojo.ClassInfo>
     **/
    public List<ClassInfo> getClassSelective(HashMap map);


}