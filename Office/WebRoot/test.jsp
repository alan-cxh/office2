<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE select PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page  import="java.io.*"%>
<%@ page  import="java.util.*"%>
<html>
<head >Read File</head>
<body>
<%
   int length;
   File f=new File("D://员工请假模板.doc");
   try
   {
        length=(int)f.length();
        FileInputStream  in=new FileInputStream(f);
        BufferedInputStream bufferIn=new BufferedInputStream(in);
        
        byte b[]=new byte[length+1];
        int n=0;
        while((n=bufferIn.read(b))!=-1)
        {
            String temp=new String(b,0,n);
            out.print(temp);
        }
        bufferIn.close();
        in.close();
   }
   catch(IOException e)
   {
       out.println("File Read Error!"+e);
   }
 %>

</body>
</html>

