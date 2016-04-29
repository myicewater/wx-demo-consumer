package com.link.weixin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.link.common.util.PropUtil;
import com.link.weixin.pojo.AccessToken;
import com.link.weixin.pojo.Button;
import com.link.weixin.pojo.ClickButton;
import com.link.weixin.pojo.ComplexButton;
import com.link.weixin.pojo.Menu;
import com.link.weixin.pojo.ViewButton;
import com.link.weixin.util.WeixinUtil;

/**
 * 
 * @author 朱素海
 *
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	private static String APPID = PropUtil.getValue("weixinAppid");
	private static String SECRET = PropUtil.getValue("weixinSecret");
	
	public static void main(String[] args) {
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(APPID, SECRET);
		if (at != null) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());
			System.out.println(result);
			// 判断菜单创建结果
			if (0 == result){
				log.info("菜单创建成功！");
			System.out.println("菜单创建成功！");
			}else{
				log.info("菜单创建失败，错误码：" + result);
			}
		}
	}
	
	
	public static String getUrlByKeyWord(String keyWord){
		//获取项目路径
		String path = PropUtil.getValue("weixinServerAddr");
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+path+"winxin/winxinFilter.htm?menuType="+keyWord+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
		return url;
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	public static Menu getMenu() {
		//获取项目路径
		String path = PropUtil.getValue("weixinServerAddr");
		
		//链接类型的菜单
		ViewButton indexMenue = new ViewButton();
		indexMenue.setName("链接菜单");
		indexMenue.setType("view");
		indexMenue.setUrl(getUrlByKeyWord("linkKeyWord"));
		//点击事件的按钮
		ClickButton  btnMenu = new ClickButton();
		btnMenu.setName("按钮菜单");
		btnMenu.setType("click");
		btnMenu.setKey("fuck");
		//二级菜单子菜单
		
		ClickButton  subMenu = new ClickButton();
		subMenu.setName("二级子菜单");
		subMenu.setType("click");
		subMenu.setKey("girl");
		
		ComplexButton complexButton = new ComplexButton();
		complexButton.setName("二级菜单");
		complexButton.setSub_button(new Button[] { subMenu});
		
		//总菜单
		Menu menu = new Menu();
		menu.setButton(new Button[] {indexMenue,btnMenu, complexButton });

		return menu;
	}
}
