<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("path", request.getContextPath());
%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>back-login</title>
    <link href="favicon.ico" rel="shortcut icon"/>
    <link rel="stylesheet" href="${path}/boot/css/bootstrap.min.css">
    <!--引入jqgrid的bootstrap css-->
    <link rel="stylesheet" href="${path}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <!--引入jquery核心js-->
    <script src="${path}/boot/js/jquery-2.2.1.min.js"></script>
    <!--引入jqgrid核心js-->
    <script src="${path}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <!--引入jqgrid国际化js-->
    <script src="${path}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <!--引入bootstrap组件js-->
    <script src="${path}/boot/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function login() {
            $.ajax({
                url: "${path}/admin/login",
                type: "POST",
                datatype: "JSON",
                data: $("#loginForm").serialize(),
                success: function (data) {
                    if (data != null & data != "") {
                        console.log("报错");
                        $("#msg").html("<span class='error'>" + data + "</span>")
                    } else {
                        console.log("成功进入");
                        location.href = "${path}/jsp/back/backhome.jsp";
                    }
                }
            })
        }
    </script>
</head>
<body style=" background: url(../../img/009e9dfd5266d016d30938279a2bd40735fa3576.jpg); background-size: 100%;">


<div class="modal-dialog" style="margin-top: 10%;">
    <div class="modal-content">
        <div class="modal-header">

            <h4 class="modal-title text-center" id="myModalLabel">持明法洲</h4>
        </div>
        <form id="loginForm" method="post" <%--action="${path}/user/login"--%>>
            <div class="modal-body" id="model-body">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" autocomplete="off" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" autocomplete="off" name="password">
                </div>
                <div class="form-group">

                    <img src="${path}/captcha/captcha" id="captchaImg" alt=""/>
                    <div class="label-text">验证码：<%--${sessionScope.securityCode}--%></div>
                    <input type="text" name="captchaCode"><br/>
                    <a href="javascript:void(0)"
                       onclick="changeCaptcha()">看不清，换一张!~</a>&nbsp;
                    <span style="color:red">${errorMsg }</span><br/>
                    <script type="text/javascript">
                        function changeCaptcha() {
                            // 根据ID获取到验证码图片对象
                            var captchaImg = document.getElementById('captchaImg');
                            captchaImg.src = '${path}/captcha/captcha?' + Math.random();
                        }
                    </script>

                </div>
                <span id="msg" style="color: red"></span>
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <button type="button" class="btn btn-primary form-control" id="log" onclick="login()">登录</button>
                </div>

            </div>
        </form>
    </div>
</div>
</body>
</html>
