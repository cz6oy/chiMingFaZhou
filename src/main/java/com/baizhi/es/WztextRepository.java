package com.baizhi.es;

import com.baizhi.entity.Wztext;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface WztextRepository{
    List<Wztext> findByTermSourceOrderPageHighlight(String field,Integer page,Integer size);
}