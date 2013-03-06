/**
 * 判断是否为空
 */
function  emptyVerify(id){
    if((id=='loanMoney' && document.getElementById("loanMoney").value!='') || (id=='loanAlreadyRefund' && document.getElementById("loanAlreadyRefund").value!='')){
        var regular = /^(([1-9]\d{0,9})|0)(\.\d{0,2})?$/;
        if(regular.test(document.getElementById(id).value)==false){
            alert("输入金额格式错误，请重新输入！");
            return;
        }
        if(id=='loanAlreadyRefund' && document.getElementById("loanAlreadyRefund").value!=''){
            var loanMoney = document.getElementById("loanMoney").value;
            var loanAlreadyRefund = document.getElementById("loanAlreadyRefund").value;
             if(loanMoney - loanAlreadyRefund < 0){
                 alert("已还金额大于借款金额，请重新输入！");
                 return;
             }
        }
    }
    if(id=='loanMoney' || id=='loanAlreadyRefund'){
        var a = document.getElementById('loanMoney').value;
        var b = document.getElementById('loanAlreadyRefund').value;
        var c = a-b;
        document.getElementById('loanBalance').value = c;
    }

    if(document.getElementById(id).value=="" || document.getElementById(id).value=="-1"){
        document.getElementById(id).style.backgroundColor = 'red';
    }else{
        document.getElementById(id).style.backgroundColor = '';
    }
}
/**
* 通过按钮控制DIV显示与隐藏
*/
function checkSelectDiv(){	
       if ( document.getElementById('selectAP').style.display == 'none'){
    	   document.getElementById('selectAP').style.display = '';
       } else {
        	 document.getElementById('selectAP').style.display = 'none';
       }
}



/**
* 通过全选按钮选择表格中的全部数据
*/
function checkSelectAll() {
	var el = document.getElementById("listForm:tran_tp");
	var CheckAll = document.getElementById("listForm:selectAll");
//	var len = el.innerHTML;   
	// 判断浏览器类型，如果是IE则使用table表格获取，否则通过ID获取	 
		if (el != null) {
			for(var i=0; i<el.rows.length; i++)  
			{   
				el.rows(i).cells(0).children.item(0).checked = CheckAll.checked; 
			}		
	 } 	
}

/**
* 通过全选按钮选择表格中的全部数据
*/
function checkSelectDelAll() {
	var el = document.getElementById("listForm:tran_tp");
	var CheckAll = document.getElementById("listForm:selectAll");

	// 判断浏览器类型，如果是IE则使用table表格获取，否则通过ID获取
			if (el != null && CheckAll != null) {
				for (i = 0; i < el.rows.length; i++) {
					if(el.rows(i).cells(0).children.item(0).checked==false){
						CheckAll.checked=false;
						break;
					}else{
						CheckAll.checked=true;
					}
				}	
			}
}

/**
* 通过全选按钮选择表格中的全部数据
*/
function setCheckedStatus2() {
	var TableData = document.getElementById("listForm:listTable");
	var CheckAll = document.getElementById("listForm:listTable:selectAll");
	// 判断浏览器类型，如果是IE则使用table表格获取，否则通过ID获取
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if (TableData != null && CheckAll != null) {
			for (i = 1; i < TableData.rows.length-1; i++) {
				TableData.rows(i).cells(0).children.item(0).checked = CheckAll.checked;
			}
		}
	} else {
		if (TableData != null && CheckAll != null) {
			for (i = 0; i < TableData.rows.length - 1; i++) {
				document
						.getElementById("listForm:listTable:" + i + ":isSelect").checked = CheckAll.checked;
			}
		}
	}
}

/**
 * 如果改变了选择框的值且不为空，则选中行首的checkbox
 */
