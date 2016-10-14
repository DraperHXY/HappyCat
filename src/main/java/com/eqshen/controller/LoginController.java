package com.eqshen.controller;


import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eqshen.base.BaseController;
import com.eqshen.bean.UserJoke;
import com.eqshen.service.IJokeService;
import com.eqshen.service.impl.JokeServiceImpl;


@Controller
public class LoginController extends BaseController {
	@Autowired
	private IJokeService JokeService;
	/**
     * ��ҳ
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/index";
    }
    
    /**
     * ��ҳ
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(Model model) {
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("index");
    	List<UserJoke> ls=JokeService.selectPageUserJoke(1, 5);
    	mav.addObject("userJokeList", ls);
    	return mav;
    }
    
    /**
     * GET ��¼
     * @return {String}
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
        	System.out.println("�û��Ѿ���½����ת����ҳ");
        	
            return "redirect:/index";
        }
        return "login";
    }
    
    
    /**
     * POST ��¼ shiro д��
     *
     * @param username �û���
     * @param password ����
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object loginPost(String username, String password) {

        if (StringUtils.isBlank(username)) {
            return renderError("�û�������Ϊ��");
        }
        if (StringUtils.isBlank(password)) {
            return renderError("���벻��Ϊ��");
        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        token.setRememberMe(true);
        try {
            user.login(token);
        } catch (UnknownAccountException e) {
            //logger.error("�˺Ų����ڣ�{}", e);
            return renderError("�˺Ų�����");
        } catch (DisabledAccountException e) {
//            logger.error("�˺�δ���ã�{}", e);
            return renderError("�˺�δ����");
        } catch (IncorrectCredentialsException e) {
//            logger.error("�������{}", e);
            return renderError("�������");
        } catch (RuntimeException e) {
//            logger.error("δ֪����,����ϵ����Ա��{}", e);
            return renderError("δ֪����,����ϵ����Ա");
        }
        return renderSuccess();
    }
    
    
    /**
     * δ��Ȩ
     * @return {String}
     */
    @RequestMapping(value = "/unauth")
    public String unauth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
        	System.out.println("δ����Ȩ����ת����½����");
            return "redirect:/login";
        }
        return "redirect:/index";
    }

//    /**
//     * �˳�
//     * @return {Result}
//     */
//    @RequestMapping(value = "/logout")
//    @ResponseBody
//    public Object logout() {
//        //logger.info("�ǳ�");
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return renderSuccess();
//    }
    /**
     * �˳�
     * @return {Result}
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/index";
    }
}
