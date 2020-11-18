package com.qhit.service.impl;

import com.qhit.mapper.GradeInfoMapper;
import com.qhit.pojo.GradeInfo;
import com.qhit.service.GradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class GradeInfoServiceImpl implements GradeInfoService {
    @Autowired
    public GradeInfoMapper gradeInfoMapper;
    @Override
    public List<GradeInfo> getGradeList() {
        return gradeInfoMapper.SelectSelective(null);
    }

    @Override
    public GradeInfo selectById(Integer gradeInfoid) {
        return gradeInfoMapper.selectById(gradeInfoid);
    }

    @Override
    public int deleteByPrimaryKey(Integer gradeid) {
        return gradeInfoMapper.deleteByPrimaryKey(gradeid);
    }

    @Override
    public int insert(GradeInfo record) {
        return gradeInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(GradeInfo record) {
        return gradeInfoMapper.insertSelective(record);
    }

    @Override
    public GradeInfo selectByPrimaryKey(Integer gradeid) {
        return gradeInfoMapper.selectByPrimaryKey(gradeid);
    }

    @Override
    public int updateByPrimaryKeySelective(GradeInfo record) {
        return gradeInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GradeInfo record) {
        return gradeInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int add(GradeInfo gradeInfo) {
        return gradeInfoMapper.add(gradeInfo);
    }

    @Override
    public int updateGradeInfo(GradeInfo gradeInfo) {
        return gradeInfoMapper.updateGradeInfo(gradeInfo);
    }

    @Override
    public int deleteGradeInfo(int id) {
        return gradeInfoMapper.deleteGradeInfo(id);
    }
}
