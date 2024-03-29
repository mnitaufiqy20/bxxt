<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-11
  Time: 下午3:28
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>华电集团报销系统</title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mark.css')}" type="text/css">
    <script src="${resource(dir: 'js/calendar', file: 'jsdate.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'WdatePicker.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'main.js')}"></script>
    <script type="text/javascript">
        function markColor(){
            if('${bxReceipt.bxdStatus}'=='已保存'){
                document.getElementById("save").style.backgroundColor = "green";
            }else if('${bxReceipt.bxdStatus}'=='已提交'){
                document.getElementById("save").style.backgroundColor = "green";
                document.getElementById("commit").style.backgroundColor = "green";
            }else if('${bxReceipt.bxdStatus}'=='已审核'){
                document.getElementById("save").style.backgroundColor = "green";
                document.getElementById("commit").style.backgroundColor = "green";
                document.getElementById("deputy").style.backgroundColor = "green";
            }else if('${bxReceipt.bxdStatus}'=='已过账'){
                document.getElementById("save").style.backgroundColor = "green";
                document.getElementById("commit").style.backgroundColor = "green";
                document.getElementById("deputy").style.backgroundColor = "green";
                document.getElementById("post").style.backgroundColor = "green";
            }else if('${bxReceipt.bxdStatus}'=='已付款'){
                document.getElementById("save").style.backgroundColor = "green";
                document.getElementById("commit").style.backgroundColor = "green";
                document.getElementById("deputy").style.backgroundColor = "green";
                document.getElementById("post").style.backgroundColor = "green";
                document.getElementById("back").style.backgroundColor = "green";
            }
            document.getElementById("goBack").disabled = true;
            document.getElementById("ideaRemark").display = "none";
        }
    </script>
    <script type="text/javascript">
        function changeIdea(){
            var idea = document.getElementById("examAppIdea").value;
            if(idea=="approve") {
                document.getElementById("goBack").disabled = true;
                document.getElementById("comm").disabled = false;
            }else{
                document.getElementById("comm").disabled = true;
                document.getElementById("goBack").disabled = false;
            }
        }
    </script>
    <script type="text/javascript">
        function commForm(id){
            if(id==1){
                var approveRemark = document.getElementById("approveRemark").value;
                if(approveRemark==null || approveRemark==""){
                    alert("请输入退回原因！");
                    return;
                }else{
                    var gForm = document.getElementById("gForm");
                    gForm.action = "examineSave?type=1";
                    gForm.controller = "bxReceipt";
                    gForm.submit();
                }
            }else{
                var gForm = document.getElementById("gForm");
                gForm.action = "examineSave?type=0";
                gForm.controller = "bxReceipt";
                gForm.submit();
            }
        }
    </script>

</head>
<body  onload="markColor();">
<g:form id="gForm" name="gForm" action="commitBxReceipt" controller="bxReceipt" method="post">
<table width="100%"  height="430" border="0" cellpadding="0" cellspacing="0">
%{--<tr>--}%
    %{--<td colspan="9" align="center" height="40"><h2>费用报销流程任务办理</h2></td>--}%
%{--</tr>--}%
<tr>
    <td colspan="10" align="center" height="20"><h2>费用报销单</h2></td>
