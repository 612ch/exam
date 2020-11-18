package com.qhit.handler;

import com.qhit.pojo.ExamChooseInfo;
import com.qhit.pojo.ExamPaperInfo;
import com.qhit.pojo.ExamSubjectMiddleInfo;
import com.qhit.service.ExamChooseInfoService;
import com.qhit.service.ExamHistoryInfoService;
import com.qhit.service.ExamPaperInfoService;
import com.qhit.service.ExamSubjectMiddleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//所有考试记录
@Controller
@RequestMapping("/Exam")
public class ExamHistoryInfoHandler {

    @Autowired
    private ExamHistoryInfoService examHistoryInfoService;
    @Autowired
    ExamSubjectMiddleInfoService examSubjectMiddleInfoService;
    @Autowired
    ExamChooseInfoService examChooseInfoService;
    @Autowired
    ExamPaperInfoService examPaperInfoService;

    @RequestMapping("/getExamList")
    public ModelAndView getExamList(Model model) {
        model.addAttribute("historys", examHistoryInfoService.getExamList());
        return new ModelAndView("/admin/frame/examHistorys");
    }

    @RequestMapping(value = "/review")
    public ModelAndView reViewExam(Integer examPaperTime,Integer studentId, Integer examPaperId, Integer score, String examPaperName, String studentName) {
        ModelAndView mo = new ModelAndView();
        mo.addObject("examPaperName", examPaperName);
        mo.addObject("examPaperTime", examPaperTime);
        mo.addObject("score", score);
        mo.addObject("studentName", studentName);
        ExamPaperInfo examPaper = new ExamPaperInfo();
        ExamSubjectMiddleInfo esm = new ExamSubjectMiddleInfo();
        examPaper.setExamPaperId(examPaperId);
        esm.setExamPaper(examPaper);
        List<ExamSubjectMiddleInfo> esms = examSubjectMiddleInfoService.getExamPaperWithSubject(esm);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("studentId", studentId);
        map.put("examPaperId", examPaperId);
        List<ExamChooseInfo> reviews = examChooseInfoService.getChooseInfoWithExamSubject(map);
        //设置试卷名称、试卷总分
        mo.addObject("examPaperName", examPaperName);
        mo.addObject("score", score);
        mo.addObject("examPaperTime",examPaperTime);
        mo.setViewName("reception/review");
        mo.addObject("views", reviews);
        mo.addObject("esms", esms);
        mo.addObject("ExamedPaper", examPaperInfoService.getExamPaper(examPaperId));

        return mo;
    }

}



