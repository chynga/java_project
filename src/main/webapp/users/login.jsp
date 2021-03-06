
<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 offset-md-3 col-xl-4 offset-xl-4">
                <div class="card shadow">
                    <div class="card-body">
                        <h5 class="card-title">Login</h5>
                        <form action="${ pageContext.request.contextPath }/login" method="POST" class="validated-form" novalidate>
                            <div class="mb-3">
                                <label class="form-label" for="email">Email</label>
                                <input class="form-control" type="email" id="email" name="email" autofocus required>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label" for="password">Password</label>
                                <input class="form-control" type="password" id="password" name="password" required>
                                <div class="valid-feedback">
                                    Looks good!
                                </div>
                            </div>
                            <button class="btn btn-success btn-block">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../footer.jsp"/>