package cn.zbw.logistics.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import cn.zbw.logistics.pojo.User;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	
	
	//将cookie中的信息放到shiro的session中
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		//从请求中获取shiro的主体
		Subject subject = getSubject(request, response);
		//从主体中获取shiro框架的session
		Session session = subject.getSession();
		//如果主体没有认证（session中没有认证）并且主体已经设置记住我了
		if(!subject.isAuthenticated()&&subject.isRemembered()) {
			//获取主体身份(从cookie中获取)
			User principal = (User) subject.getPrincipal();
			//将身份认证信息共享到session中
			session.setAttribute("principal", principal);
		}
		return subject.isAuthenticated()||subject.isRemembered();
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		//获取请求参数中的验证码
		String verifyCode = request.getParameter("verifyCode");
		//获取session中的验证码
		HttpServletRequest req = (HttpServletRequest) request;
		String rand = (String) req.getSession().getAttribute("rand");
		
		//对比
		if(StringUtils.isNotBlank(verifyCode)&&StringUtils.isNotBlank(rand)) {
			//全部转小写
			verifyCode = verifyCode.toLowerCase();
			rand = rand.toLowerCase();
			if(!rand.equals(verifyCode)) {
				req.setAttribute("errorMsg", "验证码错误");
				req.getRequestDispatcher("/login.jsp").forward(request, response);
				return false;
			}
		}
		
		return super.onAccessDenied(request, response, mappedValue);
	}
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		//清楚shiro记录的上一个请求路径
		//1.手动清除
		Session session = subject.getSession(false);
		if(session!=null) {
			//清楚shiro共享的上一次地址
			session.removeAttribute(WebUtils.SAVED_REQUEST_KEY);
		}
		//方法二，调用webutils工具类中写好的清除方法
		//WebUtils.getAndClearSavedRequest(request);
		return super.onLoginSuccess(token, subject, request, response);
	}
}