function chooseCheckBox(obj, formId, tableId) {
	// objId形如resultForm:listTable:0:chooseFraudTp,那个0即为rowIndex
	var elem = formId + ":" + tableId + ":";
	var objId = obj.id;
	var rowIndex = objId.substring(objId.indexOf(":", 20) + 1, objId.indexOf(
			":", 22));
	if ('' == obj.value) {
		document.getElementById(elem + rowIndex + ":isSelect").checked = false;
	} else {
		document.getElementById(elem + rowIndex + ":isSelect").checked = true;
	}
}
 
//日期控件,yy-MM-dd：选择当前时间以前的
function selectBefTime() {
	WdatePicker( {
		maxDate :  '%y-%M-%d'
	});
}
//日期控件,yy-MM-dd
function selectFirstTime() {
	var vd5222 = $dp.$('gForm:startDate');
	WdatePicker( {
		onpicked : function() {
			vd5222.focus();
		},
		maxDate : '#F{$dp.$D(\'gForm:startDate\')}'
	});
}
function selectEndTime() {
	WdatePicker( {
		minDate : '#F{$dp.$D(\'listForm:d5221\')}'
	});
}
//不能小于第二个控件时间，并且不能小于传入日期时间
function selectStartTimeBeforDate(date) {
	var vd5222 = $dp.$('listForm:d5222');
	WdatePicker( {
		dateFmt : 'yyyy-MM-dd',
		onpicked : function() {
		vd5222.focus();
	    }
	});
}
//不能大于第一个控件时间，并且不能小于传入日期时间
function selectEndTimeBeforDate(date) {
	WdatePicker( {
		dateFmt : 'yyyy-MM-dd',
		minDate : '#F{$dp.$D(\'gForm:endDate\')}',
		maxDate : date
	});
}

//不能大于第一个控件时间，并且不能小于传入日期时间
function selectEndTimeBeforDate3(date) {
	WdatePicker( {
		dateFmt : 'yyyy-MM-dd',
		minDate : '#F{$dp.$D(\'listForm:d5225\')}',
		maxDate : date
	});
}
//如果页面上有两组日期控件，均为yy-MM-dd格式，第二组采用下面方法获得控件
function selectFirstTime2() {
    alert(111);
	var vd5224 = document.getElementById('startDate');
    alert(vd5224);
	WdatePicker( {
		dateFmt : 'yyyy-MM-dd',
		onpicked : function() {
			vd5224.focus();
		},
		maxDate:'#F{$dp.$D(\'startDate\')}'
	});
}
function selectEndTime2() {
	WdatePicker( {
		dateFmt : 'yyyy-MM-dd',
		minDate : '#F{$dp.$D(\'gForm:endDate\')}'
	});
}
//如果页面上有三组日期控件，均为yy-MM-dd格式，第三组采用下面方法获得控件
function selectFirstTime3() {
	var vd5226 = $dp.$('listForm:d5226');
	WdatePicker( {
		onpicked : function() {
			vd5226.focus();
		},
		maxDate:'#F{$dp.$D(\'listForm:d5226\')}'
	});
}
function selectEndTime3() {
	WdatePicker( {
		minDate : '#F{$dp.$D(\'listForm:d5225\')}'
	});
}
function selectFirstTime4() {
	var vd5224 = $dp.$('listForm:d5224');
	WdatePicker( {
		onpicked : function() {
			vd5224.focus();
			vd5224.value="";
		},
		minDate:'%y-%M-%d'
	});
   }
function selectEndTime4() {
WdatePicker( {
	minDate : '#F{$dp.$D(\'listForm:d5223\')}',
	maxDate:'#F{$dp.$D(\'listForm:d5223\',{M:3})}'		
});
}

//日期控件,yy-mm-dd hh:mm:ss
function selectFirstTimeAndSecond() {
	var vd5224 = $dp.$('listForm:d5224');
	WdatePicker( {
		autoPickDate : false,
		startDate:'%y-%M-%d 00:00:00',
		dateFmt : 'yyyy-MM-dd HH:mm:ss',
		onpicked : function() {
			vd5224.focus();
		},
		maxDate : '#F{$dp.$D(\'listForm:d5224\')}'
	});
}
function selectEndTimeAndSecond() {
	WdatePicker( {
		autoPickDate : false,
		startDate:'%y-%M-%d 00:00:00',
		dateFmt : 'yyyy-MM-dd HH:mm:ss',
		minDate : '#F{$dp.$D(\'listForm:d5223\')}'
	});
}

