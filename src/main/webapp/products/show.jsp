<%@ page import="com.egadgets.demo3.model.Product" %>
<%@ page import="com.egadgets.demo3.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.egadgets.demo3.model.Message" %>
<%@ page import="com.egadgets.demo3.model.Writer" %>
<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="row">
        <%
            Product product = (Product) request.getAttribute("product");
            User seller = (User) request.getAttribute("seller");
            ArrayList<Message> chat = (ArrayList<Message>) request.getAttribute("chat");
        %>

        <div class="card col-sm-6" style="width: 18rem;">
            <%
                if (product != null) {
                    String productImageURL = null;
                    try {
                        productImageURL = product.getImage().getImageUrl();
                    } catch (Exception e) {
                        productImageURL = "data:image/jpeg;base64,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";
                    }

                    out.print("<img src=" + productImageURL + " class=\"card-img-top\">");
                    out.print("<div class=\"card-body\">");
                    out.print("<h5 class=\"card-title\">" + product.getProductName() + "</h5>");
                    out.print("<p class=\"card-text\">" + product.getDescription() + "</p>");
                    out.print("</div>");
                    out.print("<ul class=\"list-group list-group-flush\">");
                    out.print("<li class=\"list-group-item\">Price: " + product.getPrice() + "$</li>");
                    out.print("</ul>");

                    out.print("<div class=\"card-body\">");
                    out.print("<h5 class=\"card-title\">Owner: " + seller.getUsername() + "</h5>");
                    out.print("<p class=\"card-text\">Phone number: " + seller.getPhoneNumber() + "</p>");
                    out.print("<p class=\"card-text\">Email: " + seller.getEmail() + "</p>");
                    out.print("</div>");
                } else {
                    out.print("ERRrrror");
                }
            %>
        </div>

        <div class="card col-sm-6" style="width: 18rem;">
            <h2>Write to owner</h2>
            <form action="${pageContext.request.contextPath}/products/show" method="POST" class="mb-3 validated-form" novalidate>
                <input type="hidden" name="id" value="${product.getId()}">
                <div class="mb-3">
                    <label class="form-label" for="body">Write Your Text</label>
                    <textarea class="form-control" id="body" name="body" cols="30" rows="3" required></textarea>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                </div>
                <button class="btn btn-success">Send</button>
            </form>

            <div class="card mb-3 ">
                <div class="card-body">
                    <%
                        if (chat != null) {
                            for (Message message : chat) {
                                if (message.getWriter() == Writer.SELLER) {
                                    out.print("<h6 class=\"card-subtitle text-muted\">Owner</h6>");
                                    out.print("<p class=\"card-text\">" + message.getSentence() + "</p>");
                                } else if (message.getWriter() == Writer.CUSTOMER) {
                                    out.print("<h6 class=\"card-subtitle text-muted text-right\">Me</h6>");
                                    out.print("<p class=\"card-text text-right\">" + message.getSentence() + "</p>");
                                }
                            }
                        } else {
                            out.print("ERRrrror");
                        }
                    %>
<%--                    <h6 class="card-subtitle text-muted">Owner</h6>--%>
<%--                    <p class="card-text">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>--%>

<%--                    <h6 class="card-subtitle text-muted text-right">Me</h6>--%>
<%--                    <p class="card-text text-right">Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>