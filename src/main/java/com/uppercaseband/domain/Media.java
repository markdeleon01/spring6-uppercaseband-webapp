package com.uppercaseband.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data       // Project Lombok
@Entity     // JPA annotation
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    // use string enum type instead of ordinal (the default which uses numbers)
    private MediaType type;

    private String path;
}
