<%@page import="java.util.HashMap"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="java.io.Writer"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.DataInputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.DataOutputStream"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		try{
			
			Writer fileWriter=new BufferedWriter(new FileWriter("result.txt"));
			
			//bfw.write("null");
			
			//response.sendRedirect("/home");
			
			String[] fields={"title","description","prefix","EventCode","boostword"};
			
			HashMap<String, String> map=new HashMap<String, String>();
			
			for(int i=0;i<fields.length;i++){
				
				if(request.getParameter(fields[i])!=null){
					
					map.put(fields[i], request.getParameter(fields[i]));
					
				}
				
			}
			
			//out.print(map.get(fields[1]));
			
			for(int i=0;i<fields.length;i++){
				
				if(map.get(fields[i])!=null){
					
					if(!fields[i].equals("boostword")) fileWriter.write(map.get(fields[i])+" ");
					else{
						fileWriter.write("\n"+map.get(fields[i]));
					}
					
				}
				
			}
			
			fileWriter.close();
			
			FileInputStream fis = new FileInputStream("result.txt");
			
			DataInputStream dis=new DataInputStream(fis);
			
			String str=dis.readLine();
			
			out.print(str);
			
			String types[]=str.split(" ");
			
			response.sendRedirect("/admin?response=successfully");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
	%>
	<p>Chinh vu</p>
</body>
</html>