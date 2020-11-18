package com.qhit.handler;

import com.qhit.pojo.GradeInfo;
import com.qhit.service.ClassInfoService;
import com.qhit.service.GradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grade")
public class GradeInfoHandler {
    @Autowired
    private GradeInfoService gradeInfoService;
    @Autowired
    private ClassInfoService classInfoService;
    @RequestMapping("/gradeList")
    public ModelAndView getGradeList(){
        ModelAndView mo=new ModelAndView();
        mo.addObject("grades",gradeInfoService.getGradeList());
        mo.setViewName("admin/frame/grades");
        return mo;
    }

    @RequestMapping("/selectById")
    public ModelAndView selectById( int gradeId){
        ModelAndView mo=new ModelAndView();
        mo.addObject("grade",gradeInfoService.selectById(gradeId));
        mo.setViewName("admin/frame/gradeedit");
        return mo;
    }
    /*@RequestMapping("deleteGrade")
    public String deleteGradeByID(int gid){
        int result=gradeInfoService.deleteByPrimaryKey(gid);
        if(result<1){
            return "/error";
        }
        return "/grade/gradeList";
    }*/

    @RequestMapping("/add")
    public @ResponseBody String add(GradeInfo gradeInfo){
        gradeInfoService.add(gradeInfo);
        return "redirect:gradeList.action";
    }

    @RequestMapping("/updateGradeInfo")
    public @ResponseBody String updateGradeInfo(GradeInfo gradeInfo){
        gradeInfoService.updateGradeInfo(gradeInfo);
        return "redirect:gradeList.action";
    }
    @RequestMapping("/deleteGradeInfo")
    public @ResponseBody String deleteGradeInfo(int ID){
        gradeInfoService.deleteGradeInfo(ID);
        return "redirect:gradeList.action";
    }



}
