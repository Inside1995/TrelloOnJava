<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trello on Java + Spring</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/board.css">
    <script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        function createList() {
            $("#add-list").attr("style", "display:none;");
            $("#add-list-panel").attr("style", "");
        };

        function hideList() {
            $("#add-list-panel").attr("style", "display:none;");
            $("#add-list").attr("style", "");
        };

        $(document).ready(function () {
            $(".done_button").click(function () {
                var item = $(this).closest("div");
                var inputChild = $(item).find("input");
                var actionId = $(inputChild).attr("value");
                var finished = (item.attr("style") == undefined || item.attr("style") === "") ? true : false;
                $.ajax({
                    url: '/done_action',
                    data: {
                        action_id: actionId,
                        done: finished
                    },
                    method: 'POST',
                    success: function () {
                        if (item.attr("style") == undefined || item.attr("style") === "") {
                            item.css({
                                'opacity': '0.35',
                                'box-shadow': 'rgba(0, 0, 0, 0.16) 0px 6px 6px rgba(0, 0, 0, 0.23) 0px 6px 6px',
                                'text-decoration': 'line-through',
                                'background-color': 'rgb(222, 202, 255)'
                            });
                        } else {
                            item.attr("style", "");
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<div id="container">
    <jsp:include page="../parts/header.jsp"/>
    <div id="content">
        <div id="board-name">
            <h1>${board.name}</h1>
        </div>
        <div id="add-list" onclick="createList()">
            <h4>Add a list...</h4>
        </div>
        <div id="add-list-panel" style="display: none;">
            <form action="/create_list" method="post">
                <input type="text" name="list_name">
                <input type="hidden" name="board_id" value="${board.id}">
                <a onclick="hideList()" href="#"><img src="/resources/images/close-edit.png"
                                                      style="width: 25px; height: 25px;">
                </a>
                <br/>
                <input type="submit" value="SUBMIT">
            </form>
        </div>
        <c:if test="${lists ne null}">
            <table id="lists-table">
                <tr>
                    <c:forEach var="list" items="${lists}">
                        <td>
                            <div class="list">
                                <a href="/delete_list/?id=${list.id}" style="float: right;"><img src="/resources/images/close_board.png" style="height: 20px;
							                        width: 20px;"></a>
                                <h4>${list.name}</h4>
                                <hr>
                                <form action="/create_action" method="post">
                                    <input type="text" name="description">
                                    <input type="hidden" name="list_id" value="${list.id}">
                                </form>
                                <c:if test="${list.actions ne null}">
                                    <c:forEach var="action" items="${list.actions}">
                                        <div class="item" <c:if test="${action.done eq true}">
                                            style="opacity: 0.35;
                                            box-shadow: rgba(0, 0, 0, 0.16) 0px 6px 6px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
                                            text-decoration: line-through;
                                            background-color: rgb(222, 202, 255);"
                                        </c:if>>
                                            <div class="item-wrapper">
                                                <h3>${action.description}</h3>
                                                <input type="hidden" value="${action.id}">
                                                <a href="#" class="done_button">✓</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </td>
                    </c:forEach>
                </tr>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
