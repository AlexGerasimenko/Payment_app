<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- JSTL core and functions tag library--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<c:set var="title" value="Login"/>
<c:set var="currentLocale" value="en" scope="session"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">

    <%-- HEADER--%>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <%-- HEADER --%>

    <tr>
        <td class="content center">
            <form id="login_form" action="controller" method="post">

                <input type="hidden" name="command" value="login"/>

                <fieldset>
                    <legend>Login</legend>
                    <input name="login"/><br/>
                </fieldset>
                <br/>
                <fieldset>
                    <legend>Password</legend>
                    <input type="password" name="password"/>
                </fieldset>
                <br/>

                <input type="submit" value="Login"><br/>
                <input type='submit' value='Registration' formAction='registration.jsp'/>

            </form>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>


</table>
</body>
</html>