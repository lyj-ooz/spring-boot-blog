let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        })
    },

    save: function() {
        let data = {
            username: $("#username").val(),
            email: $("#email").val(),
            password: $("#pwd").val(),
        };

        console.log(data);

        // ajax 호출시 default가 비동기 호출
        $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body데이터 타입
            dataType: "json" // 응답 타입
        }).done(function(response){
            console.log(response);
            alert("회원가입 완료!");
            location.href = "/blog"
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    }
}

index.init();