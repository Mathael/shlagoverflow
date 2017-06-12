package com.shlagoverflow.core.model;

import com.shlagoverflow.core.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    private String id;

    private String title;

    private String content;

    private long createdDate;

    @DBRef(lazy = true)
    private List<Tag> tags;

    @DBRef(lazy = true)
    private List<TopicAnswer> answers;

    public Topic(String title, String content) {
        this(title, content, new ArrayList<>());
    }

    public Topic(String title, String content, List<Tag> tags) {
        this(null, title, content, Utils.getCurrentTime(), tags, new ArrayList<>());
    }
}
