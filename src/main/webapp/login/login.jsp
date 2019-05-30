<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>
    <script src="assets/js/scripts.js"></script>
    <script src="../boot/js/jquery.validate.min.js"></script>
    <script src="../boot/js/jquery.validate.min.js"></script>
    <script>
        $(function(){
            $("#captchaImage").click(function(){
                $("#captchaImage").attr("src","${pageContext.request.contextPath}/code/getCode?num="+Math.random());
            });

            $("#loginButtonId").click(function(){
                var username = $("input[name=username]").val();
                var password = $("input[name=password]").val();
                var encode = $("input[name=enCode]").val();
                if(username == ""){
                    $("#message").text("用户名不能为空！").css("color","red");
                    return;
                }else if(password == ""){
                    $("#message").text("密码不能为空！").css("color","red");
                    return;
                }else if(encode == ""){
                    $("#message").text("验证码不能为空！").css("color","red");
                    return;
                }
                $.ajax({
                    type:'POST',
                    url:'${pageContext.request.contextPath}/admin/login',
                    dataType:'json',
                    data:{
                        username:username,
                        password:password,
                        code:encode
                    },
                    success:function(data){
                        if(data.message == 'ok'){
                            location.href="${pageContext.request.contextPath}/login/index.jsp";
                        }else{
                            $("#message").text(data.message).css("color","red");
                        }
                    }
                })
            });

            $("#loginForm").validate({
                rules:{
                    username:{
                        required: true,
                        minlength: 2
                    },
                    password:{
                        required: true,
                        minlength: 5
                    },
                },
                messages:{
                    username:{
                        required: "请输入用户名",
                        minlength: "用户名必需由两个字母组成"
                    },
                    password:{
                        required: "请输入密码",
                        minlength: "密码长度不能小于 5 个字母"
                    },

                }
            })
        })
    </script>
</head>

<body>

<!-- Top content -->
<div class="top-content">

    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showall</h3>
                            <p>Enter your username and password to log</p>
                            <div id="message"></div>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" action="" method="post" class="login-form" id="loginForm">
                            <span id="msgDiv"></span>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input name="username" placeholder="请输入用户名..."
                                       class="form-username form-control" id="form-username" type="text">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="请输入密码..."
                                       class="form-password form-control" id="form-password">
                            </div>
                            <div class="form-group">
                                <img id="captchaImage" style="height: 48px" class="captchaImage"
                                     src="${pageContext.request.contextPath}/code/getCode">
                                <input style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       name="enCode" id="form-code" type="test">
                            </div>
                            <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;" id="loginButtonId" value="登录">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


</body>

</html>