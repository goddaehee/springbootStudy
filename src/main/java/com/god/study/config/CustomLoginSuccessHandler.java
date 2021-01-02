package com.god.study.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//이전 페이지 정보를 받아 Redirect하는 SavedRequestAwareAuthenticationSuccessHandler
//SimpleUrlAuthenticationSuccessHandler를 상속하는 SavedRequestAwareAuthenticationSuccessHandler를 상속
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public CustomLoginSuccessHandler(String defaultTargetUrl) {
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("prevPage");
            if (redirectUrl != null) {
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
