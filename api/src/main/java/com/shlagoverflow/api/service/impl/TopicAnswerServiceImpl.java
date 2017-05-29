package com.shlagoverflow.api.service.impl;

import com.shlagoverflow.core.model.Topic;
import com.shlagoverflow.core.model.TopicAnswer;
import com.shlagoverflow.core.model.UserAccount;
import com.shlagoverflow.core.repository.TopicAnswerRepository;
import com.shlagoverflow.core.repository.TopicRepository;
import com.shlagoverflow.api.service.TopicAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Leboc Philippe.
 */
@Service
public class TopicAnswerServiceImpl extends DefaultImpl<TopicAnswer, TopicAnswerRepository> implements TopicAnswerService {

    @Autowired
    private TopicAnswerRepository repository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public TopicAnswer create(UserAccount owner, Topic relatedTopic, String content) {
        return repository.insert(new TopicAnswer(owner, relatedTopic, content));
    }

    @Override
    public TopicAnswer create(UserAccount owner, String relatedTopicId, String content) {
        final Topic topic = topicRepository.findOne(relatedTopicId);
        return repository.insert(new TopicAnswer(owner, topic, content));
    }
}
