package dbms;

import java.sql.*;
import java.util.Scanner;
public class manager {
	
	Scanner sc=new Scanner(System.in);
	String url = "jdbc:mysql://localhost:3306/skybeats"; 
    String uname = "root";
    String password = "aadhya"; 
    String mID;
    
    
    public void existing() throws SQLException {
    	System.out.println("Enter Your managerID :");
    	mID=sc.next();
    	queries();
    }
    
    void queries() throws SQLException {
    	
    	Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();
    	
    	System.out.println("1. view number of producers under a manager");
    	System.out.println("2. View list of Producers being managed by a manager");
    	System.out.println("3. view earning of a manager");
    	System.out.println("4. the producer with highest rating under a manager");
    	System.out.println("5. the producer with highest views under a manager");
    	System.out.println("6. the performance of each producer under a manager");
    	System.out.println("7. total views of a single manager");

    	int ch4=sc.nextInt();
    	if (ch4==1) {
    		
    		
            String query="SELECT   manager_managerID, COUNT(producer.producerID)\n" + 
            		"FROM        manager \n" + 
            		"INNER JOIN  producer  \n" + 
            		"ON     producer.manager_managerID= manager.managerID\n" + 
            		"WHERE manager.managerID="+mID+"\n" + 
            		"GROUP BY manager.managerID;";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	
            	System.out.println("managerID: "+rs.getString(1));
            	System.out.println("Number of Producers: "+rs.getString(2));
            	System.out.println();
            }
            
            System.out.println();
            
    	}
    	if(ch4==2) {
    		
    		System.out.println("Producers under you are :");
    		String query="SELECT   manager_managerID,producer.producerID\n" + 
    				"FROM        manager \n" + 
    				"INNER JOIN  producer  \n" + 
    				"ON      producer.manager_managerID= manager.managerID\n" + 
    				"WHERE manager.managerID="+mID+";";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	
            	System.out.println("managerID: "+rs.getString(1));
            	System.out.println("ProducerID: "+rs.getString(2));
            	System.out.println();
            }
            
            System.out.println();
    	}
    	if(ch4==3) {
    		
    		String query="SELECT managerID , earnings FROM manager WHERE manager.managerID="+mID+"; ";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	
            	System.out.println("managerID: "+rs.getString(1));
            	System.out.println("earnings: "+rs.getString(2));
            	System.out.println();
            }
            
            System.out.println();
    	}
    	if(ch4==4) {
    		
    		System.out.println("The producer with highest rating under you is:");
    		String query="SELECT   producer.manager_managerID,producer.producerID,songs.songID,songs.rating\n" + 
    				"FROM        producer\n" + 
    				"INNER JOIN  songs\n" + 
    				"ON      producer.producerID=  songs.producer_producerID \n" + 
    				"WHERE producer.manager_managerID="+mID+"\n" + 
    				"GROUP BY producer.producerID\n" + 
    				"ORDER BY songs.rating desc\n" + 
    				"limit 1;";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	
            	System.out.println("managerID: "+rs.getString(1));
            	System.out.println("ProducerID: "+rs.getString(2));
            	System.out.println("songID: "+rs.getString(3));
            	System.out.println("song rating: "+rs.getString(4));
            	System.out.println();
            }
            
            System.out.println();
    	}
    	if(ch4==5) {
    		
    		System.out.println("The producer with highest views under you:");
    		String query="SELECT   producer.manager_managerID,producer.producerID,sum(songs.views)\n" + 
    				"FROM        producer\n" + 
    				"INNER JOIN  songs\n" + 
    				"ON      producer.producerID=  songs.producer_producerID \n" + 
    				"WHERE producer.manager_managerID="+mID+"\n" + 
    				"GROUP BY producer.producerID\n" + 
    				"ORDER BY songs.views desc\n" + 
    				"limit 1;";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	
            	System.out.println("managerID: "+rs.getString(1));
            	System.out.println("ProducerID: "+rs.getString(2));
            	System.out.println("songID: "+rs.getString(3));
            	System.out.println("song views: "+rs.getString(4));
            	System.out.println();
            }
            
            System.out.println();
    	}
    	if(ch4==6) {
    		
    		System.out.println("the performance of each producer under a you:");
    		String query="SELECT   producer.manager_managerID,producer.producerID,songs.songID,SUM(songs.views) ,songs.rating\n" + 
    				"FROM        producer\n" + 
    				"INNER JOIN  songs\n" + 
    				"ON      producer.producerID=  songs.producer_producerID \n" + 
    				"WHERE producer.manager_managerID="+mID+"\n" + 
    				"GROUP BY producer.producerID\n" + 
    				"ORDER BY songs.views;";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	
            	System.out.println("managerID: "+rs.getString(1));
            	System.out.println("ProducerID: "+rs.getString(2));
            	System.out.println("songID: "+rs.getString(3));
            	System.out.println("song views: "+rs.getString(4));
            	System.out.println("song ratings: "+rs.getString(5));
            	System.out.println();
            }
            
            System.out.println();
    	}
    	if(ch4==7) {
    		
    		System.out.println("total views of all the songs under you:");
    		String query="SELECT   manager_managerID , sum(songs.views)\n" + 
    				"FROM     manager \n" + 
    				"INNER JOIN  producer  \n" + 
    				"ON     producer.manager_managerID= manager.managerID\n" + 
    				"INNER JOIN  songs \n" + 
    				"ON     producer.producerID= producer_producerID \n" + 
    				"WHERE manager.managerID="+mID+"\n" + 
    				"GROUP BY manager.managerID\n" + 
    				"ORDER BY songs.views;\n" + 
    				"";
            
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
            	
            	System.out.println("managerID: "+rs.getString(1));
            	System.out.println("Total song views: "+rs.getString(2));
            	System.out.println();
            }
            
            System.out.println();
    	}
    	else {
    		System.out.println("Invalid choice ");
    	}
    	
            
           
    	st.close();
        con.close();
    	
    }
    
    void newManager() throws SQLException {
    	
    	Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();
        
        String mID,mName;
        int earnings;
        System.out.println("Enter ID, Name and earnings");
        mID=sc.next();
        sc.nextLine();
        mName=sc.nextLine();
        earnings=sc.nextInt();
        
        String query="insert into manager values (\""+mID+"\",\""+mName+"\",\""+earnings+"\");";
        
        st.executeUpdate(query);
        System.out.println("NEW manager added");
        
        st.close();
        con.close();
    }
}