


<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.FileUploadException"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>

<%@page import="org.apache.commons.fileupload.FileItem"%>

<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>

<!DOCTYPE html>

<%
    
String ubicacionArchvivo="C:\\Users\\Susana\\Documents\\NetBeansProjects\\VentadeCasas\\web\\images";

//String ubicacionArchvivoHosting="//petylde.esy.es//uploads//casapueblo";
//http://192.168.0.154:8080/WebServicesRESTGlassFishJEE7/webresources/contactos
//ftp://u860451933@31.170.165.188/public_html/uploads/casapueblo
String ubicacionArchvivoHosting="http://31.170.165.188:21//petylde.esy.es//uploads//casapueblo";

   DiskFileItemFactory factory = new DiskFileItemFactory();
   factory.setSizeThreshold(1024);
   factory.setRepository(new File(ubicacionArchvivoHosting));
   ServletFileUpload upload = new ServletFileUpload(factory);
   
   
   try{
   
         List<FileItem> partes = upload.parseRequest(request);
         
         for (FileItem item: partes){
             
            File file=new File(ubicacionArchvivo,item.getName()); 
            item.write(file);
        
         }
         
         out.write("Archivo subido correctamente");
         }
   
   catch(FileUploadException ex){
       
       out.write("Error al subir el archivo"+ex.getMessage());
       
   }
   
   
   
   
   %>