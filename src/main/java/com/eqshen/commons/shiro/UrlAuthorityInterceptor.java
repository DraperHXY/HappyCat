package com.eqshen.commons.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.eqshen.aop.ControllerLogAspect;
import com.eqshen.bean.ShiroUser;

/**
 * @description���û��Զ���shiro����������������url����Ȩ�޽����ж�
 * @author��eqshen
 * @date��2016��5��28��14:10:34
 */

public class UrlAuthorityInterceptor extends AuthorizationFilter {
	private static Logger log = Logger.getLogger(UrlAuthorityInterceptor.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object arg2) throws Exception {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		Subject currentUser=SecurityUtils.getSubject();
		if(currentUser.isAuthenticated()==false){
			log.info("�û���֤ʧ�� in UrlAuthorityInterceptor,������ԴΪ��"+httpRequest.getRequestURI());
			//System.out.println("�û���֤ʧ�� in urlauthority");
			return false;
		}
		String uri=httpRequest.getRequestURI();
		String resource=uri.replaceAll(httpRequest.getContextPath(), "");
		if(currentUser.isPermitted(resource)==false){
			//System.out.println("�û�"+((ShiroUser)currentUser.getPrincipal()).getName()+"��Ȩ�޷���·��"+resource);
			log.info("�û�"+((ShiroUser)currentUser.getPrincipal()).getLoginName()+"��Ȩ�޷���·��:"+resource);
			return false;
		}
		//System.out.println("�û�����shiro����������,���ص�����:"+httpRequest.getRequestURI());
		return true;
	}

}
