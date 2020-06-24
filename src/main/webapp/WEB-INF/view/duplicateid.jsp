<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<label>로그인 아이디 : </label>
<input id="loginid" name="loginid" type="text"/>
<input id="loginidcheck" type="button" value="아이디 중복 검사"/>
<script>
    $(document).ready(function () {
        $('#loginidcheck').click(function () {
            $.getJSON('checkloginid/' + $('#loginid').val(), function (result) {
                console.log(result);
                if (result.duplicated == true) {
                    alert('이미 등록된 로그인 id입니다. ' + result.availableId + '는 사용할 수 잇습니다');
                } else {
                    alert('사용할 수 있는 로그인 id 입니다');
                }
            });
        });
    });
</script>