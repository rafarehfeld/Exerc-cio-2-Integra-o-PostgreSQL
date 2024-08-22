package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.X;

public class XDAO {

    private Connection connect() {
        // Conectar ao PostgreSQL
        String url = "jdbc:postgresql://localhost:5432/teste";
        String user = "postgres";
        String password = "Faah2110";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<X> listar() {
        List<X> lista = new ArrayList<>();
        String sql = "SELECT * FROM X";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                X x = new X(rs.getInt("id"), rs.getString("nome"), 
                           rs.getString("descricao"), rs.getDouble("preco"), 
                           rs.getInt("quantidade"));
                lista.add(x);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public void inserir(X x) {
        String sql = "INSERT INTO X(nome, descricao, preco, quantidade) VALUES(?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, x.getNome());
            pstmt.setString(2, x.getDescricao());
            pstmt.setDouble(3, x.getPreco());
            pstmt.setInt(4, x.getQuantidade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizar(X x) {
        String sql = "UPDATE X SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, x.getNome());
            pstmt.setString(2, x.getDescricao());
            pstmt.setDouble(3, x.getPreco());
            pstmt.setInt(4, x.getQuantidade());
            pstmt.setInt(5, x.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM X WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
