package com.shlagoverflow.api.service;

import com.shlagoverflow.core.model.Topic;
import com.shlagoverflow.core.model.TopicAnswer;
import com.shlagoverflow.core.model.UserAccount;

/**
 * @author Leboc Philippe.
 */
public interface TopicAnswerService extends DefaultService<TopicAnswer> {
    TopicAnswer create(UserAccount owner, Topic relatedTopic, String content);
    TopicAnswer create(UserAccount owner, String relatedTopicId, String content);
}
