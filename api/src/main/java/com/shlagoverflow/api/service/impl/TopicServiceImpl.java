package com.shlagoverflow.api.service.impl;

import com.shlagoverflow.api.util.LuceneUtil;
import com.shlagoverflow.core.model.Topic;
import com.shlagoverflow.api.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
@Service
public class TopicServiceImpl implements TopicService {

    private static Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

    private static final List<Topic> TOPICS = new ArrayList<>();
    static
    {
        TOPICS.add(new Topic("Problème java", "Je n'ai pas la bonne version de java !"));

        final LuceneUtil lucene = LuceneUtil.getInstance();
        LOGGER.info("Indexing data...");
        TOPICS.forEach(t -> lucene.indexTitle(t.getTitle()));
        LOGGER.info(String.format("%s %d %s", "Index complete with ", TOPICS.size(), " entries"));
    }

    @Override
    public List<Topic> findAllMock() {
        return TOPICS;
    }
}
