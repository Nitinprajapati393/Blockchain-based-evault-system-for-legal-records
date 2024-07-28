/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainServer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class readJSON {

public readJSON()
{
    //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("caselogs.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray blockList = (JSONArray) obj;
            System.out.println(blockList);
             
            //Iterate over block chain array
            blockList.forEach( blockobject -> parseLogObject( (JSONObject) blockobject ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    
}
        
        

   
 
    private static void parseLogObject(JSONObject blockobject) 
    {
        //Get employee object within list
        JSONObject blockdetails = (JSONObject) blockobject.get("block");
         
        //Get user name
        String uname = (String) blockdetails.get("uname");    
        System.out.println(uname);
        
        String caseid = (String) blockdetails.get("caseid");    
        System.out.println(caseid);
         
        //Get operation
        String casename = (String) blockdetails.get("casename");  
        System.out.println(casename);
        
        String details = (String) blockdetails.get("details");  
        System.out.println(details);
        
        String lawyer = (String) blockdetails.get("lawyer");  
        System.out.println(lawyer);
        
        String lcomment = (String) blockdetails.get("lcomment");  
        System.out.println(lcomment);
        
        String judge = (String) blockdetails.get("judge");  
        System.out.println(judge);
        
        String jcomment = (String) blockdetails.get("jcomment");  
        System.out.println(jcomment);
        
        String fname = (String) blockdetails.get("fname");  
        System.out.println(fname);
        
        String status = (String) blockdetails.get("status");  
        System.out.println(status);
        
        String prev = (String) blockdetails.get("previoushash");  
        System.out.println(prev);
        
       
        
        String hash = (String) blockdetails.get("hash");  
        System.out.println(hash);
        
        Block b=new Block();
        b.uname=uname;
        b.caseid=caseid;
        b.casename=casename;
        b.details=details;
        b.lawyer=lawyer;
        b.lcomment=lcomment;
         b.judge=judge;
        b.jcomment=jcomment;
        b.fname=fname;
        b.status=status;
        b.previousHash=prev;
        b.hash=hash;
        
        readblockreq.blockchain.add(b);
         
        
    }
}
