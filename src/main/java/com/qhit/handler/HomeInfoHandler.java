package com.qhit.handler;

import com.qhit.mapper.ExamPaperInfoMapper;
import com.qhit.mapper.TeacherInfoMapper;
import com.qhit.service.StudentInfoService;
import com.qhit.service.SubjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName HomeInfoHandler
 * @Descriotion TODO
 * @Author demon
 * @Date 2019/5/28 23:00
 * @Version 1.0
 **/
@Controller
public class HomeInfoHandler {
    @Autowired
    ExamPaperInfoMapper examPaperInfoService;
    @Autowired
    SubjectInfoService subjectInfoService;
    @Autowired
    TeacherInfoMapper teacherInfoService;
    @Autowired
    StudentInfoService studentInfoService;

    @RequestMapping("/homeInfo")
    public void homeInfo(HttpServletResponse response) throws IOException {

        int examPaperTotal = examPaperInfoService.getCount();
        int subjectTotal = subjectInfoService.getTotalCount();
        int teacherTotal = teacherInfoService.getCount();
        int studentTotal = studentInfoService.getTotalCount();

        String json = "{\"examPaperTotal\":"+examPaperTotal+", " +
                "\"subjectTotal\":"+subjectTotal+", " +
                "\"teacherTotal\":"+teacherTotal+", " +
                "\"studentTotal\":"+studentTotal+"}";

        response.getWriter().print(json);
    }
}