</tr>
<tr>
    <td colspan="4">
        <table>
            <tr>
                <td colspan="4">
                    <table style="height:120px">
                        <tr>
                            <td height="21"><div style="width: 150px;background: #ADCDF4;">申请人填写：</div></td>
                            <td height="21">单号：</td>
                            <td height="21"> <span id="bxNo">${bxReceipt.bxNo}</span>
                                <input type="hidden" value="${bxReceipt.bxNo}" name="bxNo">
                                <input type="hidden" name="funcCode" value="${funcCode}">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <table>
                                    <tr>
                                        <td height="21">报销员工身份证号：</td>
                                        <td height="21">
                                            <input type="text" id="bxEmpIdNumber" name="bxEmpIdNumber" value="${bxReceipt.bxEmpIdNumber}" disabled>
                                            <input type="hidden" id="bxEmpNo" name="bxEmpNo" value="${bxReceipt.bxEmpNo}">
                                            %{--<input type="hidden" name="menuId" value="${menuId}">--}%
                                        </td>
                                        %{--<td height="21" width="120" >报销员工编号：</td>--}%
                                        %{--<td height="21">--}%
                                        %{--选项只能为本公司员工--}%
                                        %{--<select id="bxEmpNo" name="bxEmpNo">--}%
                                        %{--<option value="-1">--请选择--</option>--}%
                                        %{--<option value="43910343">43910343</option>--}%
                                        %{--<option value="43910342">43910342</option>--}%
                                        %{--<option value="43910345">43910345</option>--}%
                                        %{--<option value="43910348">43910348</option>--}%
                                        %{--</select>--}%
                                        %{--</td>--}%
                                        <td height="21">报销员工姓名：</td>
                                        %{--系统带入--}%
                                        <td height="21"><input style="width: 120" type="text" id="bxEmpName" name="bxEmpName" value="${bxReceipt.bxEmpName}" disabled></td>
                                    </tr>
                                    <tr>
                                        <td height="21">成本中心：</td>
                                        <td height="21">
                                            <select id="costCenter" name="costCenter" disabled>
                                                <option value="-1">----请选择----</option>
                                                <option value="1">2012-生安部-XXX</option>
                                                <option value="2">2012-技术部-XXX</option>
                                                <option value="3">2012-人事部-XXX</option>
                                            </select>
                                        </td>
                                        <td height="21" width="120">报销员工电话：</td>
                                        %{--系统带入--}%
                                        <td height="21"><input style="width: 120" type="text" id="bxEmpPhoneNumber" name="bxEmpPhoneNumber" value="${bxReceipt.bxEmpPhoneNumber}" disabled></td>
                                    </tr>
                                    <tr>
                                        <td height="21">公司名称：</td>
                                        %{--选择报销员工编号后自动带入--}%
                                        <td height="21"><input type="text" id="companyName" name="companyName" value="${bxReceipt.companyName}" disabled></td>
                                        <td height="21">报销员工职位：</td>
                                        %{--系统带入--}%
                                        <td height="21"><input style="width: 120" type="text" id="bxEmpPosition" name="bxEmpPosition" value="${bxReceipt.bxEmpPosition}" disabled></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </td>
    <td colspan="4">
        <table>
            <tr>
                <td colspan="4">
                    <table style="height:120px">
                        <tr id="allTabs">
                            <td height="21" colspan="5">
                                <div id="save">已保存</div>
                                <div id="commit">已提交</div>
                                <div id="deputy">已审核</div>
                                <div id="post">已过账</div>
                                <div id="back">已付款</div>
                            </td>
                            %{--<td height="21" colspan="5">--}%
                                %{--<g:if test="${bxReceipt.bxdStatus=='已保存'}">--}%
                                    %{--<div id="tbs1" style="background-color:green;">已保存</div>--}%
                                %{--</g:if>--}%
                                %{--<g:else>--}%
                                    %{--<div id="tbs1" style="background-color: #ccc;">已保存</div>--}%
                                %{--</g:else>--}%
                                %{--<div id="tbs2" style="background-color:#ccc;">已提交</div>--}%
                                %{--<div id="tbs3" style="background-color:#ccc;">已审核</div>--}%
                                %{--<div id="tbs4" style="background-color:#ccc;">已过账</div>--}%
                                %{--<div id="tbs5" style="background-color:#ccc;">已付款</div>--}%
                            %{--</td>--}%
                        </tr>
                        <tr>
                            <td colspan="4">
                                <table>
                                    <tr>
                                        <td height="21">预算中心：</td>
                                        <td height="21">
                                            <select id="budgetCenter" name="budgetCenter" disabled>
                                                <option value="1">2012-生安部-XXX</option>
                                                <option value="2">2012-技术部-XXX</option>
                                                <option value="3">2012-人事部-XXX</option>
                                            </select>
                                        </td>
                                        <td height="21">申请日期：</td>
                                        <td height="21"><input id="applicationDate" name="applicationDate" type="text" value="${bxReceipt.applicationDate}" disabled></td>
                                    </tr>
                                    <tr>
                                        <td height="21">需款日期：</td>
                                        %{--系统带入当前日期，日期格式YYYYMMDD--}%
                                        <td><input style="width: 123" id="needMoneyDate" name="needMoneyDate" type="text" value="${bxReceipt.needMoneyDate}"  class="Wdate" onblur="emptyVerify('loanParagraphDate');" disabled></td>
                                        <td>经办人姓名：</td>
                                        <td><input id="managerName" name="managerName" type="text" value="${bxReceipt.managerName}" disabled></td>
                                    </tr>

                                    <tr>
                                        <td height="21">付款方式：</td>
                                        %{--默认值为银行转账--}%
                                        <td>
                                            <select id="paymentMode" name="paymentMode" disabled>
                                                <option value="银行转账">银行转账</option>
                                                <option value="现金">现金</option>
                                            </select>
                                        </td>
                                        <td height="21">票据类别：</td>
                                        <td>
                                            <select id="billsCurr" name="billsCurr" disabled>
                                                <option value="CNY">CNY</option>
                                                <option value="USD">USD</option>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </td>
