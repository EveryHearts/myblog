function logoutConfirm() {
    event.preventDefault();
    swal({
        icon:'info'
        ,title:'系统消息'
        ,text:'你确定退出当前账号吗？'
        ,buttons:{
            cancel:true
            ,confirm:{
                text:'确定'
                ,value:true
                ,closeModal:true
            }
        }
    }).then(function (value) {
        if (value){
            location.href='../user/logoutAccount';
        }
    });
}