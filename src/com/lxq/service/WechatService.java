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
 * @date: 2016年4月22日 下午11:52:03
 */

public class WechatService { 
 
	/** 微信公众号配置信息 **/
	private WxMpInMemoryConfigStorage wxMpConfigStorage;
	/** 服务类 **/
	private WxMpService wxMpService;
	/** 消息路由 **/
	private WxMpMessageRouter wxMpMessageRouter;

	/** 单例 **/
	private static WechatService uWechatService = null;
	private static Object uWechatServiceObject = new Object();

	public WechatService() {
		// TODO Auto-generated constructor stub
	}
	
	private void init() {
		wxMpConfigStorage = new WxMpInMemoryConfigStorage();
		wxMpConfigStorage.setAppId("wx0c792f8b27916232"); // 设置微信公众号的appid
		wxMpConfigStorage.setSecret("05c4c9112586340e9825213e85a9b47d"); // 设置微信公众号的app
																			// corpSecret
		wxMpConfigStorage.setToken("gzyhinctoken"); // 设置微信公众号的token
		wxMpConfigStorage.setAesKey("AjBXFHAqlNC3lasz1Ks9HWhBQgOFmXhAdm0Jj4yTSzJ"); // 设置微信公众号的EncodingAESKey

		
		wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage);

		WxMpMessageHandler handler = new WxMpMessageHandler() {
			@Override
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
					WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {

				WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT().content("您好，感谢关注广州永弘企业咨询管理有限公司-永弘财税助手，我们竭诚为您服务!如需帮助，请拨打我们的服务热线：020-83208891").fromUser(wxMessage.getToUserName())
						.toUser(wxMessage.getFromUserName()).build();

				return m;
			}
		};
		
		/*WxMpMessageHandler handler2 = new WxMpMessageHandler() {
			@Override
			public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
					WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {			
				Item item = new Item();
				item.setTitle("联系我们");
				item.setDescription("邮箱：ling.f@rendetown.com\n电话：020-86682999\n地址：广州市荔湾区周门北路38号805室/广州市荔湾区环市西路139号汇美国际7楼前台");
			
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
				item.setTitle("关于我们");
				item.setDescription("广州永弘企业咨询管理有限公司位于广州市荔湾区环市西路139号二楼自编2A060（仅限办公用途）交通便利。"
						+ "广州永弘企业咨询管理有限公司本着“客户第一，诚信至上”的原则，与多家企业建立了长期的合作关系。公司拥有一支较强的技术研发"
						+ "队伍并和国内大专院校强强联手，研发出拥有自主知识产权。热诚欢迎各界朋友前来参观、考察、洽谈业务。");
			
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
				item.setTitle("最新资讯");
				item.setDescription("广州永弘企业咨询管理有限公司本着“客户第一，诚信至上”的原则，与多家企业建立了长期的合作关系。"
						+ "公司拥有一支较强的技术研发队伍并和国内大专院校强强联手，研发出拥有自主知识产权。");
				item.setPicUrl("");
				item.setUrl("");
			
				WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS().addArticle(item).fromUser(wxMessage.getToUserName())
						.toUser(wxMessage.getFromUserName()).build();

				return m;
			}
		};

		wxMpMessageRouter = new WxMpMessageRouter(wxMpService);
		wxMpMessageRouter.rule().async(false).content("哈哈") // 拦截内容为“哈哈”的消息
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
	//微信推送消息
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
