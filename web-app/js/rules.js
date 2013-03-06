function queryReportInfo() {
    $.ajax({
        type:"POST",
        url:"../tmReportRule/queryReportInfo",
        data:"depart_id=" + "",
        cache:false,
        dataType:"text",
        success:function (data) {
            var json = eval('(' + data + ')')
            alert(json)
            $("#report_name").html('<option value="-1">请选择</option>')
            for (var i = 0; i < json.length; i++) {
                $("#report_name").append('<option value="' + json[i]["report_id"] + '">' + json[i]["report_name"] + '</option>');
            }
        }
    });
}

//根据选择的报表名称 为对应该的规则中加入Report的对应关系
function queryRid() {
    var rid = document.getElementById('report_name').value
    alert("rid : " + rid)
    if (rid == null || rid == -1) {
        alert("请选择报表")
    } else {
        var aa = document.getElementById('report_name')
        var rn = aa.options[aa.selectedIndex].text
        alert("rn :" + rn)
        document.getElementById('report_id').value = rid
        document.getElementById('rn').value = rn
    }
}

//得到公司代码对应的报表信息
function queryReports() {
    var id = document.getElementById('depart_id').value
    alert(id)
    //被选择的机构代码
    document.getElementById("did").value = id

    $.ajax({
        type:"POST",
        url:"../tmReportRuleResault/queryReports",
        data:"id=" + id,
        cache:false,
        dataType:"text",
        success:function (data) {
            var json = eval('(' + data + ')')
            $("#report_id").html('<option value="-1">请选择</option>')
            for (var i = 0; i < json.length; i++) {
                $("#report_id").append('<option value="' + json[i]["report_id"] + '">' + json[i]["report_name"] + '</option>');
            }
        }
    });
}

//得到报表并引入润乾页面
function oneTab() {
    var rid = document.getElementById('report_id').value
    alert(rid)
    document.getElementById('rn').value = rid
}

//查询对应报表
function searchReport() {
    var a1 = document.getElementById('did').value
    var a2 = document.getElementById('rn').value
    if (a1 != "" && a2 != "") {
        alert(a2)
        document.getElementById('search').style.display = 'block'
        alert("还没引入润乾报表页")

    } else {
        alert("fdafdsafdadfadfs")
    }

}

//数据校验
function dataCheck() {
    var report = document.getElementById('rn').value
    alert(report)
    $.ajax({
        type:"POST",
        url:"../tmReportRuleResault/queryRuleList",
        data:"id=" + report,
        cache:false,
        dataType:"text",
        success:function (data) {
            var json = eval('(' + data + ')')
//            $("#report_id").html('<option value="-1">请选择</option>')
            for (var i = 0; i < json.length; i++) {
//                $("#report_id").append('<option value="' + json[i]["report_id"] + '">' + json[i]["report_name"] + '</option>');
                alert(json)
            }
        }
    });
}
//删除规则