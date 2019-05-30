<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>
<script>
    var edit_id;

    $(function () {
        $("#list").jqGrid({
            url: "${pageContext.request.contextPath}/wztext/queryAll",
            editurl: "${pageContext.request.contextPath}/wztext/edit",
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
            colNames: ["id", "标题", "作者", "上传日期", "内容", "状态", "操作"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "author"},
                {name: "create_date"},
                {name: "content", editable: true},
                {name: "status", editable: true, edittype: "select", editoptions: {value: "正常:正常;冻结:冻结"}},
                {
                    name: "id",
                    formatter: function (cellvalue, options, value) {
                        var html = "&nbsp;<a href='#' onclick=\"lookDetails('" + cellvalue + "')\"><span class='glyphicon glyphicon-list'></span></a>&nbsp;&nbsp;"
                            + "&nbsp;<a href='#' onclick=\"editWztext('" + cellvalue + "')\"><span class='glyphicon glyphicon-pencil'></span></a>"
                        return html;
                    }
                }
            ]
        }).jqGrid("navGrid", "#pager",
            {
                search: false,
                edit: false,
                add: false,
            },
            {//修改

            },
            {//添加

            },
            {//删除

            }
        )
    })

    function addArticle() {
        $('#myModal').modal('show');
        $('#wztextForm')[0].reset();
        KindEditor.html("#editor","");
        KindEditor.create('#editor', {
            width: '100%',
            height: '400px',
            resizeType: 1,
            filePostName: "img",
            uploadJson: "${pageContext.request.contextPath}/kindeditor/uploadImg",//上传图片
            fileManagerJson: "${pageContext.request.contextPath}/kindeditor/getAllImage",//图片空间
            allowFileManager: true,
            afterBlur:function () {
                this.sync();
            }
        });
    }
    $('#addWztext').click(function(){
        $.ajax({
            type:'POST',
            url:'${pageContext.request.contextPath}/wztext/addWztext',
            dataType:'json',
            data:$('#wztextForm').serialize(),
            success:function(response){
               if(response == 'ok'){
                   $("#overAddWztext").click();
               }
            }
        })
    })
    function lookDetails(id){
        var value = $("#list").jqGrid("getRowData",id);
        $('#myModal2').modal('show');
        $('#wztextForm2')[0].reset();
        KindEditor.html("#editor2","");
        $("#inputtitle2").val(value.title);
        $("#inputauthor2").val(value.author);
        if(value.status == '展示'){
            $("#inputstatus2").val("展示");
        }else{
            $("#inputstatus2").val("不展示");
        }
        KindEditor.create('#editor2', {
            width: '100%',
            height: '400px',
            resizeType: 1,
            filePostName: "img",
            uploadJson: "${pageContext.request.contextPath}/kindeditor/uploadImg",
            fileManagerJson: "${pageContext.request.contextPath}/kindeditor/getAll",
            allowFileManager: true,
            afterBlur:function () {
                this.sync();
            }
        });
        KindEditor.html("#editor2",value.content);
    }

    function editWztext(id){
        edit_id = id;
        var value = $("#list").jqGrid("getRowData",id);
        $('#myModal3').modal('show');
        $('#wztextForm3')[0].reset();
        KindEditor.html("#editor3","");
        $("#inputtitle3").val(value.title);
        $("#inputauthor3").val(value.author);
        if(value.status == '展示'){
            $("#inputstatus3").val("展示");
        }else{
            $("#inputstatus3").val("不展示");
        }
        KindEditor.create('#editor3', {
            width: '100%',
            height: '400px',
            resizeType: 1,
            filePostName: "img",
            uploadJson: "${pageContext.request.contextPath}/kindeditor/uploadImg",  //上传图片
            fileManagerJson: "${pageContext.request.contextPath}/kindeditor/getAllImage",
            allowFileManager: true,
            afterBlur:function () {
                this.sync();
            }
        });
        KindEditor.html("#editor3",value.content);
    }
    $("#editWztext").click(function(){
        var content = $("#editor3").val();
        var title = $("#inputtitle3").val();
        var author = $("#inputauthor3").val();
        var status = $("#inputstatus").val();
        $.ajax({
            type:'POST',
            url:'${pageContext.request.contextPath}/wztext/editWztext',
            dataType:'json',
            data:{
                id:edit_id,
                content:content,
                title:title,
                author:author,
                status:status
            },
            success:function(response){
                if(response == 'ok'){
                    $("#overAddWztext3").click();
                }
            }
        })
    })
