package com.qhit.service.impl;


import com.qhit.mapper.ExamChooseInfoMapper;
import com.qhit.pojo.ExamChooseInfo;
import com.qhit.pojo.ExamSubjectMiddleInfo;

import com.qhit.service.ExamChooseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamChooseInfoServiceImpl implements ExamChooseInfoService {
    @Autowired
    private ExamChooseInfoMapper examChooseInfoMapper;


    @Override
    public List<ExamChooseInfo> getChooseInfoWithExamSubject(Map<String, Object> map) {
        return examChooseInfoMapper.getChooseInfoWithExamSubject(map);
    }

    @Override
    public List<ExamChooseInfo> getChooseInfoWithSumScore(Map<String, Object> choosedMap) {
        return examChooseInfoMapper.getChooseInfoWithExamSubject(choosedMap);
    }

    @Override
    public ExamChooseInfo getChooseWithIds(Map<String, Object> map) {
        return examChooseInfoMapper.getChooseWithIds(map);
    }

    @Override
    public int updateChooseWithIds(ExamChooseInfo examChoose) {
        return examChooseInfoMapper.updateChooseWithIds(examChoose);
    }

    @Override
    public int addChoose(Map<String, Object> map) {
        return examChooseInfoMapper.addChoose(map);
    }
}
