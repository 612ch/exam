package com.qhit.mapper;

import com.qhit.pojo.ExamPaperInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ExamPaperInfoMapper {
    int deleteByPrimaryKey(Integer exampaperid);

    int insert(ExamPaperInfo record);

    int insertSelective(ExamPaperInfo record);

    ExamPaperInfo selectByPrimaryKey(Integer exampaperid);

    int updateByPrimaryKeySelective(ExamPaperInfo record);

    int updateByPrimaryKey(ExamPaperInfo record);
    
    /*
     * @Author demon
     * @Date 10:20 2019/5/21
     * @Description 获取所有试卷
     * @MethodName getAllExamPaper
     * @Param [map]
     * @return java.util.List<com.qhit.pojo.ExamPaperInfo>
     **/
    List<ExamPaperInfo> getAllExamPaper(Map map);
    /*
     * @Author demon
     * @Date 10:31 2019/5/21
     * @Description 获取所有试卷数量
     * @MethodName getCount
     * @Param []
     * @return int
     **/
    int getCount();
//    查看试卷
    public List<ExamPaperInfo> getAllExamPaperInfo();

    public int isUpdateExamPaperSubjects(Map<String, Object> map);

    public int isUpdateExamPaperScore(Map<String, Object> map);

    public int isAddExamPaper(ExamPaperInfo examPaper);


}