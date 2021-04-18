import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;

public class employee {
    Scanner sc=new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/skybeats"; 
    String uname = "root";
    String password = "pranansh112"; 
    String eID;

    public void existing() throws SQLException {
        System.out.println("Enter Your EmployeeID :");
        eID=sc.next();
        String ans1="y";
    	do {
    		queries();
    		System.out.println("Do you want to continue ? (y/n)");
    		ans1=sc.next();
    	}while (ans1.equals("y"));
    }

    void queries() throws SQLException {

        Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();

        System.out.println("1. View my Earnings");
        System.out.println("2. View all pending customer complaints");
        System.out.println("3. View producer data");
        System.out.println("4. View top 5 most played songs");
        System.out.println("5. View all customer complaints which I have resolved");

        int ch3=sc.nextInt();
        if (ch3==1) {

            String query="select salary\n" +
                    "\tfrom employee\n" +
                    "\twhere employeeID=\""+eID+"\";";


            //System.out.println(st.executeQuery(query));
            
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("salary: "+rs.getString(1));
                System.out.println();
            }
            
            System.out.println();
        }

        else if (ch3==2) {
            String query="select complainID, reason, customer_customerID\n" +
                    "\t\tfrom complain;";

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("Complaint ID: "+rs.getString(1));
                System.out.println("Complaint: "+rs.getString(2));
                System.out.println("ID of Customer who filed complaint: "+rs.getString(3));
                System.out.println();
            }

            System.out.println();

        }
        else if (ch3==3) {
            String query1="select producerID, name, earning, manager_managerID\n" +
                    "\t\tfrom producer;";


            ResultSet rs = st.executeQuery(query1);
            while (rs.next()) {
                System.out.println("Producer ID: "+rs.getString(1));
                System.out.println("Name: "+rs.getString(2));
                System.out.println("Earning: "+rs.getString(3));
                System.out.println();
            }

            System.out.println();

        }
        else if (ch3==4) {

        	String query="select songID, name, producer_producerID, views\n" +
                    "\tfrom (SELECT songID, name, producer_producerID, views\n" +
                    "from songs\n" +
                    "order by views desc) as sub\n" +
                    "\tlimit 5;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("Song ID: "+rs.getString(1));
                System.out.println("Name: "+rs.getString(2));
                System.out.println("Producer: "+rs.getString(3));
                System.out.println("Views: "+rs.getString(4));
                System.out.println();
            }

        }
        else if (ch3==5) {
            //we didnt assign names to the columns in process, so I assigned my own. we should update the data accordingly.
        	String query="select complainID, reason, customer_customerID\n" +
                    "\tfrom process\n" +
                    "\tinner join complain\n" +
                    "\twhere employee_employeeID=\""+eID+"\";";


            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("Complaint ID:"+rs.getString(1));
                System.out.println("Complaint: "+rs.getString(2));
                System.out.println("ID of Customer who filed complaint: "+rs.getString(3));
                System.out.println();
            }

            System.out.println();
        }

        st.close();
        con.close();

    }

    void newEmployee() throws SQLException {

        Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();

        String eID,eName,eSalary;
        System.out.println("Enter ID, Name");
        eID=sc.next();
        sc.nextLine();
        eName=sc.nextLine();
        eSalary=sc.next();

        String query="insert into employee values (\""+eID+"\",\""+eName+"\",\""+eSalary+"\");";

        st.executeUpdate(query);
        st.close();
        con.close();
    }
}
