package com.eqshen.service;

import java.util.List;

import com.eqshen.model.Resource;
import com.github.pagehelper.PageInfo;

public interface IResourceService {
	/**
     * ��ѯ������Դ
     *
     * @return list
     */
	public PageInfo selectByPage(int page,int size);
	
	
	public void updateResource(Resource r);
}
