package com.portfolio.cms.entity;

import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="messages") @Getter @Setter @NoArgsConstructor
public class Message extends BaseEntity {
    @Column(nullable=false,length=120) private String name;
    @Column(nullable=false,length=190) private String email;
    @Column(length=200) private String subject;
    @Column(nullable=false,columnDefinition="TEXT") private String message;
    @Column(nullable=false) private boolean read = false;
}
