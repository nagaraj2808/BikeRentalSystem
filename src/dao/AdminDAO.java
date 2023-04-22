package dao;

import Models.Admin;


import java.sql.SQLException;


public interface AdminDAO {
    void addAdmin(Admin admin) throws SQLException;
    Admin searchAdmin(String adminName) throws SQLException;
}
