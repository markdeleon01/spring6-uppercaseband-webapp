package com.uppercaseband.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDTO { // DTO:  Data Transfer Object

    private String type;

    private String url;
}
