package com.lxq.service;

import java.util.Map;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage.Item;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

/**
 * 
 * @ClassName: WechatService
 * @Description: TODO
 * @author: ChenXZ
 * @date: 2016��4��22�� ����11:52:03
 */

public class WechatService { 
 
	/** ΢�Ź��ں�������Ϣ **/
	private WxMpInMemoryConfigStorage wxMpConfigStorage;
	/** ������ **/
	private WxMpService wxMpService;
	/** ��Ϣ·�� **/
	private WxMpMessageRouter wxMpMessageRouter;

	/** ���� **/
	private static WechatService uWechatService = null;
	private static Object uWechatServiceObject = new Object();

	public WechatService() {
		// TODO Auto-generated constructor stub
	}
	
	private void init() {
		wxMpConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpConfigStorage.setAppId("wx0c792f8b27916232"); // ����΢�Ź��ںŵ�appid
		wxMpConfigStorage.setSecret("05c4c9112586340e9825213e85a9b47d"); // ����΢�Ź��ںŵ�app
																			// corpSecret
		wxMpConfigStorage.setToken("gzyhinctoken"); // ����΢�Ź��ںŵ�token
		wxMpConfigStorage.setAesKey("AjBXFHAqlNC3lasz1Ks9HWhBQgOFmXhAdm0Jj4yTSzJ"); // ����΢�Ź��ںŵ�EncodingAESKey

		
		wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage);

		WxMpMessageHandler handler = new WxMpMessageHandler() {
			@Override
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
					WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {

				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("���ã���л��ע����������ҵ��ѯ�������޹�˾-�����˰���֣����ǽ߳�Ϊ������!����������벦�����ǵķ������ߣ�020-83208891").fromUser(wxMessage.getToUserName())
						.toUser(wxMessage.getFromUserName()).build();

				return m;
			}
		};
		
		/*WxMpMessageHandler handler2 = new WxMpMessageHandler() {
			@Override
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
					WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {			
				Item item = new Item();
				item.setTitle("��ϵ����");
				item.setDescription("���䣺ling.f@rendetown.com\n�绰��020-86682999\n��ַ�����������������ű�·38��805��/������������������·139�Ż�������7¥ǰ̨");
			
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().addArticle(item).fromUser(wxMessage.getToUserName())
						.toUser(wxMessage.getFromUserName()).build();

				return m;
			}
			
		};*/
		
		/*WxMpMessageHandler handler3 = new WxMpMessageHandler() {
			@Override
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
					WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {			
				Item item = new Item();
				item.setTitle("��������");
				item.setDescription("����������ҵ��ѯ�������޹�˾λ�ڹ�����������������·139�Ŷ�¥�Ա�2A060�����ް칫��;����ͨ������"
						+ "����������ҵ��ѯ�������޹�˾���š��ͻ���һ���������ϡ���ԭ��������ҵ�����˳��ڵĺ�����ϵ����˾ӵ��һ֧��ǿ�ļ����з�"
						+ "���鲢�͹��ڴ�רԺУǿǿ���֣��з���ӵ������֪ʶ��Ȩ���ȳϻ�ӭ��������ǰ���ιۡ����졢Ǣ̸ҵ��");
			
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().addArticle(item).fromUser(wxMessage.getToUserName())
						.toUser(wxMessage.getFromUserName()).build();

				return m;
			}
		};*/
		
		WxMpMessageHandler handler4 = new WxMpMessageHandler() {
			@Override
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
					WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {			
				Item item = new Item();
				item.setTitle("������Ѷ");
				item.setDescription("����������ҵ��ѯ�������޹�˾���š��ͻ���һ���������ϡ���ԭ��������ҵ�����˳��ڵĺ�����ϵ��"
						+ "��˾ӵ��һ֧��ǿ�ļ����з����鲢�͹��ڴ�רԺУǿǿ���֣��з���ӵ������֪ʶ��Ȩ��");
				item.setPicUrl("");
				item.setUrl("");
			
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().addArticle(item).fromUser(wxMessage.getToUserName())
						.toUser(wxMessage.getFromUserName()).build();

				return m;
			}
		};

		wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
		wxMpMessageRouter.rule().async(false).content("����") // ��������Ϊ������������Ϣ
				.handler(handler).end().rule().async(false).event(WxConsts.EVT_SUBSCRIBE).handler(handler).end().rule()
				.event(WxConsts.EVT_UNSUBSCRIBE).async(false).handler(handler).end().rule().async(false)
				.event(WxConsts.BUTTON_CLICK).handler(handler4).end();
	}

	public boolean checkSignature(String timestamp, String nonce, String signature) {
		return wxMpService.checkSignature(timestamp, nonce, signature);
	}

	public WxMpXmlOutMessage route(WxMpXmlMessage inMessage) {
		return wxMpMessageRouter.route(inMessage);
	}

	public WxMpInMemoryConfigStorage getWxMpInMemoryConfigStorage() {
		return wxMpConfigStorage;
	}

	public WxMenu menuGet() throws WxErrorException {
		return wxMpService.menuGet();
	}

	public void menuCreate(WxMenu menu) throws WxErrorException {
		wxMpService.menuCreate(menu);
	}
	//΢��������Ϣ
	public void templateSend(WxMpTemplateMessage templateMessage) throws WxErrorException{
		wxMpService.templateSend(templateMessage);
	}

	public void menuDelete() throws WxErrorException {
		wxMpService.menuDelete();
	}

	public String oauth2buildAuthorizationUrl(String redirectURI, String scope, String state) {
		return wxMpService.oauth2buildAuthorizationUrl(redirectURI, scope, state);
	}

	public String getWxUser(String code) throws WxErrorException {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);

		return wxMpOAuth2AccessToken.getOpenId();
	}

	public static WechatService getInstance() {
		synchronized (uWechatServiceObject) {
			if (uWechatService == null) {
				uWechatService = new WechatService();
				uWechatService.init();
			}
		}
		return uWechatService;
	}
}
