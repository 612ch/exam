package com.qhit.service;

import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.TeacherInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TeacherInfoService {
    public List<TeacherInfo> getAll(HashMap hashMap);
    public TeacherInfo getTeacherInfoByteacherId(int teacherId);
    public int updateTeacherInfo(TeacherInfo teacherInfo);
    public int addTeacherInfo(TeacherInfo teacherInfo);
    public int deleteTeacherInfo(int teacherId);
    public List<TeacherInfo> getIsWorkTearcher();
    //    获取教师信息
    public Map getAllWithPage(int[] ints);
    public int addIsWork(int teacherid);
    public int deleteIsWork(int teacherid);
    public ClassInfo getClassInfoByClassId(int classId);
}
