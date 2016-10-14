package com.eqshen.bean;

import java.io.Serializable;
import java.util.List;

import com.eqshen.model.User;

/**
 * @description���Զ���Authentication����ʹ��Subject����Я���û��ĵ�¼���⻹����Я��������Ϣ
 * @author��eqshen
 * @date��2016��5��27��16:34:59
 */
public class ShiroUser   implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;
    private Long id;
    private String loginName;
    private String name;
    private String password;
    private String icon;
    public List<Long> roleList;
    
    
    public ShiroUser(Long id,String loginName,String name,String password,String icon,List<Long> roleList){
        this.id=id;
    	this.name=name;
        this.loginName=loginName;
        this.password=password;
        this.icon=icon;
        this.roleList = roleList;
    }
    
    
    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getLoginName() {
		return loginName;
	}



	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getIcon() {
		return icon;
	}



	public void setIcon(String icon) {
		this.icon = icon;
	}


	public List<Long> getRoleList() {
		return roleList;
	}



	public void setRoleList(List<Long> roleList) {
		this.roleList = roleList;
	}



	/**
     * �������������ΪĬ�ϵ�<shiro:principal/>���.
     */
    @Override
    public String toString() {
    	//return null;
        return loginName;
    }
}