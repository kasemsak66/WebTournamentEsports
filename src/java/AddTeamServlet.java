import model.Team;
import model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddTeamServlet", urlPatterns = {"/AddTeamServlet"})
public class AddTeamServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        // รับค่าจากผู้ใช้งาน
        String teamName = request.getParameter("teamName");
        String player1Name = request.getParameter("player1Name");
        String player1Position = request.getParameter("player1Position");
        String player2Name = request.getParameter("player2Name");
        String player2Position = request.getParameter("player2Position");
        String player3Name = request.getParameter("player3Name");
        String player3Position = request.getParameter("player3Position");
        String player4Name = request.getParameter("player4Name");
        String player4Position = request.getParameter("player4Position");
        String player5Name = request.getParameter("player5Name");
        String player5Position = request.getParameter("player5Position");

        // สร้าง instance ของ Team และ Player
        Team team = new Team();
        team.setTeamName(teamName);

        Player player1 = new Player(player1Name, player1Position);
        Player player2 = new Player(player2Name, player2Position);
        Player player3 = new Player(player3Name, player3Position);
        Player player4 = new Player(player4Name, player4Position);
        Player player5 = new Player(player5Name, player5Position);

        team.addPlayer(player1);
        team.addPlayer(player2);
        team.addPlayer(player3);
        team.addPlayer(player4);
        team.addPlayer(player5);
        
        DBConnection dbConnection = new DBConnection();
        if(!dbConnection.insertNewTeam(team)){
            System.out.println(">>> AddNewTeamMySQL ERROR");
        }
        // เก็บ team ไว้ที่ session
        HttpSession session = request.getSession();
        session.setAttribute("team", team);

        // forward ไป addTeamSuccess.jsp
        request.getRequestDispatcher("/addTeamSuccess.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "AddTeamServlet handles team creation and stores team information in session";
    }
}
