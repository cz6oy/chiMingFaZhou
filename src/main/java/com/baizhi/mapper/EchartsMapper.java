package com.baizhi.mapper;

import com.baizhi.entity.Echarts;

import java.util.List;

public interface EchartsMapper {
    List<Echarts> findByUserAddCharts();
    List<Echarts> findByUserProvinceCount();
}
