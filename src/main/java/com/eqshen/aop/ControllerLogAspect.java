package com.eqshen.aop;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;

import com.eqshen.bean.ShiroUser;
import com.eqshen.model.User;

public class ControllerLogAspect {
	private static Logger log = Logger.getLogger(ControllerLogAspect.class);
	
	public Object aroundCheck(ProceedingJoinPoint jp){
		Subject currentUser=SecurityUtils.getSubject();
		String username=null;
		username=(currentUser==null||currentUser.getPrincipal()==null ?"����":((ShiroUser)currentUser.getPrincipal()).getLoginName());
		
		//��ȡ����
		String className=jp.getTarget().getClass().getSimpleName();
		//��ȡ��������
		String methodName=jp.getSignature().getName();
		//��ȡĿ�귽���Ĳ���
		Object []args=jp.getArgs();
		//Ŀ�귽���ķ���ֵ
		Object rvt=null;
		try {
			//ִ��Ŀ�귽��
			rvt=jp.proceed(args);
		} catch (Throwable e) {
			log.error("aop��־������ִ��Ŀ�귽������",e);
		}
		log.info("�û���"+username+"ִ����"+className+",������"+methodName+",����ֵ"+rvt.toString());
		return rvt;
	}
}
