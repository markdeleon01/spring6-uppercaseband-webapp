package com.uppercaseband.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity // JPA annotation
@Data   // Project Lombok
public class Article {  // domain object (POJO)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer displayOrder;

    @OneToOne(cascade = CascadeType.ALL)
    // will cascade down to related media
    private Media media;

    @Lob
    //String has 255 characters limit; JPA will store this in a CLOB database field
    private String subContent;

    @Enumerated(value = EnumType.STRING)
    // use string enum type instead of ordinal (the default which uses numbers)
    private Category category;
}
