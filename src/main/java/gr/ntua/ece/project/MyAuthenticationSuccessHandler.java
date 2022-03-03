package gr.ntua.ece.project;


import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.repositories.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class MyAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;

    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {

        handle(request, response, authentication);

//        User user = userRepository.findByUsername(authentication.getName());
//        HttpSession session = request.getSession(true);
//        session.setAttribute("user", user);

        clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication,request);

        if (response.isCommitted()) {
            logger.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication,HttpServletRequest request) {
        boolean isParent = false;
        boolean isProvider = false;
        boolean isAdmin = false;
        boolean isBanned =false;
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        if(userRepository.findByUsername(authentication.getName()).getIsBanned()==1)
            isBanned=true;
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("parent")) {
                isParent = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("provider")) {
                isProvider = true;
                break;
            }
            else if (grantedAuthority.getAuthority().equals("administrator")) {
                isAdmin = true;
                break;
            }
        }
        if (isBanned) {
            SecurityContextHolder.getContext().setAuthentication(null);

            return "/login?message=you are banned";
        }
      else if (isParent) {
            return "/parent";
        } else if (isProvider) {
            return "/provider";
        } else if (isAdmin) {
            return "/admin";
        }

            else {
            throw new IllegalStateException();
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}