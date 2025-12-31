<%@page import="java.util.List" %>
    <%@page import="Model.Patient" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Patient List</title>
                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
                <link rel="stylesheet"
                    href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
                <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
                    id="bootstrap-css">
                <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
                <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                <link rel="stylesheet" type="text/css" href="css/adddataform.css">
                <link rel="stylesheet" type="text/css" href="css/adddatafrm1.css">
                <link rel="stylesheet" type="text/css" href="css/table.css">
                <style>
                    body {
                        background-image: url("img/Medical.jpg");
                        background-color: #cccccc;
                    }

                    .serchbar {
                        width: 60%;
                        height: 5%;
                        margin-top: 2%;
                        margin-left: 100px;
                        margin-bottom: 0%;
                    }

                    .search {
                        width: 40%;
                        height: 40px;
                        border-radius: 10px;
                    }

                    .text-center {
                        color: grey;
                        padding: 10px;
                        margin-top: 0px;
                    }

                    input {
                        text-align: center;
                    }

                    ::-webkit-input-placeholder {
                        text-align: center;
                    }

                    :-moz-placeholder {
                        text-align: center;
                    }

                    .mybutton {
                        display: inline;
                    }
                </style>
            </head>

            <body>
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <a href="#" class="navbar-brand"> <img src="img/2855.jpeg" height="30" width="100"
                            alt="HospitalManagementSystem">
                    </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <h3>
                            <b>Hospital Management System</b>
                        </h3>
                        <ul class="navbar-nav ml-auto" style="margin-right: 70px;">

                            <li class="nav-item active">
                                <a class="nav-link" href="AdminHome.jsp">Home <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    PATIENT
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="addpatient.jsp">Add Patient</a>
                                    <a class="dropdown-item" href="adminPatientList.jsp">Patient List</a>
                                </div>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    DOCTOR
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="addDoctor.jsp">Add Doctor</a>
                                    <a class="dropdown-item" href="adminDoctorList.jsp">View Doctor</a>
                                </div>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    RECEPTIONIST
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="addRecp.jsp">Add Receptonist</a>
                                    <a class="dropdown-item" href="adminRecpList.jsp">View Receptonist</a>
                                </div>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    WORKER
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="addWorker.jsp">Add Worker</a>
                                    <a class="dropdown-item" href="adminWorkerList.jsp">View Worker</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>

                <div class="serchbar">
                    <form action="PatientServlet" method="get">
                        <input type="hidden" name="action" value="list">
                        <input class="search" type="text" name="search" placeholder="Search Here..." />
                    </form>
                </div>




                <div>
                    <div class="container-table100">
                        <div class="wrap-table100">
                            <div class="table100 ver3 m-b-110">
                                <div class="table100-head">
                                    <table>
                                        <thead>
                                            <tr class="row100 head">
                                                <th class="cell100 column1">First Name</th>
                                                <th class="cell100 column2">Last Name</th>
                                                <th class="cell100 column3">Gender</th>
                                                <th class="cell100 column4">City</th>
                                                <th class="cell100 column5">Email</th>
                                                <th class="cell100 column6">Age</th>
                                                <th class="cell100 column7">Address</th>
                                                <th class="cell100 column8">Date</th>
                                                <th class="cell100 column9">Mobile</th>
                                                <th class="cell100 column10">Action</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <% java.util.List<Model.Patient> patients = (java.util.List<Model.Patient>)
                                        request.getAttribute("patients");

                                        if (patients == null) {
                                        response.sendRedirect("PatientServlet?action=list");
                                        return;
                                        }

                                        for(Model.Patient p : patients) {
                                        %>
                                        <div class="table100-body js-pscroll">
                                            <table>
                                                <tbody>
                                                    <tr class="row100 body">
                                                        <td class="cell100 column1">
                                                            <%=p.getFname()%>
                                                        </td>
                                                        <td class="cell100 column2">
                                                            <%=p.getLname()%>
                                                        </td>
                                                        <td class="cell100 column3">
                                                            <%=p.getGender()%>
                                                        </td>
                                                        <td class="cell100 column4">
                                                            <%=p.getCity()%>
                                                        </td>
                                                        <td class="cell100 column5">
                                                            <%=p.getEmail()%>
                                                        </td>
                                                        <td class="cell100 column6">
                                                            <%=p.getAge()%>
                                                        </td>
                                                        <td class="cell100 column7">
                                                            <%=p.getAddress()%>
                                                        </td>
                                                        <td class="cell100 column8">
                                                            <%=p.getDate()%>
                                                        </td>
                                                        <td class="cell100 column9">
                                                            <%=p.getMobile()%>
                                                        </td>
                                                        <td class="cell100 column10">
                                                            <a href="updatePatient.jsp?mob=<%=p.getMobile()%>">Edit</a>
                                                            &nbsp;&nbsp;
                                                            <a href="PatientServlet?action=delete&mob=<%=p.getMobile()%>"
                                                                onclick="return confirm('Are you sure?')">Delete</a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                                <% } %>
                                            </table>
                                        </div>
                                        </table>
                            </div>
                        </div>
                    </div>
                </div>
                </div>



            </body>

            </html>