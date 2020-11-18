package com.qhit.realm;

import com.qhit.mapper.TeacherInfoMapper;
import com.qhit.pojo.TeacherInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class TeacherRealm extends AuthenticatingRealm {
    @Autowired
    public TeacherInfoMapper teacherInfoMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=((UsernamePasswordToken)authenticationToken).getUsername();
        TeacherInfo t = teacherInfoMapper.getTeacherByName(username);
        if (t==null){
            return null;
        }else {
            /*封装用户名密码数据*/
            ByteSource bytes = ByteSource.Util.bytes(t.getSalt());
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(t,t.getTeacherPwd(),bytes,getName());
            return simpleAuthenticationInfo;
        }
    }
}
