package com.qhit.service;

import com.qhit.pojo.ExamPaperInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ExamPaperInfoService
 * @Descriotion TODO
 * @Author demon
 * @Date 2019/5/21 9:52
 * @Version 1.0
 **/
public interface ExamPaperInfoService {
    /*
     * @Author demon
     * @Date 10:33 2019/5/21
     * @Description 获取试卷分页后的信息
     * @MethodName getAllWithPage
     * @Param [ints]
     * @return java.util.Map
     **/
    Map getAllWithPage(int[] ints);
    //    查看试卷
    public List<ExamPaperInfo> getAllExamPaperInfo();

    /*
     * @Author demon
     * @Date 10:32 2019/5/28
     * @Description 通过试卷Id获取试卷信息
     * @MethodName getExamPaper
     * @Param [examPaperId]
     * @return com.qhit.pojo.ExamPaperInfo
     **/
    public ExamPaperInfo getExamPaper(int examPaperId);

    int addPaper(ExamPaperInfo examPaperInfo);

    int updatepaper(ExamPaperInfo examPaperInfo);

    int deletePaper(Integer examPaperInfoId);

    public int isUpdateExamPaperSubjects(Map<String, Object> map);

    public int isUpdateExamPaperScore(Map<String, Object> map);

    public int isAddExamPaper(ExamPaperInfo examPaper);

}
