package com.qhit.service;

import com.qhit.pojo.ExamHistoryInfo;
import com.qhit.pojo.ExamHistoryPaper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ExamHistoryInfoService {

    List<ExamHistoryInfo> getExamList();
    /*
     * @Author demon
     * @Date 0:04 2019/5/28
     * @Description 通过学生编号获取考试历史信息
     * @MethodName getExamHistoryByStudentId
     * @Param [studentId]
     * @return java.util.List<com.qhit.pojo.ExamHistoryInfo>
     **/
    public List<ExamHistoryPaper> getExamHistoryByStudentId(int studentId);

    int getHistoryInfoWithIds(Map<String, Object> map);

    int isAddExamHistory(Map<String, Object> map);
}
