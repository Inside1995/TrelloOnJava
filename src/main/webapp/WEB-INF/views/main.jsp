<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trello on Java + Spring</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("img[id]")
                .mouseover(function () {
                    $("#logoIMG").attr("src", '/resources/images/rotate.png');
                    $("#logoIMG").attr("style", 'height: 70px; width: 70px; filter: drop-shadow(6px 6px 6px #333);');
                });
            $("img[id]")
                .mouseout(function () {
                    $("#logoIMG").attr("src", '/resources/images/original.png');
                    $("#logoIMG").attr("style", 'height: 65px; width: 65px; filter: drop-shadow(3px 3px 3px #333);');
                });
        });

        function createNewBoard() {
            $("#new-board").attr("style", "display:none;");
            $("#create-new-board").attr("style", "");
        };

        function hideCreateBlock() {
            $("#create-new-board").attr("style", "display: none;");
            $("#new-board").attr("style", "");
        };
    </script>
</head>
<body>
<div id="container">
    <jsp:include page="../parts/header.jsp"/>
    <div id="content">
        <div class="border-wrapper">
            <div id="new-board" onclick="createNewBoard()">
                <h2>Create new board</h2>
            </div>
            <div id="create-new-board" style="display: none;">
                <div id="top-panel">
                    <h3 style="display: inline;">Creating board</h3>
                    <a href="#"><img onclick="hideCreateBlock()" src="/resources/images/close.png"
                                     style="width: 20px; height: 20px;"></a>
                </div>
                <form action="/create_board" method="post">
                    <p>What shall we call the board?</p>
                    <input type="text" name="board_name">
                    <a href="#" onclick="hideCreateBlock()" id="cancel_button">Cancel</a>
                    <input type="submit" value="CREATE">
                </form>
            </div>
        </div>
        <c:if test="${allBoards ne null}">
            <table>
                <c:forEach var="board" items="${allBoards}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.count % 2 ne 0}">
                            <tr>
                            <td>
                                <div class="new_block">
                                    <a class="close" href="/delete_board/?id=${board.id}"
                                       style="position: relative;
	                                        left: 150px;"><img
                                            src="/resources/images/close_board.png" style="width: 20px; height: 20px;"></a>
                                    <a href="/show_board/?id=${board.id}">
                                        <h2 style="color: black;
                                                word-break: break-all;
                                                padding: 15px;
                                                margin-top: 0;">${board.name}</h2>
                                    </a>
                                </div>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <div class="new_block">
                                    <a class="close" href="/delete_board/?id=${board.id}"
                                       style="position: relative;
	                                        left: 150px;"><img
                                            src="/resources/images/close_board.png" style="width: 20px; height: 20px;"></a>
                                    <a href="/show_board/?id=${board.id}">
                                        <h2 style="color: black;
                                            word-break: break-all;
                                            padding: 15px;
                                            margin-top: 0;">${board.name}</h2>
                                    </a>
                                </div>
                            </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${allBoards.size() % 2 ne 0}">
                    </tr>
                </c:if>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
