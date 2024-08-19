import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Connection connection;

    public AutorDAO() throws SQLException {
        this.connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserirAutor(Autor autor) throws SQLException {
        String sql = "INSERT INTO Autor (ID_Autor, Nome, Nacionalidade) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, autor.getIdAutor());
            stmt.setString(2, autor.getNome());
            stmt.setString(3, autor.getNacionalidade());
            stmt.executeUpdate();
        }
    }

    public void atualizarAutor(Autor autor) throws SQLException {
        String sql = "UPDATE Autor SET Nome = ?, Nacionalidade = ? WHERE ID_Autor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getIdAutor());
            stmt.executeUpdate();
        }
    }

    public void excluirAutor(int idAutor) throws SQLException {
        String sql = "DELETE FROM Autor WHERE ID_Autor = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            stmt.executeUpdate();
        }
    }

    public List<Autor> listarAutores() throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Autor autor = new Autor(rs.getInt("ID_Autor"), rs.getString("Nome"), rs.getString("Nacionalidade"));
                autores.add(autor);
            }
        }
        return autores;
    }
}
