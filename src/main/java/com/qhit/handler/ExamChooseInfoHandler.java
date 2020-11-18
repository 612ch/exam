package com.qhit.handler;

import com.qhit.pojo.ExamChooseInfo;
import com.qhit.service.ExamChooseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExamChooseInfoHandler
 * @Descriotion TODO
 * @Author demon
 * @Date 2019/5/29 15:48
 * @Version 1.0
 **/
@Controller
@RequestMapping("/examChoose")
public class ExamChooseInfoHandler {

    @Autowired
    private ExamChooseInfoService examChooseInfoService;
    @RequestMapping("/choose")
    public void examChooseHandler(Integer studentId,  Integer examPaperId, Integer subjectId, Integer index, String chooseAswer,
            HttpServletResponse response) throws IOException {

        //判断该考生是否已经选择过该试题
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("studentId", studentId);
        map.put("examPaperId", examPaperId);
        map.put("subjectId", subjectId);
        ExamChooseInfo examChoose=new ExamChooseInfo();
        examChoose = examChooseInfoService.getChooseWithIds(map);
        if (examChoose == null) {
            map.put("chooseResult", chooseAswer);
            /** 添加选择记录 */
            examChooseInfoService.addChoose(map);
        } else if (examChoose.getChooseId() != null && examChoose != null) {
            /*
             * 如果选择了和上次相同的答案，则不做修改操作
             * 优化 -- 前台判断选择了相同答案则不发出请求
             */
            if(!chooseAswer.equals(examChoose.getChooseResult())) {
                examChoose.setChooseResult(chooseAswer);
                /** 当前选择答案和之前选择答案不同 修改答案记录 */
                examChooseInfoService.updateChooseWithIds(examChoose);
            } else {
            }
        } else {
            response.getWriter().print("f");
            return;
        }

        response.getWriter().print("t");
    }
}
