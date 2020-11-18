package com.qhit.handler;

import cn.hutool.core.util.PageUtil;

import com.qhit.pojo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qhit.handler.echars.Student;
import com.qhit.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/student")
public class StudentInfoHandler {
    @Autowired
    private StudentInfoService studentInfoService;
    @Autowired
    private ClassInfoService classInfoService;
    @Autowired
    private ExamHistoryInfoService examHistoryInfoService;
    @Autowired
    private ExamSubjectMiddleInfoService examSubjectMiddleInfoService;

    @Autowired
    private ExamChooseInfoService examChooseInfoService;

    /*
     * 查询学生列表
     * */
    @RequestMapping("/studentList")
    public ModelAndView studentList(Integer pageNow) {
        if (null == pageNow) {
            pageNow = 1;
        }
        int[] ints = PageUtil.transToStartEnd(pageNow, 11);
        ModelAndView mo = new ModelAndView();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("start", ints[0]);
        mo.addObject("students", studentInfoService.SelectSelective(hashMap));
        mo.addObject("totalCount", studentInfoService.getTotalCount());
        mo.addObject("pageNow", pageNow);
        mo.setViewName("admin/frame/students");
        return mo;
    }

    /*
     * 修改学生信息
     * */
    @RequestMapping("/updateStudent")
    public @ResponseBody
    String updateStudent(StudentInfo studentInfo) {
        studentInfoService.updateByPrimaryKeySelective(studentInfo);
        return "redircet:studentList";
    }

    /*
     * 通过Id获取StudentInfo
     * */
    @RequestMapping("/selectByPrimaryKey")
    public ModelAndView selectByPrimaryKey(int studentId) {
        ModelAndView mo = new ModelAndView();
        mo.addObject("student", studentInfoService.selectByPrimaryKey(studentId));
//        还需要一个classList

        mo.addObject("classes", classInfoService.getAllClassInfo(null));

        mo.addObject("classes", classInfoService.getAllClassInfo(null));

        mo.setViewName("admin/frame/studentedit");
        return mo;
    }

    /*
     * 根据Id删除学生
     * */
    @RequestMapping("/deleteByPrimaryKey")
    public @ResponseBody
    String deleteByPrimaryKey(int ID) {
        int result = studentInfoService.deleteByPrimaryKey(ID);
        return "redircet:studentList";
    }



    /*
    * 查询班级下考试过的学生
    * */
    @RequestMapping("/getExamStudent")
    public  ModelAndView getExamStudent(Integer teacherId){
        ModelAndView model = new ModelAndView();
        model.addObject("students",studentInfoService.getExamStudent(teacherId));
        model.setViewName("admin/charts/studentExamCount");
        return model;
    }
    @RequestMapping("/Echars")
    public @ResponseBody void echars(Integer teacherId ,HttpServletResponse response) throws IOException {
        ArrayList<Student> list = new ArrayList<>();
        List<StudentInfo> examCount = studentInfoService.getExamCount(teacherId);
        for(int i=0;i<examCount.size();i++){
            Student student = new Student();
            student.setName(examCount.get(i).getStudentName());
            student.setValue(examCount.get(i).getExamCount());
            list.add(student);
        }
        ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
        String json = mapper.writeValueAsString(list);    //将list中的对象转换为Json格式的数组
        //将json数据返回给客户端
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(json);
    }

    /*
    * 通过名字查询考生考试过的试卷和分数
    * */
    @RequestMapping("/getExamPaperANDexamScore")
    public @ResponseBody void getExamPaperANDexamScore(String studentName ,HttpServletResponse response) throws IOException {
        ArrayList<Student> list = new ArrayList<>();
        List<StudentInfo> examPaperANDexamScore = studentInfoService.getExamPaperANDexamScore(studentName);
        for(int i=0;i<examPaperANDexamScore.size();i++){
            Student student = new Student();
            student.setName(examPaperANDexamScore.get(i).getExamPaperName());
            student.setValue(examPaperANDexamScore.get(i).getExamScore());
            list.add(student);
        }
        ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
        String json = mapper.writeValueAsString(list);    //将list中的对象转换为Json格式的数组
        //将json数据返回给客户端
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(json);
    }





    /**
     * @Author demon
     * @Date 10:28 2019/5/27
     * @Description 学生登陆
     * @MethodName login
     * @Param [username, pwd, request]
     * @return java.util.Map
     **/
    @RequestMapping("/login")
    public @ResponseBody
    Map login(String username, String pwd, HttpServletRequest request) {
        HashMap<Object, Object> map = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        if (!subject.isAuthenticated()) {
            try {
                map.put("state", "0");
                map.put("msg", "登陆成功！");
                subject.login(token);
            } catch (UnknownAccountException unknownAccountException) {
                map.put("state", "1");
                map.put("msg", "账户不存在！");
            } catch (CredentialsException credentialsException) {
                map.put("state", "2");
                map.put("msg", "密码错误！");
            } catch (AuthenticationException authenticationException) {
                map.put("state", "3");
                map.put("msg", "登陆失败！");
            }
        }
        StudentInfo principal = (StudentInfo) SecurityUtils.getSubject().getPrincipal();
        HttpSession session = request.getSession();
        StudentInfo studentInfo = new StudentInfo();
        studentInfo = principal;
        GradeInfo grade = studentInfoService.getGradeByClassId(principal.getClassId());
        studentInfo.setGrade(grade);
        session.setAttribute("student", studentInfo);
        return map;
    }

