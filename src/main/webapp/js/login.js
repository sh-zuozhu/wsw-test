/**
 * 按钮监听
 */
function keyPress(e){
    if( 13 == e.keyCode){
        login();
    }
}

//登陆
function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    //判非空
    if (username == EMPTY) {
        showAttention("请输入用户名!");
        return;
    }
    if (password == EMPTY) {
        showAttention("请输入密码!");
        return;
    }
    // 判断字符串是否含有非法字符
    var result = checkStr(username, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("用户名包含非法字符:" + result["symbol"]);
        return;
    }
    result = checkStr(password, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("密码包含非法字符:" + result["symbol"]);
        return;
    }

    //ajax登陆
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:contextPath + "login.htm",
        data:"username=" + filterStr(username) + "&password=" + password,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                //判登陆是否成功
                if (false == data["isSuccess"]) {
                    showAttention(data["message"]);
                    document.getElementById("password").value = EMPTY;
                    return;
                } else {
                    //登陆成功
                    showSuccess(data["message"]);
                    location.href = contextPath + "index.htm";
                }
            } else {
                showAttention("服务器连接异常，请稍后再试！");
            }
        },
        error:function (data, textStatus) {
            showAttention("服务器连接异常，请稍后再试！");
        }
    });
}