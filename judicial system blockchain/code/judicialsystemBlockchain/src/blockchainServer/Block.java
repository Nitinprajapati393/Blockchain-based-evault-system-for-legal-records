package blockchainServer;

import blockchainServer.blockcserver;
import blockchainServer.readblockreq;
import java.util.Date;

public class Block {

	public String hash;
	 String previousHash; 
         String uname;
	 String caseid; //our data will be a simple message.
         String casename;
         String details;
         String lawyer;
         
         String lcomment; //our data will be a simple message.
         String judge;
         String jcomment;
         String fname;
         String status;
	 
	 int nonce;

         
         public Block()
         {
             
         }
         
	//Block Constructor.
	public Block(String uname,String caseid,String casename,String details,String lawyer,String lcomment,String judge,String jcomment,String fname,String status,String previousHash ) {
                this.uname=uname;
		this.caseid=caseid;
		this.casename = casename;
		this.details = details;
                this.lawyer=lawyer;
                
                this.lcomment=lcomment;
                this.judge=judge;
                this.jcomment=jcomment;
                this.fname=fname;
                this.status=status;
                
                this.hash = calculateHash(); //Making sure we do this after we set the other values.
	
	}
        
        //Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				
				Integer.toString(nonce) + 
				uname + caseid + casename +details 
				);
		return calculatedhash;
	}
        
        
        public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		blockcserver.jTextArea1.append("Block Mined!!! : " + hash+"\n");
                readblockreq.previousHash=hash;
	}
}