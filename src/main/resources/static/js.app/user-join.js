var main = {
    init : function () {
        var _this = this;
        $('#btn-join').on('click', function () {
            _this.join();
        })
    },
    join : function () {
        var data = {
            studentId : $("#studentId").val(),
            communityId: $("#communityId").val(),
            name : $("#name").val(),
            email : $("#email").val(),
            enrollmentStatus : $("#enrollmentStatus").val(),
            grade : $("#grade").val(),
            nickname : $("#nickname").val()
        };

        $.ajax({
            type : 'POST',
            url : '/api/users',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('가입되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
};

main.init();