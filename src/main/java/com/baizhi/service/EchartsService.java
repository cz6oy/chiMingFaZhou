package com.baizhi.service;

import com.baizhi.entity.Echarts;

import java.util.List;

public interface EchartsService {
    List<Echarts> findByUserAddCharts();
    List<Echarts> findByUserProvinceCount();
}
