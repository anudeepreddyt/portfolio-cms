package com.portfolio.cms.entity;

import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="testimonials") @Getter @Setter @NoArgsConstructor
public class Testimonial extends BaseEntity {
    @Column(nullable=false,length=120) @NotBlank @Size(max=120) private String name;
    @Column(length=120) private String role;
    @Column(nullable=false,columnDefinition="TEXT") @NotBlank @Size(max=5000) private String message;
    private String imageUrl;
    private Integer displayOrder = 0;
}
