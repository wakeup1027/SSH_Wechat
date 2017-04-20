package com.lxq.loginFilter;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.lxq.service.MenuManger;

@Service
public class MenuMangerLister implements ApplicationListener<ContextRefreshedEvent> {
	
	private MenuManger uMenuManger;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			/** 系统启动检查自定义菜单是否已经配置自定义菜单 **/
			uMenuManger = new MenuManger();
			uMenuManger.CheckMenu();
		}
	}
	
	public static void main(String[] args) {
		MenuManger sds = new MenuManger();
		sds.CheckMenu();
	}

}
