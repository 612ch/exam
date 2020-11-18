package com.qhit.handler;

import cn.hutool.core.util.PageUtil;
import com.qhit.pojo.*;
import com.qhit.service.*;
import com.qhit.util.SubjectImportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/subject")
public class SubjectInfoHandler {

    @Autowired
    private SubjectInfoService subjectInfoService;
    @Autowired
    private ExamPaperInfoService examPaperInfoService;
    @Autowired
    private ExamSubjectMiddleInfoService esmService;
    @Autowired
    private GradeInfoService gradeInfoService;
    @Autowired
    private CourseInfoService courseInfoService;

    @RequestMapping("/subjectList")
    public ModelAndView subjectList(Integer pageNow){
        if(null==pageNow){
            pageNow=1;
        }
        int[] ints = PageUtil.transToStartEnd(pageNow,11);
        ModelAndView mo=new ModelAndView();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("start",ints[0]);
        //分页查询
        mo.addObject("subjects",subjectInfoService.SelectSelective(hashMap));
        //总条数
        mo.addObject("totalCount",subjectInfoService.getTotalCount());
        //当前页面
        mo.addObject("pageNow",pageNow);
        mo.setViewName("admin/frame/subjects");
        return mo;
    }


    /*
    * 添加时，所需要的科目和年级
    * */
    @RequestMapping("/getCoursesAndGrades")
    public ModelAndView getCoursesAndGrades(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("courses",subjectInfoService.getCourseList(null));
        modelAndView.addObject("grades",subjectInfoService.getGradeList(null));
        modelAndView.setViewName("admin/frame/subject-test");
        return modelAndView;
    }
    /*
    * 添加试题
    * */
    @RequestMapping(value="/addSubject", method=RequestMethod.POST)
    public void addSubject(SubjectInfo subject, HttpServletResponse response) throws IOException {
        if(subject != null){
            subject.setSubjectName(trimChar(subject.getSubjectName()));
            subject.setRightResult(trimChar(subject.getRightResult()));
            subject.setOptionA(trimChar(subject.getOptionA()));
            subject.setOptionB(trimChar(subject.getOptionB()));
            subject.setOptionC(trimChar(subject.getOptionC()));
            subject.setOptionD(trimChar(subject.getOptionD()));
        }
        int row = subjectInfoService.isAddSubject(subject);

        response.getWriter().print("试题添加成功!");
    }
    /*
    * 删除之前先查询examchooseinfo
    * 删除试题
    * */
    @RequestMapping("/delSubject")
    public @ResponseBody  String delSubject(Integer ID){
        if(subjectInfoService.isDelSubject(ID).isEmpty()){
            subjectInfoService.deleteByPrimaryKey(ID);
        }else{
            HashMap<Object, Object> map = new HashMap<>();
            map.put("msg","删除失败！");
        }

        return "redirect:subjectList";
    }

    /*
    * 导入试题
    * */
    @RequestMapping("/initImport")
    public ModelAndView initImportExcel() {
        ModelAndView model = new ModelAndView("admin/frame/importSubject");
        //获取所有科目
        model.addObject("courses",subjectInfoService.getCourseList(null));
        //获取所有年级
        model.addObject("grades",subjectInfoService.getGradeList(null));
        //获取所有试卷名称
        model.addObject("examPapers", examPaperInfoService.getAllExamPaperInfo());
        return model;
    }
    /*
    * 预修改
    * */
    @RequestMapping("/preSubjectUpdate")
    public ModelAndView preUpdate(Integer subjectId) {
        ModelAndView model = new ModelAndView();
        model.addObject("subject",subjectInfoService.getSubjectById(subjectId));
        model.setViewName("admin/frame/subject-test");
        return model;
    }
     /*
    * 修改
    * */
    @RequestMapping("/subjectUpdate")
    public @ResponseBody String subjectUpdate(SubjectInfo subjectInfo) {
        subjectInfoService.updateByPrimaryKeySelective(subjectInfo);
        return "redirect:subjectList";
    }
    /*
     * @Author demon
     * @Date 16:16 2019/5/29
     * @Description 获取所有试题
     * @MethodName getsubjects
     * @Param [subjectId, courseId, gradeId, startPage, pageShow, handAdd, examPaperId, session]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/subjects")
    public ModelAndView getsubjects(Integer subjectId,Integer courseId,Integer gradeId,Integer startPage, Integer pageShow,Integer handAdd,Integer examPaperId,
            HttpSession session) {

        ModelAndView model = new ModelAndView();
        model.setViewName("admin/frame/subjectslist");
         SubjectInfo subject=new SubjectInfo();

         CourseInfo course=new CourseInfo();
         GradeInfo grade=new GradeInfo();
        //条件处理
        if(pageShow==null) pageShow=10;
        if(startPage==null) startPage=1;
        if (subjectId != null) subject.setSubjectId(subjectId);
        if (courseId != null) course.setCourseId(courseId);
        if (gradeId != null) grade.setGradeId(gradeId);

        Map<String, Object> map = new HashMap<String, Object>();
        //计算当前查询起始数据索引
        int startIndex = (startPage-1) * pageShow;
        //map.put("subject", subject);
        map.put("startIndex", startIndex);
        map.put("pageShow", pageShow);
        List<SubjectInfo> subjects = subjectInfoService.getSubjects(map);
        model.addObject("subjects", subjects);

        //获取试题总量
        int subjectTotal = subjectInfoService.getTotalCount();
        //计算总页数
        int pageTotal = 1;
        if (subjectTotal % pageShow == 0)
            pageTotal = subjectTotal / pageShow;
        else
            pageTotal = subjectTotal / pageShow + 1;
        model.addObject("pageTotal", pageTotal);
        model.addObject("pageNow", startPage);

        //是否为需要进行手动添加试题到试卷而发起的请求
        if (handAdd != null && handAdd == 1) {
            model.addObject("handAdd", "1");
        }
        //如果是手动添加试题到试卷，则需要返回试卷编号, 且返回当前已经选择试题数量
        if (examPaperId != null) {
            model.addObject("examPaperId", examPaperId);
            List<String> ids = (List<String>) session.getAttribute("ids");
            if (ids == null) {
                model.addObject("choosed", 0);
            } else {
                model.addObject("choosed", ids.size());
            }
        }

        return model;
    }

    @RequestMapping("/dispatcherUpload")
    public ModelAndView dispatcherUpload(HttpServletRequest request,Integer division,Integer courseId,Integer gradeId,Integer examPaperId, String importOption, Integer examPaperEasy, String examPaperName, Integer examPaperTime, MultipartFile excel) {
        ModelAndView model = new ModelAndView("reception/suc");
        String savePath = "";

        try {
            /** 保存上传 excel 文件 */
            savePath = saveUploadFile(excel, request.getRealPath("/WEB-INF/upload"));

