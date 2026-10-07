package com.portfolio.cms.dto;
import jakarta.validation.constraints.*;
public record LoginRequest(@Email @NotBlank String email, @NotBlank @Size(min=8,max=100) String password) {}