</tr>
%{--    差旅   Start  --}%
<tr>
    <td colspan="10" height="30"><div style="width: 150px;background: #ADCDF4;">差旅费用明细</div></td>
</tr>
<tr>
    <td>
        <table>
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="addCl" value="添加"  onclick="AddSignRow_cl();" readonly/></td>--}%
            %{--</tr>--}%
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="clearCl" value="清空" onclick="ClearAllSign_cl();" readonly/></td>--}%
            %{--</tr>--}%
            <tr>
                <td><input name='clIndex' type='hidden' id='clIndex' value="1" readonly/></td>
            </tr>
        </table>
    </td>
    <td colspan="10">
        <table width="100%" id="cl" height="21"   border="1" cellpadding="0" cellspacing="0">
            <tr align="center" style="width: 100%;height:100%;background: #ADCDF4;" colspan="10">
                <td height="21" colspan="2" width="17%">出差事由：</td>
                <td width="10%"><input id="clTravelDetails" style="width: 100%;height: 100%" name="clTravelDetails" type="text" value="" readonly></td>
                <td width="12%" height="21">出差天数</td>
                <td width="5%"><input id="clTravelDaysCount" style="width: 100%;height: 100%" name="clTravelDaysCount" type="text" value="" readonly></td>
                <td width="73%" colspan="5"></td>
            </tr>
            <tr align="center" style="width: 100%;height:100%;background: #ADCDF4;" colspan="10">
                <td height="21" width="7%">序号</td>
                <td width="10%">出发日期</td>
                <td width="10%">到达日期</td>
                <td width="12%">起止地点</td>
                <td  width="5%">天数 </td>
                <td width="10%">费用类型</td>
                <td width="10%">交通工具</td>
                <td width="10%">原币金额</td>
                <td  width="14%">人民币报销金额 </td>
                <td  width="12%">备注 </td>
            </tr>

            <g:each in="${listTravel}" var="item" status="index">
                <tr align="center" id="clList${index}" readonly>
                    <td height="23" width="7%"><input id="cNoList" name="cNoList" value="${item.cNo}" readonly></td>
                    <td width="10%"><input id="clStartDateList" style="width: 100%;height: 100%" name="clStartDateList" value="${item.clStartDate}"  class="Wdate" readonly></td>
                    <td width="10%"><input id="clEndDateList" style="width: 100%;height: 100%" name="clEndDateList" value="${item.clEndDate}"  class="Wdate" readonly></td>
                    <td width="12%"><input id="clSeAddressList" style="width: 100%;height: 100%" name="clSeAddressList" value="${item.clSeAddress}" readonly></td>
                    <td  width="5%"><input id="clTravelDaysList" style="width: 100%;height: 100%" name="clTravelDaysList" value="${item.clTravelDays}" readonly> </td>
                    <td  width="10%"><input id="clExpenseTypeList" name="clExpenseTypeList" style="width: 100%;height: 100%" value="${item.clExpenseType}" readonly> </td>
                    <td  width="10%"><input id="clTransportList" name="clTransportList" style="width: 100%;height: 100%" value="${item.clTransport}" readonly> </td>
                    <td  width="10%"><input id="clOriginalSumList" name="clOriginalSumList" style="width: 100%;height: 100%" value="${item.clOriginalSum}" readonly> </td>
                    <td  width="14%"><input id="clBxRmbSumList" name="clBxRmbSumList" style="width: 100%;height: 100%" value="${item.clBxRmbSum}" readonly> </td>
                    <td  width="12%"><input id="clRemarkList" name="clRemarkList" style="width: 100%;height: 100%" value="${item.clRemark}" readonly> </td>
                </tr>
            </g:each>
        </table>
    </td>
