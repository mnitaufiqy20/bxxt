<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>华电集团统一用户管理平台</title>
    <link rel="stylesheet" href="${resource(dir: 'js/ligerUI/skins/Aqua/css/', file: 'ligerui-all.css')}" type="text/css">
    <script src="${resource(dir: 'js', file: 'jquery/jquery-1.5.2.min.js')}" type="text/javascript"></script>
    <script src="${resource(dir: 'js', file: 'ligerUI/js/plugins/ligerTree.js')}" type="text/javascript"></script>
    <script src="${resource(dir: 'js', file: 'ligerUI/js/plugins/ligerDialog.js')}" type="text/javascript"></script>
    <script src="${resource(dir: 'js', file: 'ligerUI/js/ligerui.min.js')}" type="text/javascript"></script>
    <style type="text/css">
    body,html{height:100%;}
    body{ padding:0px; margin:0;   overflow:hidden;}
    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:underline; color:#333;}
    .l-link2{text-decoration:underline; color:white; margin-left:2px;margin-right:2px;}
    .l-layout-top{background:#102A49; color:White;}
    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
    #pageloading{position:absolute; left:0px; top:0px; background:white url('loading.gif') no-repeat center; width:100%; height:100%;z-index:99999;}
    .l-link{ display:block; line-height:22px; height:22px; padding-left:16px;border:1px solid white; margin:4px;}
    .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;}
    .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
    .space{ color:#E7E7E7;}
        /* 顶部 */
    .l-topmenu{ margin:0; padding:0; height:31px; line-height:31px; background:url('${resource(dir: 'js', file: 'framework/images/top.jpg')}') repeat-x bottom;  position:relative; border-top:1px solid #1D438B;}
    .l-topmenu-logo{ color:#E7E7E7; padding-left:35px; line-height:26px;background:url('${resource(dir: 'js', file: 'images/chd.gif')}') no-repeat 10px 5px;}
    .l-topmenu-welcome{  position:absolute; height:24px; line-height:24px;  right:30px; top:2px;color:#070A0C;}
    .l-topmenu-welcome a{ color:#E7E7E7; text-decoration:underline}

    </style>
    <script type="text/javascript">
        var tab = null;
        var accordion = null;
        var tree = null;
        $(function ()
        {

            //布局
            $("#layout1").ligerLayout({
                leftWidth:200,
                height: '100%',
                heightDiff:-34,
                space:4,
                onHeightChanged: f_heightChanged,
                allowLeftResize:false
            });

            var height = $(".l-layout-center").height();

            //Tab
            $("#framecenter").ligerTab({ height: height });

            //面板
            $("#accordion1").ligerAccordion({ height: height - 24, speed: null });

            $(".l-link").hover(function ()
            {
                $(this).addClass("l-link-over");
            }, function ()
            {
                $(this).removeClass("l-link-over");
            });
            //树

             $("#tree1").ligerTree({
             url:'${createLink(uri: '/frameMain/tree')}',
             checkbox: false,
             slide: false,
             nodeWidth: 120,
             attribute: ['nodename', 'url'],
             onSelect: function (node)
             {
             if (!node.data.url) return;
             var tabid = $(node.target).attr("tabid");
             if (!tabid)
             {
             tabid = new Date().getTime();
             $(node.target).attr("tabid", tabid)
             }
             f_addTab(tabid, node.data.text, node.data.url);
             }
             });

            tab = $("#framecenter").ligerGetTabManager();
            accordion = $("#accordion1").ligerGetAccordionManager();
            //tree = $("#tree1").ligerGetTreeManager();
            $("#pageloading").hide();

        });
        function f_heightChanged(options)
        {
            if (tab)
                tab.addHeight(options.diff);
            if (accordion && options.middleHeight - 24 > 0)
                accordion.setHeight(options.middleHeight - 24);
        }
        function f_addTab(tabid,text, url)
        {

            tab.addTabItem({ tabid : tabid,text: text, url: url });
        }

       

      

            
    </script>
</head>
<body style="padding:0px;background:#EAEEF5;">
<div id="pageloading"></div>
<div id="topmenu" class="l-topmenu">

        <div class="l-topmenu-logo">
        %{--导航主页--}%
        %{--<img style="height: 26px" src="${resource(dir: 'images', file: 'chd.jpg')}" alt="Grails"/>--}%
    </div>
    <div class="l-topmenu-welcome">
        <span class="l-link2" >当前用户：${request.getAttribute("userId")}</span>
        <span class="space">|</span>
        %{--<a href="#" class="l-link2" >密码保护问题</a>--}%
        %{--<span class="space">|</span>--}%
        <a href="#" class="l-link2" title="点击修改当前密码" id="xgmm" onclick="modifyPassword()">修改密码</a>
        <span class="space">|</span>
        <a href="../logout/index" class="l-link2" title="点击将退出"  id="tc1">退出</a>
    </div>
</div>
<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; ">
    <div position="left"  title="主要菜单" id="accordion1">
        %{--<div title="功能列表" class="l-scroll">--}%
        <div title="功能列表"   class="l-scroll" style="height:300px;width: 200px">
            <ul id="tree1" style="margin-top:3px;width:auto;">

            </ul>
        </div>
    </div>
    <div position="center" id="framecenter">
        <div tabid="home" title="我的主页" style="height:300px" >
            <iframe frameborder="0" name="home" id="home" src="${createLink(uri: '/tmWfForm5/index')}"></iframe>
        </div>
    </div>
    <div id="detail" style="display:none;">
        <form class="l-form" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 原密码：
            <input type="password" id="pass" onkeyup="Pass()" onblur="Pass4()"/>&nbsp;&nbsp;<span id="checkPass" style="color: red"></span><br/><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 新密码：
            <input type="password" id="password" onkeyup="Pass2()" onblur="Pass4()"/>&nbsp;&nbsp;<span id="checkPass2" style="color: red"></span><br/><br/>
            新密码确认：
            <input type="password" id="password2" onkeyup="Pass3()" onblur="Pass4()"/>&nbsp;&nbsp;<span id="checkPass3" style="color: red"></span><br/><br/>
            <span id="checkPass4" style="color: red"></span>
            <span id="checkPass5" style="color:green;"></span>

        </form>
    </div>

</div>
<div  style="height:32px; line-height:32px; text-align:center;">
    Copyright © 凯捷 2012
</div>
<div style="display:none"></div>
</body>
</html>