import ordination.DagligSkaev;
import ordination.Laegemiddel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorDagligSkaevTest {
    @Test
    void TC1Constructor() {
        LocalDate startDato = LocalDate.of(2025, 3, 20);
        LocalDate slutDato = LocalDate.of(2025, 3, 21);

        Laegemiddel laegemiddel = new Laegemiddel("paracetamol", 0.1,0.2,0.3, "styk");

        DagligSkaev dagligSkaev = new DagligSkaev(startDato, slutDato, laegemiddel);

        assertNotNull(dagligSkaev, "Objektet skal ikke være null");
    }

    @Test
    void TC2Constructor() {
        LocalDate startDato = LocalDate.now();
        LocalDate slutDato = null;
        Laegemiddel laegemiddel = new Laegemiddel("paracetamol", 0.1,0.2,0.3, "styk");

        assertThrows(NullPointerException.class, () -> {
            new DagligSkaev(startDato, slutDato, laegemiddel);
        });
    }
}