<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("path", request.getContextPath());
%>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">轮播图管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">轮播图信息</a></li>
    <li>
</ul>

<script>
    $(function () {

        $("#guruTable").jqGrid(
            {
                url: "${path}/guru/showAllGuru",
                datatype: "json",
                colNames: ['ID', '法号', '图片', '状态', '昵称'],
                colModel: [
                    {name: 'id', hidden: true},
                    {name: 'name', align: "center", editable: true, editrules: {required: true}},
                    {
                        name: 'photo', align: "center", formatter: function (data) {
                            console.log("data;;" + data)
                            return "<img style='width: 50px;height: 50px;' src='" + data + "'/>"
                        }, editable: true, edittype: "file", editoptions: {enctype: "multipart/form-data"}
                    },
                    {
                        name: 'status', align: "center", formatter: function (data) {
                            if (data == "1") {
                                return "展示";
                            } else return "冻结";
                        }, editable: true, edittype: "select", editoptions: {value: "1:展示;2:冻结"}
                    },
                    {name: 'nick_name', align: "center", editable: true, editrules: {required: true}}
                ],
                rowNum: 10,
                rowList: [1, 10, 20, 30],
                pager: '#guruPage',
                mtype: "post",
                viewrecords: true,
                styleUI: "Bootstrap",
                autowidth: true,
                multiselect: true,
                height: 500,
                editurl: "${path}/guru/edit"
            });
        $("#guruTable").jqGrid('navGrid', '#guruPage',
            {
                edit: true, add: true, del: true,
                edittext: "编辑", addtext: "添加", deltext: "删除"
            },
            // {} --> edit {}-->add  {}-->del
            {
                closeAfterEdit: true,
                // frm ---> 表单对象
                /*beforeShowForm:function (frm) {
                    frm.find("#url").attr("disabled",true);
                },*/
                afterSubmit: function (response, postData) {
                    var guruID = response.responseJSON.guruId;
                    $.ajaxFileUpload({
                        url: "${path}/guru/upload",
                        datatype: "json",
                        type: "post",
                        data: {guruId: guruID},
                        // 指定的上传input框的id
                        fileElementId: "photo",
                        success: function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#guruTable").trigger("reloadGrid");
                        }
                    });
                    return postData;
                }
            }, {
                closeAfterAdd: true,
                afterSubmit: function (response, postData) {
                    var guruID = response.responseJSON.guruId;
                    $.ajaxFileUpload({
                        url: "${path}/guru/upload",
                        datatype: "json",
                        type: "post",
                        data: {guruId: guruID},
                        // 指定的上传input框的id
                        fileElementId: "photo",
                        success: function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#guruTable").trigger("reloadGrid");
                        }
                    });
                    return postData;
                }
            }, {
                closeAfterDel: true
            });
    });
</script>
<div>
    <table id="guruTable"></table>
    <div id="guruPage" style="height: 50px"></div>
</div>