package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJdbc implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1,obj.getName());

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                rs = st.getGeneratedKeys();
                System.out.println(rowsAffected + " rows affected");
                if(rs.next()){
                    obj.setId(rs.getInt(1));
                }
            }

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
            st.setString(1,obj.getName());
            st.setInt(2,obj.getId());

            st.executeUpdate();

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1,id);

            Integer result = st.executeUpdate();
            if(result > 0){
                System.out.println("Departamento excluido com sucesso!");
            }else{
                System.out.println("ERROR! Nao existe departamento com numero de ID informado");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();

            if(rs.next()){
                Department department = instanciateDepartment(rs);
                return department;
            }

            System.out.println("ERROR! O numero de Id solicitado não retornou resultado.");
            return null;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Department instanciateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT * FROM department"
            );
            rs = st.executeQuery();

            List<Department> departments = new ArrayList<>();
            while(rs.next()){
                Department dep = instanciateDepartment(rs);
                departments.add(dep);
            }
            return departments;
        }catch(SQLException e){
            throw new DbException(e.getMessage()) ;
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
