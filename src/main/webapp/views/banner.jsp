<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>
<script>
    $(function () {
        $("#list").jqGrid({
            url: "${pageContext.request.contextPath}/banner/queryAll",
            editurl: "${pageContext.request.contextPath}/banner/edit",
            datatype: 'json',
            colNames: ["id", "title", "img_pic", "status", "create_date", "description"],    //列
            pager: "#bannerPager", //分页
            rownumbers: true,//初始化顺序号
            rowNum:5,
            rowList: ["5", "10", "15"],   //显示记录条数
            viewrecords: true,   //是否要显示总记录数
            autowidth: true, //宽度自适应
            height: 350,
            multiselect: true,//开启多选
            styleUI: "Bootstrap",
            colModel: [
                {name: "id", align: "center"},
                {name: "title", align: "center", editable: true, resizable: false},
                {
                    name: "img_pic", align: "center", editable: true,edittype:"file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='height:50px;width:50px;' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'/>"
                    }
                },
                {name: "status", align: "center", editable: true,edittype:"select",editoptions:{value:"展示:展示;不展示:不展示"}},
                {name: "create_date", align: "center",
                    formatter: function timestampToTime(cellvalue, options, rowObject) {
                        var date = new Date(cellvalue);
                        var year = date.getFullYear();
                        var month = date.getMonth()+1;
                        var day = date.getDate();
                        month = month < 10 ? "0"+month:month;
                        day = day < 10 ? "0"+day:day;
                        return newDate = year+'-'+month+'-'+day;
                    }
                },
                {name: "description", align: "center", editable: true}
            ]
        }).jqGrid("navGrid", "#bannerPager",

            {search: false},
            {//修改
                closeAfterAdd:true,
                afterSubmit: function (response) {
                    var bannerId = response.responseJSON.key;
                    // var bannerShow = response.responseJSON.msg;

                    console.log(response);
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: "img_pic",
                        type: "post",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            // $("#list").trigger("reloadGrid");   //重新加载
                            // $("#bannerShow").show().html("<h3>"+bannerShow+"</h3>");
                            // setTimeout(function(){
                            //     $("#bannerShow").hide();
                            // },3000);
                        }
                    })
                    return response;
                }
            },
            {//添加
                closeAfterAdd:true,
                afterSubmit: function (response) {
                    var bannerId = response.responseJSON.key;
                    var bannerShow = response.responseJSON.msg;
                    console.log(bannerId);
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: "img_pic",
                        type: "post",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            $("#list").trigger("reloadGrid");   //重新加载
                            $("#bannerShow").show().html("<h3>"+bannerShow+"</h3>");
                            setTimeout(function(){
                                $("#bannerShow").hide();
                            },3000);
                        }
                    })
                    return response;
                }
            },
            {//删除
                afterComplete:function(response){
                    var bannerShow = response.responseJSON.msg;
                    $("#bannerShow").show().html("<h3>"+bannerShow+"</h3>");
                    setTimeout(function(){
                        $("#bannerShow").hide();
                    },3000);
                }
            }
        )
    })
</script>

<!--初始化表格-->
<div>
    <h2>轮播图管理</h2>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">轮播图信息</a></li>
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home"></div>
    </div>
</div>
<table id="list" class="table table-striped">

</table>
<div id="bannerPager" style="height: 50px;"></div>
<div id="bannerShow" style="display: none;" class="alert alert-warning" role="alert"></div>