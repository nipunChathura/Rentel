package rental.lk.ijse.layered.db;

import rental.lk.ijse.layered.util.Constants;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static DBConnection dbConnection;
    private Connection connection;
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    private DBConnection() throws ClassNotFoundException, SQLException, IOException {
        loadPropertyValue();

        Class.forName(driver);
        connection= DriverManager.getConnection(url,user,password);
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException, IOException {
        if(dbConnection==null){
            dbConnection=new DBConnection();
            return dbConnection;
        }
        return dbConnection;

    }

    public Connection getConnection(){
        return connection;
    }

    private void loadPropertyValue() throws IOException {
//        FileInputStream stream = new FileInputStream("../src/rental/lk/ijse/layered/resource/config. properties");
//        FileReader reader = new FileReader(Constants.PROPERTY_FILE_NAME);

//        InputStream stream = getClass().getResourceAsStream("config. properties");

        FileInputStream stream = new FileInputStream("src\\rental\\lk\\ijse\\layered\\resource\\config. properties");

        System.out.println("stream = " + stream);
        Properties properties = new Properties();
        properties.load(stream);
        driver = properties.getProperty(Constants.DIVER_CLASS);
        System.out.println("driver = " + driver);
        url = properties.getProperty(Constants.DB_URL);
        user = properties.getProperty(Constants.DB_USERNAME);
        password = properties.getProperty(Constants.DB_PASSWORD);
    }


}
