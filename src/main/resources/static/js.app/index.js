var main = {
    init : function () {
        var _this = this;
        $('#btn-login').on('click', function () {
            _this.login();
        })
    },
    login : function () {
        alert("login 준비중");
    }
};

main.init();