//日期控件,yy-mm-dd hh:mm:ss
function selectFirstTimeAndSecond1() {
	var vd5224 = $dp.$('listForm:d5224');
	WdatePicker( {
		autoPickDate : false,
		startDate: 'default',
		dateFmt : 'yyyy-MM-dd HH:mm:ss',
		onpicked : function() {
			vd5224.focus();
		},
		maxDate : '#F{$dp.$D(\'listForm:d5224\')}',
		maxDate :'%y-%M-%d'
	});
}
function selectEndTimeAndSecond1() {
	WdatePicker( {
		autoPickDate : false,
		startDate:'default',
		dateFmt : 'yyyy-MM-dd HH:mm:ss',
		minDate : '#F{$dp.$D(\'listForm:d5223\')}',
		maxDate :'%y-%M-%d'
	});
}
//日期控件,yy-mm-dd hh:mm:ss
function selectFirstTimeAndSecond2() {
	var vd5226 = $dp.$('listForm:d5226');
	WdatePicker( {
		autoPickDate : false,
		startDate:'%y-%M-%d 00:00:00',
		dateFmt : 'yyyy-MM-dd HH:mm:ss',
		onpicked : function() {
			vd5226.focus();
		},
		maxDate : '#F{$dp.$D(\'listForm:d5226\')}'
	});
}
function selectEndTimeAndSecond2() {
	WdatePicker( {
		autoPickDate : false,
		startDate:'%y-%M-%d 00:00:00',
		dateFmt : 'yyyy-MM-dd HH:mm:ss',
		minDate : '#F{$dp.$D(\'listForm:d5225\')}'
	});
}
//日期控件，格式为yyyy-MM
function selectFirstDate() {
	var vd5222 = $dp.$('listForm:d5222');
	WdatePicker( {
		dateFmt : 'yyyy-MM',
		onpicked : function() {
			vd5222.focus();
		},
		maxDate : '#F{$dp.$D(\'listForm:d5222\')}'
	});
}
function selectEndDate() {
	WdatePicker( {
		dateFmt : 'yyyy-MM',
		minDate : '#F{$dp.$D(\'listForm:d5221\')}'
	});
}

/**
* 通过全选按钮选择表格中的全部数据
*/
function checkSelectAllStatus(listFormId,listTableId) {
	var TableData = document.getElementById(listFormId+":"+listTableId);
	if(TableData==null){
		TableData = document.getElementById("listForm:listTable");
	}
	var CheckAll = document.getElementById(listFormId+":"+listTableId+":selectAll");
	if(CheckAll==null){
		CheckAll = document.getElementById("listForm:listTable:selectAll");
	}
	//var TableData = document.getElementById("listForm:listTable");
	//var CheckAll = document.getElementById("listForm:listTable:selectAll");
	// 判断浏览器类型，如果是IE则使用table表格获取，否则通过ID获取
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if (TableData != null && CheckAll != null) {
			for (i = 1; i < TableData.rows.length - 1; i++) {
				var disabledValue=TableData.rows(i).cells(0).children.item(0).disabled;
				if(TableData.rows(i).cells(0).children.item(0).checked==false){
					CheckAll.checked=false;
					break;
				}else{
					CheckAll.checked=true;
				}
			}	
		}
	}
}

