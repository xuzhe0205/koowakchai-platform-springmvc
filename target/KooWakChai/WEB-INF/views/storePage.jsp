<%--
  Created by IntelliJ IDEA.
  User: oliverxu
  Date: 2019-09-17
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Toronto KooWakChai Yitiaolong</title>
</head>
<body>
<h2>${product}</h2>
<p><strong>This is jsp scriptlet tag</strong></p>
<p>
    <%
        for (int i = 0; i<3; i++){
            out.print("welcome to jsp <br>");
        }
    %>
</p>

<p>
    <strong>
        This is jsp expression tag (代替out.print)
    </strong>
</p>
<p>
    Current Time: <%= java.util.Calendar.getInstance().getTime() %>
</p>
<p>
    <strong>
        This is jsp declaration tag. The JSP declaration tag is used to declare fields and methods.
    </strong>
</p>

<p>
    <%! int data=50; %>
    <%= "Value of the variable is:"+data %>
</p>


<p>
    <%!
        int cube(int n){
            return n*n*n;
        }
    %>
    <%= "Cube of 3 is:"+cube(3) %>
</p>

</body>
</html>