</tr>
%{--差旅   END--}%
%{--  招待费用明细   start    --}%
<tr>
    <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">招待费用明细</div></td>
</tr>
<tr>
    <td >
        <table>
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="addZd" value="添加" onclick="AddSignRow_zd();" readonly/></td>--}%
            %{--</tr>--}%
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="clearZd" value="清空" onclick="ClearAllSign_zd();" readonly/></td>--}%
            %{--</tr>--}%
            <tr>
                <td><input name='zdIndex' type='hidden' id='zdIndex' value="1" readonly /></td>
            </tr>
        </table>
    </td>
    <td colspan="7">
        <table width="100%" id="zd" height="21"  border="1" cellpadding="0" cellspacing="0">

            <tr align="center" style="width: 100%;height:100%;background: #ADCDF4;" colspan="10">
                <td height="21" width="10%">序号</td>
                <td width="15%">日期</td>
                <td width="15%">费用类型</td>
                <td width="10%">原币金额</td>
                <td  width="15%">人民币报销金额 </td>
                <td  width="20%">票据说明 </td>
                <td  width="15%">备注 </td>
            </tr>

            <g:each in="${listZhaoDai}" var="item" status="index">
                <tr align="center" id="zdList${index}" readonly>
                    <td height="23" width="10%"><input id="zNoList" name="zNoList" value="${item.zNo}" readonly></td>
                    <td width="15%"><input id="zdDateList" style="width: 100%;height: 100%" name="zdDateList" value="${item.zdDate}"  class="Wdate"  readonly></td>
                    <td width="15%"><input id="zdExpenseTypeList" style="width: 100%;height: 100%" name="zdExpenseTypeList" value="${item.zdExpenseType}" readonly></td>
                    <td  width="10%"><input id="zdOriginalSumList" style="width: 100%;height: 100%" name="zdOriginalSumList" value="${item.zdOriginalSum}" readonly> </td>
                    <td  width="15%"><input id="zdBxRmbSumList" name="zdBxRmbSumList" style="width: 100%;height: 100%" value="${item.zdBxRmbSum}" readonly> </td>
                    <td  width="20%"><input id="zdBillsExplainList" name="zdBillsExplainList" style="width: 100%;height: 100%" value="${item.zdBillsExplain}" readonly> </td>
                    <td  width="15%"><input id="zdRemarkList" name="zdRemarkList" style="width: 100%;height: 100%" value="${item.zdRemark}" readonly> </td>
                </tr>
            </g:each>
        </table>
    </td>
</tr>
%{--  招待费用明细    end    --}%
%{--  办公费用明细   start    --}%
<tr>
    <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">办公费用明细</div></td>
</tr>
<tr>
    <td >
        <table>
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="addBg" value="添加" onclick="AddSignRow_bg();" readonly/></td>--}%
            %{--</tr>--}%
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="clearBg" value="清空" onclick="ClearAllSign_bg();" readonly/></td>--}%
            %{--</tr>--}%
            <tr>
                <td><input name='bgIndex' type='hidden' id='bgIndex' value="1" readonly/></td>
            </tr>
        </table>
    </td>
    <td colspan="7">
        <table width="100%" id="bg" height="21"  border="1" cellpadding="0" cellspacing="0">

            <tr align="center" style="width: 100%;height:100%;background: #ADCDF4;" colspan="10">
                <td height="21" width="10%">序号</td>
                <td width="15%">日期</td>
                <td width="15%">费用类型</td>
                <td width="10%">原币金额</td>
                <td  width="15%">人民币报销金额 </td>
                <td  width="20%">票据说明 </td>
                <td  width="15%">备注 </td>
            </tr>

            <g:each in="${listWork}" var="item" status="index">
                <tr align="center" id="bgList${index}" readonly>
                    <td height="23" width="10%"><input id="bNoList" name="bNoList" value="${item.bNo}" readonly></td>
                    <td width="15%"><input id="bgDateList" style="width: 100%;height: 100%" name="bgDateList" value="${item.bgDate}"  class="Wdate" readonly></td>
                    <td width="15%"><input id="bgExpenseTypeList" style="width: 100%;height: 100%" name="bgExpenseTypeList" value="${item.bgExpenseType}" readonly></td>
                    <td  width="10%"><input id="bgOriginalSumList" style="width: 100%;height: 100%" name="bgOriginalSumList" value="${item.bgOriginalSum}" readonly> </td>
                    <td  width="15%"><input id="bgBxRmbSumList" name="bgBxRmbSumList" style="width: 100%;height: 100%" value="${item.bgBxRmbSum}" readonly> </td>
                    <td  width="20%"><input id="bgBillsExplainList" name="bgBillsExplainList" style="width: 100%;height: 100%" value="${item.bgBillsExplain}" readonly> </td>
                    <td  width="15%"><input id="bgRemarkList" name="bgRemarkList" style="width: 100%;height: 100%" value="${item.bgRemark}" readonly> </td>
                </tr>
            </g:each>
        </table>
    </td>
