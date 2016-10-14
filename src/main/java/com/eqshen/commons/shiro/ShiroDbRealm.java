package com.eqshen.commons.shiro;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;





import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.eqshen.bean.ShiroUser;
import com.eqshen.bean.UserGen;
import com.eqshen.model.Role;
import com.eqshen.model.User;
import com.eqshen.service.IRoleService;
import com.eqshen.service.IUserService;
import com.eqshen.service.impl.UserServiceImpl;

public class ShiroDbRealm extends AuthorizingRealm {
	
	private static Logger log = Logger.getLogger(UrlAuthorityInterceptor.class);
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		log.info(shiroUser.getLoginName()+"ִ��Ȩ����֤,����doGetAuthorizationInfo");
		List<Long> roleList = shiroUser.roleList;
		Set<String> urlSet = new HashSet<String>();
		Set<String> roles = new HashSet<String>();
        //��ȡurl
		for (Long roleId : roleList) {
            List<Map<Long, String>> roleResourceList = roleService.findRoleResourceListByRoleId(roleId);
            if (roleResourceList != null) {
                for (Map<Long, String> map : roleResourceList) {
                    if (StringUtils.isNoneBlank(map.get("url"))) {
                        urlSet.add(map.get("url"));
                    }
                }
            }
        }
        //��ȡ��ɫ
        List<Role> roleList2=userService.findRoleListById(shiroUser.getId());
        for(Role role : roleList2){
        	roles.add(role.getName());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
        info.setRoles(roles);
        return info;
	}

	
	/**
     * Shiro��¼��֤(ԭ���û��ύ �û���������  --- shiro ��װ���� ---- 
     * realm ͨ���û����������ѯ���� ---- shiro �Զ�ȥ�Ƚϲ�ѯ��������û����������Ƿ�һ��---- ���е�½���� )
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		//User user=userService.findUserByLoginName(token.getUsername());
		UserGen user=userService.findUserGenByLoginName(token.getUsername());
		log.info(user.getLoginname()+"ִ�������֤doGetAuthenticationInfo");
		// �˺Ų�����
        if (user == null) {
            return null;
        }
        // �˺�δ����
        if (user.getStatus() == 0) {
            return null;
        }
        List<Long> roleIdList=roleService.findRoleIdListByUserId(user.getId());
        ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginname(), user.getName(), user.getPassword(), user.getIcon(),roleIdList );
        
       
        //��֤����
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), getName());
        
        return info;
	}

}
