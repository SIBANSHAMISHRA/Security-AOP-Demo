package com.example.securityaop.aspect;

import com.example.securityaop.annotation.RequireRoles;
import com.example.securityaop.security.SecurityContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    @Before("@annotation(requireRoles)")
    public void checkRoles(JoinPoint joinPoint, RequireRoles requireRoles) {
        String[] roles = requireRoles.value();
        System.out.println("[AUTHZ] Checking roles for: " + joinPoint.getSignature());
        for (String r : roles) {
            if (!SecurityContext.hasRole(r)) {
                System.out.println("[AUTHZ] Access denied, missing role: " + r);
                throw new SecurityException("Role " + r + " required");
            }
        }
        System.out.println("[AUTHZ] Access granted");
    }
}
