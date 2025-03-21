import ordination.DagligFast;
import ordination.Laegemiddel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorDagligFastTest {
    @Test
    void testConstructorDagligFastNotNull() {
        LocalDate startDato = LocalDate.of(2025,3,20);
        LocalDate slutDato = LocalDate.of(2025,3,21);
        Laegemiddel testLaegemiddel = new Laegemiddel("TestMedicin",0.1,0.2,0.3,"pust");

        DagligFast dagligFast = new DagligFast(startDato, slutDato, testLaegemiddel);


        assertNotNull(dagligFast, "Objekt ikke null");
    }

    @Test
    void testConstructorDagligFastThrowsException() {
        LocalDate startDato = LocalDate.of(2025,3,20);
        LocalDate slutDato = LocalDate.of(2025,3,19);
        Laegemiddel testLaegemiddel = new Laegemiddel("TestMedicin", 0.1,0.2,0.3,"Styk");

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new DagligFast(startDato, slutDato, testLaegemiddel);
        });

        assertEquals("Slutdato er før startdato", e.getMessage());
    }

    @Test
    void testOpretValidDosis() {
        Laegemiddel testLaegemiddel = new Laegemiddel("TestMedicin", 0.1,0.2,0.3,"Styk");
        DagligFast dagligFast = new DagligFast(java.time.LocalDate.now(), java.time.LocalDate.now().plusDays(10), testLaegemiddel);

        dagligFast.opretDosis(2.0);

        assertNotNull(dagligFast.getDoser()[0], "Det første indeks i arrayet burde ikke være null");
        assertEquals(2.0, dagligFast.getDoser()[0].getAntal(), 0.0001, "Dosis skal være 2.0 i dette eksempel.");
    }
}