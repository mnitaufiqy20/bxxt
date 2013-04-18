<%--
  Created by IntelliJ IDEA.
  User: Lau
  Date: 12-12-26
  Time: 下午7:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    %{--<meta name="layout" content="main"/>--}%
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'index.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'login.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'menu.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'js/ligerUI/skins/Aqua/css/', file: 'ligerui-all.css')}" type="text/css">
    <script src="${resource(dir: 'js', file: 'jquery/jquery-1.5.2.min.js')}" type="text/javascript"></script>
    <script src="${resource(dir: 'js', file: 'ligerUI/js/plugins/ligerTree.js')}" type="text/javascript"></script>
    <script src="${resource(dir: 'js', file: 'ligerUI/js/plugins/ligerDialog.js')}" type="text/javascript"></script>
    <script src="${resource(dir: 'js', file: 'ligerUI/js/ligerui.min.js')}" type="text/javascript"></script>
    <script type="text/javascript">
        var tab = null;
        var accordion = null;
        var tree = null;
        %{--var datas=[--}%
        %{--{"text":"华电用户管理平台","children":[{"text":"系统配置","children":[{"text":"系统信息","children":[{"url":"${createLink(uri:'/tmMenu/index?funcCode=FN010001')}","text":"菜单维护"},--}%
        %{--{"url":"${createLink(uri:'/tmFunction/index?funcCode=FN010002')}","text":"功能维护"},--}%
        %{--{"url":"${createLink(uri:'/tmRole/index?funcCode=FN010003')}","text":"角色维护"},--}%
        %{--{"url":"${createLink(uri:'/tmParameter/index?funcCode=FN010004')}","text":"参数维护"}--}%
        %{--]},--}%
        %{--{"text":"基础信息","children":[{"url":"${createLink(uri:'/tmLookupType/index?funcCode=FN010005')}","text":"数据字典类别维护"},--}%
        %{--{"url":"${createLink(uri:'/tmLookup/index?funcCode=FN010006')}","text":"数据字典内容维护"},--}%
        %{--{"url":"${createLink(uri:'/tmLdapSchema/index?funcCode=FN010007')}","text":"LDAP Schema维护"},--}%
        %{--{"url":"${createLink(uri:'/tmLdapSchema/LdapDefineIndex?funcCode=FN010008')}","text":"LDAP数据定义"}--}%
        %{--]}--}%
        %{--]},--}%
        %{--{"text":"核心数据","children":[{"url":"${createLink(uri:'/organization/index?funcCode=FN020001')}","text":"组织管理"},--}%
        %{--{"url":"${createLink(uri:'/user/index?funcCode=FN020002')}","text":"人员管理"},--}%
        %{--{"url":"${createLink(uri:'/tmApplication/index?funcCode=FN020003')}","text":"应用系统管理"},--}%
        %{--{"url":"${createLink(uri:'/appRole/index?funcCode=FN020004')}","text":"角色管理"}--}%
        %{--]},--}%
        %{--{"text":"流程","children":[{"url":"${createLink(uri:'/process/index?funcCode=FN030001')}","text":"流程管理"},--}%
        %{--{"url":"${createLink(uri:'/taskStore/index?funcCode=FN030002')}","text":"任务管理"},--}%
        %{--{"url":"${createLink(uri:'/tmWfForm/index?funcCode=FN030003')}","text":"申请应用系统权限"},--}%
        %{--{"url":"${createLink(uri:'/tmWfForm2/index?funcCode=FN030004')}","text":"申请应用系统变更"},--}%
        %{--{"url":"${createLink(uri:'/tmWfForm3/index?funcCode=FN030005')}","text":"申请应用系统删除"},--}%
        %{--{"url":"${createLink(uri:'/tmWfLdapuser/index?funcCode=FN030006')}","text":"LDAP帐号申请"}--}%
        %{--]}--}%
        %{--]}--}%
        %{--];--}%


        $(function ()
        {
            //布局
            $("#layout1").ligerLayout({ leftWidth: 190, height: '100%',heightDiff:-34,space:4, onHeightChanged: f_heightChanged });

            var height = $("#welcome").height();
//            alert("height:"+height);

            //Tab
            $("#framecenter").ligerTab({ height: height });

            //面板
            $("#accordion1").ligerAccordion({ height: height+28, speed: null });

            $(".l-link").hover(function ()
            {
                $(this).addClass("l-link-over");
            }, function ()
            {
                $(this).removeClass("l-link-over");
            });
            //树

            $("#tree1").ligerTree({
                url:'${createLink(uri: '/workbench/tree')}',
                //url: 'tree',
//                data : datas,
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
//            tree = $("#tree1").ligerGetTreeManager();
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

<body id="">
<table id="container" width="100%" height="100%" border="1" cellpadding="0" cellspacing="0">
    <tr height="10%">
        <g:form>
            <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="header">
                    <tr>
                        <td width="90%" height="71" class="header_left">&nbsp;</td>
                        <td width="20%" align="right" valign="middle" class="header_right">
                            <a>您&nbsp;好，&nbsp;<sec:loggedInUserInfo field="username"/></a> <br>
                            <a href="../logout/index">注销</a>
                        </td>
                    </tr>
                </table>
            </td>
        </g:form>
    </tr>
    <tr height="85%">
        <td valign="top" id="content">
            <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="content_bg"
                   style="background: #ADCDF4;">
                <tr>
                    <td width="280" height="85%">
                        <div id="layout1" align="left" style="width:100%; margin:0 auto; margin-top:0px; ">
                            <div position="absolute"  title="主要菜单" id="accordion1">
                                %{--<div title="功能列表" class="l-scroll">--}%
                                <div title="功能列表"   class="l-scroll" style="height:539px;width: 278px">
                                    <ul id="tree1" style="margin-top:3px;width:auto;">

                                    </ul>
                                </div>
                            </div>
                        </div>                                    %{--<g:each in="${menuItems}" status="i" var="menuItem">--}%
                                        %{--<li class="level1"><a href="#">${menuItem.categoryName}</a>--}%
                                            %{--<ul class="level2">--}%
                                                %{--<g:each in="${menuItem.menus}" status="ii" var="menu">--}%
                                                    %{--<g:if test="${menu.list.size()==0}">--}%
                                                        %{--<li class="level3"><a href="${menu.actionUrl}?menuId=${menu.id}"--}%
                                                               %{--target="contentFrame">${menu.menuName}</a>--}%
                                                        %{--</li>--}%
                                                %{--</g:each>--}%
                                            %{--</ul>--}%
                                        %{--</li>--}%
                                    %{--</g:each>--}%
                                %{--</ul>--}%
                    </td>
                    <td height="100%">
                        <div id="main">
                            <div position="center" id="framecenter">
                                <div tabid="home" id="welcome" style="height:532px" >
                                    %{--<iframe frameborder="0" name="home" id="home" src="${createLink(uri: '/tmWfForm5/index')}"></iframe>--}%
                                    <g:if test="${isAuth=='1'}">
                                        <iframe id="home" name="home" width="100%" height="100%" frameborder="0"
                                               src="../processes/initProcess" >
                                        </iframe>
                                    </g:if>
                                    <g:else>
                                        <iframe id="home" name="home" width="100%" height="100%" frameborder="0"
                                                 >
                                        </iframe>
                                    </g:else>
                                </div>
                            </div>
                            %{--<iframe id="blank" name="contentFrame" width="100%" height="100%" frameborder="0"--}%
                                    %{--scrolling="yes" src="../processes/initProcess" >--}%

                            %{--</iframe>--}%


                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr height="5%">
        <td align="center">
            <div id="pagefooter">
                <div class="footer_right">
                    Copyright(C) 北京市联系 地址：中国华电集团公司
                </div>

                <div class="clear"></div>
            </div>
        </td>
    </tr>
</table>
</body>
</html>