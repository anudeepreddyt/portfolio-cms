package com.portfolio.cms.entity;

import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="media") @Getter @Setter @NoArgsConstructor
public class Media extends BaseEntity {
    @Column(nullable=false) private String originalName;
    @Column(nullable=false,unique=true) private String storedName;
    @Column(nullable=false) private String url;
    private String contentType;
    private Long size;
}
