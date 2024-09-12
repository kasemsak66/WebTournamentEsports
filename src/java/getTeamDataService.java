import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Team;
import model.Player;
import java.io.IOException;
import java.net.URLDecoder;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "getTeamDataService", urlPatterns = {"/getTeamDataService"})
public class getTeamDataService extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String requestUrl = request.getRequestURI();
        String teamName = requestUrl.substring("/getTeamDataService/".length());
        teamName = URLDecoder.decode(teamName, "UTF-8");

        System.out.println("Request URL: " + requestUrl);
        System.out.println("Team Name: " + teamName);

        Team team = getTeam(teamName); // Call method to get team from database

        JSONObject teamJson = new JSONObject();
        if (team != null) {
            teamJson.put("teamName", team.getTeamName());
            JSONArray playersJson = new JSONArray();
            for (Player player : team.getPlayers()) {
                JSONObject playerJson = new JSONObject();
                playerJson.put("name", player.getName());
                playerJson.put("position", player.getPosition());
                playersJson.put(playerJson);
            }
            teamJson.put("players", playersJson);
        }

        response.getWriter().print(teamJson.toString());
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
        return "getTeamDataService provides team data in JSON format";
    }

    private Team getTeam(String teamName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
