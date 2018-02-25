package com.myuan.web.shiro;

import com.google.common.collect.Lists;
import com.myuan.web.entity.MyAuthority;
import com.myuan.web.entity.MyRole;
import com.myuan.web.entity.MyUser;
import com.myuan.web.service.AuthService;
import com.myuan.web.service.RoleService;
import com.myuan.web.service.UserService;
import com.myuan.web.utils.UserUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
    private AuthService authService;
	/**
	 * 授权 <liuwei> [2018/1/28 10:11]
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		MyUser user = UserUtil.getCurrentUser();
		//获取角色集合
		Set<String> roleNames = new HashSet<String>();
        //权限集合
        Set<String> authNames = new HashSet<String>();
		List<MyRole> roleList = roleService.findRoleByUserId(user.getId());
		List<MyAuthority> authList = Lists.newArrayList();
		for (MyRole role : roleList) {
			roleNames.add(role.getType());
            authList.addAll(authService.findAuthByRoleId(role.getId()));
		}
        for (MyAuthority auth : authList) {
            authNames.add(auth.getAuth());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(authNames);
        return info;
	}

	/**
	 * 登陆验证 <liuwei> [2018/1/28 10:11]
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        MyUser user = userService.getUserByName(name);
		if(user!=null) {
			if("1".equals(user.getLocked())) {
	        	throw new LockedAccountException();
	        } else {
	        	return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
	        }
	    } else {
	       throw new UnknownAccountException();
	    }
	}
}
