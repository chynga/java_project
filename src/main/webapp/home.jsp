<%@ page import="com.egadgets.demo3.model.User" %>
<%@ page import="java.util.List" %>
<jsp:include page="header.jsp"/>
<div class="col-lg-6">
        <strong>Listing users</strong>
        <hr/>
        <table border="1">
            <thead>
            <th>User ID </th>
            <th>Username </th>
            <th>Email </th>
            </thead>
            <%
                List<User> users = (List) request.getAttribute("users");
                for (int i = 0; i < users.size(); i++){
                    out.print("<tr>");
                    out.print("<td>"+ users.get(i).getId()+"</td>");
                    out.print("<td>"+ users.get(i).getUsername()+"</td>");
                    out.print("<td>"+ users.get(i).getEmail()+"</td>");
                    out.print("</tr>");
                }

            %>
        </table>
</div>
<jsp:include page="footer.jsp"/>