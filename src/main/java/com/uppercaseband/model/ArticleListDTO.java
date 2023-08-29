package com.uppercaseband.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListDTO {  // wrapper object for the list of articles

    List<ArticleDTO> articles;
}
