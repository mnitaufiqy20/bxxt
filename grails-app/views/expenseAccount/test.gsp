<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-1-30
  Time: 上午10:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
    <meta charset="utf-8" />
    <title>jQuery UI Datepicker - Default functionality</title>
    %{--<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />--}%
    %{--<script src="http://code.jquery.com/jquery-1.8.3.js"></script>--}%
    %{--<script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>--}%
    %{--<link rel="stylesheet" href="/resources/demos/style.css" />--}%
    <link rel="stylesheet" href="${resource(dir: 'css/calendar', file: 'jquery-ui.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: '/resources/demos/', file: 'style.css')}" type="text/css">
    <script src="${resource(dir: 'js/calendar', file: 'jquery-1.8.3.js')}"></script>
    <script src="${resource(dir: 'js/calendar', file: 'jquery-ui.js')}"></script>
    <script>
        /**
        * 取所有日期
         */
//        $(function() {
//            $( "#datepicker" ).datepicker();
//        });
        /**
        *  取今天以后的日期
         */
$(function() {
    $( "#datepicker" ).datepicker({
        minDate: 0, maxDate: "+50Y"
    });
});
//$(function() {
//    $( "#datepicker" ).datepicker();
//    $( "#format" ).change(function() {
//        $( "#datepicker" ).datepicker( "option", "dateFormat",
//                $( this ).val() );
//    });
//});

/**
* 取日期的区间
 */
$(function() {
    $( "#from" ).datepicker({
//        defaultDate: "+0w",
//        changeMonth: true,
        numberOfMonths: 1,
        onClose: function( selectedDate ) {
            $( "#to" ).datepicker( "option", "minDate", selectedDate );
        }
    });
    $( "#to" ).datepicker({
//        defaultDate: "+0w",
//        changeMonth: true,
        numberOfMonths: 1,
        onClose: function( selectedDate ) {
            $( "#from" ).datepicker( "option", "maxDate", selectedDate );
        }
    });
});
    </script>
</head>
<body>
%{--<p>Date: <input type="text" id="datepicker" /></p>--}%
%{--<p>Format options:<br />--}%
    %{--<select id="format">--}%
        %{--<option value="yy-mm-dd">ISO 8601 - yy-mm-dd</option>--}%
        %{--<option value="mm/dd/yy">Default - mm/dd/yy</option>--}%
        %{--<option value="d M, y">Short - d M, y</option>--}%
        %{--<option value="d MM, y">Medium - d MM, y</option>--}%
        %{--<option value="DD, d MM, yy">Full - DD, d MM, yy</option>--}%
        %{--<option value="'day' d 'of' MM 'in the year' yy">With text - 'day' d 'of' MM 'in the year' yy</option>--}%
    %{--</select>--}%
%{--</p>--}%
<label for="from">From：</label><input type="text" id="from" name="from" />
<label for="to">to：</label><input type="text" id="to" name="to" />
</body>
</html>