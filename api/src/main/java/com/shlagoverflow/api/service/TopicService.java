package com.shlagoverflow.api.service;

import com.shlagoverflow.core.model.Tag;
import com.shlagoverflow.core.model.Topic;
import com.shlagoverflow.core.model.UserAccount;

import java.util.List;
import java.util.Optional;

/**
 * @author Leboc Philippe.
 */
public interface TopicService extends DefaultService<Topic> {
    Optional<Topic> create(UserAccount owner, String title, String content);
    Optional<Topic> create(UserAccount owner, String title, String content, List<Tag> tags);
}
