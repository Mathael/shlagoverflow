package com.shlagoverflow.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
@Data
public class Tag {
    @Id
    private String id;

    private String name;

    @DBRef(lazy = true)
    private List<Topic> topics;
}
