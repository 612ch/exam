package com.qhit.handler;

import cn.hutool.core.util.PageUtil;
import com.qhit.pojo.ExamPaperInfo;
import com.qhit.pojo.GradeInfo;
import com.qhit.service.ClassInfoService;
import com.qhit.service.ExamPaperInfoService;
import com.qhit.service.GradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExamPaperInfoHandler
 * @Descriotion TODO
 * @Author demon
 * @Date 2019/5/21 9:51
 * @Version 1.0
 **/
@Controller
@RequestMapping("/examPaper")
public class ExamPaperInfoHandler {
    @Autowired
    private ExamPaperInfoService examPaperInfoService;

    @Autowired
    private GradeInfoService gradeInfoService;

    /*
     * @Author demon
     * @Date 9:18 2019/5/25
     * @Description 获取所有试题
     * @MethodName getAllClass
     * @Param [model, page]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/getAll")
    public ModelAndView getAllClass(Model model, Integer page) {
        int currPage = 1;
        if (page != null) {
            currPage = page;
        }
        int[] ints = PageUtil.transToStartEnd(currPage, 12);
        Map map = examPaperInfoService.getAllWithPage(ints);
        map.put("pageNow", currPage);
        model.addAttribute("exmapapers", map);
        return new ModelAndView("admin/frame/examPapers");
    }

    /*
     * @Author demon
     * @Date 10:13 2019/5/28
     * @Description 跳转到添加试卷页面
     * @MethodName toAdd
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/toAdd")
    public ModelAndView toAdd() {
        ModelAndView mo = new ModelAndView();
        mo.addObject("grades", gradeInfoService.getGradeList());
        mo.setViewName("/admin/frame/examPaperedit");
        return mo;
    }

    /*
     * @Author demon
     * @Date 10:28 2019/5/28
     * @Description 跳转到修改页面
     * @MethodName toUpdate
     * @Param [examPaperId]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/toUpdate")
    public ModelAndView toUpdate(Integer examPaperId) {
        ModelAndView mo = new ModelAndView();
        mo.addObject("grades", gradeInfoService.getGradeList());
        ExamPaperInfo paper = examPaperInfoService.getExamPaper(examPaperId);
        mo.setViewName("/admin/frame/examPaperedit");
        mo.addObject("examPaper", paper);
        return mo;
    }
    /*
     * @Author demon
     * @Date 10:51 2019/5/28
     * @Description 添加试卷
     * @MethodName add
     * @Param [examPaperInfo]
     * @return java.lang.String
     **/
    @RequestMapping("/add")
    public @ResponseBody String add(ExamPaperInfo examPaperInfo){
        int rs= examPaperInfoService.addPaper(examPaperInfo);
        return "redirect:gradeList.action";
    }
    /*
     * @Author demon
     * @Date 10:51 2019/5/28
     * @Description 修改试卷
     * @MethodName update
     * @Param [examPaperInfo]
     * @return java.lang.String
     **/
    @RequestMapping("/update")
    public @ResponseBody String update(ExamPaperInfo examPaperInfo){
        int rs= examPaperInfoService.updatepaper(examPaperInfo);
        return "redirect:gradeList.action";
    }
    /*
     * @Author demon
     * @Date 10:51 2019/5/28
     * @Description 删除试卷
     * @MethodName delete
     * @Param [examPaperInfo]
     * @return java.lang.String
     **/
    @RequestMapping("/delete")
    public @ResponseBody String delete(Integer ID){
        int rs= examPaperInfoService.deletePaper(ID);
        return "redirect:gradeList.action";
    }
    @RequestMapping("/addSubject")
    public @ResponseBody Map addSubject(Integer examPaperId){
        HashMap map=new HashMap();
        return map;
    }

}
