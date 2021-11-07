package DAL_Services;

import DAL_IServices.IArea_Service;
import DAL_Models.ENTITY_Area;
import Utils.JDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Area_Service implements IArea_Service {
    String INSERT_SQL = "INSERT INTO [Area]([IDArea],[AreaName],[MaxTable]) VALUES(?,?,?);";
    String UPDATE_SQL = "UPDATE [Area] Set [AreaName]=?,[MaxTable]=?, WHERE [IDArea]=?";
    String DELETE_SQL = "DELETE [Area] WHERE [IDArea]=?";
    String SELECT_ALL_SQL = "SELECT * FROM [Area]";
    String SELECT_BY_ID_SQL = "SELECT * FROM [Area] WHERE [IDArea]=?";

    @Override
    public void insert(ENTITY_Area entity) {
        try {
            JDBC.update(INSERT_SQL,
                    entity.getIDArea(),
                    entity.getAreaName(),
                    entity.getMaxTable());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ENTITY_Area entity) {
        try {
            JDBC.update(UPDATE_SQL,
                    entity.getAreaName(),
                    entity.getMaxTable(),
                    entity.getIDArea());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IDArea) throws SQLException {
        JDBC.update(DELETE_SQL, IDArea);
    }

    @Override
    public List<ENTITY_Area> select() {
        return this.SelectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public ENTITY_Area findById(String IDTable) {
        List<ENTITY_Area> list = this.SelectBySQL(SELECT_BY_ID_SQL, IDTable);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ENTITY_Area> SelectBySQL(String sql, Object... args) {
        List<ENTITY_Area> list = new ArrayList<>();
        try {
            ResultSet rs = JDBC.query(sql, args);
            while (rs.next()) {
                ENTITY_Area area = new ENTITY_Area();
                area.setIDArea(rs.getInt("IDArea"));
                area.setAreaName(rs.getString("AreaName"));
                area.setMaxTable(rs.getInt("MaxTable"));
                list.add(area);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
