package com.tpbank.elastic.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.tpbank.elastic.model.Pages;

public class FileIO {

	public List<Pages> readFile(String filePath,String tag) {
		List<Pages> pages = new ArrayList<>();
		File fileDir = new File(filePath);
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fileDir), "UTF8"));
			String str;
			int  i =0;
			String title="";
			String description="";
			String url= "";
			Long id = 1L;
			while ((str = in.readLine()) != null) {
			    switch (i%3) {
				case 0:
					title = str;
					break;
				case 1:
					url = str;
					break;
				case 2:
					description = str;
					break;

				default:
					break;
				}
			    if(i>1 && i %3==0) {
			    	id += 1;
			    	Pages p = new Pages(id, title, description, tag,"http://tpb.vn/"+ url);
			    	pages.add(p);
			    }
			    i++;
			}
			        
	                in.close();
		    
			 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pages;
	}
}
