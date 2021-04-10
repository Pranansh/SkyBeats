import java.sql.*;
import java.util.Scanner;
public class customer {
	
	Scanner sc=new Scanner(System.in);
	String url = "jdbc:mysql://localhost:3306/skybeats"; 
    String uname = "root";
    String password = "pranansh112"; 
    String cID;
    
    
    public void existing() throws SQLException {
    	//System.out.println("IN customer class");
    	System.out.println("Enter Your cutomerID :");
    	cID=sc.next();
    	queries();
    }
    
    void queries() throws SQLException {
    	
    	Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();
    	
    	System.out.println("1. search song by name");
    	System.out.println("2. search by producer name");
    	System.out.println("3. access playlist");
    	System.out.println("4. update membership");
    	System.out.println("5. See number of songs by all producers");
    	
    	int ch3=sc.nextInt();
    	if (ch3==1) {
    		
    		System.out.println("Enter Song NAME");
    		sc.nextLine();
            String songname=sc.nextLine();
            System.out.println(songname);
            
            String query="select * from songs\r\n"
            		+ "where name=\""+songname+"\";";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
            	System.out.println("songID: "+rs.getString(1));
            	System.out.println("songNAME: "+rs.getString(2));
            	System.out.println("Producer: "+rs.getString(6));
            	System.out.println();
            }
            
            System.out.println();
            
    	}
    	else if (ch3==2) {
    		
            
    		System.out.println("Enter Producer NAME");
    		sc.nextLine();
            String prodname=sc.nextLine();
            //System.out.println(prodname);
            
            String query="select songID,songs.name,producer_producerID,producer.name\r\n"
            		+ "from songs,producer\r\n"
            		+ "where producer.name=\""+prodname+"\"\r\n"
            		+ "and producer_producerID=producerID;";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
            	System.out.println("songID: "+rs.getString(1));
            	System.out.println("songNAME: "+rs.getString(2));
            	System.out.println("ProducerID: "+rs.getString(3));
            	System.out.println("ProducerNAME: "+rs.getString(4));
            	System.out.println();
            }
            
            System.out.println();
            
       
    	}
    	else if (ch3==3) {
    		
            
            String query1="select * from playlist \r\n"
            		+ "where customer_customerID=\""+cID+"\";";
            
            
            ResultSet rs = st.executeQuery(query1);
            while (rs.next()) {
            	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
            	System.out.println("playlistID: "+rs.getString(1));
            	System.out.println("playlistNAME: "+rs.getString(2));
            	System.out.println();
            }
            
            System.out.println();
            
            System.out.println("Enter playlist ID");
            String playlistID=sc.next();
            
            System.out.println("1. See songs");
            System.out.println("2. play song");
            System.out.println("3. add songs to playlist");
            
            int ch4=sc.nextInt();
            
            if (ch4==1) {
                
                String query="select * from contains\r\n"
                		+ "where playlist_playlistID=\""+playlistID+"\";";
                
                
                rs = st.executeQuery(query);
                while (rs.next()) {
                	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
                	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
                	System.out.println("songID: "+rs.getString(2));
                	System.out.println();
                }
                
                System.out.println();
            }
            else if (ch4==2) {
            	
            	
            }
            else if (ch4==3) {
            	
            	String songID;
                System.out.println("Enter songID");
                songID=sc.next();
                
                String query="insert into contains values(\""+playlistID+"\",\""+songID+"\");\r\n"
                		+ "";
                
                st.executeUpdate(query);
            }
            else {
            	System.out.println("Invalid choice");
            }
    	}
    	else if (ch3==4) {
    		
            String query="update customer\r\n"
            		+ "set type = \"premium\"\r\n"
            		+ "where customerID=\""+cID+"\";";
            
            st.executeUpdate(query);
    	}
    	else if (ch3==5) {
    		
            
            String query="select count(producer_producerID), producer_producerID, producer.name\r\n"
            		+ "from songs,producer\r\n"
            		+ "where producer_producerID=producerID\r\n"
            		+ "group by producer_producerID;";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
            	System.out.println("count(producerID) "+rs.getString(1));
            	System.out.println("ProducerID "+rs.getString(2));
            	System.out.println("ProducerNAME: "+rs.getString(3));
            	System.out.println();
            }
            
            System.out.println();
    	}
    	
        
        
    	st.close();
        con.close();
    	
    }
    
    void newCustomer() throws SQLException {
    	
    	Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();
        
        String cID,cName,cType;
        System.out.println("Enter ID, Name and type");
        cID=sc.next();
        sc.nextLine();
        cName=sc.nextLine();
        cType=sc.next();
        
        String query="insert into customer values (\""+cID+"\",\""+cName+"\",\""+cType+"\");";
        
        st.executeUpdate(query);
        //System.out.println("NEW C added");
        
        st.close();
        con.close();
    }
}
