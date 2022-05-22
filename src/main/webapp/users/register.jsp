<%@ page import="java.util.ArrayList" %>
<%@ page import="com.egadgets.demo3.model.City" %>
<jsp:include page="../header.jsp"/>

<%
  ArrayList<City> cities = (ArrayList) request.getAttribute("cities");
%>

<div class="container">
  <div class="container mt-5 mb-5">
    <div class="row">
      <div class="col-md-6 offset-md-3 col-xl-4 offset-xl-4">
        <div class="card shadow">
          <div class="card-body">
            <h5 class="card-title">Register</h5>
            <form action="${ pageContext.request.contextPath }/register" method="POST" class="validated-form" novalidate>
              <div class="mb-3">
                <label class="form-label" for="username">Username</label>
                <input class="form-control" type="text" id="username" name="username" required autofocus>
                <div class="valid-feedback">
                  Looks good!
                </div>
              </div>
              <div class="mb-3">
                <label class="form-label" for="email">Email</label>
                <input class="form-control" type="email" id="email" name="email" required>
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
              <div class="mb-3">
                <label class="form-label" for="firstname">First Name</label>
                <input class="form-control" type="text" id="firstname" name="firstname" required>
                <div class="valid-feedback">
                  Looks good!
                </div>
              </div>
              <div class="mb-3">
                <label class="form-label" for="lastname">Last Name</label>
                <input class="form-control" type="text" id="lastname" name="lastname" required>
                <div class="valid-feedback">
                  Looks good!
                </div>
              </div>
              <div class="mb-3">
                <label class="form-label" for="phone">Phone Number</label>
                <input class="form-control" type="text" id="phone" name="phone" required>
                <div class="valid-feedback">
                  Looks good!
                </div>
              </div>
              <div class="mb-3">
                <select class="custom-select" name="city_id" aria-label="Default select example">
                  <%
                    for (City city : cities) {
                      out.print("<option value='" + city.getId() + "'>" + city.getCityName() + "</option>");
                    }
                  %>
                </select>
              </div>
              <button type="submit" class="btn btn-success btn-block">Register</button>
            </form>

          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="../footer.jsp"/>