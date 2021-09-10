<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- JSTL core and functions tag library--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<c:set var="title" value="Registration"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">


    <%-- HEADER --%>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%-- HEADER --%>

        <tr>
            <td class="content center">
                <form id="login_form" action="controller" method="post">

                    <input type="hidden" name="command" value="registration"/>
                    <fieldset>
                        <legend>First name</legend>

                        <input type="first_name" class="form-control" name="first name"
                               placeholder="Enter First name"/><br/>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend>Last name</legend>

                        <input type="last_name" class="form-control" name="last name"
                               placeholder="Enter Last name"/><br/>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend>Login</legend>

                        <input type="login" class="form-control" name="login"
                               placeholder="Enter Login"/>
                    </fieldset>
                    <br/>
                    <fieldset>
                        <legend>Password</legend>

                        <input type="password" class="form-control" name="password"
                               placeholder="Enter Password"/>
                    </fieldset>
                    <br/>

                    <input type="submit" value="Registration">
                </form>

            </td>
        </tr>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>