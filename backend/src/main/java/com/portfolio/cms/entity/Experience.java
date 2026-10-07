package com.portfolio.cms.entity;

import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="experience") @Getter @Setter @NoArgsConstructor
public class Experience extends BaseEntity {
    @Column(nullable=false,length=160) @NotBlank @Size(max=160) private String title;
    @Column(nullable=false,length=160) @NotBlank @Size(max=160) private String organization;
    private String location;
    @Column(length=40) private String startDate;
    @Column(length=40) private String endDate;
    @Column(columnDefinition="TEXT") private String description;
    private Integer displayOrder = 0;
}
