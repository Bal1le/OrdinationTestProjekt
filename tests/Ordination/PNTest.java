import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ordination.*;

class PNTest {
    private PN pnOrdination;
    private LocalDate startDato;
    private LocalDate slutDato;

    @BeforeEach
    void setUp() {
        startDato = LocalDate.of(2025, 3, 17);
        slutDato = LocalDate.of(2025, 3, 19);
        Laegemiddel laegemiddel = new Laegemiddel("Paracetamol", 1.0, 1.5, 2.0, "mg");
        pnOrdination = new PN(startDato, slutDato, laegemiddel, 70);
    }

    @Test
    void TC1_GivDosis_ValidStartDate() {
        assertTrue(pnOrdination.givDosis(startDato));
        assertEquals(1, pnOrdination.getAntalGangeGivet());
    }

    @Test
    void TC2_GivDosis_ValidEndDate() {
        assertTrue(pnOrdination.givDosis(slutDato));
        assertEquals(1, pnOrdination.getAntalGangeGivet());
    }

    @Test
    void TC3_GivDosis_ValidMiddleDate() {
        LocalDate middleDate = LocalDate.of(2025, 3, 18);
        assertTrue(pnOrdination.givDosis(middleDate));
        assertEquals(1, pnOrdination.getAntalGangeGivet());
    }

    @Test
    void TC4_GivDosis_DateBeforeStartDate() {
        LocalDate beforeStart = startDato.minusDays(1);
        assertFalse(pnOrdination.givDosis(beforeStart));
        assertEquals(0, pnOrdination.getAntalGangeGivet());
    }

    @Test
    void TC5_GivDosis_DateAfterEndDate() {
        LocalDate afterEnd = slutDato.plusDays(1);
        assertFalse(pnOrdination.givDosis(afterEnd));
        assertEquals(0, pnOrdination.getAntalGangeGivet());
    }
}