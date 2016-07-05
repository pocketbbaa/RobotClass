package interceptor;

import model.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2016/7/5.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handler2 = (HandlerMethod) handler;
            NotValidateLogin notval = handler2.getMethodAnnotation(NotValidateLogin.class);
            if (null != notval) {
                return true;
            }

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                String url = request.getRequestURL().toString();
                String redirectIndex = request.getContextPath() + "/login";
                response.sendRedirect(redirectIndex);
                return false;
            }
        }
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (LoginInterceptor.applicationContext == null) {
            LoginInterceptor.applicationContext = applicationContext;
        }
    }
}
