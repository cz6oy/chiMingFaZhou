<%@ page contentType="text/html; utf-8" isELIgnored="false" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#list").jqGrid({
            url: '${pageContext.request.contextPath}/album/queryAll',
            editurl: '${pageContext.request.contextPath}/album/edit',
            datatype: 'json',
            height: 400,
            pager: 'pager',
            rowList: ['5', '10', '15'],
            autowidth: true,
            viewrecords: true,
            multiselect: true,//开启多选
            styleUI: 'Bootstrap',
            subGrid: true,
            colNames: ['id', '标题', '分数', '作者', '播音员', '集数', '简介', '上传时间', '封面'],
            colModel: [
                {name: 'id'},
                {name: 'title', editable: true},
                {name: 'score', editable: true},
                {name: 'author', editable: true},
                {name: 'broadcast', editable: true}, //播音员
                {name: 'count', editable: true},    //集数
                {name: 'brief', editable: true},    //简介
                {name: 'create_date'},
                {
                    name: 'cover_pic', editable: true, edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='height:50px;width:50px;' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'/>"
                    }
                }
            ],
            subGridRowExpanded: function (subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }

        }).jqGrid("navGrid", "#pager", {
            search: false
        }, {
            //修改
            closeAfterAdd:true,
            afterSubmit: function (response) {
                var albumId = response.responseJSON.key;
                // var bannerShow = response.responseJSON.msg;
                console.log(albumId);
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/album/upload",
                    fileElementId: "cover_pic",
                    type: "post",
                    data: {albumId: albumId},
                    success: function (data) {
                        $("#list").trigger("reloadGrid");   //重新加载
                        // $("#list").trigger("reloadGrid");   //重新加载
                        // $("#bannerShow").show().html("<h3>"+bannerShow+"</h3>");
                        // setTimeout(function(){
                        //     $("#bannerShow").hide();
                        // },3000);
                    }
                })
                return response;
            }
        }, {
            //添加
            closeAfterAdd:true,
            afterSubmit: function (response) {
                var albumId = response.responseJSON.key;
                // var bannerShow = response.responseJSON.msg;
                console.log(albumId);
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/album/upload",
                    fileElementId: "cover_pic",
                    type: "post",
                    data: {albumId: albumId},
                    success: function (data) {
                        $("#list").trigger("reloadGrid");   //重新加载
                        // $("#bannerShow").show().html("<h3>"+bannerShow+"</h3>");
                        // setTimeout(function(){
                        //     $("#bannerShow").hide();
                        // },3000);
                    }
                })
                return response;
            }
        }, {
            //删除
        });
    })

    function addSubGrid(subGidId, albumId) {
        var subGridTableId = subGidId + "table";
        var subGridPagerId = subGidId + "pager";
        $("#" + subGidId).html(
            "<table id='" + subGridTableId + "' class=\"table table-striped\"></table>" +
            "<div id='" + subGridPagerId + "' style=\"height: 50px\"></div>");

        $("#" + subGridTableId).jqGrid({
            url: "${pageContext.request.contextPath}/article/queryAll?albumId=" + albumId,
            editurl: '${pageContext.request.contextPath}/article/edit?albumId=' + albumId,
            datatype: "json",
            colNames: ["id", "标题", "大小", "上传时间", "时长", "音频", "操作"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "size"},
                {name: "create_date"},
                {name: "duration"},
                {name: "voice", editable: true, edittype: "file"},
                {
                    name: "voice", formatter: function (cellvalue, options, value) {
                        return "<a href='#' onclick=\"playAudio('"+cellvalue+"')\"><span class='glyphicon glyphicon-play-circle'></span></a>&nbsp;&nbsp;&nbsp;" +
                            "<a href='#' onclick=\"downLoadAudio('"+cellvalue+"')\"><span class='glyphicon glyphicon-download'></span></a> "
                    }
                }
            ],
            pager: "#" + subGridPagerId,
            rowNum: 5,
            rowList: [3, 4, 7],
            viewrecords: true,
            height: '200px',
            multiselect: true,
            rownumbers: true,
            viewrecords: true,
            styleUI: "Bootstrap",
            autowidth: true
        }).jqGrid("navGrid", "#" + subGridPagerId, {
            search: false
        }, {
            //修改
            closeAfterAdd:true,
            afterSubmit: function (response) {
                var articleId = response.responseJSON.key;
                console.log(response);
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/article/upload",
                    fileElementId: "voice",
                    type: "post",
                    data: {
                        articleId: articleId,
                    },
                    success: function (data) {
                        $("#"+subGridTableId).trigger("reloadGrid");
                        // $("#bannerShow").show().html("<h3>"+bannerShow+"</h3>");
                        // setTimeout(function(){
                        //     $("#bannerShow").hide();
                        // },3000);
                    }
                })
                return response;
            }

        }, {
            //添加
            closeAfterAdd:true,
            afterSubmit: function (response) {
                var articleId = response.responseJSON.key;
                // var bannerShow = response.responseJSON.msg;
                console.log(response);
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/article/upload",
                    fileElementId: "voice",
                    type: "post",
                    data: {
                        articleId: articleId,
                    },
                    success: function (data) {
                        $("#"+subGridTableId).trigger("reloadGrid");   //重新加载
                        // $("#bannerShow").show().html("<h3>"+bannerShow+"</h3>");
                        // setTimeout(function(){
                        //     $("#bannerShow").hide();
                        // },3000);
                    }
                })
                return response;
            }
        }, {
            //删除
        });
    }

    function playAudio(cellvalue){
        console.log(cellvalue);
        $("#playAudio").modal("show");
        $("#audioId").attr("src","${pageContext.request.contextPath}/upload/music/"+cellvalue);
    }
    function downLoadAudio(cellvalue){
        location.href="${pageContext.request.contextPath}/article/downLoadAudio?audioName="+cellvalue;
    }
</script>

<div id="playAudio" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" src="" controls></audio>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<table id="list" class="table table-striped"></table>
<div id="pager" style="height: 50px;"></div>