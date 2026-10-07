package com.portfolio.cms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="users") @Getter @Setter @NoArgsConstructor
public class User extends BaseEntity {
    @Column(nullable=false, unique=true, length=190) private String email;
    @Column(nullable=false) private String passwordHash;
    @Column(nullable=false, length=30) private String role = "ADMIN";
}
