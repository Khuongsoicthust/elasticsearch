package com.tpbank.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tpbank.elastic.model.Pages;

import java.util.List;

public interface PagesRepository extends ElasticsearchRepository<Pages, Long> {
    List<Pages> findByTitle(String text);
    List<Pages> findByTag(String tag);
    List<Pages> findByDescription(String description);
}
