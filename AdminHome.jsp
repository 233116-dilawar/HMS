<%@page import="java.sql.Connection" %>
    <%@page import="java.sql.ResultSet" %>
        <%@page import="java.sql.Statement" %>
            <%@page import="Database.DatabaseConnection" %>
                <%@page contentType="text/html" pageEncoding="UTF-8" %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                        <title>Admin Home</title>
                        <link rel="stylesheet"
                            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
                        <style>
                            body {
                                background-color: #cccccc;
                            }

                            .card-block {
                                padding: 20px;
                                color: white;
                            }
                        </style>
                    </head>

                    <body>
                        <% if(session.getAttribute("username")==null) { response.sendRedirect("index.jsp"); return; } %>
                            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                                <a class="navbar-brand" href="#">HMS Admin</a>
                                <div class="collapse navbar-collapse">
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item">
                                            <a class="nav-link" href="#">Welcome, <%=session.getAttribute("username")%>
                                                    </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link btn btn-danger text-white" href="Logout">Logout</a>
                                        </li>
                                    </ul>
                                </div>
                            </nav>

                            <div class="container-fluid mt-4">
                                <div class="row">
                                    <% Connection con=null; try { con=DatabaseConnection.initializeDatabase(); Statement
                                        st=con.createStatement(); String q1="select count(*) from patient" ; ResultSet
                                        rs=st.executeQuery(q1); if(rs.next()) { %>
                                        <div class="col-md-3">
                                            <div class="card bg-success text-white">
                                                <div class="card-body text-center">
                                                    <h1>
                                                        <%= rs.getInt(1) %>
                                                    </h1>
                                                    <h6>Patients</h6>
                                                    <a href="adminPatientList.jsp" class="text-white">View</a>
                                                </div>
                                            </div>
                                        </div>
                                        <% } rs.close(); String q2="select count(*) from doctor" ;
                                            rs=st.executeQuery(q2); if(rs.next()) { %>
                                            <div class="col-md-3">
                                                <div class="card bg-danger text-white">
                                                    <div class="card-body text-center">
                                                        <h1>
                                                            <%= rs.getInt(1) %>
                                                        </h1>
                                                        <h6>Doctors</h6>
                                                        <a href="adminDoctorList.jsp" class="text-white">View</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <% } rs.close(); con.close(); } catch (Exception e) { out.println("Error: " + e.getMessage());
}
%>
</div>
</div>
</body>
</html>