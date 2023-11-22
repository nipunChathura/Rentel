package rental.lk.ijse.layered.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T, ID> extends SuperDAO{
    public boolean add(T entity) throws ClassNotFoundException, SQLException, IOException;
    public boolean delete(ID id) throws ClassNotFoundException, SQLException, IOException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException, IOException;
    public T getById(ID id) throws SQLException, ClassNotFoundException, IOException;
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException, IOException;
}
