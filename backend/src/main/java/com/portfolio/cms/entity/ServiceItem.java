package com.portfolio.cms.entity;

import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="services") @Getter @Setter @NoArgsConstructor
public class ServiceItem extends BaseEntity {
    @Column(nullable=false,length=140) @NotBlank @Size(max=140) private String title;
    @Column(nullable=false,columnDefinition="TEXT") @NotBlank @Size(max=10000) private String description;
    private String icon;
    private Integer displayOrder = 0;
}
