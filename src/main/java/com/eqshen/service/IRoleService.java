package com.eqshen.service;

import java.util.List;
import java.util.Map;

import com.eqshen.model.Role;
import com.github.pagehelper.PageInfo;

public interface IRoleService {
	/**
     * ���Ȩ��
     *
     * @param role
     */
    void addRole(Role role);

    /**
     * ����idɾ��Ȩ��
     *
     * @param id
     */
    void deleteRoleById(Long id);

    /**
     * ����id��ѯȨ��
     *
     * @param id
     * @return
     */
    Role findRoleById(Long id);

    /**
     * ����Ȩ��
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * ����Ȩ��id��ѯ��Դ����
     *
     * @param id
     * @return
     */
    List<Long> findResourceIdListByRoleId(Long id);

    /**
     * ����Ȩ�޺���Դ�Ĺ�����ϵ
     *
     * @param id
     * @param resourceIds
     */
    void updateRoleResource(Long id, String resourceIds);

    /**
     * �����û���ѯid��ѯȨ�޼���
     *
     * @param userId
     * @return
     */
    List<Long> findRoleIdListByUserId(Long userId);

    /**
     * ����Ȩ�޲�ѯid��ѯ��Դ·������
     *
     * @param roleId
     * @return
     */
    List<Map<Long, String>> findRoleResourceListByRoleId(Long roleId);

	PageInfo selectByPage(int page, int size);
	
	List<Role> findAll();

}
