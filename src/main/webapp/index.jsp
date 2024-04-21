<%@ page import="web.internet.magazin.project2mini.repo.OrderRepo" %>
<%@ page import="web.internet.magazin.project2mini.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="web.internet.magazin.project2mini.entity.enums.Status" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: anas
  Date: 18/04/24
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders CRUD</title>
    <link href="static/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    OrderRepo orderRepo = new OrderRepo();
    List<Order> orders = new ArrayList<>();
    String status = request.getParameter("status");
    if (status != null) {
        if (status.equals("open")) {
            orders = orderRepo.findAll().stream().filter(order -> order.getStatus().equals(Status.OPEN)).toList();
        } else if (status.equals("inProgress")) {
            orders = orderRepo.findAll().stream().filter(order -> order.getStatus().equals(Status.IN_PROGRESS)).toList();
        } else if (status.equals("completed")) {
            orders = orderRepo.findAll().stream().filter(order -> order.getStatus().equals(Status.COMPLETED)).toList();
        }
    } else {
        orders = orderRepo.findAll();
    }
%>

<div class="row">
    <div class="col-10 offset-1">
        <div class="d-flex offset-3">
            <form role="search" action="/" method="get">
                <button class="btn btn-outline-success ms-4" name="status" value="open" type="submit">Open</button>
                <button class="btn btn-outline-success ms-4" name="status" value="inProgress" type="submit">In Progress
                </button>
                <button class="btn btn-outline-success ms-4" name="status" value="completed" type="submit">Completed
                </button>
                <button class="btn btn-outline-success ms-4" type="submit">Clear</button>
            </form>
            <a class="btn btn-outline-success ms-4 mb-3 " type="button" href="/login.jsp">Login</a>
        </div>
        <div class="pe-4 pt-4">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Order Id</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>User</th>
                </tr>
                </thead>
                <tbody>
                <% for (Order order : orders) {%>
                <tr style="vertical-align: middle">
                    <td><%= order.getId()%>
                    </td>
                    <td><%= order.getSimpleDate()%>
                    </td>
                    <td><%=order.getStatus()%>
                    </td>
                    <td><%=order.getUser().getName()%>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
