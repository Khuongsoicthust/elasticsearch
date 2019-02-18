package com.tpbank.elastic.resource.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tpbank.elastic.model.Pages;
import com.tpbank.elastic.repository.PagesRepository;

@Controller
@RequestMapping("/search")
public class Search {

    @Autowired
    PagesRepository pagesRepository;
    
    @RequestMapping("/title/{text}")
    public String searchByTitle(Model model,@PathVariable final String text) {
    	List<Pages> pages = new ArrayList<>();
    	pages = pagesRepository.findByTitle(text);
    	model.addAttribute("pages", pages);
    	return "search";
    }
    @RequestMapping("/description/{text}")
    public String searchByDescription(Model model, @PathVariable final String text) {
    	System.out.println("vl");
    	List<Pages> pages = new ArrayList<>();
    	pages = pagesRepository.findByDescription(text);
    	model.addAttribute("pages", pages);
    	return "search";
    
    	
    }
}
