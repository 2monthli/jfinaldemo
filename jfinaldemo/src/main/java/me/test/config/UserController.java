package me.test.config;

import java.util.HashMap;
import java.util.Map;

import com.jfinal.core.Controller;

public class UserController extends Controller {
	public void index(){
		Map<String,String> map=new HashMap<>();
		map.put("name", "lep");
		map.put("age", "25");
		setAttr("users", map);
		//render("/WEB-INF/jsp/index.jsp");
		render("/WEB-INF/html/test.html");
	}
}
