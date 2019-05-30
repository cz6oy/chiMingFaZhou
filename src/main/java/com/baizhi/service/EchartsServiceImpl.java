package com.baizhi.service;

import com.baizhi.entity.Echarts;
import com.baizhi.mapper.EchartsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsServiceImpl implements EchartsService{

    @Autowired
    private EchartsMapper echartsMapper;

    @Override
    public List<Echarts> findByUserAddCharts() {
        List<Echarts> list = echartsMapper.findByUserAddCharts();
        return list;
    }

    @Override
    public List<Echarts> findByUserProvinceCount() {
        List<Echarts> list = echartsMapper.findByUserProvinceCount();
        return list;
    }
}
