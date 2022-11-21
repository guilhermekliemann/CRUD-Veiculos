package br.kliemann.flashcar.repository;

import br.kliemann.flashcar.database.DatabaseConnection;
import br.kliemann.flashcar.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ModeloRepository {
    
    private String INSERT = "insert into modelo(descricao, marca_id) values(?, ?);";
    private String UPDATE = "update modelo set descricao = ?, marca_id = ? where modelo_id = ?";
    private String DELETE = "delete modelo where modelo_id = ?";
    private String FIND_BY_ID = "select modelo_id, descricao from modelo, marca where marca_id = ?";
    private String FIND_ALL = "select modelo.modelo_id, modelo.descricao, marca.descricao from modelo, marca where modelo.marca_id = marca.marca_id";
    
    public void insert(Modelo modelo) throws SQLException {

        Connection conn = new DatabaseConnection().getConnection();
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, modelo.getDescricao());
            ps.setInt(2, modelo.getMarca_id().getMarca_id());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modelo cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Modelo ao cadastrar marca!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps);
        }

    }
    
    public ArrayList<Modelo> findAll() throws SQLException {

        Connection conn = new DatabaseConnection().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Modelo> modelos = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setModelo_id(rs.getInt("modelo.modelo_id"));
                modelo.setDescricao(rs.getString("modelo.descricao"));
                modelo.setMarca_id(new Marca());
                modelo.getMarca_id().setDescricao(rs.getString("marca.descricao"));
                modelos.add(modelo);
            }
        } catch(SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Erro ao listar modelos!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps, rs);
        }

        return modelos;
        
    }
    
    public void delete(Modelo modelo) throws SQLException {

        Connection conn = new DatabaseConnection().getConnection();;
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, modelo.getModelo_id());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modelo excluida com sucesso!");
        } catch(SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Erro ao excluir modelo!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps);
        }

    }
    
    public void update(Modelo modelo) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = new DatabaseConnection().getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, modelo.getDescricao());
            ps.setInt(2, modelo.getMarca_id().getMarca_id());
            ps.setInt(3, modelo.getModelo_id());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Modelo atualizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar modelo!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps);
        }

    }
    
    public Modelo findById(int id) throws SQLException {   

        Connection conn = new DatabaseConnection().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Modelo modelo = new Modelo();
                
        try {
            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                modelo.setDescricao(rs.getString("descricao"));
                modelo.setModelo_id(rs.getInt("modelo_id"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao procurar marca!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps, rs);
        }
        
        return modelo;

    }
    
}