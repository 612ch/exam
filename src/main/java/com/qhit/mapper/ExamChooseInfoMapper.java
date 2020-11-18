package com.qhit.mapper;

import com.qhit.pojo.ExamChooseInfo;
import com.qhit.pojo.ExamSubjectMiddleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExamChooseInfoMapper {
    int deleteByPrimaryKey(Integer chooseid);

    int insert(ExamChooseInfo record);

    int insertSelective(ExamChooseInfo record);

    ExamChooseInfo selectByPrimaryKey(Integer chooseid);

    int updateByPrimaryKeySelective(ExamChooseInfo record);

    int updateByPrimaryKey(ExamChooseInfo record);

    List<ExamChooseInfo> getChooseInfoWithExamSubject(Map<String, Object> map);

    ExamChooseInfo getChooseWithIds(Map<String, Object> map);

    public int updateChooseWithIds(ExamChooseInfo examChoose);

    public int addChoose(Map<String, Object> map);

}