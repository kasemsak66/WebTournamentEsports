<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Team"%>
<%@page import="java.util.List"%>
<%@page import="model.Player"%>
<!DOCTYPE html>
<html>
    <body>
        <!-- get session object team -->
        <% 
            Team team = (Team) session.getAttribute("team");
            if (team != null) {
        %>
            <p>ข้อมูลทีมที่เพิ่มได้แล้ว</p>
            ชื่อทีม: <%= team.getTeamName() %><br/>
            <h3>รายชื่อผู้เล่น:</h3>
            <%
                List<Player> players = team.getPlayers();
                for (Player player : players) {
            %>
                ชื่อผู้เล่น: <%= player.getName() %> ตำแหน่ง: <%= player.getPosition() %><br/>
            <%
                }
            %>
            <a href="updateTeam.jsp">แก้ไขข้อมูลทีม</a>
        <% 
            } else {
        %>
            <p>ไม่มีข้อมูลทีมใน session</p>
        <% 
            }
        %>
    </body>
</html>
