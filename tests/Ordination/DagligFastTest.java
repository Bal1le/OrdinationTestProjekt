package Ordination;

import ordination.DagligFast;
import ordination.Laegemiddel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {
    private DagligFast dagligFast;

    @BeforeEach
    void setUp() {
        dagligFast = new DagligFast(LocalDate.of(2025, 3, 20), LocalDate.of(2025, 3 ,21), new Laegemiddel("TestMeds", 0.1, 0.2, 0.3, "pust"));
    }

    @Test
    void TC1opretDosis() {
        dagligFast.opretDosis(2.0);

        assertNotNull(dagligFast.getDoser()[0]);
        assertEquals(2.0, dagligFast.getDoser()[0].getAntal());
    }

    @Test
    void TC2opretDosis() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            dagligFast.opretDosis(5.0);
        });
        assertEquals("For mange antal er givet i dosis til sammen er skrevet ind (Maks 5)", e.getMessage());
    }
    @Test
    void TC1samletDosis() {
        dagligFast.opretDosis(2.0);

        double samletDosis = dagligFast.samletDosis();

        assertEquals(2.0, samletDosis);
    }

    @Test
    void TC2samletDosis() {
        dagligFast.opretDosis(-1.0);

        double samletDosis = dagligFast.samletDosis();

        assertEquals(0, samletDosis);
    }

    @Test
    void TC3samletDosis() {
        DagligFast dagligFastTC3 = new DagligFast(LocalDate.of(2025,3,20), LocalDate.of(2025,3,25), new Laegemiddel("TestMeds", 0.1,0.2,0.3, "pust"));

        dagligFastTC3.opretDosis(3.0);

        double samletDosis = dagligFastTC3.samletDosis();

        assertEquals(15, samletDosis);
    }

}