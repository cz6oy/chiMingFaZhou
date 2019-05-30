package com.baizhi.controller;

import com.baizhi.entity.Echarts;
import com.baizhi.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private EchartsService echartsService;

    @RequestMapping("/queryLineEcharts")
    @ResponseBody
    public List<Echarts> findByUserAddEcharts(){
        List<Echarts> list = echartsService.findByUserAddCharts();
        return list;
    }

    @RequestMapping("/queryProvinceCount")
    @ResponseBody
    public List<Echarts> findByUserProvinceCount(){
        List<Echarts> list = echartsService.findByUserProvinceCount();
        return list;
    }
}
