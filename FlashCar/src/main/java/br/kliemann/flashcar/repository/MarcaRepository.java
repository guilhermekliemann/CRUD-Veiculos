package br.kliemann.flashcar.repository;

import br.kliemann.flashcar.database.DatabaseConnection;
import br.kliemann.flashcar.model.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MarcaRepository {
    
    private String INSERT = "insert into marca(descricao) values(?);";
    private String UPDATE = "update marca set descricao = ? where marca_id = ?";
    private String DELETE = "delete marca where marca_id = ?";
    private String FIND_BY_ID = "select marca_id, descricao from marca where marca_id = ?";
    private String FIND_ALL = "select marca_id, descricao from marca";
    
    public void insert(Marca marca) throws SQLException {

        Connection conn = new DatabaseConnection().getConnection();
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, marca.getDescricao());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Marca cadastrada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar marca!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps);
        }

    }
    
    public ArrayList<Marca> findAll() throws SQLException {

        Connection conn = new DatabaseConnection().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Marca> marcas = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                Marca marca = new Marca();
                marca.setMarca_id(rs.getInt("marca_id"));
                marca.setDescricao(rs.getString("descricao"));
                marcas.add(marca);
            }
        } catch(SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Erro ao listar marcas!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps, rs);
        }

        return marcas;
        
    }
    
    public void delete(Marca marca) throws SQLException {

        Connection conn = new DatabaseConnection().getConnection();;
        PreparedStatement ps = null;
        
        try {
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, marca.getMarca_id());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Marca excluida com sucesso!");
        } catch(SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Erro ao excluir marca!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps);
        }

    }
    
    public void update(Marca marca) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = new DatabaseConnection().getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, marca.getDescricao());
            ps.setInt(2, marca.getMarca_id());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Marca atualizada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar marca!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps);
        }

    }
    
    public Marca findById(int id) throws SQLException {   

        Connection conn = new DatabaseConnection().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Marca marca = new Marca();
                
        try {
            ps = conn.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while(rs.next()){
                marca.setDescricao(rs.getString("descricao"));
                marca.setMarca_id(rs.getInt("marca_id"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao procurar marca!");
        } finally {
            DatabaseConnection.closeConnection(conn, ps, rs);
        }
        
        return marca;

    }
    
}