function initNav(env_path, prof) {
    $("#btn_new_proj").attr("href", env_path + "/new_proj");
    $("#btn_my_proj").attr("href", env_path + "/my_proj");
    $("#btn_doc_template").attr("href", env_path + "/my_doc_template?profId="
        + prof.account);
    $("#btn_review_doc").attr("href", env_path + "/review_doc");
    $("#btn_my_profile").attr("href", env_path + "/my_profile");
}


function dl(obj) {
    console.log($(obj).attr('id'));
    window.location.href = "/pm/doc_template/download?id=" + $(obj).attr('id');
    // $.ajax({
    //     url: "/pm/doc_template/download?id=" + $(obj).attr('id'),
    //     type: "GET"
    // });
}

function del(obj) {

}