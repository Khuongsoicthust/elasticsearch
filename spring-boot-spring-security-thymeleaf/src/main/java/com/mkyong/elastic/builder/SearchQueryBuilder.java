/*package com.mkyong.elastic.builder;

import java.util.List;

import org.elasticsearch.index.query.PrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import com.mkyong.elastic.model.Pages;

@Component
public class SearchQueryBuilder {
	@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    public List<Pages> getAll(String text) {

        QueryBuilder query = QueryBuilders.boolQuery()
                .should(
                        QueryBuilders.queryStringQuery(text)
                                .lenient(true)
                                .field("title")
                                .field("description")
                ).should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true)
                        .field("title")
                        .field("description"));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<Pages> pages = elasticsearchTemplate.queryForList(build, Pages.class);

        return pages;
    }
    
    public List<Pages> getPagesByField(String field, String text){
    	QueryBuilder query = QueryBuilders.matchQuery(field, text);
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<Pages> pages = elasticsearchTemplate.queryForList(build, Pages.class);
    	return pages;
    }
    public List<Pages> getPagesByPrefixQuery(String name,String prefix){
    	PrefixQueryBuilder query = QueryBuilders.prefixQuery(name, prefix);
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<Pages> pages = elasticsearchTemplate.queryForList(build, Pages.class);
    	return pages;
    }
}
*/