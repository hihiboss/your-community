var main = {
    init: function () {
        var _this = this;
        $('#btn-login').on('click', function () {
            _this.login();
        })
    },
    login: function () {
        var data = {
            communityId: $("#communityId").val(),
        };

        alert("로그인 성공!");
        window.location.href = '/communities/' + data.communityId;
        // $.ajax({
        //     type : 'POST',
        //     url : '/api/users',
        //     dataType : 'json',
        //     contentType : 'application/json; charset=utf-8',
        //     data : JSON.stringify(data)
        // }).done(function () {
        //     alert('가입되었습니다.');
        //     window.location.href = '/';
        // }).fail(function (error) {
        //     alert(JSON.stringify(error));
        // })
    }
};

main.init();