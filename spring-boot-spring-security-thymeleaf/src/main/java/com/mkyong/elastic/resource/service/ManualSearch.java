/*package com.mkyong.elastic.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mkyong.elastic.builder.SearchQueryBuilder;
import com.mkyong.elastic.model.Pages;


@Controller
@RequestMapping("/manual/search")
public class ManualSearch {
    @Autowired
    private SearchQueryBuilder searchQueryBuilder;

    @GetMapping(value = "${str.value}"+"/{text}")
    public String getAll(Model model, @PathVariable final String text,@RequestParam(value="NofPage", required=false) String nofPage
    		,@RequestParam(value="beforePage",required=false)String beforePage) {
    	List<Pages> pages = null;
    	if(nofPage==null||Integer.valueOf(nofPage)==1) {
    		pages=  searchQueryBuilder.getAll(text).subList(0, 5);
    	}
    	else {
    		pages=  searchQueryBuilder.getAll(text).subList(5*(Integer.valueOf(nofPage)-1), 5*Integer.valueOf(nofPage));
    	}
    	model.addAttribute("pages", pages);
    	model.addAttribute("urlMap", "/all/"+text);
    	model.addAttribute("fullPages", searchQueryBuilder.getAll(text));
    	
    	System.out.println(nofPage+"--------------");
    	
    	if(beforePage!=null) model.addAttribute("beforePage",Integer.valueOf(beforePage));
    	else {
    		model.addAttribute("beforePage",1);
    	}
    	
    	return "test";
    }
    
    @GetMapping(value = "/title/{text}")
    public String getByTitle(Model model, @PathVariable final String text) {
    	List<Pages> pages = searchQueryBuilder.getPagesByField("title", text);
    	model.addAttribute("pages", pages);
    	return "test";
    }
    
    @GetMapping(value = "/description/{text}")
    public String getByDescription(Model model, @PathVariable final String text) {
    	List<Pages> pages = searchQueryBuilder.getPagesByField("description", text);
    	model.addAttribute("pages", pages);
    	return "test";
    }
    @GetMapping(value = "/prefix/{text}")
    public String getByPrefix(Model model, @PathVariable final String text) {
    	List<Pages> pages = searchQueryBuilder.getPagesByPrefixQuery("title", text);
    	model.addAttribute("pages", pages);
    	return "test";
    }
    
    @GetMapping(value="/demo")
    public String demo() {
    	return "/demo";
    }
    
}*/