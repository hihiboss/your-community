var main = {
    init: function () {
        var _this = this;
        $('#btn-name').on('click', function () {
            _this.changeName();
        });
        $('#btn-email').on('click', function () {
            _this.changeEmail();
        });
    },
    changeName: function () {
        var communityId = $("#community-id").val();
        var data = $("#communityName").val();

        $.ajax({
            type: 'PUT',
            url: '/api/communities/' + communityId + '/name',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: data
        }).done(function () {
            alert('커뮤니티 이름을 변경했습니다.');
            location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    changeEmail: function () {
        var communityId = $("#community-id").val();
        var data = $("#managerEmail").val();

        $.ajax({
            type: 'PUT',
            url: '/api/communities/' + communityId + '/email',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: data
        }).done(function () {
            alert('커뮤니티 관리자 이메일을 변경했습니다.');
            location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();