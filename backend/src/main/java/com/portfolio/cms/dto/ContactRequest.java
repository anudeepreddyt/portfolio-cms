package com.portfolio.cms.dto;
import jakarta.validation.constraints.*;
public record ContactRequest(@NotBlank @Size(max=120) String name,@NotBlank @Email @Size(max=190) String email,@Size(max=200) String subject,@NotBlank @Size(min=10,max=5000) String message) {}
