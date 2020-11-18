package com.qhit.mapper;

import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.TeacherInfo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Repository
public interface TeacherInfoMapper{

    TeacherInfo getTeacherByName(String username);
    int getCount();//教师总数
    public List<TeacherInfo> getAll(HashMap hashMap);
    public TeacherInfo getTeacherInfoByteacherId(int teacherId);
    public int updateTeacherInfo(TeacherInfo teacherInfo);
    public int addTeacherInfo(TeacherInfo teacherInfo);
    public int deleteTeacherInfo(int teacherId);

    List<TeacherInfo> getIsWorkTearcher();
    int addIsWork(int teacherid);

    int deleteIsWork(int teacherid);

    public ClassInfo getClassInfoByClassId(int classId);

}