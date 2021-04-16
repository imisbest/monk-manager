<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>系统主页</title>
    <!--引入bootstrap css-->
    <link rel="stylesheet" href="${path}/statics/bootstrap/css/bootstrap.min.css">
    <!--引入jqgrid的bootstrap css-->
    <link rel="stylesheet" href="${path}/statics/jqgrid/css/ui.jqgrid-bootstrap.css">
    <!--引入jquery核心js-->
    <script src="${path}/statics/js/jquery-3.4.1.min.js"></script>
    <!--引入jqgrid核心js-->
    <script src="${path}/statics/jqgrid/js/jquery.jqGrid.min.js"></script>
    <!--引入jqgrid国际化js-->
    <script src="${path}/statics/jqgrid/i18n/grid.locale-cn.js"></script>
    <!--引入bootstrap组件js-->
    <script src="${path}/statics/bootstrap/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $('#search_menu').click(function () {
                if ($('#quids').val() == null || $('#quids').val() == "") {
                } else {
                    $.post("${path}/article/hotwords", {hotwords: $('#quids').val()}, function (list) {
                        $('#uladd').empty();
                        $.each(list, function (i, poems) {
                            $('#uladd').append($('<li><span style="color: cornflowerblue">' + poems.id + '</span></li>'));
                            $('#uladd').append($('<li>' + poems.title + '</li>'));
                            $('#uladd').append($('<li>' + poems.content + '</li>'));
                        })
                    }, "json");
                }
            });
        });

    </script>
</head>
<body>

<h1 style="text-align: center">唐诗宋词检索系统</h1><br>
<div style="text-align: center">检索唐诗宋词
    <input type="text" name="" value="" id="quids" size="100">
    <input type="button" id="search_menu" value="检索">
</div>
<div class="row">
    <ul id="uladd">
    </ul>
</div>
<br>

<div id="content"></div>
</body>
</html>