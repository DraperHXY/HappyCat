package com.eqshen.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eqshen.base.BaseController;

@Controller
public class TestController extends BaseController {
	@RequestMapping(value="/info")
	public String info(Model model){
		return "info";
	}
	
	@RequestMapping("/stest")
	@ResponseBody
	public Object test(){
		Subject currentUser=SecurityUtils.getSubject();
		System.out.println("����û��Ƿ���֤:"+currentUser.isAuthenticated());
		System.out.println("����û��Ƿ����manage��Ȩ��:"+currentUser.isPermitted("/manage"));
		return this.renderSuccess("У�����");
	}
	
}
