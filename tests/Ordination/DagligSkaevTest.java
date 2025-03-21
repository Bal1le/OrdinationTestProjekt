package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {
    private DagligSkaev dagligSkaev;

    @BeforeEach
    void setUp() {
        dagligSkaev = new DagligSkaev(LocalDate.of(2025,3,20), LocalDate.of(2025,3,21), new Laegemiddel("paracetamol",0.1,0.2,0.3, "styk"));
    }

    @Test
    void TC1opretDosis() {
        LocalTime tid = LocalTime.of(8,0);
        double antal = 2.0;

        dagligSkaev.opretDosis(tid,antal);

        assertFalse(dagligSkaev.getDoser().isEmpty());
        assertEquals(1, dagligSkaev.getDoser().size());
        assertEquals(2.0, dagligSkaev.getDoser().get(0).getAntal());
    }

    @Test
    void TC2opretDosis() {
        LocalTime tid = LocalTime.of(14,30);
        double antal = 0.0;

        dagligSkaev.opretDosis(tid,antal);

        assertTrue(dagligSkaev.getDoser().isEmpty()); // Dosis må ikke oprettes antal = 0;
    }

    @Test
    void TC3opretDosis() {
        LocalTime tid = LocalTime.of(18,45);
        double antal = -1.0;

        try {
            dagligSkaev.opretDosis(tid, antal);
        } catch (IllegalArgumentException e) {
            // Expected exception, test continues
        }

        assertTrue(dagligSkaev.getDoser().isEmpty(), "Dosis må ikke oprettes, når antal er negativt.");
    }

    @Test
    void TC4opretDosis() {
        LocalTime tid = null;
        double antal = 2.0;

        assertThrows(NullPointerException.class, () -> {
            dagligSkaev.opretDosis(tid,antal);
        });
    }

    @Test
    void TC1samletDosis() {
        dagligSkaev.opretDosis(LocalTime.of(8,0), 2.0);

        double samletDosis = dagligSkaev.samletDosis();

        assertEquals(2.0, samletDosis);
    }

    @Test
    void TC2samletDosis() {
        dagligSkaev.opretDosis(LocalTime.of(8,0), -1.0);

        double samletDosis = dagligSkaev.samletDosis();

        assertEquals(0, samletDosis);
    }

    @Test
    void TC3samletDosis() {
        DagligSkaev dagligSkaevTC3 = new DagligSkaev(LocalDate.of(2025,3,20), LocalDate.of(2025,3,25), new Laegemiddel("paracetamol", 0.1,0.2,0.3, "styk"));

        dagligSkaevTC3.opretDosis(LocalTime.of(8,0), 3.0);

        double samletDosis = dagligSkaevTC3.samletDosis();

        assertEquals(15.0, samletDosis);
    }
}
