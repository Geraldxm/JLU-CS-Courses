import java.io.*;
import java.net.*;
public class Client
{  public static void main(String args[])
   {  String s=null;
      Socket mysocket;
      DataInputStream in=null;
      DataOutputStream out=null;
      try{
          mysocket=new Socket("localhost",4331);
          in=new DataInputStream(mysocket.getInputStream());
          out=new DataOutputStream(mysocket.getOutputStream()); 
          out.writeUTF("你好！");//通过 out向"线路"写入信息。

          while(true)
            { 
               s=in.readUTF();//通过使用in读取服务器放入"线路"里的信息。堵塞状态，
                             //除非读取到信息。
               out.writeUTF(":"+Math.random());
               System.out.println("客户收到:"+s);
               
               try{
                 Thread.sleep(500);
               }
               catch(InterruptedException e){}
               
            } 
         }
       catch(IOException e)
         {  
       	   System.out.println(e.getMessage());
         }

   } 
}
