package com.rdkp63.ecom_product_services.util.context;

import com.rdkp63.ecom_product_services.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class UserContext {

    @Autowired
    private HttpServletRequest request;

    public Long getUserId() {
        String userId = request.getHeader("X-User-Id");
        if (userId == null) {
            throw new UnauthorizedException("Missing user context");
        }
        return Long.valueOf(userId);
    }

    public String getRoles() {
        return request.getHeader("X-User-Roles");
    }
}
