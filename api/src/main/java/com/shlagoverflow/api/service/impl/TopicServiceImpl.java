package com.shlagoverflow.api.service.impl;

import com.shlagoverflow.core.model.Tag;
import com.shlagoverflow.core.model.Topic;
import com.shlagoverflow.core.model.UserAccount;
import com.shlagoverflow.core.repository.TopicRepository;
import com.shlagoverflow.api.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Leboc Philippe.
 */
@Service
public class TopicServiceImpl extends DefaultImpl<Topic, TopicRepository> implements TopicService {

    @Autowired
    private TopicRepository repository;

    private static final List<Topic> TOPICS = new ArrayList<>();

    @PostConstruct
    public void init() {}

    @Override
    public Optional<Topic> create(UserAccount owner, String title, String content) {
        return Optional.of(repository.insert(new Topic(owner, title, content)));
    }

    @Override
    public Optional<Topic> create(UserAccount owner, String title, String content, List<Tag> tags) {
        return Optional.of(repository.insert(new Topic(owner, title, content, tags)));
    }
}
