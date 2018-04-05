package club.javalearn.basic.security.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 为了解决js中路径问题
 *
 * @author king-pan
 * @date 2018-04-03
 **/
@WebListener
@Slf4j
public class BasicPathHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("Session创建");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path = request.getContextPath();
        //绝对路径
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        request.setAttribute("URL",basePath);
        se.getSession().setAttribute("URL",basePath);
        log.info("请求绝对路径为:"+basePath);


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("销毁Session");
    }
}