    /**
     * @Author demon
     * @Date 21:39 2019/5/27
     * @Description 跳转到学生注册
     * @MethodName goRegister
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/toRegister")
    public ModelAndView goRegister() {
        ModelAndView mo = new ModelAndView();
        mo.addObject("classs", classInfoService.getAllClassInfo(null));
        mo.setViewName("reception/register");
        return mo;
    }

    /**
     * @Author demon
     * @Date 22:36 2019/5/27
     * @Description 使用MD5和UUID盐加密实现学生注册
     * @MethodName register
     * @Param [studentName, studentAccount, studentPwd, classId]
     * @return java.util.Map
     **/
    @RequestMapping("/register")
    public @ResponseBody
    Map register(String studentName, String studentAccount, String studentPwd, Integer classId) {
        HashMap map = new HashMap();
        StudentInfo stu = new StudentInfo();
        stu.setStudentName(studentName);
        stu.setStudentAccount(studentAccount);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        ByteSource salt = ByteSource.Util.bytes(uuid);
        SimpleHash simpleHash = new SimpleHash("MD5", studentPwd, salt, 1024);
        stu.setSalt(uuid);
        stu.setStudentPwd(simpleHash.toString());
        stu.setClassId(classId);
        int rs = studentInfoService.insertSelective(stu);
        if (rs == 0) {
            map.put("state", 0);
        } else {
            map.put("state", 1);
        }
        return map;
    }

    /**
     * @Author demon
     * @Date 0:40 2019/5/28
     * @Description 开始考试
     * @MethodName beginExam
     * @Param [classId, examPaperId, studentId, examTime, beginTime, gradeId, session]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/beginExam")
    public ModelAndView beginExam(Integer classId, Integer examPaperId, Integer studentId, Integer examTime, String beginTime, Integer gradeId,
                                  HttpSession session) {
        ModelAndView model = new ModelAndView();

        /*
         * 查询该考试当前进入的试卷是否已经在历史记录中存在
         * 如果存在，则不能再次进入考试； 反之进入考试
         */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("studentId", studentId);
        map.put("examPaperId", examPaperId);
        int count = examHistoryInfoService.getHistoryInfoWithIds(map);
        if (count >= 1) {
            model.addObject("error", "你已经考试过了");
            model.setViewName("error");
            return model;
        } else {
            model.setViewName("/reception/exam");

            ExamPaperInfo examPaper = new ExamPaperInfo();
            examPaper.setExamPaperId(examPaperId);
            //获取试卷 试题集合
            ExamSubjectMiddleInfo esm=new ExamSubjectMiddleInfo();
            esm.setExamPaper(examPaper);
            List<ExamSubjectMiddleInfo> esms = examSubjectMiddleInfoService.getExamPaperWithSubject(esm);

            //获取当前考生在当前试卷中已选答案记录
            Map<String, Object> choosedMap = new HashMap<String, Object>();
            choosedMap.put("studentId", studentId);
            choosedMap.put("examPaperId", examPaperId);
            List<ExamChooseInfo> chooses = examChooseInfoService.getChooseInfoWithSumScore(choosedMap);
            if (chooses == null || chooses.size() == 0) {
                model.addObject("chooses", null);
            } else {
                model.addObject("chooses", chooses);
            }


            model.addObject("esms", esms);
            model.addObject("sumSubject", esms.size());
            model.addObject("examPaperId", examPaperId);
            model.addObject("examTime", examTime);
            model.addObject("beginTime", beginTime);
            model.addObject("classId", classId);
            model.addObject("gradeId", gradeId);

            return model;

        }

    }

        /**
         * @Author demon
         * @Date 9:19 2019/5/29
         * @Description 学生查询个人信息
         * @MethodName selfInfo
         * @Param [studentId]
         * @return org.springframework.web.servlet.ModelAndView
         **/
        @RequestMapping("/self")
        public ModelAndView selfInfo (Integer studentId){
            StudentInfo stu = studentInfoService.selectByPrimaryKey(studentId);
            ModelAndView mo = new ModelAndView();
            mo.setViewName("reception/self");
            mo.addObject("self", stu);
            return mo;
        }
    @RequestMapping("/submit")
    public String examSubmit(Integer studentId, Integer examPaperId, Integer classId, Integer gradeId) {

        //获取当前学生当前试卷所选择的全部答案
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("studentId", studentId);
        map.put("examPaperId", examPaperId);
        List<ExamChooseInfo> chooses = examChooseInfoService.getChooseInfoWithSumScore(map);

        //总分记录
        int sumScore = 0;
        for (ExamChooseInfo choose : chooses) {
            SubjectInfo subject = choose.getSubject();
            String chooseResult = choose.getChooseResult();
            String rightResult = subject.getRightResult();

            if (chooseResult.equals(rightResult)) {	//答案正确
                sumScore += subject.getSubjectScore();
            } else {
            }
        }

        /**
         * 首先判断当前记录是否已经添加过
         * 防止当前学生点击提交后，系统倒计时再次进行提交
         */
        int count = examHistoryInfoService.getHistoryInfoWithIds(map);


            //添加到历史记录
            map.put("examScore", sumScore);
            int row = examHistoryInfoService.isAddExamHistory(map);


        return "redirect:../examPlanInfo/willexams.action?gradeId="+gradeId+"&classId="+classId+"&studentId="+studentId;
    }


}
