package com.baizhi.service;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.DelCache;
import com.baizhi.entity.Wztext;
import com.baizhi.mapper.WztextMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WztextServiceImpl implements WztextService {

    @Autowired
    private WztextMapper wztextMapper;

    @Override
    @AddCache
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        HashMap<String,Object> map = new HashMap<>();
        Integer start = (page-1)*rows;
        List<Wztext> wztexts = wztextMapper.queryAll(start, rows);
        Integer count = wztextMapper.getCount();
        Integer total = count%rows==0?count/rows:count/rows+1;

        map.put("page",page);
        map.put("total",total);
        map.put("records",count);
        map.put("rows",wztexts);
        return map;
    }

    @Override
    @DelCache
    public void addWztext(Wztext wztext) {
        wztext.setCreate_date(new Date()).setId(UUID.randomUUID().toString());
        wztextMapper.addWztext(wztext);
    }

    @Override
    @DelCache
    public void update(Wztext wztext) {
        wztextMapper.update(wztext);
    }

    @Override
    @DelCache
    public void delete(String[] id) {
        wztextMapper.delete(id);
    }
}
