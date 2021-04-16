<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("path", request.getContextPath());
%>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">用户管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">用户信息</a></li>
    <li>
</ul>

<script>
    $(function () {

        $("#userTable").jqGrid(
            {
                url: "${path}/user/showAllusers",
                datatype: "json",
                colNames: ['ID', '电话', '密码', '盐', '状态', '照片', '名称', "昵称", "性别", "信号", "住址", "注册时间", "最后登陆时间"],
                colModel: [
                    {name: 'id', hidden: true},
                    {name: 'phone', align: "center", editrules: {required: true}},
                    {name: 'password', align: "center", editrules: {required: true}},
                    {name: 'salt', align: "center", editrules: {required: true}},
                    {
                        name: 'status', align: "center", formatter: function (data) {
                            if (data == "1") {
                                return "展示";
                            } else return "冻结";
                        }, editable: true, edittype: "select", editoptions: {value: "1:展示;2:冻结"}
                    },
                    {
                        name: 'photo', align: "center", formatter: function (data) {
                            console.log("data;;" + data)
                            return "<img style='width: 50px;height: 50px;' src='" + data + "'/>"
                        }, editable: true, edittype: "file", editoptions: {enctype: "multipart/form-data"}
                    },
                    {name: 'name', align: "center", editrules: {required: true}},
                    {name: 'nick_name', align: "center", editrules: {required: true}},
                    {name: 'sex', align: "center", editrules: {required: true}},

                    {name: 'sign', align: "center", editoptions: {required: true}},
                    {name: 'location', align: "center", editrules: {required: true}},
                    {name: 'rigest_date', align: "center"},
                    {name: 'last_login', align: "center"},

                ],
                rowNum: 10,
                rowList: [1, 10, 20, 30],
                pager: '#userPage',
                mtype: "post",
                viewrecords: true,
                styleUI: "Bootstrap",
                autowidth: true,
                multiselect: true,
                height: 500,
                editurl: "${path}/user/edit"
            });
        $("#userTable").jqGrid('navGrid', '#userPage',
            {
                add: false,
                edit: true,
                del: true,
                edittext: "编辑",
            },
            // {} --> edit {}-->add  {}-->del
            {
                closeAfterEdit: true,
                // frm ---> 表单对象
                success: function (data) {
                    // 输出上传成功
                    // jqgrid重新载入
                    $("#Table").trigger("reloadGrid");
                }
            }, {}, {
                closeAfterDel: true
            });
    });
</script>
<div>
    <table id="userTable"></table>
    <div id="userPage" style="height: 50px"></div>
</div>