package rental.lk.ijse.layered.util;

import rental.lk.ijse.layered.db.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

    private static PreparedStatement getPreparedStatement(String sql, Object... params) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            pstm.setObject((i + 1), params[i]);
        }

        return pstm;

    }

    public static boolean executeUpdate(String sql, Object... params) throws ClassNotFoundException, SQLException, IOException {
        return getPreparedStatement(sql, params).executeUpdate() > 0;
    }

    public static ResultSet executeQuery(String sql, Object... params) throws ClassNotFoundException, SQLException, IOException {
        return getPreparedStatement(sql, params).executeQuery();
    }
}
