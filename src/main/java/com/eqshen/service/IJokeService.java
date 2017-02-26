package com.eqshen.service;

import java.util.List;

import com.eqshen.bean.UserJoke;
import com.eqshen.model.Joke;

public interface IJokeService {
	List<UserJoke> selectAllUserJoke();
	
	List<UserJoke> selectPageUserJoke(int page,int size);
	
	
	List<UserJoke> selectRandomUserJoke(int limit);
	
	List<UserJoke> selectHotUserJoke(int page,int size);
	
	List<UserJoke> selectLastestUserJoke(int page,int size);
	
	Joke selectJokeById(Long id);
	/*
	 * ����Ц����null�ֶβ�����
	 * */
	void updateByIdIgoreNull();
	
	void insert(Joke joke);
	
	void deleteById(Long id);
}
