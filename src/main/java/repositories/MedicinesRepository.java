package repositories;
        import data.interfaces.IDB;
        import entities.Medicines;
        import repositories.interfaces.IMedicinesRepository;

        import javax.inject.Inject;
        import java.sql.*;
        import java.util.LinkedList;
        import java.util.List;

public class MedicinesRepository implements IMedicinesRepository  {
    @Inject
    private IDB db;

    @Override
    public boolean createMedicines(Medicines medicines) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO users(name,surname,gender) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, medicines.getName());
            st.setString(2, medicines.getSurname());
            st.setBoolean(3, medicines.getGender());

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Medicines getMedicinesById(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender FROM users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Medicines medicines = new Medicines(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender")) ;

                return medicines;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Medicines> getAllMedicines() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,surname,gender FROM users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Medicines> medicines = new LinkedList<>();
            while (rs.next()) {
                Medicines medicine = new Medicines(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender")) ;

                medicines.add(medicine);
            }

            return medicines;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}