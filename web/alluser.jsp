<%-- 
    Document   : alluser
    Created on : Jun 6, 2018, 4:41:10 PM
    Author     : thuyento
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
  </head>
  <body>
    <div style="margin: 0px 0px;padding: auto; display: inline;">
    <c:set var="data" value="${sessionScope.USERS}" scope="page" />
    <c:set var="roles" value="${sessionScope.ROLES}" scope="page" />
    <input type="button" name="btnAddNewUser" value="Add User" onclick="" />
    <table border="1" style="padding: auto;margin: auto">
      <thead>
        <tr>
          <th colspan="2"style="width: 64px;">No</th>                        
          <th>Username</th>
          <th>Upass</th>
          <th>Role</th>
          <th>Access</th>
          <th>Manager</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="user" items="${data}" varStatus="count" begin="0" end="19" >
          <tr>
            <td style="text-align: center">${count.index + 1}</td>
            <td style="text-align: center"><input type="checkbox" name="multipleEdit" value="" /></td>
            <td><b>${user.uname}</b></td>
            <td>
              <input type="button" name="btnResetPassword" value="Reset Password" onclick="" />
            <td>        
              <select name="listRoles" >
                <c:forEach var="role" items="${roles}" >
                  <option 
                    <c:if test="${role.value eq user.urole}" > 
                      selected="true"
                    </c:if> >
                    ${role.value}
                  </option>
                </c:forEach>      
              </select>
            </td>
            <td style="text-align: center">
              <span style="color: greenyellow;font-weight: bold">
                <c:if test="${not user.access}">
                </span><span style="color: red">Not 
                </c:if>
                Accessed
              </span>
            </td>
            <td>
              <input type="hidden" value="${user.uid}" name="uid" />
              <input type="button" name="btnMoreInfo" value="More Info" onclick="" />
              <input type="button" name="btnDelete" value="Detele" onclick="" />
              <input type="button" name="btnUpdate" value="Update" onclick="" disabled="true"/>
            </td>
          </tr>
        </c:forEach>
        <tr>
          <td colspan="3" style="text-align: center">
            Select All 
            <input type="checkbox" name="SelectAll" value="" />
          </td>
          <td>
            <input type="button" name="btnResetPassword" value="Reset Password" onclick="" />
          </td>
          <td>
            <select name="listRoles" >
              <c:forEach var="role" items="${roles}" >
                <option 
                  <c:if test="${role.value eq 'User'}" > 
                    selected="true"
                  </c:if> >
                  ${role.value}
                </option>
              </c:forEach>      
            </select>
          </td>
          <td style="text-align: center">
            <span style="color: red;font-weight: bold">Not Accessed</span>
            <input type="checkbox" name="notAccessed" value="" />
          </td>
          <td style="text-align: center">
            <input type="button" name="btnDelete" value="Detele" onclick="" disabled="true"/>
            <input type="button" name="btnUpdate" value="Update" onclick="" disabled="true"/>
          </td>            
        </tr>
      </tbody>
    </table>


    </div>
  </body>
</html>
