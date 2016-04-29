package com.link.controller.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.link.common.util.PropUtil;
import com.link.model.weixin.TWeixinInfo;
import com.link.service.weixin.WeixinInfoService;
import com.link.weixin.main.MenuManager;
import com.link.weixin.pojo.AccessToken;
import com.link.weixin.pojo.WeiXinTokenCode;
import com.link.weixin.service.CoreService;
import com.link.weixin.util.LogUtil;
import com.link.weixin.util.SHA1;
import com.link.weixin.util.WeixinUtil;

@Controller
public class WeixinController {

	private static String TOKEN = PropUtil.getValue("weixinToken");
	
	private static String APPID = PropUtil.getValue("weixinAppid");
	private static String SECRET = PropUtil.getValue("weixinSecret");

	private WeixinInfoService weixinInfoService;
	
	@RequestMapping(value = "/weixin", method = RequestMethod.GET)
	public void validator(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 // 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        String[] str = { TOKEN, timestamp, nonce };
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();

        // 确认请求来至微信
        if (digest.equals(signature)) {
            response.getWriter().print(echostr);
        }

	}
	
	/**
	   *
	   * @param request
	   * @param model
	   * @return
	   */
	  @RequestMapping(value="/weixin/loadWeixinMenue.htm")
	  @ResponseBody
	  public Map  loadWeixinMenue(HttpServletRequest request,Model model){
	      Map resultMap = new HashMap();
	      
	      
	   
	      AccessToken at = WeixinUtil.getAccessToken(APPID, SECRET);
			if (at != null) {
				// 调用接口创建菜单
				int result = WeixinUtil.createMenu(MenuManager.getMenu(), at.getToken());
				System.out.println(result);
				// 判断菜单创建结果
				if (0 == result){
					resultMap.put("result", "菜单创建成功！");
					System.out.println("菜单创建成功！");
				}else{
					resultMap.put("result", "菜单创建失败，错误码");
				}
			}
	      
	      
	      return resultMap;
	  }
	
	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	public void validatorPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		LogUtil.printInfoLog("【微信发起请求】");
		 // 将请求、响应的编码均设置为UTF-8（防止中文乱码） 
        request.setCharacterEncoding("UTF-8"); 
        response.setCharacterEncoding("UTF-8"); 
 
        // 调用核心业务类接收消息、处理消息 
        String respMessage = new CoreService().processRequest(request); 
        
       
        // 响应消息 
        PrintWriter out = response.getWriter(); 
        out.print(respMessage); 
        out.close(); 
        
        

	}
	
	@RequestMapping(value = "/weixin/weixinFilter")
	public ModelAndView weixinFilter(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView mav = new ModelAndView();
		//获取从微信跳转过来的参数
		String contextPath = request.getContextPath();
		String wlcMenuType = request.getParameter("wlcMenuType");
		String openId = request.getParameter("openId");
		
		//如果openId为null，说明是view的menu进入，则使用网页授权得到openid
		if(StringUtil.isBlank(openId)){
			String code = request.getParameter("code");//我们要的code
			//通过code换取网页授权access_token
			WeiXinTokenCode wxtc = WeixinUtil.getAccessToken(code);
			openId = wxtc.getOpenid();
		}
		//设置session超时时间
		request.getSession().setMaxInactiveInterval(60*60);
		request.getSession().setAttribute("openId", openId);
		TWeixinInfo weixinInfo = null;
		//查询用户信息
		mav.addObject("openId", openId);
		weixinInfo =weixinInfoService.getWeixinInfoByOpenId(openId);
		if(weixinInfo!=null && weixinInfo.getUserId()!=null){
			////如果存在，说明已经绑定过了
			request.getSession().setAttribute("userId", weixinInfo.getUserId());////设置session的memberId，用于用户免登陆
			mav.addObject("userId", weixinInfo.getUserId());
		}else{
			request.getSession().removeAttribute("userId");
			mav.addObject("userId", null);
		}
		
		Map map = new HashMap();
		
		
			if("login".equals(wlcMenuType)){
				if(weixinInfo.getUserId()==null||"".equals(weixinInfo.getUserId())){
					mav.setView(new RedirectView(contextPath+"/login/register.htm"));
					LogUtil.printInfoLog("【重定向地址】："+contextPath);
					return mav;
				}			
			}
			
			
		mav.setView(new RedirectView(contextPath+"/login/unbound.htm"));
		return mav;
	}
	
}
