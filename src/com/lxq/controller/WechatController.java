package com.lxq.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lxq.service.WechatService;

import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
/**
 * 
 * @ClassName: WechatController 
 * @Description: ΢�����������
 * @author: ChenXZ
 * @date: 2016��5��6�� ����2:45:52
 */
@Controller
@RequestMapping("/wechatController")
public class WechatController {
	
	private WechatService wxMpService =  WechatService.getInstance();
	/**
     * 
     * @Title: wechatGet 
     * @Description: ΢��GET����
     * @param request
     * @param response
     * @throws IOException
     * @return: void
     */
	@ResponseBody
	@RequestMapping(value = {"/wechat.action"}, method = RequestMethod.GET)
	public void wechatGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("chenxz28");
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK); 

		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");

		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
			// ��Ϣǩ������ȷ��˵�����ǹ���ƽ̨����������Ϣ
			response.getWriter().println("�Ƿ�����");
			return;
		}

		String echostr = request.getParameter("echostr");
		
		/*System.out.println("signature��"+signature);
		System.out.println("nonce��"+nonce);
		System.out.println("timestamp��"+timestamp);
		System.out.println("echostr��"+echostr);*/
		
		if (StringUtils.isNotBlank(echostr)) {
			// ˵����һ������������֤�����󣬻���echostr
			response.getWriter().println(echostr);
			return;
		}
	}
	
	/**
	 * 
	 * @Title: wechatPost 
	 * @Description: ΢��POST����
	 * @param request
	 * @param response
	 * @throws IOException
	 * @return: void
	 */
	@ResponseBody
	@RequestMapping(value = {"/wechat.action"}, method = RequestMethod.POST)
	public void wechatPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("chenxz52");
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		String signature = request.getParameter("signature");
		String nonce = request.getParameter("nonce");
		String timestamp = request.getParameter("timestamp");

		if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
			// ��Ϣǩ������ȷ��˵�����ǹ���ƽ̨����������Ϣ
			response.getWriter().println("�Ƿ�����");
			return;
		}

		String encryptType = StringUtils.isBlank(request.getParameter("encrypt_type")) ? "raw"
				: request.getParameter("encrypt_type");

		if ("raw".equals(encryptType)) {
			// ���Ĵ������Ϣ
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(request.getInputStream());
			System.out.println(inMessage.toString());
			WxMpXmlOutMessage outMessage = wxMpService.route(inMessage);
			if(outMessage != null) {
				response.getWriter().write(outMessage.toXml());
			} else {
				response.getWriter().write("");
			}
			
			return;
		}

		if ("aes".equals(encryptType)) {
			// ��aes���ܵ���Ϣ
			String msgSignature = request.getParameter("msg_signature");
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(request.getInputStream(),
					wxMpService.getWxMpInMemoryConfigStorage(), timestamp, nonce, msgSignature);
			WxMpXmlOutMessage outMessage = wxMpService.route(inMessage);
			response.getWriter().write(outMessage.toEncryptedXml(wxMpService.getWxMpInMemoryConfigStorage()));
			return;
		}

		response.getWriter().println("����ʶ��ļ�������");
		return;
	}
	
}