</tr>
%{--  办公费用明细    end    --}%
%{--  其他费用明细   start    --}%
<tr>
    <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">其他费用明细</div></td>
</tr>
<tr>
    <td >
        <table>
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="addOther" value="添加" onclick="AddSignRow_other();" readonly/></td>--}%
            %{--</tr>--}%
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="clearOther" value="清空" onclick="ClearAllSign_other();" readonly/></td>--}%
            %{--</tr>--}%
            <tr>
                <td><input name='otherIndex' type='hidden' id='otherIndex' value="1" readonly/></td>
            </tr>
        </table>
    </td>
    <td colspan="6">
        <table id="other"  width="100%" height="21"  border="1" cellpadding="0" cellspacing="0">

            <tr align="center" style="width: 100%;height:100%;background: #ADCDF4;" colspan="10">
                <td height="21" width="10%">序号</td>
                <td width="15%">日期</td>
                <td width="10%">原币金额</td>
                <td  width="15%">人民币报销金额 </td>
                <td  width="20%">票据说明 </td>
                <td  width="15%">备注 </td>
            </tr>
            <g:each in="${listOther}" var="item" status="index">
                <tr align="center" id="otherList${index}" readonly>
                    <td height="23" width="10%"><input id="oNoList" name="oNoList" value="${item.oNo}" readonly></td>
                    <td width="15%"><input id="otherDateList" style="width: 100%;height: 100%" name="otherDateList" value="${item.otherDate}"  class="Wdate" readonly></td>
                    <td width="10%"><input id="otherOriginalSumList" style="width: 100%;height: 100%" name="otherOriginalSumList" value="${item.otherOriginalSum}" readonly></td>
                    <td  width="15%"><input id="otherBxRmbSumList" style="width: 100%;height: 100%" name="otherBxRmbSumList" value="${item.otherBxRmbSum}" readonly> </td>
                    <td  width="20%"><input id="otherBillsExplainList" name="otherBillsExplainList" style="width: 100%;height: 100%" value="${item.otherBillsExplain}" readonly> </td>
                    <td  width="15%"><input id="otherRemarkList" name="otherRemarkList" style="width: 100%;height: 100%" value="${item.otherRemark}" readonly> </td>
                </tr>
            </g:each>
        </table>
    </td>
</tr>
%{--  其他费用明细    end    --}%
%{--报销金额合计   start  --}%
<tr>
    <td >&nbsp;&nbsp;</td>
    <td colspan="4">
        <table width="100%" height="30"  border="1" cellpadding="0" cellspacing="0">
            <tr align="center" style="width: 100%;height:100%;background: #ADCDF4;" colspan="4">
                <td width="20%">&nbsp;&nbsp;</td>
                %{--  计算公式：差旅费+招待费+办公费+其它费  --}%
                <td width="15%">报销金额合计：</td>
                <td width="15%"><input id="bxCostCounts" name="bxCostCounts" style="width: 100%;height: 100%" value="${bxReceipt.bxCostCounts}" disabled> </td>
                <td width="50%"></td>
            </tr>
        </table>
    </td>
</tr>
%{--报销金额合计   end  --}%
%{--  借款费用明细   start    --}%
<tr>
    <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">借款费用明细</div></td>
