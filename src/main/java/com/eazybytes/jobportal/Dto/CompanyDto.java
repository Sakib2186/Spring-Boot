package com.eazybytes.jobportal.Dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

public record CompanyDto(Long Id, String name, String logo, String industry,
                         String size, BigDecimal rating, String locations, Integer founded,
                         String description, BigInteger employees, String website,
                         Instant createdAt) {
}
