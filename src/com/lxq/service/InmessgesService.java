package com.lxq.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxq.beans.Inmessges;
import com.lxq.dao.Dao;

@Service
@Transactional
public class InmessgesService {
	@Resource
	private Dao dao;
	
	/**
	 * ����user
	 * @param user
	 * @return
	 */
	public int addUser(Inmessges user){
		try {
			dao.save(user);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * ɾ��user
	 * @param user
	 * @return
	 */
	public int delete(Inmessges user){
		try {
			dao.delete(user);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * ��ȡȫ����Inmessges���ݱ��������Ϣ
	 * @return
	 */
	public Inmessges list(){
		List<Inmessges> list = dao.find("FROM Inmessges");
		return list.get(0);
	}
}
