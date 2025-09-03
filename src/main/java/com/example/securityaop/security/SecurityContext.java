package com.example.securityaop.security;

import java.util.Set;

public class SecurityContext {
    private static final ThreadLocal<String> currentUser = new ThreadLocal<>();
    private static final ThreadLocal<Set<String>> currentRoles = new ThreadLocal<>();

    public static void set(String email, Set<String> roles) {
        currentUser.set(email);
        currentRoles.set(roles);
    }
    public static void clear() {
        currentUser.remove();
        currentRoles.remove();
    }
    public static String getUser() { return currentUser.get(); }
    public static Set<String> getRoles() { return currentRoles.get(); }
    public static boolean hasRole(String r) {
        Set<String> roles = currentRoles.get();
        return roles != null && roles.contains(r);
    }
}
