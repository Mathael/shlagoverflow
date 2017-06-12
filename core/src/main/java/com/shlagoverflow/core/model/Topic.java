package com.shlagoverflow.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Leboc Philippe.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    private String id;

    private String title;

    private String content;

    private long createdDate;

    public Topic(String title, String content) {
        this(null, title, content, 0);
    }
}
