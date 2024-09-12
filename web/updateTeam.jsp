<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Team"%>
<%@page import="model.Player"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <body>
        <% 
            Team team = (Team) session.getAttribute("team");
            if (team != null) {
        %>
            <p>แก้ไขข้อมูลทีม</p>
            <form action="AddTeamServlet">
                ชื่อทีม: <input type="text" name="teamName" value="<%= team.getTeamName() %>"><br/>
                <h3>รายชื่อผู้เล่น:</h3>
                <%
                    List<Player> players = team.getPlayers();
                    for (int i = 0; i < players.size(); i++) {
                        Player player = players.get(i);
                %>
                    ชื่อผู้เล่น: <input type="text" name="player<%=i+1%>Name" value="<%= player.getName() %>">
                    ตำแหน่ง: <input type="text" name="player<%=i+1%>Position" value="<%= player.getPosition() %>"><br/>
                <%
                    }
                %>
                <input type="submit" value="แก้ไข">
            </form>
            <% session.removeAttribute("team"); %>
        <% 
            } else {
        %>
            <p>ไม่มีข้อมูลทีมใน session</p>
        <% 
            }
        %>
    </body>
</html>

