package com.shlagoverflow.core.repository;

import com.shlagoverflow.core.model.TopicAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Leboc Philippe.
 */
public interface TopicAnswerRepository extends MongoRepository<TopicAnswer, String> {}
