package com.portfolio.cms.entity;

import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="skills") @Getter @Setter @NoArgsConstructor
public class Skill extends BaseEntity {
    @Column(nullable=false,length=100) @NotBlank @Size(max=100) private String name;
    @Column(length=60) private String category;
    private Integer proficiency;
    private String iconUrl;
}
