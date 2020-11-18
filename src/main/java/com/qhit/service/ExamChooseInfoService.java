package com.qhit.service;

import com.qhit.pojo.ExamChooseInfo;


import java.util.List;
import java.util.Map;

public interface ExamChooseInfoService {
    List<ExamChooseInfo> getChooseInfoWithExamSubject(Map<String, Object> map);

    List<ExamChooseInfo> getChooseInfoWithSumScore(Map<String, Object> choosedMap);

    ExamChooseInfo getChooseWithIds(Map<String, Object> map);

    public int updateChooseWithIds(ExamChooseInfo examChoose);

    public int addChoose(Map<String, Object> map);
}
