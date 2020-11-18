package com.qhit.realm;

import com.qhit.mapper.StudentInfoMapper;
import com.qhit.pojo.StudentInfo;
import com.qhit.pojo.TeacherInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName StudentRealm
 * @Descriotion
 * @Author demon
 * @Date 2019/5/27 9:30
 * @Version 1.0
 **/
public class StudentRealm extends AuthenticatingRealm {
    @Autowired
    private StudentInfoMapper studentInfoMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=((UsernamePasswordToken)authenticationToken).getUsername();
        StudentInfo s = studentInfoMapper.getStudentByName(username);
        if (s==null){
            return null;
        }else {
            /*封装用户名密码数据*/
            ByteSource bytes = ByteSource.Util.bytes(s.getSalt());
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(s,s.getStudentPwd(),bytes,getName());
            return simpleAuthenticationInfo;
        }
    }
}
