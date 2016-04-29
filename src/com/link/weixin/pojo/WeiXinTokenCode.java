package com.link.weixin.pojo;
/**
 * 
 * @author 朱素海
 *
 * 用户用户授权用token
 * 1、微信网页授权是通过OAuth2.0机制实现的，在用户授权给公众号后，公众号可以获取到一个网页授权特有的接口调用凭证（网页授权access_token），
 * 通过网页授权access_token可以进行授权后接口调用，如获取用户基本信息；
 * 2、其他微信接口，需要通过基础支持中的“获取access_token”接口来获取到的普通access_token调用。
 */
public class WeiXinTokenCode {
	private String access_token;
	private String expires_in;
	private String refresh_token;
	private String openid;
	private String scope;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}
