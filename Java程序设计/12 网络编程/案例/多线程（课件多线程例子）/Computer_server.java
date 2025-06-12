import java.io.*;import java.net.*;
import java.util.*;import java.sql.*;
public class Computer_server 
{  public static void main(String args[])
   {  
      ServerSocket server=null;
      Server_thread thread;
      Socket you=null;

     try{  
        server=new ServerSocket(4331);        
        System.out.println("正在监听");
      }
     catch(IOException e) 
     {  
     	System.out.println(e.getMessage()); 
     } 
	      
      while(true) 
       { 
          try{  you=server.accept();
                System.out.println("客户的地址:"+you.getInetAddress());
             }
         catch (IOException e)
             {  System.out.println("正在等待客户");
             }
         if(you!=null) 
             {  
             	new Server_thread(you).start(); //为每个客户启动一个专门的线程。  
             }
         else 
             {  continue;
             }
      }
   }
}
class Server_thread extends Thread
{ 
   Socket socket;
   DataOutputStream out=null;
   DataInputStream  in=null;
   int n=0;
   String s=null;

   Server_thread(Socket t)
   {  socket=t;
      try 
       {  
      	     in=new DataInputStream(socket.getInputStream());
             out=new DataOutputStream(socket.getOutputStream());
       }
      catch (IOException e){}
   }  
   public void run()        
   {  while(true)
      { 
      	
         try{  
                 s=in.readUTF();//堵塞状态，除非读取到信息。

                
                out.writeUTF("客户发的信息："+s);
                //sleep(2);    
            }
         catch (IOException e) 
            {  
               System.out.println("客户离开");
               break;
             }
         //catch(InterruptedException e){}
      }
   } 
}
