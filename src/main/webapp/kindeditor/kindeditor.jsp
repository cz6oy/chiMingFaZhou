<%@page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script charset="utf-8" src="kindeditor-all-min.js"></script>
    <script charset="utf-8" src="lang/zh-CN.js"></script>
    <script>
        KindEditor.ready(function(K) {
            K.create('#editor_id',{
                uploadJson:"${pageContext.request.contextPath}/kindeditor/uploadImg",
                filePostName:"img",
                allowFileManager:true
            });
        });
    </script>
</head>
<body>
    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
        请输入文章内容
    </textarea>
</body>
</html>