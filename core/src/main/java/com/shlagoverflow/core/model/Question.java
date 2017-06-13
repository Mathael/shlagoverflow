package com.shlagoverflow.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Leboc Philippe.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {

    private String id;

    private String title;

    private String answer;

    private long answerUnixTime;

    public Question(String title) {
        this(null, title, null, 0L);
    }

    public Question(String title, String answer) {
        this(null, title, answer, 0L);
    }
}
