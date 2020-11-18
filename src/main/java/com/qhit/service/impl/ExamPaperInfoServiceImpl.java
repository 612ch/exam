package com.qhit.service.impl;

import cn.hutool.core.util.PageUtil;
import com.qhit.mapper.ExamPaperInfoMapper;
import com.qhit.pojo.ExamPaperInfo;
import com.qhit.service.ExamPaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExamPaperInfoServiceImpl
 * @Descriotion TODO
 * @Author demon
 * @Date 2019/5/21 9:53
 * @Version 1.0
 **/
@Service
public class ExamPaperInfoServiceImpl implements ExamPaperInfoService {
    @Autowired
    private ExamPaperInfoMapper examPaperInfoMapper;
    @Override
    public Map getAllWithPage(int[] ints) {
        int classCount = examPaperInfoMapper.getCount();//获取总数
        Map hashMap = new HashMap();
        hashMap.put("start",ints[0]);
        hashMap.put("end",ints[1]);
        hashMap.put("result",examPaperInfoMapper.getAllExamPaper(hashMap));
        hashMap.put("classCount",classCount);
        hashMap.put("pageCount", PageUtil.totalPage(classCount,12));
        return hashMap;
    }

    @Override
    public List<ExamPaperInfo> getAllExamPaperInfo() {
        return examPaperInfoMapper.getAllExamPaperInfo();
    }

    @Override
    public ExamPaperInfo getExamPaper(int examPaperId) {
        return examPaperInfoMapper.selectByPrimaryKey(examPaperId);
    }

    @Override
    public int addPaper(ExamPaperInfo examPaperInfo) {
        return examPaperInfoMapper.insertSelective(examPaperInfo);
    }

    @Override
    public int updatepaper(ExamPaperInfo examPaperInfo) {
        return examPaperInfoMapper.updateByPrimaryKeySelective(examPaperInfo);
    }

    @Override
    public int deletePaper(Integer examPaperInfoId) {
        return examPaperInfoMapper.deleteByPrimaryKey(examPaperInfoId);
    }

    @Override
    public int isUpdateExamPaperSubjects(Map<String, Object> map) {
        return examPaperInfoMapper.isUpdateExamPaperSubjects(map);
    }

    @Override
    public int isUpdateExamPaperScore(Map<String, Object> map) {
        return examPaperInfoMapper.isUpdateExamPaperScore(map);
    }

    @Override
    public int isAddExamPaper(ExamPaperInfo examPaper) {
        return examPaperInfoMapper.isAddExamPaper(examPaper);
    }
}
