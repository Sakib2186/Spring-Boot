package com.eazybytes.jobportal.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component("auditAwareImp")
public class AuditAwareImp implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Anonymous User"); // Later update when we learn sping security. time and Date auto done by Spring JPA
    }
}
