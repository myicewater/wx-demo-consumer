package com.link.controller.dubbo;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dubble.service.hello.HelloService2;



@Controller
public class DubboContorller {

	private static final Logger logger = Logger.getLogger(DubboContorller.class);
	@Autowired
	private HelloService2 helloService2;
		
		@ResponseBody
		@RequestMapping("/dubbo/test.htm")
		public Map dubboTest(){
			
			String result = helloService2.hello("plasea");
			logger.info("service:"+result);
			return null;
		}
}
