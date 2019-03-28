package me.test.blog;

import java.sql.SQLException;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

@Before(BlogInterceptor.class)
public class BlogController extends Controller {
  public void index() {
	List<Blog> p = Blog.dao.find("select * from test2");
	//Page<Blog> p = Blog.dao.paginate(1, 10, "select * ","from test2");
    setAttr("page", p);
    //System.out.println(getAttr("page").toString());
    render("/WEB-INF/jsp/index2.jsp");
  }
 
 
  @Before(value={Tx.class})
  public void save()  {
	  //Db.update("delete from test2 where name='台'");
	  //int a=1/0;
	  for(int i=0;i<10;i++){
		  
		  Db.update("insert into test2(name) VALUES('台"+i+"')");
		  if(i==5){
				  //int a=1/0;
		  }
	  }
      renderText("ok");
  }
  public void pay() {  
      boolean bl = Db.tx(new IAtom() {  
          @Override  
          public boolean run() throws SQLException {  
        	  try {
        		  Db.update("delete from test2 where name like '台%'");
        		  int a=1/0;
        		  Db.update("insert into test2(name) VALUES('台a')");
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
        	  
                
              return true;  
          }  
      });  
      System.out.println(bl);
        
      renderText("pay ok");
  }  

 
  public void edit() {
    setAttr("blog", Blog.dao.findById(getParaToInt()));
  }
 
  @Before(BlogValidator.class)
  public void update() {
    getModel(Blog.class).update();
  }
 
  public void delete() {
    Blog.dao.deleteById(getParaToInt());
  }
}