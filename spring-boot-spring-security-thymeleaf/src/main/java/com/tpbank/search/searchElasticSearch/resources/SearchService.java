package com.tpbank.search.searchElasticSearch.resources;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			@RequestParam(value = "beforePage", required = false) String beforePage
			,@RequestParam(value = "category", required = false) String category) {

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

	/*@RequestMapping(value = "/boost/{text}/{boostWord}", method = RequestMethod.GET)
	public String boostMatchQuery(Model model, @PathVariable final String text,
			@PathVariable final String boostWord, @RequestParam(value = "NofPage", required = false) String nofPage,
			@RequestParam(value = "beforePage", required = false) String beforePage
			,@RequestParam(value = "category", required = false) String category) {

		List<Pages> fullResults = new ArrayList<Pages>();
		try {
			final List<Pages> resultsWithFirstTag = new ArrayList<>();
			final List<Pages> resultsWithSecondTag = new ArrayList<>();
			
			FileInputStream fis = new FileInputStream("result.txt");
			
			DataInputStream dis=new DataInputStream(fis);
			
			String str=dis.readLine();
			
			String []types=str.split(" ");
			
			List<Pages> pages = null;
			List<Pages> fullPages = new ArrayList<>();
			List<Pages> fixedfullPages = new ArrayList<>();
			
			Arrays.stream(types).forEach((type)->{
				try {
					resultsWithFirstTag.addAll(new SearchResource().mustMatchTypeSearchWithTag(type, text, "khuyến mại"));
					resultsWithSecondTag.addAll(new SearchResource().mustNotMatchTypeSearchWithTag(type, text, "spdv"));
					fixedfullPages.addAll(new SearchResource().boostMatchSearch(type, text, boostWord));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
			/// Process
			//fullResults.addAll(resultsWithFirstTag);
			//fullResults.addAll(resultsWithSecondTag);

			
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
				fullPages=fixedfullPages;
			if (nofPage == null || Integer.valueOf(nofPage) == 1) {
				
				pages = fullPages.size()<5?fullPages:fullPages.subList(0, 5);
			} else {
				pages = fullPages.size()<5?fullPages:fullPages
						.subList(5 * (Integer.valueOf(nofPage) - 1), 5 * Integer.valueOf(nofPage));
			}
			}
			model.addAttribute("pages", pages);
			model.addAttribute("urlMap", "boost/" + text);

			if(category==null||category.isEmpty()||category.equals("null")) {
				model.addAttribute("fullPages", fullPages);
			}

			model.addAttribute("fixedfullPages", fixedfullPages);
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
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return "test";
	}*/

	@RequestMapping(value = "/general", method = RequestMethod.GET)
	public String generalSearch(Model model,@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "NofPage", required = false) String nofPage,
			@RequestParam(value = "beforePage", required = false) String beforePage,
			@RequestParam(value = "category", required = false) String category) {

		try {
			
			query=(query.isEmpty()||query.equals("null"))?"":query;
			
			/*int index=query.indexOf("#");
			
			if(query.contains("#")) {
				if(index!=0)query=query.substring(0,index);
				else {
					query="";
				}
			}*/
			
			query=URLDecoder.decode(query);
			
			Pattern p=Pattern.compile("(\\?)(query)(\\=)(.*)");
			
			Matcher matcher=p.matcher(query);
			
			if(matcher.find()) {
				query=matcher.group(4);
			}
			
			//List<Pages> fullResults = new ArrayList<>();
			final List<Pages> resultsWithFirstTag = new ArrayList<>();
			final List<Pages> resultsWithSecondTag = new ArrayList<>();
			
			FileInputStream fis = new FileInputStream("result.txt");
			
			DataInputStream dis=new DataInputStream(fis);
			
			String strFirstLine=dis.readLine();
			
			String boostWord=dis.readLine();
			
			String []types=strFirstLine.split(" ");
			
			List<Pages> pages = null;
			List<Pages> fullPages = new ArrayList<>();
			List<Pages> fixedfullPages = new ArrayList<>();
			
			for(String type:types){
				try {
					
					resultsWithFirstTag.addAll(new SearchResource().mustNotMatchTypeSearchWithTag(type, query, "khuyến mại"));
					resultsWithSecondTag.addAll(new SearchResource().mustNotMatchTypeSearchWithTag(type, query, "spdv"));
					if(boostWord!=null&&!boostWord.isEmpty()&&!boostWord.equals("")) {
						fixedfullPages.addAll(new SearchResource().boostMatchSearch(type, query, boostWord));
					}else {
						fixedfullPages.addAll(new SearchResource().prefixMatchWithTypeSearch(type, query));
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/// Process
			//fullResults.addAll(resultsWithFirstTag);
			//fullResults.addAll(resultsWithSecondTag);

			
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
			
			if(category==null||category.equals("null")||category.equals("all")) {
				fullPages=fixedfullPages;
			if (nofPage == null || Integer.valueOf(nofPage) == 1) {
				
				pages = fullPages.size()<5?fullPages:fullPages.subList(0, 5);
			} else {
				pages = fullPages.size()<5?fullPages:fullPages
						.subList(5 * (Integer.valueOf(nofPage) - 1), 5 * Integer.valueOf(nofPage));
			}
			}
			model.addAttribute("pages", pages);
			model.addAttribute("urlMap", "general?query=" + query);

			if(category==null||category.isEmpty()||category.equals("null")) {
				model.addAttribute("fullPages", fullPages);
			}

			model.addAttribute("fixedfullPages", fixedfullPages);
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "test";
	}

}
