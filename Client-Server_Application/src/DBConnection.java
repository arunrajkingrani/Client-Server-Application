
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static String url, user, password;
    static void init1() {
        url = "jdbc:mysql://localhost/operationslog"; 
        user = "root"; 
        password = "pentforac1@1"; 
    }

    static void init() {
        url = "jdbc:mysql://localhost/project2"; 
        user = "root"; 
        password = "pentforac1@1"; 
    }

    public static Connection getConnection() {
       init();

       try {
           return DriverManager.getConnection(url, user, password);
       } catch (Exception ex) {
           System.out.println("Error Connection");
           ex.printStackTrace();
           return null;
       }
    }
    public static Connection getConnection1() {
        init1();

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println("Error Connection");
            ex.printStackTrace();
            return null;
        }
    }

}