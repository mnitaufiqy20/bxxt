function findObj(theObj, theDoc)
{
    var p, i, foundObj;
    if(!theDoc) theDoc = document;
    if( (p = theObj.indexOf("?")) > 0 && parent.frames.length)
    {    theDoc = parent.frames[theObj.substring(p+1)].document;    theObj = theObj.substring(0,p); }
    if(!(foundObj = theDoc[theObj]) && theDoc.all)
        foundObj = theDoc.all[theObj];
    for (i=0; !foundObj && i < theDoc.forms.length; i++)
        foundObj = theDoc.forms[i][theObj];
    for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
        foundObj = findObj(theObj,theDoc.layers[i].document);
    if(!foundObj && document.getElementById) foundObj = document.getElementById(theObj);
    return foundObj;
}
//添加一个参与人填写行
function AddSignRow_other(){
 //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("otherIndex",document);
    var rowID = parseInt(txtTRLastIndex.value);

    var signFrame = findObj("other",document);
//添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "otherRow" + rowID;

//添加列:序号
    var newNameTD=newTR.insertCell(0);
//添加列内容
    newNameTD.innerHTML ="<td height='23' width='10%'><input  id='oNo"+rowID+"' name='oNo"+rowID+"' value='"+newTR.rowIndex.toString()+"'> </td>" ;

//添加列:日期
    var newNameTD=newTR.insertCell(1);
//添加列内容
    newNameTD.innerHTML = "<td width='15%'><input id='otherDate"+rowID+"' style='width: 100%;height: 100%' name='otherDate"+rowID+"' value=''  class='Wdate' onclick=\"SelectDate(this,'yyyy-MM-dd',null,null);\"></td>";

//添加列:原币金额
    var newEmailTD=newTR.insertCell(2);
//添加列内容
    newEmailTD.innerHTML = "<td width='10%'><input id='otherOriginalSum"+rowID+"' style='width: 100%;height: 100%' name='otherOriginalSum"+rowID+"' value=''></td>";

//添加列:RMB报销金额
    var newTelTD=newTR.insertCell(3);
//添加列内容
    newTelTD.innerHTML = "<td  width='15%'><input id='otherBxRmbSum"+rowID+"' style='width: 100%;height: 100%' name='otherBxRmbSum"+rowID+"' value=''> </td>";

//添加列:票据说明
    var newMobileTD=newTR.insertCell(4);
//添加列内容
    newMobileTD.innerHTML = "<td  width='20%'><input id='otherBillsExplain"+rowID+"' name='otherBillsExplain"+rowID+"' style='width: 100%;height: 100%' value=''> </td>";

//添加列:备注
    var newCompanyTD=newTR.insertCell(5);
//添加列内容
    newCompanyTD.innerHTML = "<td  width='15%'><input id='otherRemark"+rowID+"' name='otherRemark"+rowID+"' style='width: 100%;height: 100%'' value=''> </td>";


//添加列:删除按钮
    var newDeleteTD=newTR.insertCell(6);
//添加列内容
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a onMouseOver=\"this.style.fontStyle='italic'\"onMouseOut=\"this.style.fontStyle=''\" style='color: red;' onclick=\"DeleteSignRow_other('otherRow" + rowID + "')\">删除</a></div>";

//将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString() ;
}
//删除指定行
function DeleteSignRow_other(rowid){
    var signFrame = findObj("other",document);
    var signItem = findObj(rowid,document);

//获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;

//删除指定Index的行
    signFrame.deleteRow(rowIndex);

//重新排列序号，如果没有序号，这一步省略
    for(i=rowIndex;i<signFrame.rows.length;i++){
        signFrame.rows[i].cells[0].innerHTML = i.toString();
    }
}
//清空列表
function ClearAllSign_other(){
    if(confirm('确定要清空其他费用报销列表吗？')){
        var signFrame = findObj("other",document);
        var rowscount = signFrame.rows.length;

//循环删除行,从最后一行往前删除
        for(i=rowscount - 1;i > 0; i--){
            signFrame.deleteRow(i);
        }

//重置最后行号为1
        var txtTRLastIndex = findObj("otherIndex",document);
        txtTRLastIndex.value = "1";

//预添加一行
//        AddSignRow();
    }
}
function AddSignRow_cl(){
    //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("clIndex",document);
    var rowID = parseInt(txtTRLastIndex.value);

    var signFrame = findObj("cl",document);
//添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "clRow" + rowID;

//添加列:序号
    var newNameTD=newTR.insertCell(0);
//添加列内容
    var rr=newTR.rowIndex-1;
    newNameTD.innerHTML ="<td height=\"23\" width=\"7%\"><input id=\"cNo"+rowID+"\" name=\"cNo"+rowID+"\" value=\""+rr+"\"></td>" ;

//添加列:开始日期
    var newNameTD=newTR.insertCell(1);
//添加列内容
    newNameTD.innerHTML = "<td width=\"10%\"><input id=\"clStartDate"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"clStartDate"+rowID+"\" value=\"\"  class=\"Wdate\" onclick=\"SelectDate(this,'yyyy-MM-dd',null,null);\"></td>";
//    newNameTD.innerHTML = "<td width=\"10%\"><input id=\"startDate"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"clStartDate"+rowID+"\" value=\"\"  class=\"Wdate\"></td>";

//添加列:结束日期
    var newEmailTD=newTR.insertCell(2);
//添加列内容
    newEmailTD.innerHTML = "<td width=\"10%\"><input id=\"clEndDate"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"clEndDate"+rowID+"\" value=\"\"  class=\"Wdate\" onclick=\"SelectDate(this,'yyyy-MM-dd',null,null);\"></td>";
//    newEmailTD.innerHTML = "<td width=\"10%\"><input id=\"endDate"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"clEndDate"+rowID+"\" value=\"\"  class=\"Wdate\"></td>";

//添加列:起止地址
    var newTelTD=newTR.insertCell(3);
//添加列内容
    newTelTD.innerHTML = "<td width=\"12%\"><input id=\"clSeAddress"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"clSeAddress"+rowID+"\" value=\"\"></td>";

//添加列:出差天数
    var newMobileTD=newTR.insertCell(4);
//添加列内容
    newMobileTD.innerHTML = "<td  width=\"5%\"><input id=\"clTravelDays"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"clTravelDays"+rowID+"\" value=\"\"> </td>";

//添加列:费用类型
    var newCompanyTD=newTR.insertCell(5);
//添加列内容
    newCompanyTD.innerHTML = "<td width=\"10%\">  <select id=\"clExpenseType"+rowID+"\" name=\"clExpenseType"+rowID+"\" style=\"width: 100%;height: 100%\"><option value=\"交通费\">交通费</option>    <option value=\"住宿费\">住宿费</option>       <option value=\"路桥费\">路桥费</option>       <option value=\"手续费\">手续费</option>    <option value=\"伙食补助费\">伙食补助费</option>     <option value=\"其它\">其它</option>     </select></td>";
//添加列:交通工具
    var newCompanyTD=newTR.insertCell(6);
//添加列内容
    newCompanyTD.innerHTML = "<td width=\"10%\">     <select id=\"clTransport"+rowID+"\" name=\"clTransport"+rowID+"\" style=\"width: 100%;height: 100%\">    <option value=\"火车卧铺\">火车卧铺</option>    <option value=\"火车硬座\">火车硬座</option>    <option value=\"船\">船</option>    <option value=\"汽车\">汽车</option>    <option value=\"飞机\">飞机</option>    <option value=\"其它\">其它</option>    </select></td>";

    //添加列:原币金额
    var newCompanyTD=newTR.insertCell(7);
//添加列内容
    newCompanyTD.innerHTML = "<td width=\"10%\"><input id=\"clOriginalSum"+rowID+"\" name=\"clOriginalSum"+rowID+"\" style=\"width: 100%;height: 100%\" value=\"\"></td>";
    //添加列:RMB金额
    var newCompanyTD=newTR.insertCell(8);
//添加列内容
    newCompanyTD.innerHTML = "<td  width=\"14%\"><input id=\"clBxRmbSum"+rowID+"\" name=\"clBxRmbSum"+rowID+"\" style=\"width: 100%;height: 100%\" value=\"\"> </td>";
    //添加列:备注
    var newCompanyTD=newTR.insertCell(9);
//添加列内容
    newCompanyTD.innerHTML = "<td  width=\"12%\"><input id=\"clRemark"+rowID+"\" name=\"clRemark"+rowID+"\" style=\"width: 100%;height: 100%\" value=\"\"> </td>";

//添加列:删除按钮
    var newDeleteTD=newTR.insertCell(10);
//添加列内容
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a onMouseOver=\"this.style.fontStyle='italic'\"onMouseOut=\"this.style.fontStyle=''\" style='color: red;' onclick=\"DeleteSignRow_cl('clRow" + rowID + "')\">删除</a></div>";

//将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString() ;
}
//删除指定行
function DeleteSignRow_cl(rowid){
    var signFrame = findObj("cl",document);
    var signItem = findObj(rowid,document);

//获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;

//删除指定Index的行
    signFrame.deleteRow(rowIndex);

//重新排列序号，如果没有序号，这一步省略
    for(i=rowIndex;i<signFrame.rows.length;i++){
        signFrame.rows[i].cells[0].innerHTML = i.toString();
    }
}
//清空列表
function ClearAllSign_cl(){
    if(confirm('确定要清空差旅费用报销列表吗？')){
        var signFrame = findObj("cl",document);
        var rowscount = signFrame.rows.length;

//循环删除行,从最后一行往前删除
        for(i=rowscount - 1;i > 1; i--){
            signFrame.deleteRow(i);
        }

//重置最后行号为1
        var txtTRLastIndex = findObj("clIndex",document);
        txtTRLastIndex.value = "1";

//预添加一行
//        AddSignRow();
    }
}
//添加一个参与人填写行
function AddSignRow_zd(){
    //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("zdIndex",document);
    var rowID = parseInt(txtTRLastIndex.value);

    var signFrame = findObj("zd",document);
//添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "zdRow" + rowID;

//添加列:序号
    var newNameTD=newTR.insertCell(0);
//添加列内容
    newNameTD.innerHTML ="<td height='23' width='10%'><input  id='zNo"+rowID+"' name='zNo"+rowID+"' value='"+newTR.rowIndex.toString()+"'> </td>" ;

//添加列:日期
    var newNameTD=newTR.insertCell(1);
//添加列内容
    newNameTD.innerHTML = "<td width='15%'><input id='zdDate"+rowID+"' style='width: 100%;height: 100%' name='zdDate"+rowID+"' value=''  class='Wdate' onclick=\"SelectDate(this,'yyyy-MM-dd',null,null);\"></td>";
    //添加列:费用类型
    var newEmailTD=newTR.insertCell(2);
//添加列内容
    newEmailTD.innerHTML = "<td width=\"15%\">    <select id=\"zdExpenseType"+rowID+"\" name=\"zdExpenseType"+rowID+"\" style=\"width: 100%;height: 100%\">    <option value=\"餐饮费\">餐饮费</option>    <option value=\"礼品礼金费\">礼品礼金费</option>    <option value=\"住宿费\">住宿费</option>   <option value=\"其它\">其它</option>    </select></td>";

//添加列:原币金额
    var newEmailTD=newTR.insertCell(3);
//添加列内容
    newEmailTD.innerHTML = "<td width=\"10%\"><input id=\"zdOriginalSum"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"zdOriginalSum"+rowID+"\" value=\"\"></td>";

//添加列:RMB报销金额
    var newTelTD=newTR.insertCell(4);
//添加列内容
    newTelTD.innerHTML = "<td  width=\"15%\"><input id=\"zdBxRmbSum"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"zdBxRmbSum"+rowID+"\" value=\"\"> </td>";

//添加列:票据说明
    var newMobileTD=newTR.insertCell(5);
//添加列内容
    newMobileTD.innerHTML = "<td  width=\"20%\"><input id=\"zdBillsExplain"+rowID+"\" name=\"zdBillsExplain"+rowID+"\" style=\"width: 100%;height: 100%\" value=\"\"> </td>";

//添加列:备注
    var newCompanyTD=newTR.insertCell(6);
//添加列内容
    newCompanyTD.innerHTML = "<td  width=\"15%\"><input id=\"zdRemark"+rowID+"\" name=\"zdRemark"+rowID+"\" style=\"width: 100%;height: 100%\" value=\"\">";


//添加列:删除按钮
    var newDeleteTD=newTR.insertCell(7);
//添加列内容
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a onMouseOver=\"this.style.fontStyle='italic'\"onMouseOut=\"this.style.fontStyle=''\" style='color: red;' onclick=\"DeleteSignRow_zd('zdRow" + rowID + "')\">删除</a></div>";

//将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString() ;
}
//删除指定行
function DeleteSignRow_zd(rowid){
    var signFrame = findObj("zd",document);
    var signItem = findObj(rowid,document);

//获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;

//删除指定Index的行
    signFrame.deleteRow(rowIndex);

//重新排列序号，如果没有序号，这一步省略
    for(i=rowIndex;i<signFrame.rows.length;i++){
        signFrame.rows[i].cells[0].innerHTML = i.toString();
    }
}
//清空列表
function ClearAllSign_zd(){
    if(confirm('确定要清空招待费用报销列表吗？')){
        var signFrame = findObj("zd",document);
        var rowscount = signFrame.rows.length;

//循环删除行,从最后一行往前删除
        for(i=rowscount - 1;i > 0; i--){
            signFrame.deleteRow(i);
        }

//重置最后行号为1
        var txtTRLastIndex = findObj("zdIndex",document);
        txtTRLastIndex.value = "1";

//预添加一行
//        AddSignRow();
    }
}
//添加一个参与人填写行
function AddSignRow_bg(){
    //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("bgIndex",document);
    var rowID = parseInt(txtTRLastIndex.value);

    var signFrame = findObj("bg",document);
//添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "bgRow" + rowID;

//添加列:序号
    var newNameTD=newTR.insertCell(0);
//添加列内容
    newNameTD.innerHTML ="<td height='23' width='10%'><input  id='bNo"+rowID+"' name='bNo"+rowID+"' value='"+newTR.rowIndex.toString()+"'> </td>" ;

//添加列:日期
    var newNameTD=newTR.insertCell(1);
//添加列内容
    newNameTD.innerHTML = "<td width='15%'><input id='bgDate"+rowID+"' style='width: 100%;height: 100%' name='bgDate"+rowID+"' value=''  class='Wdate' onclick=\"SelectDate(this,'yyyy-MM-dd',null,null);\"></td>";

    //添加列:费用类型
    var newNameTD=newTR.insertCell(2);
//添加列内容
    newNameTD.innerHTML = "<td width=\"15%\">      <select id=\"bgExpenseType"+rowID+"\" name=\"bgExpenseType"+rowID+"\" style=\"width: 100%;height: 100%\">        <option value=\"办公用品\">办公用品</option>         <option value=\"电话费\">电话费</option>     <option value=\"邮寄费\">邮寄费</option>     <option value=\"手续费\">手续费</option>      <option value=\"图书资料费\">图书资料费</option>     <option value=\"印刷费\">印刷费</option>     <option value=\"证书评审费\">证书评审费</option>    <option value=\"礼仪服务费\">礼仪服务费</option>    <option value=\"通讯费\">通讯费</option>     <option value=\"土产日杂\">土产日杂</option>     <option value=\"其它\">其它</option>     </select></td>";

//添加列:原币金额
    var newEmailTD=newTR.insertCell(3);
//添加列内容
    newEmailTD.innerHTML = "<td width=\"10%\"><input id=\"bgOriginalSum"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"bgOriginalSum"+rowID+"\" value=\"\"></td>";

//添加列:RMB报销金额
    var newTelTD=newTR.insertCell(4);
//添加列内容
    newTelTD.innerHTML = "<td  width=\"15%\"><input id=\"bgBxRmbSum"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"bgBxRmbSum"+rowID+"\" value=\"\"> </td>";

//添加列:票据说明
    var newMobileTD=newTR.insertCell(5);
//添加列内容
    newMobileTD.innerHTML = "<td  width=\"20%\"><input id=\"bgBillsExplain"+rowID+"\" name=\"bgBillsExplain"+rowID+"\" style=\"width: 100%;height: 100%\" value=\"\"> </td>";

//添加列:备注
    var newCompanyTD=newTR.insertCell(6);
//添加列内容
    newCompanyTD.innerHTML = "<td  width=\"15%\"><input id=\"bgRemark"+rowID+"\" name=\"bgRemark"+rowID+"\" style=\"width: 100%;height: 100%\" value=\"\"> </td>";


//添加列:删除按钮
    var newDeleteTD=newTR.insertCell(7);
//添加列内容
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a onMouseOver=\"this.style.fontStyle='italic'\"onMouseOut=\"this.style.fontStyle=''\" style='color: red;' onclick=\"DeleteSignRow_bg('bgRow" + rowID + "')\">删除</a></div>";

//将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString() ;
}
//删除指定行
function DeleteSignRow_bg(rowid){
    var signFrame = findObj("bg",document);
    var signItem = findObj(rowid,document);

//获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;

//删除指定Index的行
    signFrame.deleteRow(rowIndex);

//重新排列序号，如果没有序号，这一步省略
    for(i=rowIndex;i<signFrame.rows.length;i++){
        signFrame.rows[i].cells[0].innerHTML = i.toString();
    }
}
//清空列表
function ClearAllSign_bg(){
    if(confirm('确定要清空办公费用报销列表吗？')){
        var signFrame = findObj("bg",document);
        var rowscount = signFrame.rows.length;

//循环删除行,从最后一行往前删除
        for(i=rowscount - 1;i > 0; i--){
            signFrame.deleteRow(i);
        }

//重置最后行号为1
        var txtTRLastIndex = findObj("bgIndex",document);
        txtTRLastIndex.value = "1";

//预添加一行
//        AddSignRow();
    }
}
//添加一个参与人填写行
function AddSignRow_loan(str,strId){
    var s = str.split(";");
    var id = strId.split(";");
    var s1 = s[0].split(",");
//    var defs=loan_list;
//    alert("222"+defs);
    //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("loanIndex",document);
    var rowID = parseInt(txtTRLastIndex.value);

    var signFrame = findObj("loan",document);
//添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "loanRow" + rowID;

//添加列:序号
    var newNameTD=newTR.insertCell(0);
//添加列内容
    newNameTD.innerHTML ="<td height='23' width='10%'><input  id='lNo"+rowID+"' name='lNo"+rowID+"' value='"+newTR.rowIndex.toString()+"' readonly> </td>" ;

//添加列:日期
    var newNameTD=newTR.insertCell(1);
//添加列内容
//    newNameTD.innerHTML = "<td width='12%'><input id='loanDate"+rowID+"' style='width: 100%;height: 100%' name='loanDate"+rowID+"' value=''  class='Wdate' onclick=\"SelectDate(this,'yyyy-MM-dd',null,null);\"></td>";
    newNameTD.innerHTML = "<td width='10%'><input id='loanDate"+rowID+"' style='width: 100%;height: 100%' name='loanDate"+rowID+"' value="+s1[1]+" readonly></td>";

    //添加列:借款单号
    var newEmailTD=newTR.insertCell(2);
//添加列内容
//    newEmailTD.innerHTML = "<td width=\"12%\">     <select id=\"loanNo"+rowID+"\" name=\"loanNo"+rowID+"\" style=\"width: 100%;height: 100%\"> <option value=\"L0001\">L0001</option>          <option value=\"L0002\">L0002</option>      </select></td>";
//    newEmailTD.innerHTML = "<td width=\"12%\">     <select id=\"loanNo"+rowID+"\" name=\"loanNo"+rowID+"\" style=\"width: 100%;height: 100%\"> " +
//        "<g:each in="+loan_list+"\" var=\"item\" status=\"index\"> <option value="+${item.loanAppReceiptsId}+"\">${item.loanAppReceiptsId}</option>   </g:each>     </select></td>";
//    var str="qqqqq";
    var  b="<td width=\"14%\">  " +
        "   <select id=\"loanNo"+rowID+"\" " +
        " name=\"loanNo"+rowID+"\"" +
        " onchange='" +
        " ChangeLoanId(\"loanRow" + rowID +"\",\"" + str+"\",\"loanNo" + rowID+"\",\"" + strId+"\");" +
        " ' " +
        " style=\"width: 100%;height: 100%\"> " ;

    var  a= "</select></td>";
    var c ="" ;
    for(var i=0;i<id.length-1;i++){
        c+= " <option value="+id[i]+">"+id[i]+"</option>" ;
     }
    var d=b+c+a;
    newEmailTD.innerHTML =d;
//    newEmailTD.innerHTML = "<td width=\"14%\">     <select id=\"loanNo"+rowID+"\" name=\"loanNo"+rowID+"\" style=\"width: 100%;height: 100%\"> " +
//        " <option value="+id+"\">"+id+"</option></select></td>";
//添加列:原币金额
    var newEmailTD=newTR.insertCell(3);
//添加列内容
    newEmailTD.innerHTML = "<td width=\"12%\"><input id=\"loanBillsCurr"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"loanBillsCurr"+rowID+"\" value="+s1[2]+" readonly></td>";

//添加列:RMB报销金额
    var newTelTD=newTR.insertCell(4);
//添加列内容
    newTelTD.innerHTML = "<td  width=\"12%\"><input id=\"loanOriginalSum"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"loanOriginalSum"+rowID+"\" value="+s1[3]+" readonly> </td>";

//添加列:票据说明
    var newMobileTD=newTR.insertCell(5);
//添加列内容
    newMobileTD.innerHTML = "<td  width=\"12%\"><input id=\"loanBalance"+rowID+"\" name=\"loanBalance"+rowID+"\" style=\"width: 100%;height: 100%\" value="+s1[4]+" readonly> </td>";
    //添加列:备注
    var newCompanyTD=newTR.insertCell(6);
//添加列内容
    newCompanyTD.innerHTML = "<td  width=\"15%\"><input id=\"loanTheRepayment"+rowID+"\" name=\"loanTheRepayment"+rowID+"\" style=\"width: 100%;height: 100%\" value="+s1[5]+" readonly> </td>";

//添加列:备注
    var newCompanyTD=newTR.insertCell(7);
//添加列内容
    newCompanyTD.innerHTML = " <td  width=\"15%\"><input id=\"loanRemark"+rowID+"\" name=\"loanRemark"+rowID+"\" style=\"width: 100%;height: 100%\" value="+s1[6]+" readonly> </td>";



//添加列:删除按钮
    var newDeleteTD=newTR.insertCell(8);
//添加列内容
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a onMouseOver=\"this.style.fontStyle='italic'\"onMouseOut=\"this.style.fontStyle=''\" style='color: red;' onclick=\"DeleteSignRow_loan('loanRow" + rowID + "')\">删除</a></div>";

//将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString() ;
}
//删除指定行
function DeleteSignRow_loan(rowid){
    var signFrame = findObj("loan",document);
    var signItem = findObj(rowid,document);

//获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;

//删除指定Index的行
    signFrame.deleteRow(rowIndex);

//重新排列序号，如果没有序号，这一步省略
    for(var i=rowIndex;i<signFrame.rows.length;i++){
        signFrame.rows[i].cells[0].innerHTML = i.toString();
    }
}
function ChangeLoanId(rowid,str,loanId,strId){
    var loan = document.getElementById(loanId).value;
    DeleteSignRow_loan(rowid);
    var sL = str.split(loan)[1];
    var sL1  = sL.split(";")[0];
    var sL2 = sL1.split(",");

    var s = str.split(";");
    var id = strId.split(";");
    var s1 = s[0].split(",");
//    var defs=loan_list;
//    alert("222"+defs);
    //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("loanIndex",document);
    var rowID = parseInt(txtTRLastIndex.value);

    var signFrame = findObj("loan",document);
//添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "loanRow" + rowID;

//添加列:序号
    var newNameTD=newTR.insertCell(0);
//添加列内容
    newNameTD.innerHTML ="<td height='23' width='10%'><input  id='lNo"+rowID+"' name='lNo"+rowID+"' value='"+newTR.rowIndex.toString()+"' readonly=\"true\"> </td>" ;

//添加列:日期
    var newNameTD=newTR.insertCell(1);
//添加列内容
//    newNameTD.innerHTML = "<td width='12%'><input id='loanDate"+rowID+"' style='width: 100%;height: 100%' name='loanDate"+rowID+"' value=''  class='Wdate' onclick=\"SelectDate(this,'yyyy-MM-dd',null,null);\"></td>";
    newNameTD.innerHTML = "<td width='10%'><input id='loanDate"+rowID+"' style='width: 100%;height: 100%' name='loanDate"+rowID+"' value="+sL2[1]+" readonly></td>";

    //添加列:借款单号
    var newEmailTD=newTR.insertCell(2);
//添加列内容
//    newEmailTD.innerHTML = "<td width=\"12%\">     <select id=\"loanNo"+rowID+"\" name=\"loanNo"+rowID+"\" style=\"width: 100%;height: 100%\"> <option value=\"L0001\">L0001</option>          <option value=\"L0002\">L0002</option>      </select></td>";
//    newEmailTD.innerHTML = "<td width=\"12%\">     <select id=\"loanNo"+rowID+"\" name=\"loanNo"+rowID+"\" style=\"width: 100%;height: 100%\"> " +
//        "<g:each in="+loan_list+"\" var=\"item\" status=\"index\"> <option value="+${item.loanAppReceiptsId}+"\">${item.loanAppReceiptsId}</option>   </g:each>     </select></td>";
//    var str="qqqqq";
    var  b="<td width=\"14%\">  " +
        "   <select id=\"loanNo"+rowID+"\" " +
        " name=\"loanNo"+rowID+"\"" +
        " onchange='" +
        " ChangeLoanId(\"loanRow" + rowID +"\",\"" + str+"\",\"loanNo" + rowID+"\",\"" + strId+"\");" +
        " ' " +
        " style=\"width: 100%;height: 100%\"> " ;

    var  a= "</select></td>";
    var c ="" ;
    c+= " <option value="+loan+">"+loan+"</option>" ;
    for(var i=0;i<id.length-1;i++){
        if(id[i]!=loan){
            c+= " <option value="+id[i]+">"+id[i]+"</option>" ;
        }
    }
    var d=b+c+a;
    newEmailTD.innerHTML =d;
//    newEmailTD.innerHTML = "<td width=\"14%\">     <select id=\"loanNo"+rowID+"\" name=\"loanNo"+rowID+"\" style=\"width: 100%;height: 100%\"> " +
//        " <option value="+id+"\">"+id+"</option></select></td>";
//添加列:原币金额
    var newEmailTD=newTR.insertCell(3);
//添加列内容
    newEmailTD.innerHTML = "<td width=\"12%\"><input id=\"loanBillsCurr"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"loanBillsCurr"+rowID+"\" value="+sL2[2]+" readonly></td>";

//添加列:RMB报销金额
    var newTelTD=newTR.insertCell(4);
//添加列内容
    newTelTD.innerHTML = "<td  width=\"12%\"><input id=\"loanOriginalSum"+rowID+"\" style=\"width: 100%;height: 100%\" name=\"loanOriginalSum"+rowID+"\" value="+sL2[3]+" readonly> </td>";

//添加列:票据说明
    var newMobileTD=newTR.insertCell(5);
//添加列内容
    newMobileTD.innerHTML = "<td  width=\"12%\"><input id=\"loanBalance"+rowID+"\" name=\"loanBalance"+rowID+"\" style=\"width: 100%;height: 100%\" value="+sL2[4]+" readonly> </td>";
    //添加列:备注
    var newCompanyTD=newTR.insertCell(6);
//添加列内容
    newCompanyTD.innerHTML = "<td  width=\"15%\"><input id=\"loanTheRepayment"+rowID+"\" name=\"loanTheRepayment"+rowID+"\" style=\"width: 100%;height: 100%\" value="+sL2[5]+" readonly> </td>";

//添加列:备注
    var newCompanyTD=newTR.insertCell(7);
//添加列内容
    newCompanyTD.innerHTML = " <td  width=\"15%\"><input id=\"loanRemark"+rowID+"\" name=\"loanRemark"+rowID+"\" style=\"width: 100%;height: 100%\" value="+sL2[6]+" readonly> </td>";



//添加列:删除按钮
    var newDeleteTD=newTR.insertCell(8);
//添加列内容
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a onMouseOver=\"this.style.fontStyle='italic'\"onMouseOut=\"this.style.fontStyle=''\" style='color: red;' onclick=\"DeleteSignRow_loan('loanRow" + rowID + "')\">删除</a></div>";

//将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString() ;

}
//清空列表
function ClearAllSign_loan(){
    if(confirm('确定要清空借款费用列表吗？')){
        var signFrame = findObj("loan",document);
        var rowscount = signFrame.rows.length;

//循环删除行,从最后一行往前删除
        for(i=rowscount - 1;i > 0; i--){
            signFrame.deleteRow(i);
        }

//重置最后行号为1
        var txtTRLastIndex = findObj("loanIndex",document);
        txtTRLastIndex.value = "1";

//预添加一行
//        AddSignRow();
    }
}