package DAL_IServices;

import DAL_Models.ENTITY_Area;

import java.sql.SQLException;
import java.util.List;

public interface IArea_Service {
    public void insert(ENTITY_Area entity);

    public void update(ENTITY_Area entity);

    public void delete(String IDTable) throws SQLException;

    public List<ENTITY_Area> select();

    public ENTITY_Area findById(String IDTable);

    public List<ENTITY_Area> SelectBySQL(String sql, Object... args);
}
