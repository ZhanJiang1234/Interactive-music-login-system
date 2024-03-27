<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="shortcut icon" href="${ctx}/images/favicon.ico"/>
    <meta charset="UTF-8"/>
    <title>Music</title>

    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/audio.js"></script>

    <style>
        .pagenavi {
            clear: both;
            padding: 0px 0px 20px 0px;
        }

        .pagenavi a,
        .pagenavi a:visited {
            margin-right: 10px;
            color: #555555;
            outline: 0px;
            font-size: 12px;
            text-transform: capitalize;
            display: block;
            display: inline-block;
            border: solid 1px #eaeaea;
            border-bottom: solid 1px #bfbfbf;
            border-right: solid 1px #bfbfbf;
            border-radius: 4px;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            height: 24px;
            line-height: 24px;
            padding: 0 10px;

        }

        .pagenavi a:hover {
            text-decoration: none;
            color: #dc6a4d;
        }

        .pagenavi .current {
            margin-right: 10px;
            color: #555555;
            outline: 0px;
            font-size: 12px;
            font-weight: bold;
            text-transform: capitalize;
            display: block;
            display: inline-block;
            border: solid 1px #eaeaea;
            border-bottom: solid 1px #bfbfbf;
            border-right: solid 1px #bfbfbf;
            border-radius: 4px;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            height: 24px;
            line-height: 24px;
            padding: 0 10px;

        }

        .pagenavi .pages {
            padding: 0 5px;
            margin-right: 0px;
        }
    </style>


    <script type="text/javascript">
        $(document).ready(function () {

            var $song = $('.listen-song')
            $song.hide()


            $('#audio').on('play', function () {
                $song.show()
                audio = getAudio()
                init(audio);
                audio.play();
            });

        });

        function getAudio() {
            var $audio = $('<audio src="mp3/Beyond-再见理想.mp3" id="audio"  controlsList="nodownload" controls >audio element not supported</audio>')
            $('#payer-div').html($audio);
            const audio = $('#audio')[0];
            audio.crossOrigin = "anonymous";
            audio.volume = 0.5;
            return audio;

        }

        function listen() {
            $('.listen-song').show()
            audio = getAudio()
            init(audio);
            audio.play();
        }

    </script>
</head>

<body>

<div id="outer-container">
    <div id="container">
        <div id="top">
            <div id="logo">
                <span>Cloud music</span>
            </div>
            <div id="nav">
                <ul id="topnav" class="sf-menu">
                    <li><a href="${ctx}/index" >Home</a></li>
                    <li><a href="${ctx}/music" >Discovery</a></li>
                    <li><a href="${ctx}/my" class="current">My music</a></li>
                    <c:choose>
                        <c:when test="${sessionScope.USER_SESSION != null  && sessionScope.USER_SESSION.userType=='M'}">
                           <li><a href="${ctx}/mgr">Manage</a></li>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${sessionScope.USER_SESSION != null}">
                            <span style="font-size: 12px;color: dodgerblue">hello, ${sessionScope.USER_SESSION.nick}</span>
                            <span onclick="location.href='${ctx}/logout'" style="font-size: 12px;color: brown;cursor: pointer;margin-left: 6px;">sign out</span>
                        </c:when>
                        <c:otherwise>
                            <a style="font-size: 12px" href="${ctx}/login">sign in</a>
                        </c:otherwise>
                    </c:choose>
                </ul>

            </div>
        </div>

<%--        <div id="header">--%>
<%--            <div class="listen-song">--%>
<%--                <canvas id="canvas" width="800" height="100"></canvas>--%>
<%--                <div id="payer-div">--%>

<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

        <div id="content">
            <div class="main">
                <h2 class="title_pattern uppercase"><span>My Collection</span></h2>
                <ul class="recentpost">

                </ul>
                <div class="pagenavi productnav" id="page">

                </div>
                <div class="clear"></div>
            </div>


        </div>

    </div>
</div>

<script>
    let pageSize = 20;
    let page = 1
    $(function(){
        queryList();
    })

    function queryList(current){
        if(current){
            page = current
        }
        let formData = {
            pageSize,
            page
        }
        $.ajax({
            type: 'post',
            url: '${ctx}/my/page',
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(formData),
            success: function (res){
                let $recentpost=$('.recentpost');

                let $page = $('#page')

                $recentpost.empty();
                $page.empty()
                let html = []
                let code = res.code
                if(code === 2000){
                    let data = res.data
                    let dataList = data.records

                    dataList.forEach(function(item){
                        html.push('<li>')
                        if(item.imgUrl){
                            html.push('<img src="${ctx}/file/'+item.imgUrl+'" alt="" class="frame"/>')
                        }else{
                            html.push('<img src="images/content/pic4.jpg" alt="" class="frame"/>')
                        }
                        html.push('<span class="shadowimg220"></span>')
                        html.push('<a href="javascript:cancelCollect(\''+item.collectId+'\')" class="listen">Cancel</a>')
                        html.push('<span>'+item.singer+'-'+item.musicName+'</span>')
                        html.push('</li>')
                    })

                    let pages = data.pages
                    let current = data.current
                    if(pages>1){
                        let pageHtml = [];
                        for (let i = 1; i <= pages ; i++) {
                            if(i===current){
                                pageHtml.push('<span class="current">'+i+'</span>')
                            }else{
                                pageHtml.push('<a href="javascript:queryList(\''+i+'\')" class="page">'+i+'</a>')
                            }
                        }
                        $page.append(pageHtml.join(''))
                    }

                    $recentpost.append(html.join(''))
                }

            }
        })
    }

    function cancelCollect(collectId){
        $.ajax({
            type: 'get',
            url:'${ctx}/collect/cancel?collectId='+collectId,
            dataType: 'json',
            success: function(res){
                let code = res.code ;
                if(code === 2000){
                    alert('success')
                    location.reload();
                }
            }
        })
    }
</script>

</body>

</html>