package Metier;
import java.util.Date;

public class Ticket {
    private idTicket idticket;
    private String heureTicket;
    private Date dateTicket;


    public Ticket(idTicket idticket, Date dateTicket, String heureTicket) {
        this.idticket = idticket;
        this.heureTicket = heureTicket;
        this.dateTicket = dateTicket;
    }

    public idTicket getIdticket(){
        return idticket;
    }

    public void setIdticket(idTicket idticket) {
        this.idticket = idticket;
    }
    public String getHeureTicket() {
        return heureTicket;
    }

    public void setHeureTicket(String heureTicket) {
        this.heureTicket = heureTicket;
    }

    public Date getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(Date dateTicket) {
        this.dateTicket = dateTicket;
    }
}
