<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>info</title>
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
    <script type="text/javascript">
        $(function () {
            console.log("进入function")
            $.post('http://localhost:8771/info/queryall', function (data) {
                console.log("data]" + data);
                $('#thead').empty();
                $('#tbody').empty();
                $('#thead').append("<tr><th>ID</th><th>名字</th><th>价格</th><th>库存</th><th>描述</th><th>封面</th><th>类型</th><th>创建时间</th><th>销量</th><th>更新时间</th><th>操作</th></tr>");
                for (let i = 0; i < data.length; i++) {
                    console.log(data[i]);
                    $('#tbody').append('<tr><td>' + data[i].product_id + '</td><td>' + data[i].prodcut_name + '</td><td>' + data[i].product_price + '</td><td>' + data[i].product_stock + '</td><td>' + data[i].product_description + '</td><td>' + data[i].product_icon + '</td><td>' + data[i].category_type + '</td><td>' + data[i].create_time + '</td><td>' + data[i].sales + '</td><td>' + data[i].update_time + '</td> <td><a href="http://localhost:8772/master/master?id=' + data[i].product_id + '"><input type="button" value="添加购物车" class="success"></a></td></tr>\n');
                }
            }, 'json')
        })

    </script>
</head>
<body>
<form>
    <table class="table table-condensed">`````````````
        <caption>商品信息</caption>
        <thead id="thead">

        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
</form>

</body>
</html>