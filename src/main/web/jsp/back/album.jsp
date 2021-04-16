<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("path", request.getContextPath());
%>

<script>
    $(function () {
        // 创建父级JqGrid表格
        $("#table").jqGrid(
            {
                url: "${path}/album/showAllalbums",
                datatype: "json",
                height: 500,
                colNames: ['ID', '标题', '分数', "作者", "播音员", "章节数", "专题简介", "状态", "创建时间", "封面"],
                colModel: [
                    {name: 'id', align: "center"},
                    {name: 'title', align: "center", editable: true, editrules: {required: true}},
                    {name: 'score', align: "center", editable: true},
                    {name: 'author', align: "center", editable: true},
                    {name: 'broadcast', align: "center", editable: true},
                    {name: 'count', align: "center", editable: true},
                    {name: 'desc', align: "center", editable: true, editoptions: {required: true}},
                    {
                        name: 'status', align: "center",
                        formatter: function (data) {
                            if (data == "1") {
                                return "展示";
                            } else return "冻结";
                        },
                        editable: true, edittype: "select", editoptions: {value: "1:展示;2:冻结"}
                    },
                    {name: 'create_date', align: "center", editable: true, edittype: "date"},
                    {
                        name: 'cover', align: "center", formatter: function (data) {
                            console.log("data;;" + data)
                            return "<img style='width: 50px;height: 50px;' src='" + data + "'/>"
                        }, editable: true, edittype: "file", editoptions: {enctype: "multipart/form-data"}
                    },
                ],
                rowNum: 8,
                rowList: [8, 10, 20, 30],
                pager: '#page',
                mtype: "post",
                sortname: 'id',
                viewrecords: true,
                sortorder: "desc",
                multiselect: true,
                // 开启多级表格支持
                subGrid: true,
                caption: "Subgrid案例",
                autowidth: true,
                styleUI: "Bootstrap",
                editurl: "${path}/album/edit",
                // 重写创建子表格方法
                subGridRowExpanded: function (subgrid_id, row_id) {
                    addTable(subgrid_id, row_id);
                },
                // 删除表格方法
                subGridRowColapsed: function (subgrid_id, row_id) {

                }
            });
        $("#table").jqGrid('navGrid', '#page', {
                edit: true, add: true, del: true,
                edittext: "编辑", addtext: "添加", deltext: "删除"
            },
            // {} --> edit {}-->add  {}-->del
            {
                closeAfterEdit: true,
                // frm ---> 表单对象
                afterSubmit: function (response, postData) {
                    var albumID = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url: "${path}/album/upload",
                        datatype: "json",
                        type: "post",
                        //
                        data: {albumId: albumID},
                        // 指定的上传input框的id
                        fileElementId: "cover",
                        success: function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#table").trigger("reloadGrid");
                        }
                    })
                    return postData;
                }
            }, {
                closeAfterAdd: true,
                afterSubmit: function (response, postData) {
                    var albumID = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url: "${path}/album/upload",
                        datatype: "json",
                        type: "post",
                        //
                        data: {albumId: albumID},
                        // 指定的上传input框的id
                        fileElementId: "cover",
                        success: function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#table").trigger("reloadGrid");
                        }
                    });
                    return postData;
                }
            }, {
                closeAfterDel: true
            });
    });

    // subgrid_id 下方空间的id  row_id 当前行id数据
    function addTable(subgrid_id, row_id) {
        // 声明子表格|工具栏id
        var subgridTable = subgrid_id + "table";
        var subgridPage = subgrid_id + "page";
        // 根据下方空间id 创建表格及工具栏
        $("#" + subgrid_id).html("<table id='" + subgridTable + "'></table><div style='height: 50px' id='" + subgridPage + "'></div>");
        // 子表格JqGrid声明
        $("#" + subgridTable).jqGrid({
            url: "${path}/chapter/queryAllChapters?id=" + row_id,
            datatype: "json",
            colNames: ['ID', '标题', "大小", "时长", "上传时间", "音频"],
            colModel: [
                {name: 'id', hidden: true},
                {name: "title", align: "center", editable: true},
                {name: "size", align: "center"},
                {name: "time", align: "center"},
                {name: "create_time", align: "center", editable: true, edittype: "date"},
                {
                    name: "url",
                    align: "center",
                    editable: true,
                    edittype: "file",
                    editoptions: {enctype: "multpart/form-data"},
                    formatter: function (data) {
                        var result = "";
                        result += "<a href='javascript:void(0)' onclick=\"playAudio('" + data + "')\" class='btn btn-lg' title='播放'><span class='glyphicon glyphicon-play-circle'></span></a>";
                        result += "<a href='javascript:void(0)' onclick=\"downloadAudio('" + data + "')\" class='btn btn-lg' title='下载'><span class='glyphicon glyphicon-download'></span></a>";
                        return result;
                    }
                },
            ],
            rowNum: 20,
            pager: "#" + subgridPage,
            sortname: 'num',
            mtype: "post",
            sortorder: "asc",
            height: '100%',
            styleUI: "Bootstrap",
            autowidth: true,
            editurl: "${path}/chapter/edit?albumId=" + row_id,
        });
        $("#" + subgridTable).jqGrid('navGrid', '#' + subgridPage,
            {
                edit: true, add: true, del: true,
                edittext: "编辑", addtext: "添加", deltext: "删除"
            },
            // {} --> edit {}-->add  {}-->del
            {
                closeAfterEdit: true,
                // frm ---> 表单对象
                beforeShowForm: function (frm) {
                    frm.find("#create_date").attr("readOnly", true);
                    // frm.find("#url").attr("disabled",true);
                },
                afterSubmit: function (response, postData) {
                    //
                    var chapterID = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url: "${path}/chapter/upload",
                        datatype: "json",
                        type: "post",
                        //
                        data: {chapterId: chapterID},
                        // 指定的上传input框的id
                        fileElementId: "url",
                        success: function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#" + subgridTable).trigger("reloadGrid");
                        }
                    });
                    return postData;
                }
            }, {
                closeAfterAdd: true,
                afterSubmit: function (response, postData) {
                    //
                    var chapterID = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url: "${path}/chapter/upload",
                        datatype: "json",
                        type: "post",
                        //
                        data: {chapterId: chapterID},
                        // 指定的上传input框的id
                        fileElementId: "url",
                        success: function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#" + subgridTable).trigger("reloadGrid");
                        }
                    });
                    return postData;
                }
            }, {
                closeAfterDel: true
            });
    }

    function playAudio(data) {
        $("#myModal").modal("show");
        $("#myaudio").attr("src", data);
    }

    function downloadAudio(data) {
        location.href = "${path}/chapter/download?url=" + data;
    }

</script>
<table id="table"></table>
<div style="height: 50px" id="page"></div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <audio src="" id="myaudio" controls></audio>
</div>