/**
* 通过全选按钮选择表格中的全部数据
*/
function checkSelectAllStatusWithNo(listFormId,listTableId,no) {
	var TableData = document.getElementById(listFormId+":"+listTableId);
	if(TableData==null){
		TableData = document.getElementById("listForm:listTable");
	}
	var CheckAll = document.getElementById(listFormId+":"+listTableId+":selectAll");
	if(CheckAll==null){
		CheckAll = document.getElementById("listForm:listTable:selectAll");
	}
	//var TableData = document.getElementById("listForm:listTable");
	//var CheckAll = document.getElementById("listForm:listTable:selectAll");
	// 判断浏览器类型，如果是IE则使用table表格获取，否则通过ID获取
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if (TableData != null && CheckAll != null) {
			for (i = 1; i < TableData.rows.length - no; i++) {
				var disabledValue=TableData.rows(i).cells(0).children.item(0).disabled;
				if(TableData.rows(i).cells(0).children.item(0).checked==false){
					CheckAll.checked=false;
					break;
				}else{
					CheckAll.checked=true;
				}
			}	
		}
	}
}

/**
 * 用checkbox模仿radiobutton的单选
 * @param listFormId
 * @param listTableId
 * @return
 */
function checkSelectOnlyOne(listFormId, listTableId, selectObj) {
	var TableData = document.getElementById(listFormId + ":" + listTableId);
	if (TableData == null) {
		TableData = document.getElementById("listForm:listTable");
	}
	// 判断浏览器类型，如果是IE则使用table表格获取，否则通过ID获取
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if (TableData != null) {
			for (i = 0; i < TableData.rows.length; i++) {
				var item = TableData.rows(i).cells(0).children.item(0);
				if (item != null && item.id != selectObj.id) {
					item.checked = false;
				}
			}
		}
	}
}

/**
 * 使按钮变灰
 * @param objId 按钮id
 */
	function disableBtn(cmdId,cmlId) {
		var cmdObj =  document.getElementById("listForm:" + cmdId);
		var cmlObj = document.getElementById("listForm:" + cmlId);
		if (cmdObj.disabled==false) {
			cmdObj.disabled=true;
			cmlObj.click();
		}
	}
	
	
/**
 * 使按钮变灰2
 * @param formId objId 按钮id
*/
function disableBtn2(formId,cmdId,cmlId) {
	var cmdObj =  document.getElementById(formId + ":" + cmdId);
	var cmlObj = document.getElementById(formId + ":" + cmlId);
	if (cmdObj.disabled==false) {
		cmdObj.disabled=true;
		cmlObj.click();
	}
 }
 
function hideListTable(formId,id){
		   var traget=document.getElementById(formId + ":" + id);	       
	       traget.style.visibility="hidden";
	   }
/**
 * 禁用按钮，需要刷新页面后才能重新生效
 * @param formId
 * @param buttonId
 * @return
 */
function disableButton(formId, buttonId) {
	var btn = document.getElementById(formId + ":" + buttonId);
	if (btn != null) {
		btn.disabled = true;
		btn.href = "#";
		btn.onclick = "";
	}
}

/**
 * aresInsInf.jsp页面使用
 * @param delType 记录删除哪个列表
 * @param delId 待删除记录的id或其它确定信息
 * @param reRenderId 需刷新组件的id
 * @return
 */
function setInputHiddenInAresInsInf(delType, delId, reRenderId) {
	document.getElementById("listForm:delType").value = delType;
	document.getElementById("listForm:delId").value = delId;
	document.getElementById("listForm:reRenderId").value = reRenderId;
}

/**
 * 将radio的值设置到checkbox
 * @param formId
 * @param tableId
 * @param radioId
 * @return
 */
function freshRadios(formId, tableId, radioId) {
	var elem = formId + ":" + tableId;
	var TableData = document.getElementById(elem);
	if (navigator.userAgent.indexOf("MSIE") > 0) {
		if (TableData != null) {
			for (i = 0; i < TableData.rows.length; i++) {
				var ckbox = TableData.rows(i).cells(0).children.item(0);
				if (ckbox != null) {
					if (radioId == ckbox.id.split(":")[2]) {
						ckbox.checked = true;
					} else {
						ckbox.checked = false;
					}
				}
			}
		}
	}
}