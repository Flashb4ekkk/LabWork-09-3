import org.example.Main;
import org.example.Route;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void testSortArray() {
        Route[] routes = new Route[3];
        routes[0] = new Route("Lviv", "Kyiv", 3);
        routes[1] = new Route("Kyiv", "Odessa", 1);
        routes[2] = new Route("Odessa", "Lviv", 2);

        Main.sortArray(routes);

        Assertions.assertEquals(1, routes[0].getNumber());
        Assertions.assertEquals(2, routes[1].getNumber());
        Assertions.assertEquals(3, routes[2].getNumber());
    }
}
