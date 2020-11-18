package com.qhit.service;

import cn.hutool.core.util.PageUtil;
import com.qhit.pojo.ClassInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ClassInfoService {
    List<ClassInfo> getAllClassInfo(Map map);

    int deleteByPrimaryKey(int classid);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    ClassInfo selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);
    int updateClass(ClassInfo classInfo);
    /*
     * @Author demon
     * @Date 10:33 2019/5/21
     * @Description 获取分页后的所有信息
     * @MethodName getAllWithPage
     * @Param [ints]
     * @return java.util.Map
     **/
    Map getAllWithPage(int[] ints);


    List<ClassInfo> getClassByGradeId(int gradeId);

}
