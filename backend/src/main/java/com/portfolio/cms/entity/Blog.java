package com.portfolio.cms.entity;

import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*; import java.time.LocalDate;
@Entity @Table(name="blogs") @Getter @Setter @NoArgsConstructor
public class Blog extends BaseEntity {
    @Column(nullable=false,length=180) @NotBlank @Size(max=180) private String title;
    @Column(nullable=false,unique=true,length=200) @NotBlank @Size(max=200) private String slug;
    @Column(length=300) private String excerpt;
    @Column(nullable=false,columnDefinition="TEXT") @NotBlank @Size(max=50000) private String content;
    private String coverImageUrl;
    private String tags;
    private LocalDate publishedDate;
    private boolean published = true;
}
