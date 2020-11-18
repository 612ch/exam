package com.qhit.handler;

import cn.hutool.core.util.PageUtil;
import com.qhit.mapper.ClassInfoMapper;
import com.qhit.pojo.ClassInfo;
import com.qhit.pojo.GradeInfo;
import com.qhit.pojo.TeacherInfo;
import com.qhit.service.ClassInfoService;
import com.qhit.service.GradeInfoService;
import com.qhit.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("class")
public class ClassInfoHandler {

    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private ClassInfoMapper classInfoMapper;
    @Autowired
    private GradeInfoService gradeInfoService;
    @Autowired
    private TeacherInfoService teacherInfoService;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author demon
     * @Date 9:40 2019/5/21
     * @Description 获取分页后的班级信息
     * @MethodName getAllClass
     * @Param [model, page]
     **/
    @RequestMapping("classList")
    public ModelAndView getAllClass(Model model, Integer page) {
        int currPage = 1;
        if (page != null) {
            currPage = page;
        }
        int[] ints = PageUtil.transToStartEnd(currPage, 11);
        Map map = classInfoService.getAllWithPage(ints);
        map.put("pageNow", currPage);
        model.addAttribute("classes", map);
        return new ModelAndView("admin/frame/classes");
    }

    /**
     * @return java.util.Map
     * @Author demon
     * @Date 7:58 2019/5/21
     * @Description 删除班级信息
     * @MethodName deleteGradeByID
     * @Param [cid]
     **/
    @RequestMapping("deleteClass")
    public @ResponseBody
    Map deleteGradeByID(int cid) {
        HashMap<Object, Object> map = new HashMap<>();
        int result = classInfoService.deleteByPrimaryKey(cid);
        if (result == 0) {
            map.put("state", 0);
            map.put("msg", "删除失败");
        } else if (result == -1) {
            map.put("state", -1);
            map.put("msg", "删除失败，改班级下有学生");
        } else {
            map.put("state", 1);
            map.put("msg", "删除成功");
        }
        return map;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author demon
     * @Date 7:59 2019/5/21
     * @Description 跳转到班级添加
     * @MethodName toAddClass
     * @Param []
     **/
    @RequestMapping("/toAddClass")
    public ModelAndView toAddClass() {
        ModelAndView mo = new ModelAndView();
        List<GradeInfo> gradeInfoList = gradeInfoService.getGradeList();
        List<TeacherInfo> teacherInfoList = teacherInfoService.getIsWorkTearcher();
        mo.addObject("gradeInfoList", gradeInfoList);
        mo.addObject("teacherInfoList", teacherInfoList);
        mo.setViewName("admin/frame/classedit");
        return mo;
    }

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author demon
     * @Date 7:59 2019/5/21
     * @Description 获取一个班级信息并跳转到班级修改
     * @MethodName toUpdateClass
     * @Param [cid]
     **/
    @RequestMapping("/toUpdateClass")
    public ModelAndView toUpdateClass(Integer cid) {
        ModelAndView mo = new ModelAndView();
        ClassInfo classInfo = classInfoService.selectByPrimaryKey(cid);
        List<GradeInfo> gradeInfoList = gradeInfoService.getGradeList();
        List<TeacherInfo> teacherInfoList = teacherInfoService.getIsWorkTearcher();
        mo.addObject("classInfo", classInfo);
        mo.addObject("gradeInfoList", gradeInfoList);
        mo.addObject("teacherInfoList", teacherInfoList);
        mo.setViewName("admin/frame/classedit");
        return mo;
    }

    /**
     * @return java.lang.String
     * @Author demon
     * @Date 7:59 2019/5/21
     * @Description 添加班级
     * @MethodName addClass
     * @Param [classInfo, grade, teacher]
     **/
    @RequestMapping("/addClass")
    public String addClass(ClassInfo classInfo, GradeInfo grade, TeacherInfo teacher) {
        classInfo.setGrade(grade);
        classInfo.setTeacher(teacher);
        teacherInfoService.addIsWork(teacher.getTeacherId());
        int rs = classInfoService.insert(classInfo);
        if (rs == 0) {
            return "/class/toAddClass";
        } else {
            return "redirect:/class/classList.action";
        }
    }

    /**
     * @return java.lang.String
     * @Author demon
     * @Date 8:00 2019/5/21
     * @Description 修改班级
     * @MethodName updateClass
     * @Param [classInfo, grade, teacher]
     **/
    @RequestMapping(value = "/updateClass")
    public String updateClass(ClassInfo classInfo, GradeInfo grade, TeacherInfo teacher) {
        classInfo.setGrade(grade);
        classInfo.setTeacher(teacher);
        int rs = classInfoService.updateClass(classInfo);
        if (rs == 0) {
            return "redirect:/class/classList.action";
        } else {
            return "redirect:/class/classList.action";
        }
    }


    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @Author demon
     * @Date 9:30 2019/5/28
     * @Description 获取各班级学生总数
     * @MethodName preStudentCount
     * @Param []
     **/
    @RequestMapping("/preStudentCount")
    public ModelAndView preStudentCount() {

        ModelAndView model = new ModelAndView();
        //获取年级信息
//        List<GradeInfo> grades = gradeInfoService.getGradeList();
        model.setViewName("admin/charts/studentCount");
//        model.addObject("grades", grades);
        return model;
    }

    /**
     * @return java.util.List<com.qhit.pojo.ClassInfo>
     * @Author demon
     * @Date 19:32 2019/5/21
     * @Description 通过年级编号获取该编号下的所有班级
     * @MethodName getClassByGradeId
     * @Param [gradeId]
     **/
    @RequestMapping("/getClassByGradeId")
    public @ResponseBody
    List<ClassInfo> getClassByGradeId(int gradeId) {
        return classInfoService.getClassByGradeId(gradeId);
    }

    /**
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @Author demon
     * @Date 11:30 2019/5/29
     * @Description 获取各个班级的学生人数
     * @MethodName getStudentCountForClass
     * @Param [gradeId]
     **/
    @RequestMapping("/stuCount")
    public @ResponseBody
    List<Map<String, Object>> getStudentCountForClass(Integer gradeId) throws IOException {
        List<Map<String, Object>> studentCountForClass = classInfoMapper.getStudentCountForClass();
        return studentCountForClass;
    }


}
