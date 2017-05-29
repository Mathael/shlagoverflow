package com.shlagoverflow.core.repository;

import com.shlagoverflow.core.model.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Leboc Philippe.
 */
public interface TopicRepository extends MongoRepository<Topic, String> {}
