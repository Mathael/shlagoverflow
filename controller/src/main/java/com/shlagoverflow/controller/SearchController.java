package com.shlagoverflow.controller;

import com.shlagoverflow.api.service.TopicService;
import com.shlagoverflow.core.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/search", consumes = "application/json", produces = "application/json")
public class SearchController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Topic> search() {
        return topicService.findAllMock();
    }
}
