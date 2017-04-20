package com.lxq.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxq.beans.Inmessges;
import com.lxq.service.InmessgesService;

@Controller
@RequestMapping("/framework/user")
public class InmessgesController {
	@Autowired
	private InmessgesService userser;
	
	@RequestMapping(value = "/add.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(Inmessges user){
		Map<String, Object> map = new HashMap<String, Object>();
		int i = userser.addUser(user);
		if(i==1)map.put("addRes", "�������ӳɹ���");else map.put("addRes", "��������ʧ�ܣ�");
		map.put("addUser", user);
		return map;
	}
	
	@RequestMapping(value = "/delet.action", method = RequestMethod.POST)
    @ResponseBody
	public Map<String, Object> delete(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		Inmessges user  = new Inmessges();
		user.setId(id);
		int i = userser.delete(user);
		if(i==1)map.put("addRes", "����ɾ���ɹ���");else map.put("addRes", "����ɾ��ʧ�ܣ�");
		return map;
	}
	
	@RequestMapping("/business.action")
	public String resultBusiness() {
		System.out.println("ӳ��ɹ���");
		return "index";
	}
	
}
