<%@ page import="java.util.ArrayList" %>
<%@ page import="com.egadgets.demo3.model.Product" %>

<jsp:include page="../header.jsp"/>

    <div class="container">
        <h1>All Products</h1>
        <div class="row">
            <%
                ArrayList<Product> products = (ArrayList) request.getAttribute("products");
                Integer pages = (Integer) request.getAttribute("pages");
                String selectedPage = request.getParameter("p");
                System.out.println(selectedPage);
                int selectedPageInt = 1;
                if (selectedPage != null) {
                    selectedPageInt = Integer.parseInt(selectedPage);
                }
            %>

            <%
                if (products != null) {
                    for (int i = 0; i < products.size(); i++) {
                        String productImageURL = null;
                        try {
                            productImageURL = products.get(i).getImage().getImageUrl();
                        } catch (Exception e) {
                            productImageURL = "data:image/jpeg;base64,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";
                        }
                        out.print("<div class=\"card col-sm-4\">");

                        out.print("<a href='" + request.getContextPath() + "/products/show?id=" + products.get(i).getId() +"'>");
                        out.print("<img src=" + productImageURL + " class=\"card-img-top\" height=\"400\">");
                        out.print("</a>");
                        out.print("<div class=\"card-body\">");
                        out.print("<h5 class=\"card-title\">" + products.get(i).getProductName() + "</h5>");
                        out.print("<p class=\"card-text\">" + products.get(i).getDescription() + "</p>");
                        out.print("</div>");
                        out.print("<ul class=\"list-group list-group-flush\">");
                        out.print("<li class=\"list-group-item\">Price: " + products.get(i).getPrice() + "$</li>");
                        out.print("</ul>");

                        out.print("</div>");
                    }
                } else {
                    out.print("ERRrrror");
                }
            %>
        </div>

        <div class="row justify-content-md-center">
            <div class="pagination mt-5 mb-5 col-md-auto">
                <%
                    out.print("<a href='http://localhost:8080/demo3_war_exploded/products?p=1'>&laquo;</a>");
                    for (int i = 1; i <= pages; i++) {
                        out.print("<a class='" + (selectedPageInt == i ? "active" : "") + "'" +
                                " href='http://localhost:8080/demo3_war_exploded/products?p=" + i + "'>" +
                                i + "</a>");
                    }
                    out.print("<a href='http://localhost:8080/demo3_war_exploded/products?p=" + pages + "'>&raquo;</a>");
                %>
            </div>
        </div>
    </div>
<jsp:include page="../footer.jsp"/>