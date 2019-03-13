package com.tpbank.search.searchElasticSearch.resources;

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

import com.tpbank.search.model.Pages;

@Controller
@RequestMapping("/elastic/search")
public class SearchService {
	Client client;

	@SuppressWarnings("resource")
	public SearchService() throws UnknownHostException {
		client = new PreBuiltTransportClient(
				Settings.builder().put("client.transport.sniff", true).put("cluster.name", "elasticsearch").build())
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
	}

	@RequestMapping(value = "/{type}/{text}/{textTag}", method = RequestMethod.GET)
	public String mustMatchTypeSearchWithTags(Model model, @PathVariable final String type,
			@PathVariable final String text, @PathVariable final String textTag,
			@RequestParam(value = "NofPage", required = false) String nofPage,
			@RequestParam(value = "beforePage", required = false) String beforePage) {

		// Search by type all
		List<Pages> fullResults = new ArrayList<Pages>();
		// Search with tag
		List<Pages> resultsWithFirstTag = new ArrayList<Pages>();
		// Search without tag
		List<Pages> resultsWithSecondTag = new ArrayList<Pages>();
		try {
			fullResults = new SearchResource().matchWithTypeSearch(type, text);
			resultsWithFirstTag = new SearchResource().mustMatchTypeSearchWithTag(type, text, textTag);
			resultsWithSecondTag = new SearchResource().mustNotMatchTypeSearchWithTag(type, text, textTag);

			List<Pages> pages = null;
			if (nofPage == null || Integer.valueOf(nofPage) == 1) {
				pages = new SearchResource().prefixMatchWithTypeSearch(type, text).subList(0, 5);
			} else {
				pages = new SearchResource().prefixMatchWithTypeSearch(type, text)
						.subList(5 * (Integer.valueOf(nofPage) - 1), 5 * Integer.valueOf(nofPage));
			}
			model.addAttribute("pages", pages);
			model.addAttribute("urlMap", "general/" + type + "/" + text);
			model.addAttribute("fullPages", new SearchResource().prefixMatchWithTypeSearch(type, text));

			model.addAttribute("resultsWithFirstTag", resultsWithFirstTag);
			model.addAttribute("resultsWithSecondTag", resultsWithSecondTag);

			if (beforePage != null)
				model.addAttribute("beforePage", Integer.valueOf(nofPage));
			else {
				model.addAttribute("beforePage", 1);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return "test";
	}

	@RequestMapping(value = "/boost/{type}/{text}/{boostWord}", method = RequestMethod.GET)
	public String boostMatchQuery(Model model, @PathVariable final String type, @PathVariable final String text,
			@PathVariable final String boostWord, @RequestParam(value = "NofPage", required = false) String nofPage,
			@RequestParam(value = "beforePage", required = false) String beforePage) {

		List<Pages> fullResults = new ArrayList<Pages>();
		List<Pages> resultsWithFirstTag = new ArrayList<Pages>();
		List<Pages> resultsWithSecondTag = new ArrayList<Pages>();
		try {
			fullResults = new SearchResource().boostMatchSearch(type, text, boostWord);
			resultsWithFirstTag = new SearchResource().mustMatchTypeSearchWithTag(type, text, "khuyến mại");
			resultsWithFirstTag = new SearchResource().mustNotMatchTypeSearchWithTag(type, text, "spdv");

			System.out.println(fullResults.size());

			List<Pages> pages = null;
			if (nofPage == null || Integer.valueOf(nofPage) == 1) {
				pages = new SearchResource().prefixMatchWithTypeSearch(type, text).subList(0, 5);
			} else {
				pages = new SearchResource().prefixMatchWithTypeSearch(type, text)
						.subList(5 * (Integer.valueOf(nofPage) - 1), 5 * Integer.valueOf(nofPage));
			}
			model.addAttribute("pages", pages);
			model.addAttribute("urlMap", "general/" + type + "/" + text);
			model.addAttribute("fullPages", new SearchResource().prefixMatchWithTypeSearch(type, text));

			model.addAttribute("resultsWithFirstTag", resultsWithFirstTag);
			model.addAttribute("resultsWithSecondTag", resultsWithFirstTag);

			if (beforePage != null)
				model.addAttribute("beforePage", Integer.valueOf(nofPage));
			else {
				model.addAttribute("beforePage", 1);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return "test";
	}

	@RequestMapping(value = "/general/{type}/{text}", method = RequestMethod.GET)
	public String generalSearch(Model model, @PathVariable final String type, @PathVariable final String text,
			@RequestParam(value = "NofPage", required = false) String nofPage,
			@RequestParam(value = "beforePage", required = false) String beforePage,
			@RequestParam(value = "category", required = false) String category) {

		List<Pages> fullResults = new ArrayList<Pages>();
		List<Pages> resultsWithFirstTag = new ArrayList<Pages>();
		List<Pages> resultsWithSecondTag = new ArrayList<Pages>();
		try {
			resultsWithFirstTag = new SearchResource().mustNotMatchTypeSearchWithTag(type, text, "khuyến mại");
			resultsWithSecondTag = new SearchResource().mustNotMatchTypeSearchWithTag(type, text, "spdv");
			fullResults.addAll(resultsWithFirstTag);
			fullResults.addAll(resultsWithSecondTag);

			/// Process

			List<Pages> pages = null;
			List<Pages> fullPages = new ArrayList<>();
			if (category != null) {
				if (category.equals("spdv")) {
					fullPages=resultsWithFirstTag;
					
				}
				if (category.equals("km")) {
					fullPages=resultsWithSecondTag;
				}
				
				if ((nofPage == null || Integer.valueOf(nofPage) == 1)) {
					pages = fullPages.size()<5?fullPages:fullPages.subList(0, 5);
				} else {
					pages = fullPages.size()<5?fullPages:
						fullPages.subList(5 * (Integer.valueOf(nofPage) - 1), 5 * Integer.valueOf(nofPage));
				}
				
			}
			
			//if(category.isEmpty()) return "demo";
			
			if(category==null||category.equals("null")) {
				fullPages=new SearchResource().prefixMatchWithTypeSearch(type, text);
			if (nofPage == null || Integer.valueOf(nofPage) == 1) {
				
				pages = fullPages.size()<5?fullPages:fullPages.subList(0, 5);
			} else {
				pages = fullPages.size()<5?fullPages:fullPages
						.subList(5 * (Integer.valueOf(nofPage) - 1), 5 * Integer.valueOf(nofPage));
			}
			}
			model.addAttribute("pages", pages);
			model.addAttribute("urlMap", "general/" + type + "/" + text);

			if(category==null||category.isEmpty()||category.equals("null")) {
				model.addAttribute("fullPages", fullPages);
			}

			model.addAttribute("fixedfullPages", new SearchResource().prefixMatchWithTypeSearch(type, text));
			model.addAttribute("fullPages", fullPages);
			model.addAttribute("resultsWithFirstTag", resultsWithFirstTag);
			model.addAttribute("resultsWithSecondTag", resultsWithSecondTag);

			if (beforePage != null)
				model.addAttribute("beforePage", Integer.valueOf(nofPage));
			else {
				model.addAttribute("beforePage", 1);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		return "test";
	}

}
