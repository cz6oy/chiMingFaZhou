<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>
<script>
    $(function () {
        $("#list").jqGrid({
            url: "${pageContext.request.contextPath}/user/queryAll",
            editurl: "${pageContext.request.contextPath}/user/edit",
            datatype: 'json',
            pager: "#pager", //分页
            rownumbers: true,//初始化顺序号
            rowNum: 5,
            rowList: ["5", "10", "15"],   //显示记录条数
            viewrecords: true,   //是否要显示总记录数
            autowidth: true, //宽度自适应
            height: 350,
            multiselect: true,//开启多选
            styleUI: "Bootstrap",
            colNames: ["id", "手机账号", "头像", "真实姓名", "法号", "性别", "省", "市", "个性签名", "状态", "注册时间", "操作"],
            colModel: [
                {name: "id"},
                {name: "phone_num", editable: true,},
                {
                    name: "head_pic", edittype: "file", editable: true,
                    formatter: function (cellvalue, options, rowObject) {
                        console.log()
                        return "<img style='height:50px;width:50px;' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'/>"
                    }
                },
                {name: "name", editable: true,},
                {name: "dharma", editable: true,},
                {name: "sex", editable: true,},
                {name: "province", editable: true,},
                {name: "city", editable: true,},
                {name: "sign", editable: true,},
                {
                    name: "status",
                    align: "center",
                    editable: true,
                    edittype: "select",
                    editoptions: {value: "正常:正常;冻结:冻结"}
                },
                {name: "create_date", align: "center"},
                {
                    name: "id", formatter: function timestampToTime(cellvalue, options, rowObject) {
                        return "<a href='#' onclick=\"updateStatus('" + cellvalue + "')\">修改状态</a> "
                    }
                }
            ]
        }).jqGrid("navGrid", "#pager",

            {
                search: false,
                edit: false,
                add: false,
                del: false
            },
            {//修改

            },
            {//添加

            },
            {//删除

                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var message = response.responseJSON.key;
                    console.log(response);
                    $("#list").trigger("reloadGrid");
                    return response;
                }

            }
        )
    })

    function updateStatus(id) {
        $.ajax({
            type: 'post',
            url: '${pageContext.request.contextPath}/user/updateStatus?id=' + id,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                $("#list").trigger("reloadGrid");   //重新加载
            }
        })
    }

    function outExcel() {
        location.href = "${pageContext.request.contextPath}/user/outExcel";
    }

    function echart() {
        $('#myModal').modal('show');
        var myChart = echarts.init(document.getElementById('main'));
        option = {

            tooltip: {},
            yAxis: {
                type: 'value'
            },
            xAxis: {
                type: 'category',
                data: []
            }
        };
        myChart.setOption(option);
        $.ajax({
            type: 'post',
            url: '${pageContext.request.contextPath}/echarts/queryLineEcharts',
            dataType: 'json',
            success: function (data) {
                console.log(data);
                var date = [];
                var count = [];
                for (var i = 0; i < data.length; i++) {
                    date.push(data[i].create_date);
                    count.push(data[i].count);
                }
                myChart.setOption({
                    series: [
                        {
                            name: '7天注册用户',
                            data: count,
                            type: 'line'
                        }
                    ],
                    xAxis: {
                        type: 'category',
                        data: date
                    },
                });
            }
        })
    }

    function echart2() {
        $('#myModal2').modal('show');
        var myChart = echarts.init(document.getElementById('main2'));
        option = {
            title: {
                text: '用户城市分布图',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['iphone5']
            },
            visualMap: {
                min: 0,
                max: 2500,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
        };
        myChart.setOption(option);
        $.ajax({
            type: 'post',
            url: '${pageContext.request.contextPath}/echarts/queryProvinceCount',
            dataType: 'json',
            success: function (data) {
                var jsonData = JSON.stringify(data);// 转成JSON格式
                var result = $.parseJSON(jsonData);// 转成JSON对象
                console.log(result);
                myChart.setOption({
                    series: [
                        {
                            name: '位置',
                            type: 'map',
                            mapType: 'china',
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data: result
                        }
                    ]
                })
            }
        })
    }
</script>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">用户管理</a></li>
    <li role="presentation"><a href="javascript:void(0)" onclick="outExcel()">导出EXCEL</a></li>
    <li role="presentation"><a href="javascript:void(0)" onclick="echart()">注册统计图</a></li>
    <li role="presentation"><a href="javascript:void(0)" onclick="echart2()">位置分布图</a></li>
</ul>
<br>
<table id="list" class="table table-striped"></table>
<div id="pager" style="height: 50px;"></div>

<!-- 添加模态框 -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 id="modal-title" class="modal-title">注册统计图</h4>
            </div>
            <div class="modal-body">
                <div id="main" style="width: 600px;height:400px;"></div>
            </div>
            <div class="modal-footer">
                <button type="button" id="overAddWztext" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="addWztext" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<div id="myModal2" class="modal fade" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 id="modal-title" class="modal-title">位置分布图</h4>
            </div>
            <div class="modal-body">
                <div id="main2" style="width: 570px;height:400px;"></div>
            </div>
            <div class="modal-footer">
                <button type="button" id="overAddWztext" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="addWztext" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>