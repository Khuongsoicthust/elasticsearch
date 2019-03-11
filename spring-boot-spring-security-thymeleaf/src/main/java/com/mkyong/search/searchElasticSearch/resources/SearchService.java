package com.mkyong.search.searchElasticSearch.resources;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mkyong.search.model.Pages;


@Controller
@RequestMapping("/elastic/search")
public class SearchService {
	Client client;

	@SuppressWarnings("resource")
	public SearchService() throws UnknownHostException {
		client = new PreBuiltTransportClient(
				  Settings.builder().put("client.transport.sniff", true)
				                    .put("cluster.name","elasticsearch").build()) 
				  .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }
	
	@RequestMapping(value = "/{type}/{text}/{textTag}", method = RequestMethod.GET) 
    public String mustMatchTypeSearchWithTags(Model model, @PathVariable final String type,@PathVariable final String text,@PathVariable final String textTag) {

		//Search by type all
    	List<Pages> fullResults =  new ArrayList<Pages>();
    	//Search with tag
    	List<Pages> resultsWithTag =  new ArrayList<Pages>();
    	//Search without tag
    	List<Pages> resultWithoutTag = new ArrayList<Pages>();
		try {
			fullResults = new SearchResource().matchWithTypeSearch(type, text);
			resultsWithTag = new SearchResource().mustMatchTypeSearchWithTag(type, text, textTag);
			resultWithoutTag = new SearchResource().mustNotMatchTypeSearchWithTag(type, text, textTag);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
   

    	model.addAttribute("fullResults", fullResults);
    	model.addAttribute("resultsWithTag", resultsWithTag);
    	model.addAttribute("resultWithoutTag",resultWithoutTag);
    	
    	return "test";
    }
	
	@RequestMapping(value = "/boost/{type}/{text}/{boostWord}", method = RequestMethod.GET) 
    public String boostMatchQuery(Model model, @PathVariable final String type,@PathVariable final String text,@PathVariable final String boostWord) {

    	List<Pages> fullResults =  new ArrayList<Pages>();
    	List<Pages> resultsWithTag =  new ArrayList<Pages>();
    	List<Pages> resultWithoutTag = new ArrayList<Pages>();
		try {
			fullResults = new SearchResource().boostMatchSearch(type, text, boostWord);
			resultsWithTag = new SearchResource().mustMatchTypeSearchWithTag(type, text, "khuyến mại");
			resultWithoutTag = new SearchResource().mustNotMatchTypeSearchWithTag(type, text, "khuyến mại");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
   

    	model.addAttribute("fullResults", fullResults);
    	model.addAttribute("resultsWithTag", resultsWithTag);
    	model.addAttribute("resultWithoutTag",resultWithoutTag);
    	
    	return "test";
    }
	@RequestMapping(value = "/general/{type}/{text}", method = RequestMethod.GET)
    public String generalSearch(Model model, @PathVariable final String type,
    		@PathVariable final String text
    		,@RequestParam(value="NofPage", required=false) String nofPage
    		,@RequestParam(value="beforePage",required=false)String beforePage) {

    	List<Pages> fullResults =  new ArrayList<Pages>();
    	List<Pages> resultsWithTag =  new ArrayList<Pages>();
    	List<Pages> resultWithoutTag = new ArrayList<Pages>();
		try {
			fullResults = new SearchResource().prefixMatchWithTypeSearch(type, text);
			resultsWithTag = new SearchResource().mustMatchTypeSearchWithTag(type, text, "cho vay");
			resultWithoutTag = new SearchResource().mustNotMatchTypeSearchWithTag(type, text, "cho vay");
			
			///Process
			
			System.out.println(fullResults.size());
			
			List<Pages> pages = null;
	    	if(nofPage==null||Integer.valueOf(nofPage)==1) {
	    		pages=  new SearchResource().prefixMatchWithTypeSearch(type, text).subList(0, 5);
	    	}
	    	else {
	    		pages=  new SearchResource().prefixMatchWithTypeSearch(type, text).subList(5*(Integer.valueOf(nofPage)-1), 5*Integer.valueOf(nofPage));
	    	}
	    	model.addAttribute("pages", pages);
	    	model.addAttribute("urlMap", "/all/"+text);
	    	model.addAttribute("fullPages", new SearchResource().prefixMatchWithTypeSearch(type, text));
	    	
	    	System.out.println(nofPage+"--------------");
	    	
	    	if(beforePage!=null) model.addAttribute("beforePage",Integer.valueOf(beforePage));
	    	else {
	    		model.addAttribute("beforePage",1);
	    	}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
   
    	return "test";
    }
    
}
