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
        System.out.println("���ڼ���");
      }
     catch(IOException e) 
     {  
     	System.out.println(e.getMessage()); 
     } 
	      
      while(true) 
       { 
          try{  you=server.accept();
                System.out.println("�ͻ��ĵ�ַ:"+you.getInetAddress());
             }
         catch (IOException e)
             {  System.out.println("���ڵȴ��ͻ�");
             }
         if(you!=null) 
             {  
             	new Server_thread(you).start(); //Ϊÿ���ͻ�����һ��ר�ŵ��̡߳�  
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
                 s=in.readUTF();//����״̬�����Ƕ�ȡ����Ϣ��

                
                out.writeUTF("�ͻ�������Ϣ��"+s);
                //sleep(2);    
            }
         catch (IOException e) 
            {  
               System.out.println("�ͻ��뿪");
               break;
             }
         //catch(InterruptedException e){}
      }
   } 
}
