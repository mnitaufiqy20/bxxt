<%--
  Created by IntelliJ IDEA.
  User: 孟敏
  Date: 13-1-9
  Time: 下午6:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    %{--<meta name="layout" content="main"/>--}%
    <title>华电集团报销系统</title>
    <script src="${resource(dir: 'js/calendar', file: 'jsdate.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'WdatePicker.js')}"></script>
    <style type="text/css">
    .Wdate{
        border:#999 1px solid;
        height:20px;
        background:#fff url(../images/main/datePicker.gif) no-repeat right;
    }

    .WdateFmtErr{
        font-weight:bold;
        color:red;
    }
    </style>
    <script type="text/javascript">
        function query(){
            var gForm = document.getElementById("gForm");
            gForm.action = "queryReceipt";
            gForm.controller = "bxReceipt"
            gForm.submit();
        }
    </script>
</head>

<body id="">
<table width="100%"  height="180" border="0" cellpadding="0" cellspacing="0">
    <tr height="40">
        <td colspan="6" align="center" ><h2>报销单列表</h2></td>
    </tr>
    <tr height="60">
        <td colspan="6" width="100%">
            <table  width="100%">
                <tr>
                    <td colspan="6" width="100%">
<g:form id="gForm" name="gForm" action="queryReceipt" controller="bxReceipt" method="post">
                        <table  width="100%" >
                            <tr>
                                <td height="30"><div style="width: 150px;background: #ADCDF4;">查询条件：</div></td>
                            </tr>
                            <tr>
                                <td colspan="6" width="100%">
                                    <table>
                                        <tr>
                                            <td height="30" width="8%" align="right">单号：<input type="hidden" name="funcCode" value="${funcCode}"></td>
                                            <td height="30" width="15%"><input id="rNo" name="rNo" type="text" value="${rNo}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                            <td height="30" width="10%" align="right">状态：</td>
                                            <td height="30" width="8%">
                                                <select id="status"  name="status" onfocus="">
                                                    <option value="-1">请选择</option>
                                                    <option value="已保存">已保存</option>
                                                    <option value="已提交">已提交</option>
                                                    <option value="已审核">已审核</option>
                                                    <option value="已过帐">已过帐</option>
                                                    <option value="已付款">已付款</option>
                                                </select>
                                            </td>
                                            <td height="30" width="12%" align="right">时间范围：</td>
                                            <td height="30" width="37%">
                                                <input type="text" name="startDate" value="${startDate}"  class="Wdate" onclick="SelectDate(this,'yyyy-MM-dd',null,null);">
                                                至<input type="text" value="${endDate}" name="endDate"  class="Wdate" onclick="SelectDate(this,'yyyy-MM-dd',null,null);">
                                            </td>
                                            <td height="30" colspan="3" width="5%" align="right">
                                                <g:if test="${a=="V"}">
                                                    <input type="button" value="查询" onclick="query();">
                                                </g:if>
                                                <g:else>
                                                    <input type="button" value="查询" disabled>
                                                </g:else>

                                            </td> &nbsp;&nbsp;&nbsp;&nbsp;
                                            <td colspan="2" width="5%" align="right">
                                                <g:if test="${b=="N"}">
                                                    <input type="button" value="添加" onclick="location='../bxReceipt/bxdDetail?funcCode=${funcCode}'">
                                                </g:if>
                                                <g:else>
                                                    <input type="button" value="添加" disabled>
                                                </g:else>
                                            </td>
                                        </tr>
                                        <tr>

                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
    </g:form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr height="90">
        <td colspan="6" width="100%">
            <table width="100%"  border="0" cellpadding="0" cellspacing="0">
                <tr height="90">
                    <td colspan="6" width="100%">
                        <table width="100%" height="90">
                            <tr  height="30">
                                <td colspan="6"><div style="width: 150px;background: #ADCDF4;">查询结果</div></td>
                            </tr>
                            <tr height="60">
                                <td>
                                    <table width="100%" height="60" border="0" cellpadding="0" cellspacing="0">
                                        <tr height="30">
                                            <td colspan="6" height="30">
                                                <table width="100%"   border="1" cellpadding="0" cellspacing="0">
                                                    <tr align="center">
                                                        <td height="30" width="10%">序号</td>
                                                        <td width="30%">单号</td>
                                                        <td width="15%">申请日期</td>
                                                        <td width="15%">实际报销总金额</td>
                                                        <td width="20%">状态</td>
                                                        <td width="10%">操作</td>
                                                    </tr>
                                                    <g:each in="${bxdList}" var="item" status="index">
                                                        <tr align="center">
                                                            <td height="30">
                                                                ${index+1}
                                                            </td>
                                                            <td>${item.bxNo}</td>
                                                            <td>${item.applicationDate}</td>
                                                            <td>${item.payCounts}</td>
                                                            <td>${item.bxdStatus}</td>
                                                            <td>
                                                                <g:if test="${item.bxdStatus=='已保存'}">
                                                                    <g:if test="${c =="E"}">
                                                                        <a href="../bxReceipt/bxdEdit?bxNo=${item.bxNo}&funcCode=${funcCode}">修改</a>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <a href="#">
                                                                            修改
                                                                        </a>
                                                                    </g:else>
                                                                </g:if>
                                                                <g:else>
                                                                    <g:if test="${a =="V"}">
                                                                        <a href="../bxReceipt/bxdLookUp?bxNo=${item.bxNo}&funcCode=${funcCode}">查看</a>
                                                                    </g:if>
                                                                    <g:else>
                                                                        <a href="#">
                                                                            查看
                                                                        </a>
                                                                    </g:else>
                                                                </g:else>
                                                            </td>
                                                        </tr>
                                                    </g:each>
                                                </table>
                                            </td>
                                        </tr>
                                        %{--<tr height="30">--}%
                                            %{--<td colspan="6" align="right">--}%
                                                %{--<input type="button" value="上一页">每页共10条，目前是第1页<input type="button" value="下一页">--}%
                                            %{--</td>--}%
                                        %{--</tr>--}%
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
<script>document.getElementById("status").value = "${status}";</script>
</body>
</html>