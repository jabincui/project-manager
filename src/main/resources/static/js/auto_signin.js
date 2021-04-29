function auto_signin(env_path) {
    let prof = {};
    if (localStorage.getItem("token") == null) {
        alert("您未登录, 点击确定跳转登录");
        window.location.href = env_path + "/signin";
    } else {
        // alert(localStorage.token);
        $.ajax({
            url: env_path + "/prof/identity",
            type: "GET",
            dataType: "json",
            async: false,
            headers: {
                Authorization: localStorage.token,
            },
            success: function (result) {
                // alert("prof" + prof.account + prof.name + prof.password);
                if (result.account === undefined) {
                    alert("您未登录, 点击确定跳转登录");
                    window.location.href = env_path + "/signin";
                } else {
                    // 成功返回prof
                    prof = result;
                    $("#prof_name").text(prof.name);
                }
            },
            error: function (xhr, status, error) {
                alert("自动登录出错，点击确定跳转登录");
                console.log(xhr);
                console.log(status);
                console.log(error);

                localStorage.removeItem("token");
                window.location.href = env_path + "/signin";
            }
        });
    }
    return prof;
}

function init(env_path) {
    $("#btn_new_proj").attr("href", env_path + "/new_proj");
    $("#btn_my_proj").attr("href", env_path + "/my_proj");
    $("#btn_doc_template").attr("href", env_path + "/doc_template?profId="
        + prof.account);
    $("#btn_review_doc").attr("href", env_path + "/review_doc");
    $("#btn_my_profile").attr("href", env_path + "/my_profile");
}