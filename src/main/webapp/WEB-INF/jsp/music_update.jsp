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





        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            border: 1px solid #ECECEC;
            padding: 8px;
            word-wrap: break-word;
        }

        td:first-child {
            width: 30%;
        }

        input[type='submit']{
            width: 100px;
            height: 32px;
        }
        input[type='text'], select{
            height: 30px;
            padding-left: 6px;
            width: 200px;
            border-radius: 4px;
            border: 1px solid;
        }
        #err{
            color:red;
        }

    </style>


    <script type="text/javascript">
        $(document).ready(function () {

            var $song = $('.listen-song')
            $song.hide()

            //getAudio();


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
                    <li><a href="${ctx}/my">My music</a></li>
                    <c:choose>
                        <c:when test="${sessionScope.USER_SESSION != null  && sessionScope.USER_SESSION.userType=='M'}">
                            <li><a href="${ctx}/mgr" class="current">Manage</a></li>
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

        <div id="content">
            <div class="main">
                <h2 class="title_pattern uppercase"><span>Music</span><a href="${ctx}/mgr" style="margin-left: 30px">back</a></h2>

                <div>
                    <form class="form" action="${ctx}/music/update" onsubmit="return formSubmit()" method="post" enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td>Category</td>
                                <td>
                                    <label>
                                        <input type="hidden" id="id" name="id" value="${music.id}" />
                                        <select id="category" name="categoryId">
                                            <c:forEach var="item" items="${category}">
                                            <option value="${item.id}"  <c:if test="${item.id == music.categoryId}">selected</c:if>  >${item.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>Song</td>
                                <td>
                                    <label>
                                        <input type="text" name="musicName" id="musicName" value="${music.musicName}">
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Singer
                                </td>
                                <td>
                                    <label>
                                        <input type="text" name="singer" id="singer" value="${music.singer}">
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>Song Album Cover</td>
                                <td>
                                    <label >
                                        <input type="file" name="albumCover" id="albumCover" > 400*300
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>Singer Image</td>
                                <td>
                                    <label>
                                        <input type="file" id="imageFile" name="imageFile"> 150*120
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td>Song File</td>
                                <td>
                                    <label>
                                        <input type="file" id="songUrl" name="songUrl">
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="Save"> <span id="err"></span>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="clear"></div>
            </div>

        </div>

    </div>
</div>

<script>
    function getFileExtension(filename) {
        return filename.slice((filename.lastIndexOf('.') - 1 >>> 0) + 2);
    }
    let imageExtList = ['jpg', 'png', 'gif', 'jpeg']
    function formSubmit(){
        let category = $('#category').val()
        let musicName = $('#musicName').val()
        let singer = $('#singer').val()
        let imageFile = $('#imageFile').val()
        let songUrl = $('#songUrl').val()

        if(!category){
            $('#err').text('Please select category');
            return false;
        }
        if(!musicName){
            $('#err').text('Please input the song name');
            return false;
        }
        if(!singer){
            $('#err').text('Please int the singer');
            return false;
        }

        let id = $('#id').val()

        if(!id){
            if(!imageFile){
                $('#err').text('Please upload image file(jpg、png、gif、jpeg)');
                return false;
            }else{
                let ext = getFileExtension(imageFile)
                let inArr = imageExtList.includes(ext.toLowerCase())
                if(!inArr){
                    $('#err').text('Please upload image file(jpg、png、gif、jpeg)');
                    return false;
                }
            }

            if(!songUrl){
                $('#err').text('Please upload music file(mp3)');
                return false;
            }else{

                let ext = getFileExtension(songUrl)
                if(ext.toLowerCase() !== 'mp3'){
                    $('#err').text('Please upload music file(mp3)');
                    return false;
                }
            }
        }



    }

</script>

</body>

</html>