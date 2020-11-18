package com.qhit.handler;

import cn.hutool.core.util.PageUtil;
import com.qhit.pojo.TeacherInfo;
import com.qhit.service.ClassInfoService;
import com.qhit.service.TeacherInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/teacher")
public class TeacherInfoHandler {

    @Autowired
    private TeacherInfoService teacherInfoService;
    @Autowired
    private ClassInfoService classInfoService;

    public ClassInfoService getClassInfoService() {
        return classInfoService;
    }

    public void setClassInfoService(ClassInfoService classInfoService) {
        this.classInfoService = classInfoService;
    }

    public TeacherInfoService getTeacherInfoService() {
        return teacherInfoService;
    }

    public void setTeacherInfoService(TeacherInfoService teacherInfoService) {
        this.teacherInfoService = teacherInfoService;
    }

    /*    @RequestMapping("/teacherlogin")
        public  String teacherLogin(String teacgerAccount,String teacherPwd ) {
           Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(teacgerAccount, teacherPwd);
            if(!subject.isAuthenticated()){
                try {
                    subject.login(token);
                } catch (AuthenticationException e) {
                    e.printStackTrace();
                }
            }
            return "login";
        }*/
    /**
     * @Author demon
     * @Date 7:55 2019/5/21
     * @Description 跳转到教室登陆
     * @MethodName goLogin
     * @Param 无
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("goLogin")
    public ModelAndView goLogin() {
        return new ModelAndView("admin/login");
    }

    /**
     * @Author demon
     * @Date 7:56 2019/5/21
     * @Description 退出登陆
     * @MethodName logout
     * @Param []
     * @return java.lang.String
     **/
    @RequestMapping("goLogout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index.jsp";
    }

    /**
     * @Author demon
     * @Date 7:56 2019/5/21
     * @Description 教室登陆
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
        TeacherInfo principal = (TeacherInfo) SecurityUtils.getSubject().getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("user", principal);
        return map;
    }

    @RequestMapping("/getAll")
    public ModelAndView getAll(Model model, Integer page) {
        int currPage = 1;
        if (page != null) {
            currPage = page;
        }
//        HuTool
        int[] ints = PageUtil.transToStartEnd(currPage, 12);//当前页，每页显示的数量
        Map map = teacherInfoService.getAllWithPage(ints);
        map.put("pageNow", currPage);
        model.addAttribute("teachers", map);
        return new ModelAndView("admin/frame/teachers");
    }

    @RequestMapping("/updateTeacherInfo")
    public @ResponseBody
    String updateTeacherInfo(TeacherInfo teacherInfo) {
        teacherInfoService.updateTeacherInfo(teacherInfo);
        return "redirect:getAll";
    }

    @RequestMapping("/getTeacherInfoByTeacherId")
    public ModelAndView getTeacherInfoByTeacherid(int teacherId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("teacher", teacherInfoService.getTeacherInfoByteacherId(teacherId
        ));
        modelAndView.setViewName("admin/frame/teacheredit");
        return modelAndView;
    }


    @RequestMapping("/addTeacherInfo")
    public @ResponseBody
    String addTeacherInfo(TeacherInfo teacherInfo) {
        teacherInfoService.addTeacherInfo(teacherInfo);
        return "redirect:getAll";
    }

    @RequestMapping("/deleteTeacherInfo")
    public @ResponseBody
    String deleteTeacherInfo(int ID) {
        teacherInfoService.deleteTeacherInfo(ID);
        return "redirect:getAll";
    }


}
