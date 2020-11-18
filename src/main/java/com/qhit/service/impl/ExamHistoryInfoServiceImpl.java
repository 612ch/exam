package com.qhit.service.impl;

import com.qhit.mapper.ExamHistoryInfoMapper;
import com.qhit.pojo.ExamHistoryInfo;
import com.qhit.pojo.ExamHistoryPaper;
import com.qhit.service.ExamHistoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamHistoryInfoServiceImpl implements ExamHistoryInfoService {
    @Autowired
    private ExamHistoryInfoMapper examHistoryInfoMapper;

    @Override
    public List<ExamHistoryInfo> getExamList() {
        return examHistoryInfoMapper.getExamList();
    }

    @Override
    public List<ExamHistoryPaper> getExamHistoryByStudentId(int studentId) {
        return examHistoryInfoMapper.getExamHistoryByStudentId(studentId);
    }

    @Override
    public int getHistoryInfoWithIds(Map<String, Object> map) {
        return examHistoryInfoMapper.getHistoryInfoWithIds(map);
    }

    @Override
    public int isAddExamHistory(Map<String, Object> map) {
        return examHistoryInfoMapper.isAddExamHistory(map);
    }
}
