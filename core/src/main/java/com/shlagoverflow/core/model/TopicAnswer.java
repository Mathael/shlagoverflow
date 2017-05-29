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
public class TopicAnswer {

    @Id
    private String id;

    @DBRef(lazy = true)
    private UserAccount owner;

    @DBRef(lazy = true)
    private Topic relatedTopic;

    private long createdDate;

    private String content;

    // Store all already voted users ids to prevent from double vote
    private List<String> ratedUsersIds;

    private int rating;

    public TopicAnswer(UserAccount owner, Topic relatedTopic, String content) {
        this(null, owner, relatedTopic, Utils.getCurrentTime(), content, new ArrayList<>(), 0);
    }
}
