package com.eqshen.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @description���Զ���Authentication����ʹ��Subject����Я���û��ĵ�¼���⻹����Я��������Ϣ
 * @author��eqshen
 * @date��2016��5��27��16:34:59
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;
    public Long id;
    public String loginName;
    public String name;
    public List<Long> roleList;
    
    public ShiroUser(Long id, String loginName, String name, List<Long> roleList) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.roleList = roleList;
    }

    public String getName() {
        return name;
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

	public List<Long> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Long> roleList) {
		this.roleList = roleList;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * �������������ΪĬ�ϵ�<shiro:principal/>���.
     */
    @Override
    public String toString() {
        return loginName;
    }
}