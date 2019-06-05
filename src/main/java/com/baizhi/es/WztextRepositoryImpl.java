package com.baizhi.es;

import com.baizhi.entity.Wztext;

import java.util.List;

public class WztextRepositoryImpl implements WztextRepository {
    @Override
    public List<Wztext> findByTermSourceOrderPageHighlight(String field, Integer page, Integer size) {
        return null;
    }
}
