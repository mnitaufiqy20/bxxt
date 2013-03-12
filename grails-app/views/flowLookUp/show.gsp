
<!DOCTYPE html>
<html>
	<head>
		<title>流程查看</title>
        %{--<script src="${resource(dir: 'js', file: 'jquery/jquery-1.5.2.min.js')}" type="text/javascript"></script>--}%
        %{--<link rel="stylesheet" href="${resource(dir: 'js/ligerUI/skins/Aqua/css/', file: 'ligerui-all.css')}" type="text/css">--}%
        %{--<link rel="stylesheet" href="${resource(dir: 'js/ligerUI/skins', file: 'ligerui-icons.css')}" type="text/css">--}%
        %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">--}%
        %{--<link rel="stylesheet" href="${resource(dir: 'skin/royalblue/css', file: 'common.css')}" type="text/css">--}%
	</head>
<script type="text/javascript">
function getList(){
     window.location.href="index";
}
</script>
	<body style="overflow: hidden">
    <div class="center">
        <div class="title_common">
            <table border="0" cellspacing="0" cellpadding="2">
                <tr>
                    <td  style="width: 60px" class="search_th" >
                        <input type="button" class="l-button" value="流程列表" style="cursor: pointer" onclick="getList()" />
                    </td>
                </tr>

            </table>
        </div>
        <div class="bk4">
                <div class="table_sub_title"><b>流程历史信息</b></div>
                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%;" class="table_grey">
                    <thead>
                    <tr class="title">
                        <td>流程定义ID</td>

                        <td>流程名称</td>

                        <td>流程描述</td>

                    </tr>
                    </thead>
                    <tbody>
                    <tr class="title">

                        <td>${fieldValue(bean: process, field: "processId")}</td>

                        <td>${fieldValue(bean: process, field: "processName")}</td>

                        <td>${fieldValue(bean: process, field: "description")}</td>

                    </tr>
                    </tbody>
                </table>
            <div>
                <iframe id="process_img"
                        style="width: 100%;height: 400px"
                        scrolling="no" onload="iFrameHeight( 'process_img')" frameBorder=0	src="${createLink(action:'showImage',controller:'flowLookUp',params:[processId:process.processId])}"></iframe>
            </div>
        </div>
    </div>
	</body>
</html>
