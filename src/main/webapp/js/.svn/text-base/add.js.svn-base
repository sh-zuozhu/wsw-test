//登陆
function add() {
    var env = document.getElementById("env").value;
    var project = document.getElementById("project").value;
    var name = document.getElementById("name").value;
    var value = document.getElementById("value").value;
    var remark = document.getElementById("remark").value;
    //判非空
    if (env == EMPTY) {
        showAttention("请选择环境!");
        return;
    }
    if (project == EMPTY) {
        showAttention("请选择项目!");
        return;
    }
    if (name == EMPTY) {
        showAttention("请输入参数名!");
        return;
    }
    if (value == EMPTY) {
        showAttention("请输入参数值!");
        return;
    }
    // 判断字符串是否含有非法字符
    var result = checkStr(name, SYMBOL_ARRAY_1);
    if (result["isSuccess"] == false) {
        showAttention("参数名包含非法字符:" + result["symbol"]);
        return;
    }
    //ajax登陆
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:contextPath + "config/add.htm",
        data:"env=" + env + "&project=" + project + "&name=" + filterStr(name) + "&value=" + filterStr(value) + "&remark=" + filterStr(remark),
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                //判新增是否成功
                if (false == data["isSuccess"]) {
                    showAttention(data["message"]);
                    return;
                } else {
                    //新增成功
                    showSuccess(data["message"]);
                    return;
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