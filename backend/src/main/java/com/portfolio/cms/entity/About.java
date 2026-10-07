package com.portfolio.cms.entity;

import jakarta.persistence.*; import jakarta.validation.constraints.*;
import lombok.*;

@Entity @Table(name="about") @Getter @Setter @NoArgsConstructor
public class About extends BaseEntity {
    @Column(nullable=false, length=160) @NotBlank @Size(max=160) private String title;
    @Column(nullable=false, columnDefinition="TEXT") @NotBlank @Size(max=10000) private String summary;
    @Column(columnDefinition="TEXT") private String description;
    private String imageUrl;
    private String resumeUrl;
}