</tr>
<tr>
    <td >
        <table>
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="addLoan" value="添加" onclick="AddSignRow_loan();" readonly/></td>--}%
            %{--</tr>--}%
            %{--<tr>--}%
                %{--<td  height="21"><input type="button" name="clearLoan" value="清空" onclick="ClearAllSign_loan();" readonly/></td>--}%
            %{--</tr>--}%
            <tr>
                <td><input name='loanIndex' type='hidden' id='loanIndex' value="1" disabled/></td>
            </tr>
        </table>
    </td>
    <td colspan="8">
        <table width="100%" height="21" id="loan"  border="1" cellpadding="0" cellspacing="0">

            <tr align="center" style="width: 100%;height:100%;background: #ADCDF4;" colspan="10">
                <td height="21" width="10%">序号</td>
                <td width="12%">借款日期</td>
                <td width="12%">借款单号</td>
                <td  width="12%">票据币别 </td>
                <td  width="12%">原币金额 </td>
                <td  width="12%">借款余额 </td>
                <td  width="15%">本次还款金额 </td>
                <td  width="15%">备注 </td>
            </tr>

            <g:each in="${listLoan}" var="item" status="index">
                <tr align="center" id="loanList${index}" readonly>
                    <td height="23" width="10%"><input id="lNoList" name="lNoList" value="${item.lNo}" readonly></td>
                    <td width="12%"><input id="loanDateList" style="width: 100%;height: 100%" name="loanDateList" value="${item.loanDate}"  class="Wdate" readonly></td>
                    <td width="12%"><input id="loanNoList" style="width: 100%;height: 100%" name="loanNoList" value="${item.loanNo}" readonly></td>
                    <td  width="12%"><input id="loanBillsCurrList" style="width: 100%;height: 100%" name="loanBillsCurrList" value="${item.loanBillsCurr}" readonly> </td>
                    <td  width="12%"><input id="loanOriginalSumList" name="loanOriginalSumList" style="width: 100%;height: 100%" value="${item.loanOriginalSum}" readonly> </td>
                    <td  width="12%"><input id="loanBalanceList" name="loanBalanceList" style="width: 100%;height: 100%" value="${item.loanBalance}" readonly> </td>
                    <td  width="15%"><input id="loanTheRepaymentList" name="loanTheRepaymentList" style="width: 100%;height: 100%" value="${item.loanTheRepayment}" readonly> </td>
                    <td  width="15%"><input id="loanRemarkList" name="loanRemarkList" style="width: 100%;height: 100%" value="${item.loanRemark}" readonly> </td>
                </tr>
            </g:each>
        </table>
    </td>
</tr>
%{--  借款费用明细    end    --}%
%{--实际金额合计   start  --}%
<tr>
    <td >&nbsp;&nbsp;</td>
    <td colspan="7">
        <table width="100%" height="30"  border="1" cellpadding="0" cellspacing="0">
            <tr align="center" style="width: 100%;height:100%;background: #ADCDF4;" colspan="7">
                <td width="20%">&nbsp;&nbsp;</td>
                %{--计算公式：报销金额合计－本次还款金额--}%
                <td width="15%">实际金额合计：</td>
                <td width="15%"><input id="payCounts" name="payCounts" style="width: 100%;height: 100%" value="${bxReceipt.payCounts}" disabled> </td>
                <td width="10%">单据共</td>
                <td width="10%"><input id="billsCounts" name="billsCounts" style="width: 100%;height: 100%" value="${bxReceipt.billsCounts}" disabled> </td>
                <td width="10%">张</td>
                <td width="20%"></td>
            </tr>
        </table>
    </td>
</tr>
%{--实际金额合计   end  --}%
%{--  预算相关信息   start    --}%
<tr>
    <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">预算相关信息</div></td>
