var main = {
    init : function () {
        var _this = this;
        $('#btn-create').on('click', function () {
            _this.create();
        })
    },
    create : function () {
        var data = {
            communityName : $("#communityName").val(),
            managerEmail : $("#managerEmail").val(),
            password : $("#password").val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/communities',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function (result) {
            alert('커뮤니티가 생성되었습니다.');
            window.location.href = '/community' + "/" + result.id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();