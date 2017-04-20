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
 * @Description: 拦截器，作用拦截公众号自定义菜单按钮，判断是否绑定
 * @date: 2016年4月27日 下午4:05:37
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

		if (pageurl.equals("/framework/user/business.action") || pageurl.equals("/wechatController/page/mine.action")) { //自定义拦截界面，主要是自定义你想要那个用户访问那个界面是进行拦截的设置
			HttpSession session = request.getSession();
			String newCode = request.getParameter("code");
			String number = (String) session.getAttribute("number");
			String openId = (String) session.getAttribute("openId");

			if (newCode == null && "".equals(newCode)) {  //newCode为空就代表该用户还没有进行注册所以就跳转到注册界面
				response.sendRedirect(basePath + "console/wechat/business/bindAccount.jsp");
				return false;
			}
			
			if (number == null || openId == null) {
				openId = wxMpService.getWxUser(newCode);  //根据这个方法就能进行获取到openId
			} else {  //否则就是该用户访问过了，不用重复地获取openId
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