</tr>
<tr>
    <td >&nbsp;&nbsp;</td>
    <td colspan="7">
        <table width="100%" height="21"  border="0" cellpadding="0" cellspacing="0">

            <tr align="center" style="width: 100%;height:100%;" colspan="7">
                <td height="21" width="10%">费用种类：</td>
                <td width="10%">
                    <select id="expenseCategory" name="expenseCategory" style="width: 100%;height: 100%" disabled>
                        <option value="差旅费用">差旅费用</option>
                        <option value="招待费用">招待费用</option>
                        <option value="办公费用">办公费用</option>
                    </select></td>
                <td width="15%">年度预算余额</td>
                <td  width="10%"><input id="balanceBudgetYear" style="width: 100%;height: 100%" name="balanceBudgetYear" value="${bxReceipt.balanceBudgetYear}" disabled>  </td>
                <td  width="15%">年度预算完成比例</td>
                <td  width="10%"> <input id="budgetYearPro" style="width: 100%;height: 100%" name="budgetYearPro" value="${bxReceipt.budgetYearPro}" disabled> </td>
                <td  width="30%"><input type="button" value="华电集团关于差旅报销的标准">  </td>
            </tr>
            <tr align="center" style="width: 100%;height:100%;" colspan="7">
                <td height="21" width="10%">预算年度：</td>
                <td width="10%"><input id="budgetYear" style="width: 100%;height: 100%" name="budgetYear" value="${bxReceipt.budgetYear}" disabled> </td>
                <td width="15%">季度预算余额</td>
                <td  width="10%"><input id="balanceBudgetQuarter" style="width: 100%;height: 100%" name="balanceBudgetQuarter" value="${bxReceipt.balanceBudgetQuarter}" disabled>  </td>
                <td  width="15%">季度预算完成比例</td>
                <td  width="10%"> <input id="budgetQuarterPro" style="width: 100%;height: 100%" name="budgetQuarterPro" value="${bxReceipt.budgetQuarterPro}" disabled> </td>
                <td  width="30%"><input type="button" value="华电集团关于招待费用报销的标准">  </td>
            </tr>
            <tr align="center" style="width: 100%;height:100%;" colspan="7">
                <td height="21" width="10%">预算月份：</td>
                <td width="10%"><input id="budgetMonth" style="width: 100%;height: 100%" name="budgetMonth" value="${bxReceipt.budgetMonth}" disabled> </td>
                <td width="15%">月份预算余额</td>
                <td  width="10%"><input id="balanceBudgetMonth" style="width: 100%;height: 100%" name="balanceBudgetMonth" value="${bxReceipt.balanceBudgetMonth}" disabled>  </td>
                <td  width="15%">月份预算完成比例</td>
                <td  width="10%"> <input id="budgetMonthPro" style="width: 100%;height: 100%" name="budgetMonthPro" value="${bxReceipt.budgetMonthPro}" disabled> </td>
                <td  width="30%"><input type="button" value="华电集团关于办公费用报销的标准" >  </td>
            </tr>
            <tr align="center" style="width: 100%;height:100%;" >
                <td height="21" width="10%">预算季度：</td>
                <td width="10%"><input id="budgetQuarter" style="width: 100%;height: 100%" name="budgetQuarter" value="${bxReceipt.budgetQuarter}" disabled> </td>

            </tr>
        </table>
    </td>
