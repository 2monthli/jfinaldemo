package me.test.blog;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ErrorInterceptor implements Interceptor {
 
	public void intercept(Invocation ai) {
		try {
			ai.invoke();
		} catch (Exception e) {
			System.out.println(e);
			ai.getController().renderText(e.getMessage());
			/*JSONObject jo = JSONObject.parseObject(e.getMessage());
			ai.getController().renderJson(jo.toJSONString());*/
		}
	}
}
