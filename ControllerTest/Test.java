package controller;


import ordination.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;




class Test {
    private Controller controller;




    @BeforeEach
    void setUp() {
        controller = Controller.getTestController();
    }


    @org.junit.jupiter.api.Test
    void opretPatient() {
        Patient patient = controller.opretPatient("123456-7890", "TEST", 75.0);
        assertEquals("TEST", patient.getNavn());
        assertEquals(75.0, patient.getVaegt());
    }


    @org.junit.jupiter.api.Test
    void opretLaegemiddel() {
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Pandodil", 0.5, 2, 3, "Styk");
        assertEquals("Pandodil", laegemiddel.getNavn());
    }


    @org.junit.jupiter.api.Test
    void opretPNOrdination() {
        Patient patient = controller.opretPatient("123456-7890", "TEST", 80.0);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Pandodil", 0.5, 2, 3, "Styk");


        PN pn = controller.opretPNOrdination(LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 10), patient, laegemiddel, 2.0);
        assertEquals(laegemiddel, pn.getLaegemiddel(), "Medicinen skal være den samme, som den der er lavet");
        assertEquals(2.0, pn.getAntalEnheder());
    }


    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination() {
        Patient patient = controller.opretPatient("123456-7890", "TEST", 80.0);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Pandodil", 0.5, 2, 3, "Styk");


        DagligFast dagligFast = controller.opretDagligFastOrdination(LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 10), patient, laegemiddel, 1.0, 1.0, 1.0, 1.0);


        assertEquals(4, dagligFast.getDoser().length);
    }


    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdination() {
        Patient patient = controller.opretPatient("123456-7890", "TEST", 80.0);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Pandodil", 0.5, 2, 3, "Styk");


        LocalTime[] klokkeslet = {LocalTime.of(8, 0), LocalTime.of(12, 0), LocalTime.of(18, 0)};
        double[] doser = {0.5, 2.0, 3.0};


        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(LocalDate.of(2025, 3, 1),
                LocalDate.of(2025, 3, 10), patient, laegemiddel, klokkeslet, doser);




        assertEquals(3, dagligSkaev.getDoser().size());
        // Tjekker, at der er oprettet præcis 3. doser svarende til længden af arrayet.


        assertEquals(0.5, dagligSkaev.getDoser().get(0).getAntal());
        assertEquals(2.0, dagligSkaev.getDoser().get(1).getAntal());
        assertEquals(3.0, dagligSkaev.getDoser().get(2).getAntal());
        //Tjekker, at de rigtige doser, er registreret på de rigtige tidspunkter


        assertEquals(LocalTime.of(8, 0), dagligSkaev.getDoser().get(0).getTid());
        assertEquals(LocalTime.of(12, 0), dagligSkaev.getDoser().get(1).getTid());
        assertEquals(LocalTime.of(18, 0), dagligSkaev.getDoser().get(2).getTid());
        //Tjekker, at tidspunkterne for doserne er registreret korrekt




    }


    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn() {
        Patient patient = controller.opretPatient("123456-7890", "TEST", 80.0);
        Laegemiddel laegemiddel = controller.opretLaegemiddel("Pandodil", 0.5, 2, 3, "Styk");


        double anbefalerDosis = controller.anbefaletDosisPrDoegn(patient, laegemiddel);
        assertEquals(2.0, anbefalerDosis);
    }
}

