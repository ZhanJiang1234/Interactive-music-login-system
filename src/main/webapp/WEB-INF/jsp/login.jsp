<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zxx">

<head>
    <link rel="shortcut icon" href="${ctx}/images/favicon.ico" />
    <title>Music</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <link href="${ctx}/styles/login.css" rel='stylesheet' type='text/css' />
    <script src="${ctx}/js/jquery.js"></script>
    <style>
        #btn {
            color: #ffffff;
            width: 100%;
            padding: 0.4em 0;
            font-size: 1em;
            font-weight: 400;
            letter-spacing: 2px;
            cursor: pointer;
            border: none;
            outline: none;
            background: #000;
            height: 42px;
            border-radius: 4px;
        }
        .agile_label{
            display: none;
            color: #FFF;
            font-size: 14px;
        }
        a {
            font-size: 14px;
            color: #007bff; /* 设置链接文字颜色 */
            text-decoration: none; /* 去除下划线 */
        }
        a:hover {
            color: #0056b3; /* 设置鼠标悬停时的文字颜色 */
        }
        a {
            padding: 8px 4px;
        }

    </style>

</head>

<body>
    <h1>Music website login system</h1>
    <div class="music-login box">
        <img src="${ctx}/images/music_res.png" alt="logo_img" />
        <div class="form">
            <div class="agile-field-txt">
                <input type="text" name="account" id="account" placeholder="Account" />
            </div>
            <div class="agile-field-txt">
                <input type="password" name="password" id="password" placeholder="Password" />
				<div class="agile_label">

				</div>
            </div>
            <div class="w3ls-bot">
                <button id="btn">Login</button>
                <div class="">
                    <a href="${ctx}/register">Register</a>

                </div>
            </div>
        </div>
    </div>

<script>
    $(function(){
        $('#btn').click(function(){
            let account = $('#account').val()
            let password = $('#password').val()
            let msg = $('.agile_label');
            if(!account){
                msg.text('Please input the account');
                msg.show()
                return ;
            }
            if(!password){
                msg.text('Please input the password');
                msg.show()
                return ;
            }
            let formData = {
                account,
                password
            }
            $.ajax({
                type: 'post',
                url: '${ctx}/login',
                data: JSON.stringify(formData),
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                success: function(res){
                    let code = res.code;
                    if(code === 2000){
                        location.href = '${ctx}/index';
                    }else{
                        msg.text('The account or password is incorrect');
                        msg.show()
                    }
                }
            })
        })
    })
</script>

</body>

</html>