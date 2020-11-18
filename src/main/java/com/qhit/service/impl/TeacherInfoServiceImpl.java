package com.qhit.service.impl;

import cn.hutool.core.util.PageUtil;
import com.qhit.mapper.TeacherInfoMapper;
import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.TeacherInfo;
import com.qhit.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {
    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    public TeacherInfoMapper getTeacherInfoMapper() {
        return teacherInfoMapper;
    }

    public void setTeacherInfoMapper(TeacherInfoMapper teacherInfoMapper) {
        this.teacherInfoMapper = teacherInfoMapper;
    }

    @Override
    public List<TeacherInfo> getAll(HashMap hashMap) {
        return teacherInfoMapper.getAll(hashMap);
    }


    @Override
    public TeacherInfo getTeacherInfoByteacherId(int teacherId) {
        return teacherInfoMapper.getTeacherInfoByteacherId(teacherId);
    }

    @Override
    public int updateTeacherInfo(TeacherInfo teacherInfo) {
        return teacherInfoMapper.updateTeacherInfo(teacherInfo);
    }

    @Override
    public int addTeacherInfo(TeacherInfo teacherInfo) {
        return teacherInfoMapper.addTeacherInfo(teacherInfo);
    }

    @Override
    public int deleteTeacherInfo(int teacherId) {
        return teacherInfoMapper.deleteTeacherInfo(teacherId);
    }

    @Override
    public List<TeacherInfo> getIsWorkTearcher() {
        return teacherInfoMapper.getIsWorkTearcher();
    }

    @Override
    public Map getAllWithPage(int[] ints) {
        int teacherCount = teacherInfoMapper.getCount();//获取总教师数
        HashMap hashMap = new HashMap();
        hashMap.put("start",ints[0]);
        hashMap.put("end",ints[1]);
        hashMap.put("result",teacherInfoMapper.getAll(hashMap));
        hashMap.put("teacherCount",teacherCount);
        hashMap.put("pageCount", PageUtil.totalPage(teacherCount,12));
        return hashMap;
    }

    @Override
    public ClassInfo getClassInfoByClassId(int classId) {
        return teacherInfoMapper.getClassInfoByClassId(classId);
    }

    @Override
    public int addIsWork(int teacherid) {
        return 0;
    }

    @Override
    public int deleteIsWork(int teacherid) {
        return 0;
    }




}
