package com.lxq.service;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 
 * @ClassName: MenuManger
 * @author: ChenXZ
 * @date: 2016��4��24�� ����11:47:32
 */
public class MenuManger {

	private WechatService wxMpService = new WechatService().getInstance();

	/**
	 * 
	 * @Title: CheckMenu
	 * @return: void
	 */
	public void CheckMenu() { 
		try {
			// 1.��ȡ���ں��Զ���˵�
			// 2.�ж��Ƿ��Ѿ������Զ���˵�
			WxMenu wxMenuOld = wxMpService.menuGet();
			if (wxMenuOld == null) {
				WxMenu wxMenuNew = new WxMenu();
				WxMenuButton businessButton = new WxMenuButton();
				businessButton.setName("ҵ�����1");
				businessButton.setType(WxConsts.BUTTON_VIEW);
				businessButton.setUrl(buildAuthorizationUrl(
						"http://lxq2017.imwork.net/SSH_Wechat/framework/user/business.action",
						WxConsts.OAUTH2_SCOPE_BASE, null));

				WxMenuButton mineButton = new WxMenuButton();
				mineButton.setType(WxConsts.BUTTON_VIEW);
				mineButton.setName("�ҵĶ���ȥ");
				mineButton.setUrl(
						buildAuthorizationUrl("http://lxq2017.imwork.net/YHsys/wechatController/page/mine.action",
								WxConsts.OAUTH2_SCOPE_BASE, null));

				WxMenuButton yhButton = new WxMenuButton();
				yhButton.setName("Y��H");

				wxMenuNew.getButtons().add(businessButton);
				wxMenuNew.getButtons().add(mineButton);
				wxMenuNew.getButtons().add(yhButton);

				WxMenuButton newsButton = new WxMenuButton();
				newsButton.setType(WxConsts.BUTTON_CLICK);
				newsButton.setName("������Ѷ");
				newsButton.setKey("news");

				WxMenuButton luButton = new WxMenuButton();
				luButton.setType(WxConsts.BUTTON_VIEW);
				luButton.setName("��ϵ����");
				luButton.setUrl(
						buildAuthorizationUrl("http://lxq2017.imwork.net/YHsys/wechatController/page/callus.action",
								WxConsts.OAUTH2_SCOPE_BASE, null));

				WxMenuButton auButton = new WxMenuButton();
				auButton.setType(WxConsts.BUTTON_VIEW);
				auButton.setName("��������");
				auButton.setUrl(
						buildAuthorizationUrl("http://lxq2017.imwork.net/YHsys/wechatController/page/aboutUs.action",
								WxConsts.OAUTH2_SCOPE_BASE, null));

				yhButton.getSubButtons().add(newsButton);
				yhButton.getSubButtons().add(luButton);
				yhButton.getSubButtons().add(auButton);

				wxMpService.menuCreate(wxMenuNew);
				
				System.out.println("��ʼ���Զ��尴ť�ɹ�1��");

			} else {
				wxMpService.menuDelete();
				WxMenu wxMenuNew = new WxMenu();
				WxMenuButton businessButton = new WxMenuButton();
				businessButton.setName("ҵ�����4");
				businessButton.setType(WxConsts.BUTTON_VIEW);
				businessButton.setUrl(buildAuthorizationUrl(
						"http://lxq2017.imwork.net/SSH_Wechat/framework/user/business.action",
						WxConsts.OAUTH2_SCOPE_BASE, null));

				WxMenuButton mineButton = new WxMenuButton();
				mineButton.setType(WxConsts.BUTTON_VIEW);
				mineButton.setName("�ҵĶ���ȥ");
				mineButton.setUrl(
						buildAuthorizationUrl("http://lxq2017.imwork.net/YHsys/wechatController/page/mine.action",
								WxConsts.OAUTH2_SCOPE_BASE, null));

				WxMenuButton yhButton = new WxMenuButton();
				yhButton.setName("Y��H");

				wxMenuNew.getButtons().add(businessButton);
				wxMenuNew.getButtons().add(mineButton);
				wxMenuNew.getButtons().add(yhButton);
				
				WxMenuButton newsButton = new WxMenuButton();
				newsButton.setType(WxConsts.BUTTON_CLICK);
				newsButton.setName("������Ѷ");
				newsButton.setKey("news");

				WxMenuButton luButton = new WxMenuButton();
				luButton.setType(WxConsts.BUTTON_VIEW);
				luButton.setName("��ϵ����");
				luButton.setUrl(
						buildAuthorizationUrl("http://lxq2017.imwork.net/YHsys/wechatController/page/callus.action",
								WxConsts.OAUTH2_SCOPE_BASE, null));

				WxMenuButton auButton = new WxMenuButton();
				auButton.setType(WxConsts.BUTTON_VIEW);
				auButton.setName("��������");
				auButton.setUrl(
						buildAuthorizationUrl("http://lxq2017.imwork.net/YHsys/wechatController/page/aboutUs.action",
								WxConsts.OAUTH2_SCOPE_BASE, null));
				
				yhButton.getSubButtons().add(newsButton);
				yhButton.getSubButtons().add(luButton);
				yhButton.getSubButtons().add(auButton);

				wxMpService.menuCreate(wxMenuNew);
				System.out.println("�����Զ��尴ť�ɹ�2��");
			}

		} catch (WxErrorException e) {
			e.printStackTrace();
			System.out.println("�Զ���˵�����ʧ�ܣ�");
			
		}
	}

	/**
	 * 
	 * @Title: buildAuthorizationUrl
	 * @param redirectURI
	 * @param scope
	 * @param state
	 * @return
	 * @return: String
	 */
	private String buildAuthorizationUrl(String redirectURI, String scope, String state) {
		return wxMpService.oauth2buildAuthorizationUrl(redirectURI, scope, state);
	}

	/**
	 * 
	 * @Title: main
	 * @param args
	 * @return: void
	 */
	public static void main(String[] args) {
		MenuManger mm = new MenuManger();
		String url = mm.buildAuthorizationUrl("http://lxq2017.imwork.net/YHsys/wechatController/page/mine.action",
				WxConsts.OAUTH2_SCOPE_BASE, null);
		System.out.println(url);
	}
}
