package com.eqshen.service;

import java.util.List;

import com.eqshen.bean.UserGen;
import com.eqshen.model.Role;
import com.eqshen.model.User;
import com.eqshen.model.UserRole;
import com.github.pagehelper.PageInfo;

public interface IUserService {
	/**
     * �����û�����ѯ�û�
     *
     * @param username
     * @return
     */
    User findUserByLoginName(String username);

    /**
     * �����û�id��ѯ�û�
     *
     * @param id
     * @return
     */
    User findUserById(Long id);
    /**
     * ��퓲�ԃ�Ñ�
     *
     * @param id
     * @return
     */
    PageInfo selectByPage(int page,int size);

    /**
     * ����û�
     *
     * @param userVo
     * @throws Exception 
     */
    void addUser(UserGen userGen) throws Exception;

    /**
     * �޸�����
     *
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(Long id, String pwd);

    /**
     * �����û�id��ѯ�û�������
     *
     * @param id
     * @return
     */
    UserGen findUserGenById(Long id);
    
    UserGen findUserGenByLoginName(String loginname);
    
    /**
     * �����û�id��ȡ�û���ɫlist
     *
     * @param id
     */
    List<Role> findRoleListById(Long id);
    
    /**
     * �����û�id��roleid��ȡUserRole List
     *
     * @param id
     * @return List<UserRole>
     */
    List<UserRole> findUserRoleByUserIdAndRoleId(Long userid,Long roleid);
    
    /**
     * �޸��û�
     *
     * @param userVo
     * @throws Exception 
     */
    void updateUser(UserGen userGen) throws Exception;
    
    
    /**
     * ����userRole
     *
     * @param UserRole
     * @throws Exception 
     */
    void addUserRole(UserRole ur);
    
    
    /**
     * ����useridɾ������userRole
     *
     * @param userid
     * @return 
     * @throws Exception 
     */
    int removeUserRoleByUserId(Long userid);
    
    /**
     * ɾ���û�
     *
     * @param id
     */
    void deleteUserById(Long id);
}
