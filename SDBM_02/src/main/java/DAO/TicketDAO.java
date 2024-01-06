package DAO;


import Metier.Ticket;
import Metier.idTicket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private List<Ticket> tickets;

    public TicketDAO() {
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public List<Ticket> getAllTickets() {

        String sqlRequest = "Select *from TICKET";
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection sdbmConnection = SDBMConnection.getInstance();
             CallableStatement callableStatement = sdbmConnection.prepareCall(sqlRequest)) {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int annee = resultSet.getInt("annee");
                int numeroTicket = resultSet.getInt("numero_ticket");
                Date dateTicket = resultSet.getDate("dateTicket");
                String heureTicket = resultSet.getString("heureTicket");

                idTicket idTicket = new idTicket(annee, numeroTicket);
                Ticket ticket = new Ticket(idTicket, dateTicket, heureTicket);
                ticketList.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;

    }
}
