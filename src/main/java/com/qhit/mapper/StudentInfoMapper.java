package com.qhit.mapper;

import com.qhit.pojo.GradeInfo;
import com.qhit.pojo.StudentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Map;


@Repository
public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer studentid);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(Integer studentid);

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateByPrimaryKey(StudentInfo record);

    List<StudentInfo> SelectSelective(Map map);

    int getTotalCount();

    List<StudentInfo> selectByClassId(int classid);


    List<StudentInfo> getExamStudent(Integer teacherId);
    List<StudentInfo> getExamCount(Integer teacherId);

    /*
     * @Author demon
     * @Date 10:23 2019/5/27
     * @Description 通过用户名获取学生信息
     * @MethodName getStudentByName
     * @Param [username]
     * @return com.qhit.pojo.StudentInfo
     **/
    StudentInfo getStudentByName(String username);

    /*
     * @Author demon
     * @Date 23:43 2019/5/27
     * @Description 通过班级编号获取年年级信息
     * @MethodName getGradeByClassId
     * @Param [classid]
     * @return com.qhit.pojo.GradeInfo
     **/
    GradeInfo getGradeByClassId(Integer classid);

    List<StudentInfo> getExamPaperANDexamScore(String studentName);
}