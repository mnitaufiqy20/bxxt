<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-6
  Time: 下午3:48
  To change this template use File | Settings | File Templates.
--%>
%{--<%@ page contentType="text/html;charset=UTF-8" %>--}%
%{--<html>--}%
%{--<head>--}%
    %{--<meta name="layout" content="main"/>--}%
    %{--<title>华电集团报销系统</title>--}%
    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">--}%
  %{--<style type="text/css" mce_bogus="1">--}%
%{--TABLE {--}%
    %{--background: blue;--}%
%{--}--}%
%{--TD, TH {--}%
    %{--background: white;--}%
%{--} </style>--}%
%{--</head>--}%

%{--<body>--}%
 %{--<table summary="jb51.net - Tables and CSS">--}%
             %{--<tr>--}%
                   %{--<td>Mr. Jin</td>--}%
                   %{--<td>600.00</td>--}%
             %{--</tr>--}%
             %{--<tr>--}%
                          %{--<td>Mr. Jones</td>--}%
                         %{--<td>0000.00</td>--}%
             %{--</tr>--}%
             %{--<tr>--}%
                         %{--<td>Ms. Williams</td>--}%
                        %{--<td>0000.00</td>--}%
             %{--</tr>--}%
             %{--</tbody>--}%
            %{--<tfoot>--}%
                  %{--<tr>--}%
                     %{--<td colspan="2">Let's sale, sale, sale!</td>--}%
                                %{--</tr>--}%
              %{--</tfoot>--}%
              %{--</table>--}%
        %{--</body>--}%

%{--</html>--}%

<html>

<head>

<title>MB_menu</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--<script type="text/javascript" src="../commons/jquery-1.2.6.js"></script>-->

<!--<script type="text/javascript" src="../commons/jquery-1.3.js"></script>-->

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript" src="inc/mbMenu.js"></script>

<script type="text/javascript" src="inc/styleswitch.js"></script>

<link rel="stylesheet" type="text/css" href="css/menu.css" title="styles1"  media="screen">

<link rel="alternate stylesheet" type="text/css" href="css/menu1.css" title="styles2" media="screen" />

<style>
       body .style a{

    color:gray;

    font-family:sans-serif;

    font-size:13px;

    text-decoration:none;

}

                ul{

  }
                li{

      display:inline-block;

      padding:20px;

      padding-bottom:4px;

  }

</style>

<script type="text/javascript">

    $(function(){

        $(".myMenu").buildMenu(

        {

            template:"menuVoices.html",

            additionalData:"pippo=1",

            menuWidth:200,

            openOnRight:false,

            rootMenuSelector: ".rootVoices",

            menuSelector: ".menuContainer",

            iconPath:"ico/",

            hasImages:true,

            fadeTime:200,

            adjustLeft:2,

            adjustTop:10,

            opacity:.95,

            shadow:true
        });

        $(".vertMenu").buildMenu(
        {

            template:"menuVoices.html",

            menuWidth:170,

            overflow:3,

            openOnRight:true,

            rootMenuSelector: ".rootVerticalVoices",

            menuSelector: ".verticalMenu",

            iconPath:"ico/",

            hasImages:true,

            fadeTime:100,

            adjustLeft:0,

            adjustTop:0,

            opacity:.95,

            openOnClick:false,

            shadow:true,

            closeOnMouseOut:true

        });

        $(document).buildContextualMenu(

        {

            template:"menuVoices.html",

            menuWidth:200,

            overflow:2,

            rootMenuSelector: ".rootVoices",

            menuSelector: ".menuContainer",

            iconPath:"ico/",

            hasImages:false,

            fadeTime:100,

            adjustLeft:0,
            adjustTop:0,


            opacity::.99,

            effect:"fade",

            shadow:true

        });

    }

    );

    //this function get the id of the element that fires the context menu.

    function testForContextMenu(){

        alert("the ID of the element is:   "+$($.mbMenu.lastContextMenuEl).attr("id"))
    }

</script>

</head>

<body bgcolor="#ffffff">

<div style="background:#FF6600 url(../commons/images/header_bgnd.jpg); padding:10px"><font color="#ffffff" size="+3" face="Courier New, Courier, mono"> mbMenu</font></div>

<table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#EDEDED">

    <tr>

        <td width="180" height="33" style="padding:10px" class="style">

            <a href="#" rel="styles1" class="styleswitch">style 1</a> /

            <a href="#" rel="styles2" class="styleswitch">style 2</a>

        </td>

        <td valign="bottom">

            <table  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="container">

                <tr>

                    <td class="myMenu" align="right">

                        <!-- voci menu -->

                        <ul class="rootVoices" cellspacing='0' cellpadding='0' border='0'>

                            <li menu="menu_12" >ajax menu 1</li>

                            <li menu="menu_2" >menu 2</li>

                            <li menu="menu_3" >menu 3</li>

                        </ul>
                        <!-- fine voci menu -->

                    </td>

                </tr>

            </table>

        </td>

    </tr>

</table>

<br>

<br>

<br>

<br>

<br>

<br>

<a cmenu="menu_12" id="pippo"> Contextual menu 1</a>&nbsp;&nbsp;&nbsp;<a cmenu="conext_menu_1" id="pluto"> Contextual menu 2</a>

<br>

<br>

<br>

<div class="containerContent" style="float:right; margin-left:150px; margin-right:100px">

    <input type="text" name="tuoTesto" value="explorer test"><br><br>

    <a href="javascript:alert('pippo')" img="ico_view.gif" >Ajax_menu_12.2 alert via href</a>

    Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi felis leo, consequat et, lacinia a, facilisis sit amet, nulla. In ornare tincidunt ante. Donec non sem. Morbi augue mi, accumsan non, nonummy ac, tempor eleifend, lacus. Nunc quam. Suspendisse tincidunt, purus sit amet adipiscing placerat, leo nunc interdum nulla, sed suscipit lacus felis ac enim. Maecenas nibh. Cras at tortor. Maecenas leo. Nullam eget tellus. Curabitur imperdiet dignissim sem. Nullam viverra viverra leo.

    Mauris ac ipsum. Aenean eget magna. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Nullam mollis ante id ipsum. Sed elementum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Duis lobortis rutrum felis. Vestibulum varius. In tincidunt turpis sed justo. Suspendisse potenti. Ut sodales risus non dui. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Proin lectus. Pellentesque vel arcu. Etiam sem lacus, molestie at, euismod non, lobortis vitae, mi.

</div>

<!-- voci menu -->

<div class="vertMenu">

    <table class="rootVerticalVoices" cellspacing='0' cellpadding='0' border='0'>

        <tr><td menu="menu_12" >ajax menu 12</td></tr>

        <tr><td menu="menu_2" >menu 2</td></tr>

        <tr><td menu="menu_3" >menu 3</td></tr>

    </table>

</div>

<!-- fine voci menu -->

<!-- struttura menu -->

<div id="menu_1" class="menu">

    <a rel="title" >title menu_1.1</a> <!-- menuvoice title-->

    <a href="../mb_carusel/carousel.html" target="_blank" img="ico_view.gif" >menu_1.1 (href)</a> <!-- menuvoice with href-->

    <a action="document.title=('menu_1.2')" >menu_1.2</a> <!-- menuvoice with js action-->

    <a rel="separator"></a> <!-- menuvoice separator-->

    <a href="/" action="document.title=('menu_1.3')" disabled=true>menu_1.3</a> <!-- menuvoice disabled-->

    <a action="document.title=('menu_1.4')" menu="menu_1" img="24-book-blue-check.png">menu_1.4</a><!-- menuvoice with js action, image and submenu-->

</div>

<div id="menu_2" class="menu">

    <a rel="title" action="document.title=('menu_2.1')" img="icon_13.png">menu_2.1 TITLE</a>

    <a action="document.title=('menu_2.2')">menu_2.2</a>

    <a menu="sub_menu_1" img="icon_14.png">menu_2.3</a>

    <a menu="sub_menu_2" img="24-tag-add.png">menu_2.4</a>

    <a rel="separator"></a>

    <a action="document.title=('menu_2.4')">menu_2.5</a>

</div>

<div id="menu_3" class="menu">

    <a rel="text" >

        <img src="images/browser.png" style="position:absolute;margin-top:-20px; margin-left:-25px;margin-bottom:10px"/><br>

        <br>immagini che vuoi ed altro testo che ti pare Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi felis leo, consequat et, lacinia a, facilisis sit amet,<br><br>

    </a>

    <a rel="separator"></a>

    <a action="document.title=('menu_3.1')" img="iconDone.png">menu_3.1</a>

    <a id="bbb" menu="sub_menu_2" >submenu</a>

    <a action="document.title=('menu_3.4')">menu_3.4 con testo veramente molto lungo</a>

</div>

<div id="sub_menu_1" class="menu">

    <a action="document.title=('sub_menu_1.1')">sub_menu_1.1</a>

    <a rel="separator"></a>

    <a menu="menu_1">sub_menu_1.2</a>

    <a action="document.title=('sub_menu_1.3')" img="bgColor.gif">sub_menu_1.3</a>

    <a action="document.title=('sub_menu_1.4')" img="Applet.gif">sub_menu_1.4</a>

</div>

<div id="sub_menu_2" class="menu">

    <a action="document.title=('sub_menu_2.1')"  img="buttonfind.gif">sub_menu_2.1</a>

    <a action="document.title=('sub_menu_2.2')">sub_menu_2.2</a>

    <a rel="separator"></a>

    <a action="document.title=('sub_menu_2.3')">sub_menu_2.3</a>

    <a action="document.title=('sub_menu_2.4')">sub_menu_2.4</a>

</div>

<div id="conext_menu_1" class="menu">

    <a rel="text" >

        <img src="images/browser.png" style="position:absolute;margin-top:-20px; margin-left:-25px;margin-bottom:10px"/><br>

        <br>immagini che vuoi ed altro testo che ti pare Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi felis leo, consequat et, lacinia a, facilisis sit amet,<br><br>

    </a>

    <a rel="separator"></a>

    <a action="testForContextMenu()" img="iconDone.png">menu_3.1</a>

    <a id="aaa" menu="sub_menu_2" >submenu</a>

    <a action="document.title=('conext_menu_1.4')">conext_menu_1.4 con testo veramente molto lungo</a>

</div>
<!-- fine struttura menu -->

</body>

</html>
