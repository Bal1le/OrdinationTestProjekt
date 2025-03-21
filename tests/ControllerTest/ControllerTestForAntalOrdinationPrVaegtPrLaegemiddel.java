import controller.Controller;
import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTestForAntalOrdinationPrVaegtPrLaegemiddel {

    private Controller controller;
    private Storage storage;
    private Laegemiddel testLaegemiddel;

    @BeforeEach
    void setUp() {
        controller = Controller.getTestController();
        controller.createSomeObjects();


        ArrayList<Laegemiddel> laegemiddeler = (ArrayList<Laegemiddel>) controller.getAllLaegemidler();

        for (Laegemiddel laegemiddel : laegemiddeler) {
            if(laegemiddel.getNavn().equals("Paracetamol")) {
                testLaegemiddel = laegemiddel;
                break;
            }
        }

    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVaegtPrLaegemiddelTC1(){

        int actual = controller.antalOrdinationerPrVaegtPrLaegemiddel(50,75,testLaegemiddel);

        assertEquals(2,actual);

    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVaegtPrLaegemiddelTC2(){

        int actual = controller.antalOrdinationerPrVaegtPrLaegemiddel(75,50,testLaegemiddel);

        assertEquals(0,actual);

    }

    @org.junit.jupiter.api.Test
    void antalOrdinationerPrVaegtPrLaegemiddelTC3(){

        int actual = controller.antalOrdinationerPrVaegtPrLaegemiddel(-5,75,testLaegemiddel);

        assertEquals(2,actual);

    }


}
