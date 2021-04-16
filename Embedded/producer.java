import java.sql.*;
import java.util.Scanner;
public class producer {

    Scanner sc=new Scanner(System.in);
    String url = "jdbc:mysql://localhost:3306/skybeats";
    String uname = "root";
    String password = "yash";
    String cID;


    public void existing() throws SQLException{
        Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();
        //System.out.println("IN customer class");
        System.out.println("Enter Your producerID :");
        cID=sc.next();
        String query = "select * from producer\n" +
                "where producerID=\""+cID+"\";";


        ResultSet rs = st.executeQuery(query);
        if(!rs.next()){
            while(!rs.next()){
                System.out.println("Enter Valid ID ");
                cID=sc.next();
                query = "select * from producer\n" +
                        "where producerID=\""+cID+"\";";


                rs = st.executeQuery(query);
            }
            st.close();
            con.close();
            queries();


        }
        else{
            st.close();
            con.close();
            queries();
        }


    }
    void queries() throws SQLException {

        Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();

        System.out.println("1. search songs with ranking");
        System.out.println("2. View your sponsor ");
        System.out.println("3. Total views of all of your songs ");
        System.out.println("4. Top 2 songs ");
        System.out.println("5. See your manager ");
        System.out.println("6. Exit ");
        System.out.println("Enter your choice ");
        int ch3=sc.nextInt();
        while(ch3!=6) {
            if (ch3 == 1) {


                String query = "select songs.name,rank()over(order by rating desc) as rating,producer_producerID from songs where songs.producer_producerID=\"" + cID + "\";";


                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    System.out.println("song_name: " + rs.getString(1));
                    System.out.println("rating: " + rs.getString(2));
                    System.out.println();
                }

                System.out.println();

            } else if (ch3 == 2) {
                String query = "select songs.producer_producerID , songs.adcompany_adID , adcompany.name\n" +
                        "from songs \n" +
                        "inner join adcompany\n" +
                        "where adcompany.adID=songs.adcompany_adID and songs.producer_producerID=\"" + cID + "\";";


                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {


                    System.out.println("AD_COMPANY_ID: " + rs.getString(2));
                    System.out.println("AD comapany name : " + rs.getString(3));
                    System.out.println();
                }


            } else if (ch3 == 3) {


                String query1 = "select songs.producer_producerID , sum(songs.views)\n" +
                        "from songs \n" +
                        "inner join producer\n" +
                        "where songs.producer_producerID=producer.producerID and songs.producer_producerID=\"" + cID + "\";";

                ResultSet rs = st.executeQuery(query1);
                while (rs.next()) {
                    System.out.println("Total Views on your songs " + rs.getString(2));
                    System.out.println();
                }


            } else if (ch3 == 4) {

                String query = "select songs.name , songs.rating from songs \n" +
                        "inner join producer \n" +
                        "on producer.producerID=songs.producer_producerID\n" +
                        "where producerID=\"" + cID + "\" order by rating DESC\n" +
                        "limit 2;";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    System.out.println(" Song Name :   " + rs.getString(1));
                    System.out.println(" Rating : " + rs.getString(2));
                    System.out.println();
                }
            } else if (ch3 == 5) {


                String query = "select manager.name , manager.managerID , producer.producerID from manager\n" +
                        "inner join producer\n" +
                        "where producer.manager_managerID=manager.managerID and producerID=\"" + cID + "\";";


                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    System.out.println("Manager Name  " + rs.getString(1));
                    System.out.println(" Manager ID  " + rs.getString(2));
                    System.out.println();
                }
            }

            else {
                System.out.println("Enter a valid choice ");
            }
            System.out.println("1. search songs with ranking");
            System.out.println("2. View your sponsor ");
            System.out.println("3. Total views of all of your songs ");
            System.out.println("4. Top 2 songs ");
            System.out.println("5. See your manager ");
            System.out.println("6. Exit ");
            System.out.println("Enter your choice ");
            ch3=sc.nextInt();
        }
        st.close();
        con.close();





    }
    void newProducer() throws SQLException {

        Connection con = DriverManager.getConnection(url, uname, password);
        Statement st = con.createStatement();

        String cID,cName,cType,mID;
        float cearning;
//        cID="";
//
//
//        String query="select count(producerID) from producer;";
//        ResultSet rs=st.executeQuery(query);
//        String sum="";
//        while (rs.next()) {
//            sum=rs.getString(1);
//        }
//        int total=Integer.parseInt(sum);
//        total+=1000;
//        sum=Integer.toString(total);
//        cID="P"+sum;
//        System.out.println(cID);
        System.out.println("Enter ID,Name, earning and manager ID");
        cID=sc.next();
        sc.nextLine();
        cName=sc.nextLine();
        cearning=sc.nextFloat();
        mID=sc.next();
        String query="insert into producer values (\""+cID +"\",\""+cName+"\","+cearning+",\""+mID+"\");";
        st.executeUpdate(query);
        System.out.println("New producer added ");

        st.close();
        con.close();
    }
}

