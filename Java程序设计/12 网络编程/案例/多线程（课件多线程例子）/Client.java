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
          out.writeUTF("��ã�");//ͨ�� out��"��·"д����Ϣ��

          while(true)
            { 
               s=in.readUTF();//ͨ��ʹ��in��ȡ����������"��·"�����Ϣ������״̬��
                             //���Ƕ�ȡ����Ϣ��
               out.writeUTF(":"+Math.random());
               System.out.println("�ͻ��յ�:"+s);
               
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
