package com.qhit.handler;

import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.ExamHistoryPaper;
import com.qhit.pojo.ExamPlanInfo;
import com.qhit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/examPlanInfo")
public class ExamPlanInfoHandler {
    @Autowired
    private ExamPlanInfoService examPlanInfoService;
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private ExamPaperInfoService examPaperInfoService;
    @Autowired
    private ExamHistoryInfoService examHistoryInfoService;
    public ExamPlanInfoService getExamPlanInfoService() {
        return examPlanInfoService;
    }

    public void setExamPlanInfoService(ExamPlanInfoService examPlanInfoService) {
        this.examPlanInfoService = examPlanInfoService;
    }
//    查看所有ExamPlanInfo
    @RequestMapping("/getAll")
    public ModelAndView getAll(Model model){
        model.addAttribute("examPlans",examPlanInfoService.getAll());
        return new ModelAndView("admin/frame/examPlans");
    }
//    修改ExamPlanInfo
    @RequestMapping("/updateExamPlanInfo")
    public @ResponseBody String updateExamPlanInfo(ExamPlanInfo examPlanInfo){
        examPlanInfoService.updateExamPlanInfo(examPlanInfo);
        return "redirect:getAll";
    }



//     此处可能出问题
//    通过examPlanId查询一个ExamPlanInfo
    @RequestMapping("/getExamPlanByExamPlanId")
    public ModelAndView getExamPlanByExamPlanId(Integer examPlanId){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("examPlan",examPlanInfoService.getExamPlanIdByexamPlanId(examPlanId)
        );
        modelAndView.addObject("classes",classInfoService.getAllClassInfo(null));
        modelAndView.addObject("courses",courseInfoService.getAllCourseInfo());
        modelAndView.addObject("examPapers",examPaperInfoService.getAllExamPaperInfo());
        modelAndView.setViewName("admin/frame/examPlanedit");
        return modelAndView;
    }

//    添加ExamPlanInfo
    @RequestMapping("/addExamPlanInfo")
    public @ResponseBody String addExamPlanInfo(ExamPlanInfo examPlanInfo){
        examPlanInfoService.addExamPlanInfo(examPlanInfo);
        return "redirect:getAll";
    }
//    删除ExamPlanInfo
    @RequestMapping("/delExamPlanInfo")
    public @ResponseBody String delExamPlanInfo(int ID){
        examPlanInfoService.delExamPlanInfo(ID);
        return "redirect:getAll";
    }

    @RequestMapping("/toAdd")
    public ModelAndView toAddAdd(Model model){
        model.addAttribute("classes",classInfoService.getAllClassInfo(null));
        model.addAttribute("courses",courseInfoService.getAllCourseInfo());
        model.addAttribute("examPapers",examPaperInfoService.getAllExamPaperInfo());
        return new ModelAndView("admin/frame/examPlanedit");
    }
    /*
     * @Author demon
     * @Date 23:12 2019/5/27
     * @Description 查看即将要考试
     * @MethodName getStudentWillExam
     * @Param [classId, gradeId, studentId]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/willexams")
    public ModelAndView getStudentWillExam(Integer classId,Integer gradeId,Integer studentId){
        ModelAndView mo=new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("classId", classId);
        map.put("gradeId", gradeId);
        List<ExamPlanInfo> examPlans = examPlanInfoService.getStudentWillExam(map);
        mo.addObject("examPlans", examPlans);
        mo.addObject("gradeId", gradeId);
        mo.setViewName("/reception/examCenter");
        return mo;
    }
    /*
     * @Author demon
     * @Date 0:41 2019/5/28
     * @Description 获取考试历史
     * @MethodName getExamHistoryInfo
     * @Param [studentId]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/history")
    public ModelAndView getExamHistoryInfo(Integer studentId) {
        ModelAndView mo=new ModelAndView();
        List<ExamHistoryPaper> ehps = examHistoryInfoService.getExamHistoryByStudentId(studentId);
        mo.addObject("ehps", ehps);
        mo.setViewName("/reception/examHistory");
        return mo;
    }
}
