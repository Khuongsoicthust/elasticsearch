/*package com.mkyong.elastic.resource.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mkyong.elastic.model.Pages;
import com.mkyong.elastic.repository.PagesRepository;


@RestController
@RequestMapping("/rest/search")
public class SearchResource {

    @Autowired
    PagesRepository pagesRepository;

    @GetMapping(value = "/title/{text}")
    public String searchTitle(@PathVariable final String text) {
    	System.out.println(pagesRepository.findByTitle(text).get(0).toString());
        return "<html>"+"<title> khuong </title>  " +"<h1>khuong</h1>"+ "</html>";
    }


    @GetMapping(value = "/description/{description}")
    public List<Pages> searchDescription(@PathVariable final String description) {
        return pagesRepository.findByDescription(description);
    }
    
    @GetMapping(value = "/tag/{tag}")
    public List<Pages> searchTag(@PathVariable final String tag){
    	return pagesRepository.findByTag(tag);
    }


    @GetMapping(value = "/all")
    public List<Pages> searchAll() {
        List<Pages> usersList = new ArrayList<>();
        Iterable<Pages> userses = pagesRepository.findAll();
        userses.forEach(usersList::add);
        return usersList;
    }


}*/