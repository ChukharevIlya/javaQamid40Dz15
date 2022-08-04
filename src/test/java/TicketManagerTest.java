import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketManagerTest {
    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    Ticket ticket1 = new Ticket(1, 1000, "VKO", "ARH", 150);
    Ticket ticket2 = new Ticket(2,2000, "VKO", "LED", 90);
    Ticket ticket3 = new Ticket(3,3000, "VKO", "ROV", 80);
    Ticket ticket4 = new Ticket(4,1500, "VKO", "ARH", 120);
    Ticket ticket5 = new Ticket(5,3500, "VKO", "LED", 100);
    Ticket ticket6 = new Ticket(6,6000, "VKO", "ROV", 110);
    Ticket ticket7 = new Ticket(7,2000, "VKO", "ARH", 130);
    Ticket ticket8 = new Ticket(8,4000, "VKO", "LED", 110);
    Ticket ticket9 = new Ticket(9,5500, "VKO", "ROV", 120);

    @Test
    public void shouldAddOneTicket() {
        manager.add(ticket1);

        Ticket[] expected = { ticket1 };
        Ticket[] actual = repo.getTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddTwoTickets() {
        manager.add(ticket1);
        manager.add(ticket2);

        Ticket[] expected = { ticket1, ticket2 };
        Ticket[] actual = repo.getTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddSeveralTickets() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] expected = { ticket1, ticket2, ticket3 };
        Ticket[] actual = repo.getTickets();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTicketsByAirports() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);

        Ticket[] expected = {};
        Ticket[] actual = manager.searchByAirport("ROV", "LED");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneTicketByAirports() {
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);

        Ticket[] expected = { ticket7 };
        Ticket[] actual = manager.searchByAirport("VKO", "ARH");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindTwoTicketsByAirports() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);

        Ticket[] expected = { ticket1, ticket7 };
        Ticket[] actual = manager.searchByAirport("VKO", "ARH");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindAllTicketsByAirportsAndSortThem() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        manager.add(ticket9);

        Ticket[] expected = { ticket3, ticket9, ticket6 };
        Ticket[] actual = manager.searchByAirport("VKO", "ROV");

        Assertions.assertArrayEquals(expected, actual);
    }
}
