import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Team;
import model.Player;

public class DBConnection {
    public boolean insertNewTeam(Team team) {
        boolean result = false;
        Connection connection;
        connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tournamentdb", "root", "");

            String query = "INSERT INTO table_tournament "
                    + "(Team_Name, PlayerName_1, Position_1, PlayerName_2, Position_2, PlayerName_3, Position_3, PlayerName_4, Position_4, PlayerName_5, Position_5) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, team.getTeamName());
                
                for (int i = 0; i < team.getPlayers().size(); i++) {
                    preparedStatement.setString(2 + (i * 2), team.getPlayerName(i));
                    preparedStatement.setString(3 + (i * 2), team.getPlayerPosition(i));
                }
                // เติมค่าที่เหลือด้วย null ถ้ามีผู้เล่นน้อยกว่า 5 คน
                for (int i = team.getPlayers().size(); i < 5; i++) {
                    preparedStatement.setString(2 + (i * 2), null);
                    preparedStatement.setString(3 + (i * 2), null);
                }
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
                }
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        return result;
    }

    public Team getTeam(String teamName) {
        Team team = null;
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tournamentdb", "root", "");

            String query = "SELECT * FROM table_tournament WHERE Team_Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, teamName);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    team = new Team();
                    team.setTeamName(resultSet.getString("Team_Name"));
                    // Populate players
                    team.addPlayer(new Player(resultSet.getString("PlayerName_1"), resultSet.getString("Position_1")));
                    team.addPlayer(new Player(resultSet.getString("PlayerName_2"), resultSet.getString("Position_2")));
                    team.addPlayer(new Player(resultSet.getString("PlayerName_3"), resultSet.getString("Position_3")));
                    team.addPlayer(new Player(resultSet.getString("PlayerName_4"), resultSet.getString("Position_4")));
                    team.addPlayer(new Player(resultSet.getString("PlayerName_5"), resultSet.getString("Position_5")));
                }
            }
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        return team;
    }
}
