/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpSession;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author trippkm
 */
public class LoginAction extends ActionSupport 
{
    private String userId;
    private String passwd;
    
    public String execute() throws Exception 
    {
        if("admin".equals(userId) && "password".equals(passwd))
        {
            Map session = ActionContext.getContext().getSession();
            session.put("logined", "true");
            session.put("context", new Date());
            return SUCCESS;
        }
        return ERROR;
    }
    
    public String logout() throws Exception
    {
        Map session = ActionContext.getContext().getSession();
        session.remove("logined");
        session.remove("context");
        return SUCCESS;
    }
    
    public String getPasswd()
    {
        return passwd;
    }
    
    public void setPsswd(String passwd)
    {
        this.passwd = passwd;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
}
