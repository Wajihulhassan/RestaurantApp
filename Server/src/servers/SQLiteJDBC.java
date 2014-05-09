package servers;

/*  SQLITE USERNAME / PASSWORD
 * 		   MENU
 * 
 * Included Functions for Users table:
 *  public static void createUsertable() -> create username table in DB
 *  public static void connectUsertable() -> Example on simple DB connection
 *  public static void insertUser(String inName, String inJob, String inPassword) -> insert new user in to DB
 * 	public static void deleteUser(String inName, String inPassword)  -> Delete User
 *  public static boolean authenticateUser(String inName, String inPassword) -> Return true if username and password match
 * 	public static void printUserlist() -> print usernames and passwords to console
 * 
 *  public static void createMenutable() -> create menu table
 *  public static void printMenu()  -> prints menu to console
 *  public static void printMenucategory(String inCategory)  -> prints only one category to console
 *  public static void insertMenuitem(String inCategory, String inName, String inPrice) -> Insert item into menu table
 *  public static void deleteMenuitem(String inCategory, String inName) -> Delete Item from menu
 */

import java.security.GuardedObject;
import java.sql.*;

public class SQLiteJDBC {
	public SQLiteJDBC() {

		// ONLY run if DB doesn't exist. These three functions create the tables
		//createUsertable();
		//createMenutable();
		//createOrdertable();
		// ///////////////////

		// insertMenuitem("Drink" , "Blood", "6.66");
		// deleteMenuitem("Drink", "Lemonaid");
		 //printMenu();
		 //printMenucategory("Drink");

		 //insertUser("Paul", "Manager", "a");
		// System.out.println(authenticateManager("Jay", "Poop"));
		// System.out.println(authenticateUser("Jay", "Poop"));
		   printuserlistConsole();
		// deleteUser("Jay", "Poop");
		
		//insertOrder(1,"fries");
		//deleteOrder(1);
		//printOrderslist();
	}

	
	

