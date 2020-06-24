var main = {
    init: function () {
        var _this = this;
        $('#btn-create').on('click', function () {
            _this.create();
        })
    },
    create: function () {
        var data = {
            communityName: $("#communityName").val(),
            managerEmail: $("#managerEmail").val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/communities',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (result) {
            alert('커뮤니티가 생성되었습니다.\n관리자로 로그인하기 위해서는 커뮤니티 ID와 비밀번호를 입력하세요.\n\n생성된 커뮤니티 ID: '+ result);
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();