<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" /> 
<script type="text/javascript" src="javascript.js"></script>

<header>
	<a href="homePage.jsp"><img class="logo-image" src="images/logo.png"/></a>
</header>

<body onload="init()">
<% if (!user.isLoggedIn()) { %>
	<p align="right"><a href="loginPage.jsp">Login</a> / <a href="editAccountPage.jsp">Create account</a></p>
<%} else {%>
	<p align="right"> Hello, <%=user.getName()%>! / 
			<a href="editAccountPage.jsp">My Account</a> / 
            <a href="myOrdersPage.jsp">My Orders</a> / 
            <a href="cartPage.jsp">My Cart (<%=user.getCart().getTotalItems()%>)</a>
            <a href="logout.jsp">Logout</a>
    </p>
<%}%>

<nav>
   <ul>
       <li class="start"><a href="homePage.jsp">Home</a></li>
       <li class=""><a href="shopListPage.jsp?category=COMICS">Comics</a></li>
	   <li class=""><a href="shopListPage.jsp?category=MAPS">Maps</a></li>
       <li class=""><a href="shopListPage.jsp?category=MOVIES">Movies</a></li>
       <li class=""><a href="shopListPage.jsp?category=MUSIC">Music</a></li>
	   <li class=""><a href="shopListPage.jsp?category=NATURE">Nature</a></li>
	   <li class=""><a href="shopListPage.jsp?category=SPORTS">Sports</a></li>
	   <li class=""><a href="shopListPage.jsp?category=TELEVISION">Television</a></li>
   </ul>
</nav>

<div>
<form name="autofillform" action="autocomplete">
      <table border="0" cellpadding="5"> 
        <tbody>
          <tr>
            <td><strong>Search Product:</strong></td>
                        <td>
                            <input type="text" size="40" id="complete-field" onkeyup="doCompletion()">
                        </td>
          </tr>
          <tr>
              <td id="auto-row" colspan="2">
                <table id="complete-table" class="popupBox">
				</table>
              </td>
          </tr>
        </tbody>
      </table>
</form>
</div>
</body>