//function findObj(theObj, theDoc)
//{
//    var p, i, foundObj;
//    if(!theDoc) theDoc = document;
//    if( (p = theObj.indexOf("?")) > 0 && parent.frames.length)
//    {    theDoc = parent.frames[theObj.substring(p+1)].document;    theObj = theObj.substring(0,p); }
//    if(!(foundObj = theDoc[theObj]) && theDoc.all)
//        foundObj = theDoc.all[theObj];
//    for (i=0; !foundObj && i < theDoc.forms.length; i++)
//        foundObj = theDoc.forms[i][theObj];
//    for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
//        foundObj = findObj(theObj,theDoc.layers[i].document);
//    if(!foundObj && document.getElementById) foundObj = document.getElementById(theObj);
//    return foundObj;
//}
////添加一个参与人填写行
//function AddSignRow(){
// //读取最后一行的行号，存放在txtTRLastIndex文本框中
//    var txtTRLastIndex = findObj("otherIndex",document);
//    var rowID = parseInt(txtTRLastIndex.value);
//
//    var signFrame = findObj("other",document);
////添加行
//    var newTR = signFrame.insertRow(signFrame.rows.length);
//    newTR.id = "otherRow" + rowID;
//
////添加列:序号
//    var newNameTD=newTR.insertCell(0);
////添加列内容
//    newNameTD.innerHTML ="<td height='23' width='10%'>"+newTR.rowIndex.toString()+"</td>" ;
//
////添加列:日期
//    var newNameTD=newTR.insertCell(1);
////添加列内容
//    newNameTD.innerHTML = "<td width='15%'><input id='otherDate"+rowID+"' style='width: 100%;height: 100%' name='otherDate"+rowID+"' value=''  class='Wdate' onclick=\"SelectDate(this,'yyyy-MM-dd',null,null);\"></td>";
//
////添加列:原币金额
//    var newEmailTD=newTR.insertCell(2);
////添加列内容
//    newEmailTD.innerHTML = "<td width='10%'><input id='otherOriginalSum"+rowID+"' style='width: 100%;height: 100%' name='otherOriginalSum"+rowID+"' value=''></td>";
//
////添加列:RMB报销金额
//    var newTelTD=newTR.insertCell(3);
////添加列内容
//    newTelTD.innerHTML = "<td  width='15%'><input id='otherBxRmbSum"+rowID+"' style='width: 100%;height: 100%' name='otherBxRmbSum"+rowID+"' value=''> </td>";
//
////添加列:票据说明
//    var newMobileTD=newTR.insertCell(4);
////添加列内容
//    newMobileTD.innerHTML = "<td  width='20%'><input id='otherBillsExplain"+rowID+"' name='otherBillsExplain"+rowID+"' style='width: 100%;height: 100%' value=''> </td>";
//
////添加列:备注
//    var newCompanyTD=newTR.insertCell(5);
////添加列内容
//    newCompanyTD.innerHTML = "<td  width='15%'><input id='otherRemark"+rowID+"' name='otherRemark"+rowID+"' style='width: 100%;height: 100%'' value=''> </td>";
//
//
////添加列:删除按钮
//    var newDeleteTD=newTR.insertCell(6);
////添加列内容
//    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a onMouseOver=\"this.style.fontStyle='italic'\"onMouseOut=\"this.style.fontStyle=''\" style='color: red;' onclick=\"DeleteSignRow('otherRow" + rowID + "')\">删除</a></div>";
//
////将行号推进下一行
//    txtTRLastIndex.value = (rowID + 1).toString() ;
//}
////删除指定行
//function DeleteSignRow(rowid){
//    var signFrame = findObj("other",document);
//    var signItem = findObj(rowid,document);
//
////获取将要删除的行的Index
//    var rowIndex = signItem.rowIndex;
//
////删除指定Index的行
//    signFrame.deleteRow(rowIndex);
//
////重新排列序号，如果没有序号，这一步省略
//    for(i=rowIndex;i<signFrame.rows.length;i++){
//        signFrame.rows[i].cells[0].innerHTML = i.toString();
//    }
//}//清空列表
//function ClearAllSign(){
//    if(confirm('确定要清空其他费用报销列表吗？')){
//        var signFrame = findObj("other",document);
//        var rowscount = signFrame.rows.length;
//
////循环删除行,从最后一行往前删除
//        for(i=rowscount - 1;i > 0; i--){
//            signFrame.deleteRow(i);
//        }
//
////重置最后行号为1
//        var txtTRLastIndex = findObj("otherIndex",document);
//        txtTRLastIndex.value = "1";
//
////预添加一行
////        AddSignRow();
//    }
//}