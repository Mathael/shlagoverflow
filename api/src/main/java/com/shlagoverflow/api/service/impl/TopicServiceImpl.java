package com.shlagoverflow.api.service.impl;

import com.shlagoverflow.core.model.Tag;
import com.shlagoverflow.core.model.Topic;
import com.shlagoverflow.core.repository.TopicRepository;
import com.shlagoverflow.api.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    static
    {
        TOPICS.add(new Topic("Problème java", "Je n'ai pas la bonne version de java !"));
    }

    @Override
    public List<Topic> findAllMock() {
        return TOPICS;
    }

    @Override
    public Optional<Topic> create(String title, String content) {
        return Optional.of(repository.insert(new Topic(title, content)));
    }

    @Override
    public Optional<Topic> create(String title, String content, List<Tag> tags) {
        return Optional.of(repository.insert(new Topic(title, content, tags)));
    }
}
