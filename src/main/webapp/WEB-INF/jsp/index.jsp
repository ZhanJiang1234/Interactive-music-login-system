<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="shortcut icon" href="${ctx}/images/favicon.ico" />
    <meta charset="UTF-8" />
    <title>Music</title>

    <link href="${ctx}/styles/style.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/audio.js"></script>


    <script type="text/javascript">
        let audio = undefined
        $(document).ready(function () {
            let $header = $('#header')
            $header.hide()

            $('#audio').on('play', function () {
                $header.show()
                audio = getAudio()
                init(audio);
                audio.play();
            });

        });
        function getAudio(fileUrl, info) {
            var $audio = $('<div style="color: #FFF;">Currently listening to music：'+info+'<span style="margin-left:20px;color:red;cursor: pointer" onclick="closeListen()">Close</span></div><audio src="${ctx}/file/'+fileUrl+'" id="audio"  controlsList="nodownload" controls >audio element not supported</audio>')
            $('#payer-div').html($audio);
            audio = $('#audio')[0];
            audio.crossOrigin = "anonymous";
            audio.volume = 0.5;
            return audio;
        }
        function closeListen(){
            audio.pause();
            $('#header').hide()
        }
        function listen(fileUrl, info) {
            $('#header').show()
            audio = getAudio(fileUrl, info)
            init(audio);
            audio.play();
        }

        function collect(musicId){
            $.ajax({
                type:'get',
                url: '${ctx}/collect?musicId='+musicId,
                dataType: 'json',
                success: function (res){
                    let code = res.code
                    if(code === 2000){
                        alert('success')
                    }
                }
            })
        }
    </script>

</head>

<body>

    <div id="outer-container">
        <div id="container">
            <div id="top">
                <div id="logo" style="">
                    <span>Cloud music</span>
                </div>
                <div id="nav">
                    <ul id="topnav" class="sf-menu">
                        <li><a href="${ctx}/index" class="current">Home</a></li>
                        <li><a href="${ctx}/music">Discovery</a></li>
                        <li><a href="${ctx}/my">My music</a></li>
                        <c:choose>
                            <c:when test="${sessionScope.USER_SESSION != null && sessionScope.USER_SESSION.userType=='M'}">
                                <li><a href="${ctx}/mgr">Manage</a></li>
                            </c:when>
                        </c:choose>
                        <li>
                            <c:choose>
                                <c:when test="${sessionScope.USER_SESSION != null}">
                                    <span style="font-size: 12px;color: dodgerblue">hello, ${sessionScope.USER_SESSION.nick} </span>
                                    <span onclick="location.href='${ctx}/logout'" style="font-size: 12px;color: brown;cursor: pointer;margin-left: 6px;">sign out</span>
                                </c:when>
                                <c:otherwise>
                                    <a href="${ctx}/login" style="font-size: 12px">sign in</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ul>

                </div>
            </div>

            <div id="header">
                <div class="listen-song">
                    <canvas id="canvas" width="800" height="100"></canvas>
                    <div id="payer-div">

                    </div>
                </div>
            </div>
            <div id="roll-container">

                <h2 class="title_pattern uppercase"><span style="padding-left: 40px;color: #dc6a4d">Hot Music</span></h2>
                <div id="roll">
                    <ul id="roll-ul">
                        <c:forEach var="item" items="${hostMusicList}">
                        <li class="roll-li">
                            <img class="roll-img" src="${ctx}/file/${item.albumCoverUrl}" alt="empty">
                        </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>


            <div id="content">
                <c:forEach var="item" items="${musicList}">
                    <div class="main">
                        <h2 class="title_pattern uppercase"><span>${item.musicCategory.categoryName}</span></h2>
                        <ul class="recentpost">
                            <c:forEach var="music" items="${item.musicList}">
                                <li>
                                    <c:choose>
                                        <c:when test="${music.imgUrl!=''}">
                                            <img src="${ctx}/file/${music.imgUrl}" alt="" class="frame" />
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${ctx}/images/content/pic2.jpg" alt="" class="frame" />
                                        </c:otherwise>
                                    </c:choose>

                                    <span class="shadowimg220"></span>
                                    <c:if test="${music.fileUrl!=''}">
                                        <a href="javascript:listen('${music.fileUrl}', '${music.musicName}')" class="listen">Listen</a>
                                    </c:if>
                                    <a href="javascript:collect('${music.id}')" class="listen">Collect</a>
                                    <span>${music.singer}-${music.musicName}</span>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="clear"></div>

                    </div>
                </c:forEach>

            </div>

        </div>
    </div>

<script>
    let offset = 0;
    let timer = null;
    let rollUlWidth = $('#roll-ul').innerWidth();
    function autoPlay() {
        timer = setInterval(function() {
            let $rollUl = $('#roll-ul')
            offset += -10;
            if (offset <= -rollUlWidth) {
                offset = 0;
            }
            $rollUl.css("marginLeft", offset);
        }, 150);
    }
    autoPlay();
    $(".roll-li").hover(function() {
        clearInterval(timer);
        $(this).siblings().fadeTo(100, 0.5);
        $(this).fadeTo(100, 1);
    }, function() {
        autoPlay();
        $(".roll-li").fadeTo(100, 1);
    });
</script>
</body>



</html>