</script>
<div>

    <ul class="nav nav-tabs">
        <li role="presentation" class="active"><a href="#">文章管理</a></li>
        <li role="presentation"><a href="javascript:void(0)" onclick="addArticle()">添加</a></li>
    </ul>
    <br>
</div>
<table id="list" class="table table-striped"></table>
<div id="pager" style="height: 50px;"></div>

<!-- 添加模态框 -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 id="modal-title" class="modal-title">添加文章</h4>
            </div>
            <div class="modal-body">
                <form id="wztextForm" class="form-inline">
                    <div class="form-group">
                        <label for="inputtitle" class="control-label">标题：</label>
                        <input type="text" name="title" class="form-control" id="inputtitle" placeholder="请输入标题" >
                    </div><br/><br/>
                    <div class="form-group">
                        <label for="inputauthor" class="control-label">作者：</label>
                        <input type="text" name="author" class="form-control" id="inputauthor" placeholder="请输入作者" >
                    </div><br/><br/>
                    <div class="form-group">
                        <label for="inputstatus" class="control-label">状态：</label>
                        <select name="status" style="width:300px;" class="form-control" id="inputstatus" >
                            <option>展示</option>
                            <option>不展示</option>
                        </select>
                    </div><br/><br/>
                    <textarea class="form-control" id="editor" name="content" rows="3"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="overAddWztext" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="addWztext" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<%--查看模态框--%>
<div id="myModal2" class="modal fade" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">查看文章</h4>
            </div>
            <div class="modal-body">
                <form id="wztextForm2" class="form-inline">
                    <div class="form-group">
                        <label for="inputtitle" class="control-label">标题：</label>
                        <input type="text" name="title" class="form-control" id="inputtitle2" placeholder="请输入标题" disabled>
                    </div><br/><br/>
                    <div class="form-group">
                        <label for="inputauthor" class="control-label">作者：</label>
                        <input type="text" name="author" class="form-control" id="inputauthor2" placeholder="请输入作者" disabled>
                    </div><br/><br/>
                    <div class="form-group">
                        <label for="inputstatus" class="control-label">状态：</label>
                        <select name="status" style="width:300px;" class="form-control" id="inputstatus2" disabled>
                            <option>展示</option>
                            <option>不展示</option>
                        </select>
                    </div><br/><br/>
                    <textarea class="form-control" id="editor2" name="content" rows="3"></textarea>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- 编辑模态框 -->
<div id="myModal3" class="modal fade" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 id="modal-title3" class="modal-title">编辑文章</h4>
            </div>
            <div class="modal-body">
                <form id="wztextForm3" class="form-inline">
                    <div class="form-group">
                        <label for="inputtitle" class="control-label">标题：</label>
                        <input type="text" name="title" class="form-control" id="inputtitle3" placeholder="请输入标题" >
                    </div><br/><br/>
                    <div class="form-group">
                        <label for="inputauthor" class="control-label">作者：</label>
                        <input type="text" name="author" class="form-control" id="inputauthor3" placeholder="请输入作者" >
                    </div><br/><br/>
                    <div class="form-group">
                        <label for="inputstatus" class="control-label">状态：</label>
                        <select name="status" style="width:300px;" class="form-control" id="inputstatus3" >
                            <option>展示</option>
                            <option>不展示</option>
                        </select>
                    </div><br/><br/>
                    <textarea class="form-control" id="editor3" name="content" rows="3"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="overAddWztext3" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="editWztext" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>