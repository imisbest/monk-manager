<%@ page pageEncoding="UTF-8" contentType="text/html; charset=utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    request.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>backhome</title>
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
    <script src="${path}/boot/js/ajaxfileupload.js"></script>
    <script src="${path}/kindeditor/kindeditor-all-min.js"></script>
    <script src="${path}/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#myCarousel").carousel('cycle');

            /*//切换样式*/
            $(".list-group").on("click", ".list-group-item", function () {
                //去掉所有
                $(".list-group-item").removeClass().addClass("list-group-item");
                //点击激活
                $(this).addClass("active");
            });
        })

        KindEditor.ready(function (K) {
            window.editor = K.create('#editor_id', {
                width: '600px',
                // 1. 指定图片上传路径
                uploadJson: "${path}/article/uploadImg",
                allowFileManager: true,
                fileManagerJson: "${path}/article/showAllImgs",
                afterBlur: function () {
                    this.sync();
                }
            });
        });
    </script>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持名法舟后台管理系统</a>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="#">欢迎:<shiro:principal></shiro:principal></a></li>
                <li><a href="${path}/admin/logout">退出登陆</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">

                        <div class="panel-body list-group">
                            <shiro:hasPermission name="admin:select">
                                <%--  <a href="#" class="list-group-item active">免费域名注册</a>--%>
                                <a href="javascript:$('#contant').load('./user_list.jsp')"
                                   class="list-group-item active">用户信息管理</a>
                                <%-- <a href="#" class="list-group-item">用户注册趋势</a>
                                 <a href="#" class="list-group-item">用户注册分布</a>--%>
                            </shiro:hasPermission>
                        </div>

                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">

                        <div class="panel-body list-group">
                            <shiro:hasRole name="admin">
                                <a href="javascript:$('#contant').load('./guru_list.jsp')"
                                   class="list-group-item active">上师信息</a>
                            </shiro:hasRole>
                        </div>

                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body list-group">
                            <a href="javascript:$('#contant').load('./article_list.jsp')"
                               class="list-group-item active">文章列表</a>
                            <a href="javascript:$('#contant').load('./es-front.jsp')"
                               class="list-group-item active">es搜索</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapse4">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapse4" class="panel-collapse collapse">
                        <div class="panel-body list-group">
                            <a href="javascript:$('#contant').load('./album.jsp')"
                               class="list-group-item active">专辑信息</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapse5">
                                图片轮播
                            </a>
                        </h4>
                    </div>
                    <div id="collapse5" class="panel-collapse collapse">

                        <div class="panel-body list-group">
                            <a href="javascript:$('#contant').load('./banner.jsp')"
                               class="list-group-item active">轮播图信息</a>
                        </div>

                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapse6">
                                管理员管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapse6" class="panel-collapse collapse">

                        <div class="panel-body list-group">
                            <shiro:hasRole name="superadmin">
                                <a href="javascript:$('#contant').load('./admin.jsp')"
                                   class="list-group-item active">管理管理员</a>
                            </shiro:hasRole>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-10">
            <div class="container" id="contant">
                <div class="jumbotron">
                    <h1>欢迎使用持名法洲后台管理系统</h1>
                </div>
                <div id="myCarousel" class="carousel slide">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>

                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="../../img/微信图片_20190603161551.jpg" alt="First slide"
                                 style="width:1000px; height:600px;">
                        </div>
                        <div class="item">
                            <img src="../../img/微信图片_20180716115911.jpg" alt="Second slide"
                                 style="width:1000px; height:600px;">
                        </div>
                        <div class="item">
                            <img src="../../img/微信图片_20190603161641.jpg" alt="Third slide"
                                 style="width:1000px; height:600px;">
                        </div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>


        </div>
    </div>
</div>
<div class="panel-footer">
    <h4 style="text-align: center">百知教育 @baizhiedu.com.cn</h4>
</div>
<div class="modal fade" id="kind" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">文章信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" enctype="multipart/form-data" id="kindfrm">
                    <input name="id" type="text" id="formid" hidden="hidden">
                    <div class="form-group">
                        <label for="name">标题</label>
                        <input type="text" class="form-control" name="title" id="name" placeholder="请输入名称">
                    </div>
                    <div class="form-group">
                        <label for="inputfile">封面上传</label>
                        <input type="file" name="articleImg" id="inputfile">
                    </div>
                    <div class="form-group">
                        <label for="name">所属上师</label>
                        <select class="form-control" id="guruList" name="guru_id">

                        </select>
                    </div>
                    <div class="form-group">
                        <label for="editor_id">内容</label>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                        &lt;strong&gt;HTML内容&lt;/strong&gt;
                        </textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer" id="modal_foot"></div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>
