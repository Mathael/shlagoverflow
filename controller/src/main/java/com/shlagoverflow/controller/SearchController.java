package com.shlagoverflow.controller;

import com.shlagoverflow.api.util.LuceneUtil;
import com.shlagoverflow.core.dto.TopicDto;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Leboc Philippe.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/search", consumes = "application/json", produces = "application/json")
public class SearchController {

    @RequestMapping(value = "/{searchStr}", method = RequestMethod.GET)
    public List<TopicDto> search(@PathVariable(value = "searchStr") String searchStr) {
        return LuceneUtil
                .getInstance()
                .search(searchStr)
                .stream()
                .sorted(Comparator.comparing(TopicDto::getScore).reversed())
                .collect(Collectors.toList());
    }
}
