package com.lxq.LoginInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//import com.solar.tech.bean.entity.YHopenID;
import com.lxq.service.WechatService;
//import com.solar.tech.service.YHopenIDService;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 
 * @ClassName: LoginInterceptor
 * @Description: ���������������ع��ں��Զ���˵���ť���ж��Ƿ��
 * @date: 2016��4��27�� ����4:05:37
 */
@Repository
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private WechatService wxMpService = new WechatService().getInstance();

	/**
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String pageurl = path.replace(ContextPath, "");
		String root = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + root + "/";

		if (pageurl.equals("/framework/user/business.action") || pageurl.equals("/wechatController/page/mine.action")) { //�Զ������ؽ��棬��Ҫ���Զ�������Ҫ�Ǹ��û������Ǹ������ǽ������ص�����
			HttpSession session = request.getSession();
			String newCode = request.getParameter("code");
			String number = (String) session.getAttribute("number");
			String openId = (String) session.getAttribute("openId");

			if (newCode == null && "".equals(newCode)) {  //newCodeΪ�վʹ�����û���û�н���ע�����Ծ���ת��ע�����
				response.sendRedirect(basePath + "console/wechat/business/bindAccount.jsp");
				return false;
			}
			
			if (number == null || openId == null) {
				openId = wxMpService.getWxUser(newCode);  //��������������ܽ��л�ȡ��openId
			} else {  //������Ǹ��û����ʹ��ˣ������ظ��ػ�ȡopenId
				System.out.println("pageurl:" + pageurl +"number:" + number + " " + "openId:" + openId);
			}

		}

		return true;
	}

	/**
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}