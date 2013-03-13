
<%@ page import="processes.Process" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
    <div>
        <img id="image"  src="${createLink(action:'processImage',controller:'flowLookUp',params:[processId:process.processId])}" >
    </div>
</body>
</html>
