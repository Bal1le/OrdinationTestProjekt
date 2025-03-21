package ConstructorTest;
import ordination.Laegemiddel;
import ordination.PN;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PNConstructorTest {

    @Test
    void TC1testPN() {
        LocalDate startDato = LocalDate.of(2025,3,21);
        LocalDate slutDato = LocalDate.of(2025,3, 22);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 0.1, 0.2, 0.3, "Styk");

        PN pnTest = new PN(startDato, slutDato, laegemiddel, 25);
        assertNotNull(pnTest);
    }

    @Test
    void TC2testPN() {
        LocalDate startDato = LocalDate.of(2025,3,21);
        LocalDate slutDato = LocalDate.of(2025,3,20);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 0.1,0.2,0.3, "Styk");

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new PN(startDato, slutDato, laegemiddel, 25);
        });
        assertEquals("Slutdato er før startdato", e.getMessage());
    }

    @Test
    void TC3testPN() {
        LocalDate startDato = LocalDate.of(2025,3,21);
        LocalDate slutDato = LocalDate.of(2025,3,22);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 0.1, 0.2, 0.3, "Styk");

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            new PN(startDato, slutDato, laegemiddel, -25);
        });
        assertEquals("Du kan ikke have minus vægt", e.getMessage());
    }

    @Test
    void TC4testPN() {
        LocalDate startDato = LocalDate.of(2025,3,21);
        LocalDate slutDato = LocalDate.of(2025,3,21);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 0.1,0.2,0.3, "Styk");

        PN pnTest = new PN(startDato, slutDato, laegemiddel, 50);

        assertNotNull(pnTest);
    }
}