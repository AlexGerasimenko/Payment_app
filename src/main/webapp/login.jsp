<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%-- JSTL core and functions tag library--%>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>


<html>

<c:set var="title" value="Login"/>
<c:set var="currentLocale" value="en" scope="session"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">

    <%-- HEADER--%>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <style>
        body {
            margin: 0;
        }

        /* Style the header */
        .header {
            background-color: #ffe600;
            padding: 2px;
            text-align: center;
        }

        h1 {
            color: white;
            text-shadow: 1px 1px 2px black, 0 0 25px blue, 0 0 5px darkblue;
        }


    </style>
    <div class="header">
        <h1>Welcome</h1>
        <p>Enter to the application Payment or register</p>

        <%-- HEADER --%>


        <tr>
            <td class="content center">
                <form id="login_form" action="controller" method="post">

                    <input type="hidden" name="command" value="login"/>

                    <fieldset>
                        <legend>Login</legend>
                        <input type="login" class="form-control" name="login"
                               placeholder="Enter Login">
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend>Password</legend>
                        <input type="password" class="form-control" name="password"
                               placeholder="Enter Password">
                    </fieldset>
                    <br/>


                    <button type="submit" class="btn btn-primary">Login</button>

                    <input type='submit' value='Registration' formAction='registration.jsp'/>

                </form>
            </td>
        </tr>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>


</table>
</body>
</html>