package com.eqshen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eqshen.base.BaseController;
import com.eqshen.bean.UserJoke;
import com.eqshen.service.IJokeService;
import com.github.pagehelper.PageHelper;

/*
 * no need login controller
 * ����Ҫ�����֤�Ľӿ�
 * */

@Controller
@RequestMapping("/Anno")
public class AnnoController extends BaseController {
	@Autowired
	private IJokeService jokeService;
	
	/**
     * ��ҳ��ȡЦ��
     *
     * @param model
     * @return json
     */
	
	@RequestMapping("/getPageJoke")
	@ResponseBody
	public Object  getPageJoke(int page,int size){
		List<UserJoke> list=jokeService.selectPageUserJoke(page, size);
		return renderSuccess(list);
	} 
	
	/**
     * �����ȡЦ��
     *
     * @param model
     * @return
     */
	@RequestMapping("/getRandomPageJoke")
	@ResponseBody
	public Object  getRandomPageJoke(int size){
		//int size=5;
		List<UserJoke> list=jokeService.selectRandomUserJoke(size);
		return renderSuccess(list);
	}
	/**
     * ��ҳ��ȡ����Ц��
     *
     * @param model
     * @return json
     */
	@RequestMapping("/getHotPageJoke")
	@ResponseBody
	public Object  getHotPageJoke(int page,int size){
		List<UserJoke> list=jokeService.selectHotUserJoke(page,size);
		return renderSuccess(list);
	}
	
	/**
     * ��ҳ��ȡ����Ц��
     *
     * @param model
     * @return json
     */
	@RequestMapping("/getLastestPageJoke")
	@ResponseBody
	public Object  getLastestPageJoke(int page,int size){
		List<UserJoke> list=jokeService.selectLastestUserJoke(page, size);
		return renderSuccess(list);
	}
	
	 /**
     * �����ȡ
     *
     * @param model
     * @return jsp page
     */
    @RequestMapping(value = "/randomJoke")
    public ModelAndView getRandomJoke(Model model,int limit) {
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("view/randomJoke");
    	List<UserJoke> ls=jokeService.selectRandomUserJoke(limit);
    	mav.addObject("userJokeList", ls);
    	return mav;
    }
    
    
    /**
     * ����
     *
     * @param model
     * @return jsp page
     */
    @RequestMapping(value = "/hotJoke")
    public ModelAndView getHotJoke(Model model,int page,int size) {
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("view/hotJoke");
    	List<UserJoke> ls=jokeService.selectHotUserJoke(page, size);
    	mav.addObject("userJokeList", ls);
    	return mav;
    }
    /**
     * ����
     *
     * @param model
     * @return jsp page
     */
    @RequestMapping(value = "/latestJoke")
    public ModelAndView getLastestJoke(Model model,int page,int size) {
    	ModelAndView mav=new ModelAndView();
    	mav.setViewName("view/latestJoke");
    	List<UserJoke> ls=jokeService.selectLastestUserJoke(page, size);
    	mav.addObject("userJokeList", ls);
    	return mav;
    }
}
