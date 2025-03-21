package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SamletDosisDagligSkaevTest {
    private DagligSkaev dagligSkaev;

    @BeforeEach
    void setUp() {
        dagligSkaev = new DagligSkaev(LocalDate.of(2025,3,20), LocalDate.of(2025,3,21), new Laegemiddel("paracetamol", 0.1,0.2,0.3,"styk"));
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