	//AUTHENTICATE FUNCTIONS---------------------
	public static boolean authenticateManager(String inName, String inPassword) {
		Connection c = null;
		Statement stmt = null;
		boolean authenticated = false;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
			while (rs.next()) {
				String name = rs.getString("name");
				String job = rs.getString("job");
				String password = rs.getString("password");
				if (name.equals(inName) && job.equals("Manager")
						&& password.equals(inPassword)) {
					authenticated = true;
					rs.close();
					stmt.close();
					c.close();
					System.out.println("Database closed.");
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return authenticated;
	}
	
	public static boolean authenticateUser(String inName, String inPassword) {
		Connection c = null;
		Statement stmt = null;
		boolean authenticated = false;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
			while (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				if (name.equals(inName) && password.equals(inPassword)) {
					authenticated = true;
					rs.close();
					stmt.close();
					c.close();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return authenticated;
	}
	
	//SEARCH FUMCTIONS RETURN StringArray[2] 
	//I chose to just return 3 strings in an array here. DB
	public static String[] jobSearch(String inJob) {
		String[] userInfo = new String[5];
		userInfo[0] = "";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
			while (rs.next()) {					
				String name = rs.getString("name");
				String job = rs.getString("job");
				String password = rs.getString("password");
				if (job.equals(inJob)) {
				userInfo[0] = (name);
			    userInfo[1] = (job);
				userInfo[2] = (password);
					rs.close();
					stmt.close();
					c.close();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return userInfo;
	}
	
	public static String[] nameSearch(String inName) {
		String[] userInfo = new String[5];
		userInfo[0]="";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully for namesearch");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
			while (rs.next()) {					
				String name = rs.getString("name");
				String job = rs.getString("job");
				String password = rs.getString("password");
				if (name.equals(inName)) {
				System.out.println("found name ");
				userInfo[0] = (name);
			    userInfo[1] = (job);
				userInfo[2] = (password);
					rs.close();
					stmt.close();
					c.close();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return userInfo;
	}

	
	//ORDERS TABLE FUNCTIONS----------------------
	public static void createOrdertable() {
		{
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
				System.out.println("Opened database successfully");
				stmt = c.createStatement();
				String sql = "CREATE TABLE ORDERS "
						+ "(TBLNUM         INT    NOT NULL, "
						+ " ORDERSTR       TEXT   NOT NULL)";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
				System.exit(0);
			}
			System.out.println("Table created successfully");
		}
	}
	
	public static void printorderslistConsole() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS;");
			while (rs.next()) {
				int name = rs.getInt("tblnum");
				String order = rs.getString("orderstr");
				System.out.println("TABLE = " + name);
				System.out.println("ORDER = " + order);
				System.out.println("---------------------");
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
	
	public static void insertOrder(int inTable, String inOrder) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO ORDERS (TBLNUM, ORDERSTR) " + "VALUES ('"
					+ inTable + "', '" + inOrder +  "');";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + e.getMessage());
			System.exit(0);
		}
		System.out.println("Order inserted successfully");
	}
	
	
	public static void deleteOrder(int inTable) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "DELETE from ORDERS WHERE TBLNUM='" + inTable + "'";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			System.out.println("Database closed.");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	//USERS TABLE FUCTIONS-----------------------
	public static void createUsertable() {
		{
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
				System.out.println("Opened database successfully");
				stmt = c.createStatement();
				String sql = "CREATE TABLE USERS "
						+ "(NAME           TEXT    NOT NULL, "
						+ " JOB            TEXT    NOT NULL, "
						+ " PASSWORD       TEXT    NOT NULL)";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
				System.exit(0);
			}
			System.out.println("Table created successfully");
		}
	}

	public static void printuserlistConsole() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS;");
			while (rs.next()) {
				String name = rs.getString("name");
				String job = rs.getString("job");
				String password = rs.getString("password");
				System.out.println("NAME = " + name);
				System.out.println("JOB = " + job);
				System.out.println("PASSWORD = " + password);
				System.out.println("---------------------");
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public static void insertUser(String inName, String inJob, String inPassword) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened usertable successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO USERS (NAME,JOB,PASSWORD) " + "VALUES ('"
					+ inName + "', '" + inJob + "', '" + inPassword + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + e.getMessage());
			System.exit(0);
		}
		System.out.println("User Added successfully");
	}
	
	public static void deleteUser(String inName) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "DELETE from USERS WHERE NAME='" + inName + "'";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			System.out.println("User deleted successfully closed.");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	// Price needs to be recast to be used in math functions
	//MENU TABLE FUNCTIONS ----------------------
	public static void createMenutable() {
		{
			Connection c = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
				System.out.println("Opened database successfully");
				stmt = c.createStatement();
				String sql = "CREATE TABLE MENU "
						+ "(CATEGORY        TEXT    NOT NULL, "
						+ " NAME            TEXT    NOT NULL, "
						+ " PRICE           TEXT    NOT NULL)";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
				System.exit(0);
			}
			System.out.println("Table created successfully");
		}
	}

	public static void printmenuConsole() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM MENU;");
			while (rs.next()) {
				String category = rs.getString("category");
				String name = rs.getString("name");
				String price = rs.getString("price");
				System.out.println("CATEGORY = " + category);
				System.out.println("NAME = " + name);
				System.out.println("PRICE = " + price);
				System.out.println("---------------------");
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public static void printmenucategoryConsole(String inCategory) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM MENU;");
			while (rs.next()) {
				String category = rs.getString("category");
				String name = rs.getString("name");
				String price = rs.getString("price");
				if (category.equals(inCategory)) {
					System.out.println("CATEGORY = " + category);
					System.out.println("NAME = " + name);
					System.out.println("PRICE = " + price);
					System.out.println("---------------------");
				}
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public static void insertMenuitem(String inCategory, String inName,
			String inPrice) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO MENU (CATEGORY,NAME,PRICE) "
					+ "VALUES ('" + inCategory + "', '" + inName + "', '"
					+ inPrice + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static void deleteMenuitem(String inCategory, String inName) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "DELETE from MENU WHERE CATEGORY='" + inCategory
					+ "' AND NAME = '" + inName + "'";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();
			c.close();
			System.out.println("Database closed.");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

 //////SAMPLE CONNECT FUNCTION
	public static void connectUsertable() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Restuarant.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

}