            /** 解析上传 excel 文件, 得到试题集合 */
            List<SubjectInfo> subjects = SubjectImportUtil.parseSubjectExcel(savePath, courseId, gradeId, division);

            /** 只添加试题 */
            if ("0".equals(importOption)) {
                Map<String, Object> subjectsMap = new HashMap<String, Object>();
                subjectsMap.put("subjects", subjects);

                importSubejctOnly(subjects, subjectsMap);
            }
            /** 添加试题到指定的已有试卷 */
            else if ("1".equals(importOption)) {
                dispatcherExamPaperAndSubject(subjects, examPaperId);
            }
            /** 添加试题到新建试卷 */
            else if ("2".equals(importOption)) {
                ExamPaperInfo examPaper=new ExamPaperInfo();
                /** 创建新试卷 */
                examPaper.setExamPaperName(examPaperName);
                examPaper.setExamPaperEasy(examPaperEasy);
                examPaper.setExamPaperTime(examPaperTime);
                GradeInfo grade=new GradeInfo();
                grade.setGradeId(gradeId);
                examPaper.setGrade(grade);
                examPaper.setDivision(division);
                int row = examPaperInfoService.isAddExamPaper(examPaper);

                dispatcherExamPaperAndSubject(subjects, examPaper.getExamPaperId());
            }

            if (subjects.size() == 0) {
                model.addObject("success", "操作处理失败，共添加 <b style='color:red;'>"+subjects.size()+"</b> 道题, 请检查上传数据正确性!");
            } else {
                model.addObject("success", "操作处理成功，共添加 "+subjects.size()+" 道题");
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.setViewName("error");
            model.addObject("error", "上传失败, 请检查上传数据合理性或联系管理员!");
        } finally {
            /** 删除上传文件 */
            //deleteUploadFile(savePath);
        }
        return model;
    }

    private String saveUploadFile(MultipartFile file, String rootPath) {
        String fileName = file.getOriginalFilename();

        try {
            file.transferTo(new File(rootPath+"/"+fileName));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootPath+"/"+fileName;
    }


    /**
     * 只将试题上传到数据库
     * @param subjects
     * @param subjectsMap
     */
    private void importSubejctOnly(List<SubjectInfo> subjects, Map<String, Object> subjectsMap) {
        try {
            if (subjects != null && subjects.size() > 0) {
                //添加试题
                int row = subjectInfoService.isAddSubjects(subjectsMap);
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 处理 试题 添加到 试卷
     * @param subjects 试题集合
     * @param examPaperId 对应试卷编号
     */
    private void dispatcherExamPaperAndSubject(List<SubjectInfo> subjects, Integer examPaperId) {
        List<Integer> subjectIds = new ArrayList<Integer>();
        //试题总量统计
        int count = 0;
        //试题总分统计
        int score = 0;

        /** 添加试题 */
        for (SubjectInfo subjectInfo : subjects) {
            int row1 = subjectInfoService.isAddSubject(subjectInfo);
            score += subjectInfo.getSubjectScore();
            subjectIds.add(subjectInfo.getSubjectId());
            count++;
        }

        /** 添加试题到试卷 */
        Map<String, Object> esmMap = new HashMap<String, Object>();
        esmMap.put("examPaperId", examPaperId);
        esmMap.put("subjectIds", subjectIds);
        esmService.isAddESM(esmMap);

        //修改试卷信息
        Map<String, Object> scoreWithNum = new HashMap<String, Object>();
        scoreWithNum.put("subjectNum", count);
        scoreWithNum.put("score", score);
        scoreWithNum.put("examPaperId", examPaperId);
        /** 修改试卷总分 */
        examPaperInfoService.isUpdateExamPaperScore(scoreWithNum);
        /** 修改试卷试题总量 */
        examPaperInfoService.isUpdateExamPaperSubjects(scoreWithNum);
    }


    /**
     * 删除上传文件
     * @param filePath 文件路径
     */
    private void deleteUploadFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 预添加试题
     * @return
     */
    @RequestMapping("/preAddSubject")
    public ModelAndView preAddStudent() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admin/subject-test");
        List<GradeInfo> grades = gradeInfoService.getGradeList();
        model.addObject("grades", grades);
        model.addObject("courses", courseInfoService.getAllCourseInfo());
        return model;
    }

    private String trimChar(String str){
        if(str != null){
            return str.replaceAll("^,*|,*$", "");
        }
        return str;
    }


}
