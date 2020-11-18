package com.qhit.handler;



import com.qhit.pojo.CourseInfo;
import com.qhit.service.CourseInfoService;
import com.qhit.service.GradeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/course")
public class CourseInfoHandler {

    @Autowired
    private CourseInfoService courseInfoService;
    @Autowired
    private GradeInfoService gradeInfoService;
@RequestMapping("/courseList")
    public ModelAndView getAllCourses(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("courselist",courseInfoService.getAllCourseInfo());

        modelAndView.setViewName("admin/frame/courses");
        return modelAndView;
    }

    @RequestMapping("/courseadd")
    public @ResponseBody String courseadd(CourseInfo courseInfo){
        courseInfoService.courseadd(courseInfo);
//        model.addAttribute("course",gradeInfoService.getGradeList());
        return "redirect:courseList.action";
    }

    @RequestMapping("/toAdd")
    public ModelAndView getGradeList(){
        ModelAndView mo=new ModelAndView();
        mo.addObject("grades",gradeInfoService.getGradeList());
//        mo.addObject("course",courseInfoService.getAllCourseInfo());
        mo.setViewName("admin/frame/courseedit");
        return mo;
    }

    @RequestMapping("/selectByPrimaryKey")
    public ModelAndView selectByPrimaryKey(Integer courseId){
        ModelAndView mo=new ModelAndView();
        mo.addObject("course",courseInfoService.selectByPrimaryKey(courseId));
        mo.addObject("grades",gradeInfoService.getGradeList());
        mo.setViewName("admin/frame/courseedit");
        return mo;
    }

    @RequestMapping("/updateCourseInfo")
    public @ResponseBody String updateCourseInfo(CourseInfo courseInfo){
        courseInfoService.updateCourseInfo(courseInfo);
        return "redirect:courseList.action";
    }

    @RequestMapping("/deleteCourseInfo")
    public @ResponseBody String deleteCourseInfo(int ID){
        courseInfoService.deleteCourseInfo(ID);
        return "redirect:courseList.action";
    }
}