</tr>
%{--  预算相关信息    end    --}%
%{--<tr>--}%
%{--<td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">审批历史</div></td>--}%
%{--</tr>--}%
%{--<tr>--}%
%{--<td>&nbsp;&nbsp;</td>--}%
%{--<td colspan="7">--}%
%{--<table width="100%" height="46"  border="1" cellpadding="0" cellspacing="0">--}%
%{--<tr align="center">--}%
%{--<td height="23" width="10%">序号</td>--}%
%{--<td width="30%">审批时间</td>--}%
%{--<td width="20%">审批人职位</td>--}%
%{--<td width="15%">审批人</td>--}%
%{--<td width="20%">审批意见</td>--}%
%{--</tr>--}%
%{--<tr align="center">--}%
%{--<td height="23" width="10%">1</td>--}%
%{--<td width="30%">2011-11-6 00:00:00</td>--}%
%{--<td width="20%">生安部部长</td>--}%
%{--<td width="15%">ELLE</td>--}%
%{--<td  width="20%">--}%
%{--<select id="search" width="20%">--}%
%{--<option width="20%" value="同意">同意</option>--}%
%{--<option width="20%" value="不同意">不同意</option>--}%
%{--</select>--}%
%{--</td>--}%
%{--</tr>--}%
%{--</table>--}%
%{--</td>--}%
%{--<td>&nbsp;&nbsp;</td>--}%
%{--</tr>--}%
<tr>
    <td height="30">&nbsp;&nbsp;&nbsp;</td>
</tr>
%{--<tr>--}%
    %{--<td>&nbsp;&nbsp;</td>--}%
    %{--<td height="30"><input style="width: 100" type="button" value="保存" onclick="commForm(0)"></td>--}%
    %{--<td><input style="width: 100" type="button" value="提交"></td>--}%
    %{--<td><input style="width: 100" type="button" value="退回" ></td>--}%
    %{--<td><input style="width: 100" type="button" value="执行过账" disabled></td>--}%
    %{--<td><input style="width: 100" type="button" value="执行付款" disabled></td>--}%
%{--</tr>--}%
<tr>
    <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">审批历史</div></td>
</tr>
<tr>
    <td>&nbsp;&nbsp;</td>
    <td colspan="7">
        <table width="100%" height="30"  border="1" cellpadding="0" cellspacing="0">
            <tr align="center">
                <td height="30" width="10%">序号</td>
                <td width="30%">审批时间</td>
                <td width="20%">审批人职位</td>
                <td width="15%">审批人</td>
                <td width="20%">审批意见</td>
            </tr>
            <g:each in="${historyLists}" var="item" status="index">
                <tr align="center">
                    <td height="30">
                        ${index+1}
                    </td>
                    <td>${item.endTime}</td>
                    <td>${item.examAppNamePosition}</td>
                    <td>${item.assignee}</td>
                    <td>${item.result}</td>
                </tr>
            </g:each>
        </table>
    </td>
    <td>&nbsp;&nbsp;</td>
</tr>
<tr>
    <td colspan="9" height="30"><div style="width: 150px;background: #ADCDF4;">审批意见</div></td>
</tr>
<tr>
    <td>&nbsp;&nbsp;</td>
    <td colspan="7">
        <table width="100%" height="60"  border="1" cellpadding="0" cellspacing="0">
            <tr align="center">
                <td height="30" width="10%">序号</td>
                <td width="30%">审批时间</td>
                <td width="20%">审批人职位</td>
                <td width="15%">审批人</td>
                <td width="20%">审批意见</td>
            </tr>
            <tr align="center">
                <td height="30" width="10%">1<input type="hidden" name="taskId" value="${taskId}"></td>
                <td width="30%">${nowDate}</td>
                <td width="20%">${role.authority}</td>
                <td width="15%">${user.username}</td>
                <td  width="20%">
                    <select id="examAppIdea" width="20%" name="examAppIdea" onchange="changeIdea();">
                        <option width="20%" value="approve">同意</option>
                        <option width="20%" value="reject">不同意</option>
                    </select>
                </td>
            </tr>
        </table>
    </td>
    <td>&nbsp;&nbsp;</td>
</tr>
<tr>
    <td height="30">&nbsp;&nbsp;&nbsp;</td>
</tr>
<tr>
    <td>&nbsp;&nbsp;</td>
    %{--<td height="30"><input type="button" value="保存"  onclick="commForm(0);"></td>--}%
    <td height="30">&nbsp;&nbsp;&nbsp;</td>
    <td><input type="button" id="comm" value="提交"  onclick="commForm(0);"></td>
    <td><input type="button" id="goBack" value="退回" onclick="commForm(1);"></td>
    %{--<td><input type="button" value="返回" onclick="location='../processes/initProcess'"></td>--}%
    <td><input type="button" value="执行过账" disabled></td>
    <td>
        <span style="color: red">
            <div>此功能在所有审批通过后</div>
            <div>由负责费用报销的</div>
            <div>会计人员执行！</div>
        </span>
    </td>
    <td><input type="button" value="执行付款" disabled></td>
    <td>&nbsp;&nbsp;</td>
    <td>&nbsp;&nbsp;</td>
</tr>
<div id="ideaRemark" name="ideaRemark" style="width:100px;height:50px" >
    <tr>
        <td>&nbsp;&nbsp;</td>
        <td colspan="2" height="60" align="right">请填写退回原因：</td>
        <td colspan="5" height="60"><textarea id="approveRemark" name="approveRemark" rows="3" cols="30" onblur="emptyVerify('approveRemark');"></textarea></td>
    </tr>
</div>
</table>
</g:form>
<script>document.getElementById("bxEmpNo").value = "${bxReceipt.bxEmpNo}";</script>
<script>document.getElementById("costCenter").value = "${bxReceipt.costCenter}";</script>
<script>document.getElementById("budgetCenter").value = "${bxReceipt.budgetCenter}";</script>
<script>document.getElementById("paymentMode").value = "${bxReceipt.paymentMode}";</script>
<script>document.getElementById("billsCurr").value = "${bxReceipt.billsCurr}";</script>
</body>
</html>