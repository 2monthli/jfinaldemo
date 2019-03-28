package me.test.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import me.test.blog.Blog;
import me.test.blog.BlogController;

public class UserConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants constants) {
		// TODO Auto-generated method stub
		PropKit.use("config.properties");//加载配置文件
		String path=PathKit.getWebRootPath();
		System.out.println(path);
		constants.setDevMode(true);
		constants.setViewType(ViewType.JSP);
		System.out.println("---加载核心配置成功----");
	}

	@Override
	public void configEngine(Engine arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configPlugin(Plugins plugin) {
		// TODO Auto-generated method stub
		C3p0Plugin cp = new C3p0Plugin(PropKit.get("oracle.url"),
				PropKit.get("oracle.username"), PropKit.get("oracle.password"));
        // 配置Oracle驱动
        cp.setDriverClass(PropKit.get("oracle.driver"));
        plugin.add(cp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        plugin.add(arp);
        // 配置Oracle方言
        arp.setDialect(new OracleDialect());
        // 配置属性名(字段名)大小写不敏感容器工厂
        arp.setContainerFactory(new CaseInsensitiveContainerFactory());
        arp.addMapping("test2", Blog.class);

	}

	@Override
	public void configRoute(Routes routes) {
		// TODO Auto-generated method stub
		routes.add("/user",UserController.class);
		routes.add("/blog",BlogController.class);
		System.out.println("----加载路由成功----");
	}

}
