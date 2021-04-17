package test6;

import java.sql.*;
import java.util.Scanner;
public class ADCompany {
	
	Scanner sc=new Scanner(System.in);
	String url = "jdbc:mysql://localhost:3306/skybeats"; 
    String uname = "root";
    String password = "pranansh112"; 
    String AID;
    
    
    public void existing() throws SQLException {
    	//System.out.println("IN customer class");
    	System.out.println("Enter Your AdcomapyID :");
    	AID=sc.next();
    	queries();
    }
    
    void queries() throws SQLException {
    	
    	Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();
    	
    	System.out.println("1. See your total investment in Skybeats");
    	System.out.println("2. List of all the songs sponsored by your company");
    	System.out.println("3. View Top 5 most attended Livestreams");
    	System.out.println("4. Search by range of views");
    	System.out.println("5. View the highest rated song ");
    	
    	int ch3=sc.nextInt();
    	if (ch3==1) {
    		
//    		System.out.println("Enter Your AdcomapyID :");
//    		sc.nextLine();
//            String ADID=sc.nextLine();
            //System.out.println(songname);
            
            String query="select adID,adcompany.`name` , sum(funds) as Total_Funds_Invested \r\n" + 
            		"from adcompany,songs \r\n" + 
            		"where songs.adcompany_adID = adcompany.adID \r\n" + 
            		"group by adID \r\n" + 
            		") as tab\r\n" + 
            		"where \r\n" + 
            		"STRCMP(tab.adID,\""+AID +"\")=0 ;";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
            	System.out.println("name: "+rs.getString(1));
            	System.out.println("adID: "+rs.getString(2));
            	System.out.println("Total_Funds_Invested: "+rs.getString(3));
            	System.out.println();
            }
            
            System.out.println();
            
    	}
    	else if (ch3==2) {
    		
            

//            System.out.println("Enter Your AdcomapyID :");
//    		sc.nextLine();
//            String ADID=sc.nextLine();
            
            String query="select name from songs where STRCMP(adcompany_adID,\""+AID+"\")=0 ;";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
            	System.out.println("Name: "+rs.getString(1));
            	System.out.println();
            }
            
            System.out.println();
            
       
    	}
    	else if (ch3==3) {
    		
            
            String query1="select producer.name as producer_name, Tab.producer_producerID as producer_id,Tab.noofattendees,Tab.livestreamId\r\n" + 
            		"from \r\n" + 
            		"(\r\n" + 
            		"select * from livestream order by noofattendees desc limit 5\r\n" + 
            		") as Tab, producer\r\n" + 
            		"where\r\n" + 
            		"Tab.producer_producerID=producer.producerID;";
            
            
            ResultSet rs = st.executeQuery(query1);
            while (rs.next()) {
            	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
            	System.out.println("Producer_name: "+rs.getString(1));
            	System.out.println("Producer_id: "+rs.getString(2));
            	System.out.println("No of Attendees: "+rs.getString(3));
            	System.out.println("Livestream Id: "+rs.getString(4));
            	System.out.println();
            }
            
            System.out.println();
            
           
    	}
    	else if (ch3==4) {
    		
    		 System.out.println("Enter the stating value of the range");
     		sc.nextLine();
             int start=sc.nextInt();
             
             System.out.println("Enter the end value of the range");
      		sc.nextLine();
              int end=sc.nextInt();
    		
    		String query1="select songID, name, views\r\n" + 
    				"from songs\r\n" + 
    				"where\r\n" + 
    				"views >=1000 and views<=5000;";
    		 ResultSet rs = st.executeQuery(query1);
             while (rs.next()) {
             	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
             	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
             	System.out.println("Song ID: "+rs.getString(1));
             	System.out.println("Name: "+rs.getString(2));
             	System.out.println("Views: "+rs.getString(3));
             	System.out.println();
             }
             
             System.out.println();
            
            
    	}
    	else if (ch3==5) {
    		
            
            String query="select name, max(rating) as highest_rated from songs; ";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	//System.out.println(rs.getInt(1)+" "+ rs.getString(2));
            	//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getString(7));
            	System.out.println("Name "+rs.getString(1));
            	System.out.println("Rating: "+rs.getString(2));
            	
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
        
        String aID,aName;
        float aFunds;
        System.out.println("Enter ID, Name and funds");
        aID=sc.next();
        sc.nextLine();
        aName=sc.nextLine();
        aFunds=sc.nextFloat();
        
        String query="insert into adcompany values (\""+aID+"\",\""+aName+"\",\""+aFunds+"\");";
        
        st.executeUpdate(query);
        //System.out.println("NEW C added");
        
        st.close();
        con.close();
    }
}
