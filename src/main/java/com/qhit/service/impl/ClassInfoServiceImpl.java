package com.qhit.service.impl;

import cn.hutool.core.util.PageUtil;
import com.qhit.mapper.ClassInfoMapper;
import com.qhit.mapper.StudentInfoMapper;
import com.qhit.mapper.TeacherInfoMapper;
import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.StudentInfo;
import com.qhit.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private TeacherInfoMapper teacherInfoMapper;
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    public List<ClassInfo> getAllClassInfo(Map map) {
        return classInfoMapper.getAllClassInfo(map);
    }


    public int deleteByPrimaryKey(int classid) {
        int rs=0;
        List<StudentInfo> studentInfos =studentInfoMapper.selectByClassId(classid);
        System.out.println(studentInfos.size());
        if (studentInfos.size()==0){
            ClassInfo classinfo = classInfoMapper.getClassByClassId(classid);
            teacherInfoMapper.deleteIsWork(classinfo.getTeacher().getTeacherId());
            rs= classInfoMapper.deleteByPrimaryKey(classid);
        }else {
            return -1;
        }
        return rs;
    }

    @Override
    public int insert(ClassInfo record) {
        return classInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(ClassInfo record) {
        return classInfoMapper.insertSelective(record);
    }

    @Override
    public ClassInfo selectByPrimaryKey(Integer classid) {
        return classInfoMapper.selectByPrimaryKey(classid);
    }

    @Override
    public int updateByPrimaryKeySelective(ClassInfo record) {
        return classInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ClassInfo record) {
        return classInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateClass(ClassInfo classInfo) {
        return classInfoMapper.updateClass(classInfo);
    }

    @Override
    public Map getAllWithPage(int[] ints) {
        int classCount = classInfoMapper.getCount();//获取总教师数
        Map hashMap = new HashMap();
        hashMap.put("start",ints[0]);
        hashMap.put("end",ints[1]);
        hashMap.put("result",classInfoMapper.getAllClassInfo(hashMap));
        hashMap.put("classCount",classCount);
        hashMap.put("pageCount", PageUtil.totalPage(classCount,11));
        return hashMap;
    }
    @Override
    public List<ClassInfo> getClassByGradeId(int gradeId) {
        HashMap map=new HashMap();
        map.put("gradeId",gradeId);
        List<ClassInfo> classlist = classInfoMapper.getClassSelective(map);
        return classlist;
    }



}
