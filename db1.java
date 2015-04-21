
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Created by tarek on 3/20/2015.
 */
public  class db1 {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:85/galal";
    static final String USER = "root";
    static final String PASS = "";
    public static void main(String[] args) {
        System.out.println("Press Enter To Start the program");
        Scanner keyboard = new Scanner(System.in);
        char in = ' ';
        String fname = "", lname = "", street = "", city = "", state = "", zip = "", phone = "", email = "", userid = "", pass = "", credit = "n/a", creditno = "n/a", input = keyboard.nextLine();
        Connection conn = null;
        Statement stmt = null;
        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (in != 'q') {


                System.out.println(
                        "**********************************************************************\n" +
                                "***                                                                ***\n" +
                                "***             Welcome to the Online Book Store                   ***\n" +
                                "***                                                                ***\n" +
                                "**********************************************************************\n" +
                                "1. Member Login\n" +

                                "2. New Member Registration\n" +

                                "q. Quit\n"
                );

                Scanner sc = new Scanner(System.in);
                in = sc.next().charAt(0);

                if (in == '2') {


                    System.out.println("Welcome to the Online Book Store\n" +
                            "       New Member Registration\n" +
                            "\n" +
                            "\n" +
                            "Enter first name: ");

                    fname = sc.next();


                    System.out.println("Enter last name: \n");

                    lname = sc.next();

                    System.out.println("Enter street address: \n");

                    street = sc.next();

                    System.out.println("Enter City: \n");

                    city = sc.next();

                    System.out.println("Enter state: \n");

                    state = sc.next();

                    System.out.println("Enter zip: \n");

                    zip = sc.next();

                    System.out.println("Enter phone: \n");

                    phone = sc.next();

                    System.out.println("Enter email: \n");

                    email = sc.next();

                    System.out.println("Enter userID: \n");

                    userid = sc.next();

                    System.out.println("Enter password: \n");

                    pass = sc.next();

                    System.out.println("Do you wish to store credit card information(y/n): y\\n\"\n");

                    in = sc.next().charAt(0);

                    if (in == 'n') {
                        continue;
                    } else if (in == 'y') {

                        System.out.println("Enter type of Credit Card(amex/visa): ");

                        credit = sc.next();

                        System.out.println("Enter Credit Card Number: ");

                        creditno = sc.next();

                        while (creditno.length() != 16) {

                            System.out.println("invalid Entry");
                        }

                    }

                }

                System.out.println("You have registered successfully.\n" +
                        "Name:                " + fname + lname + "\n" +
                        "Address:             " + street + "\n" +
                        "City:                " + city + "\n" +
                        "Phone:               " + phone + "\n" +
                        "Email:               " + email + "\n" +
                        "UserID:              " + userid + "\n" +
                        "Password:            " + pass + "\n" +
                        "CreditCard Type:     " + credit + "\n" +
                        "CreditCard Number:   " + creditno + "\n" +
                        "Press Enter to go back to Menu");
                String sql = "INSERT INTO `galal`.`members` (`fname`, `lname`, `address`, `city`, `state`, `zip`, `phone`, `email`, `userid`, `password`, `creditcardtype`, `creditcardint`)" +
                        " VALUES('"+fname+"','"+lname+"','"+street+"','"+city+"','STATE', 'ZIP','"+phone+"','"+email+"','"+userid+"','"+pass+"','"+credit+"','"+creditno+"');";
                stmt.executeUpdate(sql);
                System.out.println(
                                "**********************************************************************\n" +
                                "***                                                                ***\n" +
                                "***                 Welcome to Online Book Store                   ***\n" +
                                "***                            Member Menu                         ***\n" +
                                "***                                                                ***\n" +
                                "**********************************************************************\n" +
                                "\n" +
                                "                     1. Browse by Subject\n" +
                                "\n" +
                                "                     2. Search by Author/Title/Subject\n" +
                                "\n" +
                                "                     3. View/Edit Shopping Cart\n" +
                                "\n" +
                                "                     4. Check Order Status\n" +
                                "\n" +
                                "                     5. Check Out\n" +
                                "\n" +
                                "                     6. One Click Check Out\n" +
                                "\n" +
                                "                     7. View/Edit Personal Information\n" +
                                "\n" +
                                "                     8. Logout\n"
                );

                input = keyboard.nextLine();
                if (input.equals('1'))
                {
                    sql="select  subject from books ";
                    ResultSet subjects=stmt.executeQuery(sql);
                    LinkedList<String> subjectlist=new LinkedList<String>();
                    int j=0;
                    while (subjects.next())
                    {
                        subjectlist.add(subjects.getString(j));
                        j++;

                    }
                    Collections.sort(subjectlist);
                    for (int i=0;i<subjectlist.size();i++)
                    {
                        System.out.println(subjectlist.get(i));
                    }
                    System.out.println("please choose a subject");
                    input=sc.nextLine();
                    // change this to get all book details :)
                    sql="select  * from `books` where 'subject'="+input;
                    ResultSet books=stmt.executeQuery(sql);
                    LinkedList<String> booklist=new LinkedList<String>();
                    j=0;
                    while (subjects.next())
                    {
                        booklist.add(books.getString(j));
                        j++;

                    }
                    for(int i=0;i<booklist.size()-1;i++)
                    {
                        System.out.println(booklist.get(i));
                        System.out.println(booklist.get(i+1));
                    }
                    System.out.println("do you want to enter an ISBN of a book ? " +
                            "press 1 for yes and 2 for no");
                    if (input.equals("1"))

                    {
                        // i think we should get the book first by a query then put it into cart because he just gives us the
                        //isbn not the book details
                        input=sc.nextLine();// ISBN
                        String x="";
                        System.out.println("please enter quantity");
                        x=sc.nextLine();
                        sql="INSERT into `galal`.`cart` (userid,input,x)";// change that one i stopped here

                    }
                    else
                    {
                        System.out.println("press enter for main menu and press N to continue browsing");
                    }
                }
                else if (input.equals("4"))
                {
                    ResultSet result = stmt.executeQuery("SELECT * FROM `orders`");
                    LinkedList<String> ids = new LinkedList<String>();
                    while(result.next()){
                    	ids.add(result.getString("ono"));
                    	System.out.println("Order number"+result.getString("ono")
                						+ "		Received: "+result.getString("received")
                						+ "		Shipped:  "+result.getString("shipped")
                						+ "		Address:  "+result.getString("shipAddress")
                    			);
                    }
                    input = sc.nextLine();
                    if(!ids.contains(input)){
                    	System.out.println("Sorry, Order not found.");
                    }
                    else{
                    	result = stmt.executeQuery("SELECT * FROM `orders`,`odetails` WHERE 'ono' ="+input);
                        while(result.next()){
                        	ids.add(result.getString("ono"));
                        	System.out.println("Order number"+result.getString("ono")
                    						+ "		Received: "+result.getString("received")
                    						+ "		Shipped:  "+result.getString("shipped")
                    						+ "		Address:  "+result.getString("shipAddress")
                    						+ "		ISBN:  "+result.getString("isbn")
                    						+ "		Quantity:  "+result.getString("qty")
                    						+ "		Price:  "+result.getString("price")
                        			);
                        }
                    }
                }
                else if (input.equals("6"))
                {
                    //salah
                }
            }


        }

    catch(Exception e){
    	System.out.println("Failed to connect");
    	
    }
    }
}

