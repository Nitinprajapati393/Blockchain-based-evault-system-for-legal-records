/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainServer;

import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Lenovo
 */
public class readblockreq extends Thread
{
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;
    public static String previousHash="0";
    
    readblockreq()
    {
        super();
        start();
    }
    
    public void run()
    {
        try
        {
            ServerSocket ss=new ServerSocket(3000);
            
            while (true)
            {
                Socket soc=ss.accept();
                ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
                ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
                
                String req=(String)oin.readObject();
                
                if (req.equals("ADDBLOCK"))
                {
                   String uname=(String)oin.readObject();
                   String caseid=(String)oin.readObject();
                   String casename=(String)oin.readObject();
                   String details=(String)oin.readObject();
                   
                   String lawyer=(String)oin.readObject();
                   String lcomment=(String)oin.readObject();
                   String judge=(String)oin.readObject();
                   String jcomment=(String)oin.readObject();
                   String fname=(String)oin.readObject();
                   String status=(String)oin.readObject();
                   
                  // String opr=pid+"-->"+opr2+"-->"+opr3;
                   
                    blockcserver.jTextArea1.append("case details received from "+uname+"\n");
                   
                    Block b=new Block(uname,caseid,casename,details,lawyer,lcomment,judge,jcomment,fname,status,previousHash);
                    blockchain.add(b);
                    blockcserver.jTextArea1.append("Block Successfully added!\n");
                    blockchain.get(blockchain.size()-1).mineBlock(difficulty);
                        
                        
                        oos.writeObject("SUCCESS");
                   
                  
                }
                else
                if (req.equals("GETUSERCASES"))
                {
                    String user=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" request received from user "+user+" \n");
                    Vector v=getusercases(user);
                    oos.writeObject(v);
                }    
                else
                if (req.equals("SEARCHCASE"))
                {
                    
                    String cid=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" request received for "+cid+" \n");
                    String v=searchcase(cid);
                    oos.writeObject(v);
                }    
                else
                if (req.equals("GETLAWYERCASES"))
                {
                    String lawyer=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" request received from lawyer "+lawyer+" \n");
                    Vector v=getlawyercases(lawyer);
                    oos.writeObject(v);
                }
                else
                if (req.equals("GETJUDGECASES"))
                {
                    String judge=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" request received from Judge "+judge+" \n");
                    Vector v=getjudgecases(judge);
                    oos.writeObject(v);
                }
              
                else
                if (req.equals("UPDATELCOMMENT"))
                {
                    String caseid=(String)oin.readObject();
                    String comment=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" request received from Case ID "+caseid+" \n");
                    String status=updatelcomment(caseid,comment);
                    oos.writeObject(status);
                }
                else
                if (req.equals("UPDATEJCOMMENT"))
                {
                    String caseid=(String)oin.readObject();
                    String comment=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" request received from Case ID "+caseid+" \n");
                    String status=updatejcomment(caseid,comment);
                    oos.writeObject(status);
                }
                else
                if (req.equals("UPDATEJSTATUS"))
                {
                    String caseid=(String)oin.readObject();
                    String status=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" request received from Case ID "+caseid+" \n");
                    String reply=updatejstatus(caseid,status);
                    oos.writeObject(reply);
                }
                /*
                else
                if (req.equals("GETEXPENSE"))
                {
                    String pid=(String)oin.readObject();
                    Vector v=getexpense(pid);
                    oos.writeObject(v);
                }
                else
                if (req.equals("GETMYBIDS"))
                {
                    String uname=(String)oin.readObject();
                    Vector v=getmybids(uname);
                    oos.writeObject(v);
                }
                else
                if (req.equals("VIEWLOG"))
                {
                    String user=(String)oin.readObject();
                    System.out.println(user);
                    String log=getlog(user);
                    
                    oos.writeObject(log);
                }
                */
                
                oos.close();
                oin.close();
                soc.close();
                
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }    
    
    
    Vector getusercases(String user)
    {
        
        Vector v=new Vector();
        //String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.uname.equals(user))
                {
                    String data=b.caseid+"$"+b.casename+"$"+b.details+"$"+b.lawyer+"$"+b.lcomment+"$"+b.judge+"$"+b.jcomment+"$"+b.fname+"$"+b.status;
                            
                    v.add(data);
                   
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return v;
    }
    
    
    String searchcase(String cid)
    {
        
        String v="";
        //String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.caseid.equals(cid))
                {
                    String data=b.caseid+"$"+b.casename+"$"+b.details+"$"+b.lawyer+"$"+b.lcomment+"$"+b.judge+"$"+b.jcomment+"$"+b.status;
                            
                    v=data;
                   
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return v;
    }
    
    
    Vector getlawyercases(String lawyer)
    {
        
        Vector v=new Vector();
        //String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.lawyer.equals(lawyer))
                {
                    String data=b.uname+"$"+b.caseid+"$"+b.casename+"$"+b.details+"$"+b.judge+"$"+b.jcomment+"$"+b.lcomment+"$"+b.status+"$"+b.fname;
                            
                    v.add(data);
                   
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return v;
    }
    
    Vector getjudgecases(String judge)
    {
        
        Vector v=new Vector();
        //String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.judge.equals(judge))
                {
                    String data=b.uname+"$"+b.caseid+"$"+b.casename+"$"+b.details+"$"+b.lawyer+"$"+b.lcomment+"$"+b.jcomment+"$"+b.status+"$"+b.fname;
                            
                    v.add(data);
                   
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return v;
    }
    
    String updatelcomment(String caseid,String comment)
    {
        
       
        String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.caseid.equals(caseid))
                {
                    b.lcomment=comment;
                    blockchain.set(i, b);
                    //String data=b.uname+"$"+b.caseid+"$"+b.casename+"$"+b.details+"$"+b.judge+"$"+b.jcomment+"$"+b.lcomment+"$"+b.status+"$"+b.fname;
                            
                    reply="SUCCESS";
                   
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return reply;
    }
    
    String updatejcomment(String caseid,String comment)
    {
        
       
        String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.caseid.equals(caseid))
                {
                    b.jcomment=comment;
                    blockchain.set(i, b);
                    //String data=b.uname+"$"+b.caseid+"$"+b.casename+"$"+b.details+"$"+b.judge+"$"+b.jcomment+"$"+b.lcomment+"$"+b.status+"$"+b.fname;
                            
                    reply="SUCCESS";
                   
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return reply;
    }
    
    
    String updatejstatus(String caseid,String status)
    {
        
       
        String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.caseid.equals(caseid))
                {
                    b.status=status;
                    blockchain.set(i, b);
                    //String data=b.uname+"$"+b.caseid+"$"+b.casename+"$"+b.details+"$"+b.judge+"$"+b.jcomment+"$"+b.lcomment+"$"+b.status+"$"+b.fname;
                            
                    reply="SUCCESS";
                   
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return reply;
    }
    
    
   
    
    
    void writelogs()
    {
        try
        {
            if (blockchain.size()>0)
            {
                JSONArray blockList = new JSONArray();
                
                for (int i=0;i<blockchain.size();i++)
                {
                    Block b=(Block)blockchain.get(i);
                    JSONObject blockdetails = new JSONObject();
                    blockdetails.put("uname", b.uname);
                    blockdetails.put("caseid", b.caseid);
                    blockdetails.put("casename", b.casename);
                    blockdetails.put("details", b.details);
                    blockdetails.put("lawyer", b.lawyer);
                    blockdetails.put("lcomment", b.lcomment);
                    blockdetails.put("judge", b.judge);
                    blockdetails.put("jcomment", b.jcomment);
                    blockdetails.put("fname", b.fname);
                    blockdetails.put("status", b.status);
                    blockdetails.put("previoushash", b.previousHash);
                    blockdetails.put("hash", b.hash);
                    
                    JSONObject blockObject = new JSONObject(); 
                    blockObject.put("block", blockdetails);
                    
                    blockList.add(blockObject);
                }
                
                FileWriter file = new FileWriter("caselogs.json");
                 file.write(blockList.toJSONString()); 
                 file.flush();
                 file.close();
                
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
        
}
