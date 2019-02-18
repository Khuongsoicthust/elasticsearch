package com.tpbank.elastic.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tpbank.elastic.builder.SearchQueryBuilder;
import com.tpbank.elastic.model.Pages;

@Controller
@RequestMapping("/manual/search")
public class ManualSearch {
    @Autowired
    private SearchQueryBuilder searchQueryBuilder;

    @GetMapping(value = "/all/{text}")
    public String getAll(Model model, @PathVariable final String text) {
        List<Pages> pages=  searchQueryBuilder.getAll(text);
    	model.addAttribute("pages", pages);
    	return "search";
    }
    
    @GetMapping(value = "/title/{text}")
    public String getByTitle(Model model, @PathVariable final String text) {
    	List<Pages> pages = searchQueryBuilder.getPagesByField("title", text);
    	model.addAttribute("pages", pages);
    	return "search";
    }
    
    @GetMapping(value = "/description/{text}")
    public String getByDescription(Model model, @PathVariable final String text) {
    	List<Pages> pages = searchQueryBuilder.getPagesByField("description", text);
    	model.addAttribute("pages", pages);
    	return "search";
    }
    @GetMapping(value = "/prefix/{text}")
    public String getByPrefix(Model model, @PathVariable final String text) {
    	List<Pages> pages = searchQueryBuilder.getPagesByPrefixQuery("title", text);
    	model.addAttribute("pages", pages);
    	return "search";
    }
    
    
}
