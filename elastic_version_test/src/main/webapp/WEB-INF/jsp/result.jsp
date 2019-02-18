<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link href="<c:url value="/resources/css/search_box.css"/>" type="text/css" rel="stylesheet">
<title>TP bank</title>
</head>
 <script>
function showTable(){
document.getElementById('t01').style.visibility = "visible";
}
function hideTable(){
document.getElementById('t01').style.visibility = "hidden";
}
</script>
<body onload="javascript:hideTable()">
        <div class="search-box">
            <input type="text" class="search-txt" name="" placeholder="LOOKING...?">
            <a class="search-btn" onClick='javascript:showTable();' value='show'>
                    <i class="fas fa-search"></i>

            </a> 

        </div>

<table id="t01" border="1" cellpadding="10">  
   <tr>
   <th> Title </th>
   <th> Link </th>
   <td> Description </td>
   </tr>
   <c:forEach items="${pages}" var="page">
   <tr>
       <td> ${page.title}</td>
       <td> ${page.url } </td>
       <td>${page.description } </td>
   </tr>
   
   
   </c:forEach>


</table>>
</body>
</html>