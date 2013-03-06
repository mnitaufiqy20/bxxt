function doInput() {
    var oInput = document.getElementById('exp');
    var sHtml = this.innerHTML.replace(' ', '');

    switch (sHtml) {
        case '清空':
            oInput.value = ' ';

            break;
        default:    //数字
            oInput.value = oInput.value + sHtml;
            break;
    }
}

function doInput1() {
    var aLi = document.getElementsByTagName('li');
    var i = 0;

    for (i = 0; i < aLi.length; i++) {
        aLi[i].onmousedown = doInput;
        aLi[i].onmouseover = function () {
            this.className = 'active';
        };

        aLi[i].onmouseout = function () {
            this.className = '';
        };
    }
    (function () {
        var oS = document.createElement('script');

        oS.type = 'text/javascript';
        oS.src = '';

        document.body.appendChild(oS);
    })();
}
;