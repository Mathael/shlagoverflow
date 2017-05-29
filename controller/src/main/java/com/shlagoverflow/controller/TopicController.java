package com.shlagoverflow.controller;

import com.shlagoverflow.core.model.Topic;
import com.shlagoverflow.api.service.TopicService;
import com.shlagoverflow.core.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Leboc Philippe.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Topic> retrieveAll() {
        return topicService.findAll().stream().sorted(Comparator.comparing(Topic::getId)).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id]", method = RequestMethod.GET)
    public Topic retrieve(@PathVariable(value = "id") String id) {
        Assert.hasLength(id, "The topic id is null or empty");
        return topicService.findOneById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public boolean update(@RequestBody Topic topic) {
        Assert.notNull(topic, "Object Topic is null");
        return topicService.update(topic);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable(value = "id") String id) {
        Assert.hasLength(id, "Parameter id is null or empty");
        return topicService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Topic create(@RequestBody Topic topic) {
        Assert.notNull(topic, "The topic object is null");
        Assert.hasLength(topic.getTitle(), "The topic title is null or empty");
        Assert.hasLength(topic.getContent(), "The topic content is null");

        topic.setCreatedDate(Utils.getCurrentTime());
        // topic.setOwner();
        return topicService.create(topic);
    }
}
