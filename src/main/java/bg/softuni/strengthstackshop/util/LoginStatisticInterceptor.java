package bg.softuni.strengthstackshop.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginStatisticInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginStatisticInterceptor.class);
    private static Map<String, Integer> loginCountMap = new HashMap<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (session.getAttribute(this.getClass().getName() + ".alreadyHandled") == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
                String userEmail = authentication.getName();
                incrementLoginCount(userEmail);
                LOGGER.info("Number Logins for user {}: {}", userEmail, getLoginCount(userEmail));

                session.setAttribute(this.getClass().getName() + ".alreadyHandled", true);
            }
        }

        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        // This method is intentionally left blank because post-processing logic is not required
        // in the current context of this interceptor.
        // The necessary actions are performed in the preHandle method.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HttpSession session = request.getSession();
        session.removeAttribute("alreadyHandled");
    }
    public static Map<String, Integer> getLoginCountMap() {
        return new HashMap<>(loginCountMap);
    }

    public static int getLoginCount(String userEmail) {
        return loginCountMap.getOrDefault(userEmail, 0);
    }

    private static void incrementLoginCount(String userEmail) {
        int count = getLoginCount(userEmail);
        loginCountMap.put(userEmail, count + 1);
    }


}
