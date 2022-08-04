import java.util.Arrays;

public class TicketManager {
    private TicketRepository repo;

    public TicketManager(TicketRepository repo) {
        this.repo = repo;
    }

    public void add(Ticket ticket) {
        repo.save(ticket);
    }

    public Ticket[] searchByAirport(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repo.findAll(from, to)) {
            if (matchesFrom(ticket, from) && matchesTo(ticket,to) ) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public boolean matchesFrom(Ticket ticket, String search) {
        if (ticket.getAirportFrom().contains(search)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean matchesTo(Ticket ticket, String search) {
        if (ticket.getAirportTo().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
