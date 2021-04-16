<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("path", request.getContextPath());
%>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">管理员管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">管理管理员</a></li>
    <li>
</ul>

<script>
    $(function () {

        $("#adminTable").jqGrid(
            {
                url: "${path}/admin/showAlladmins",
                datatype: "json",
                colNames: ['ID', '账号', '密码', '权限ID', '权限'],
                colModel: [
                    {name: 'id', hidden: false},
                    {name: 'username', align: "center"},
                    {name: 'password', align: "center"},
                    {
                        name: 'resource_id', hidden: false, editable: true,
                        // roles=[Role(id=2, role_name=superadmin, resources=[Resource(id=2, resource_name=superadmin:*)])`
                        formatter: function (value, options, row) {
                            console.log("roles" + row.roles);
                            for (let i = 0; i < row.roles.length; i++) {
                                console.log("1" + row.roles[i]);
                                for (let j = 0; j < row.roles[i].resources.length; j++) {
                                    console.log("2");
                                    console.log(row.roles[i].resources[j].resource_name);
                                    return row.roles[i].resources[j].id;
                                }
                            }
                        }
                    },
                    {
                        name: 'resource_name', hidden: false, editable: true,
                        // roles=[Role(id=2, role_name=superadmin, resources=[Resource(id=2, resource_name=superadmin:*)])`
                        formatter: function (value, options, row) {
                            console.log("roles" + row.roles);
                            for (let i = 0; i < row.roles.length; i++) {
                                console.log("1" + row.roles[i]);
                                for (let j = 0; j < row.roles[i].resources.length; j++) {
                                    console.log("2");
                                    console.log(row.roles[i].resources[j]);
                                    return row.roles[i].resources[j].resource_name;
                                }
                            }
                        }
                    },
                ],
                rowNum: 10,
                rowList: [10, 20, 30],
                pager: '#adminPage',
                mtype: "post",
                viewrecords: true,
                styleUI: "Bootstrap",
                autowidth: true,
                multiselect: true,
                height: 500,
                editurl: "${path}/admin/edit"
            });
        $("#adminTable").jqGrid('navGrid', '#adminPage',
            {
                edit: true, add: true, del: true,
                edittext: "编辑", addtext: "添加", deltext: "删除"
            },
            // {} --> edit {}-->add  {}-->del
            {
                closeAfterEdit: true
            }, {
                closeAfterAdd: true
            }, {
                closeAfterDel: true
            });
    });
</script>
<div>
    <table id="adminTable"></table>
    <div id="adminPage" style="height: 50px"></div>
</div>