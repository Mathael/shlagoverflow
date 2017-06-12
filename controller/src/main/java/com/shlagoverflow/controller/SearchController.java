package com.shlagoverflow.controller;

import com.shlagoverflow.api.util.LuceneUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/search", consumes = "application/json", produces = "application/json")
public class SearchController {

    @RequestMapping(value = "/{searchStr}", method = RequestMethod.GET)
    public List<String> search(@PathVariable(value = "searchStr") String searchStr) {
        return LuceneUtil.getInstance().search(searchStr);
    }
}
