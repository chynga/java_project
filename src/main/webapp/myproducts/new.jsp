<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center">New Product</h1>
            <form action="${ pageContext.request.contextPath }/myproducts/new" method="GET" novalidate class="validated-form" enctype="multipart/form-data">
                <div class="mb-3">
                    <label class="form-label" for="name">Name</label>
                    <input class="form-control" type="text" id="name" name="name" required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="price">Price</label>
                    <div class="input-group">
                        <span class="input-group-text" id="price-label">$</span>
                        <input type="text" class="form-control" id="price" placeholder="0.00" aria-label="price"
                               aria-describedby="price-label" name="price" required>
                    </div>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label" for="description">Description</label>
                    <textarea class="form-control" type="text" id="description" name="description"
                              required></textarea>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="image">Image URL</label>
                    <input class="form-control" type="text" id="image" name="image" required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                </div>
<%--                <div class="mb-3">--%>
<%--                    <div class="form-file custom-file">--%>
<%--                        <input type="file" class="form-file-input" id="image" name="image" multiple>--%>
<%--                        <label class="form-file-label" for="image">--%>
<%--                            <span class="form-file-text custom-file-label">Choose image(s)...</span>--%>
<%--                            <span class="form-file-button">Browse</span>--%>
<%--                        </label>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="mb-3">
                    <button class="btn btn-success">Add Product</button>
                </div>
            </form>
            <a href="/campgrounds">All Products</a>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>