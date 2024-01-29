package com.pe.HeoComisiones.Config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            //SI NO ESTA REGISTRADO QUE GUARDE "NO REGISTRADO"
            return Optional.of("NO REGISTRADO");
        }
        return Optional.of(authentication.getName().toUpperCase());
    }
}
