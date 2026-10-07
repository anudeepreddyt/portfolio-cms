package com.portfolio.cms.entity;

import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="projects") @Getter @Setter @NoArgsConstructor
public class Project extends BaseEntity {
    @Column(nullable=false,length=160) @NotBlank @Size(max=160) private String title;
    @Column(nullable=false,columnDefinition="TEXT") @NotBlank @Size(max=10000) private String description;
    @Column(length=500) private String technologies;
    private String imageUrl;
    private String githubUrl;
    private String liveUrl;
    private Integer displayOrder = 0;
}
