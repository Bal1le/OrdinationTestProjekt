package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class DagligFast extends Ordination {

    private Dosis[] dosis= new Dosis[4];

    public DagligFast(LocalDate startDato, LocalDate slutDato, Laegemiddel laegemiddel) {
        super(startDato, slutDato, laegemiddel);

        if(slutDato.isBefore(startDato))
            throw new IllegalArgumentException("Slutdato er før startdato");
    }

    public void opretDosis(double antal){

        int antalNuværendeDosis = 0;

        for (Dosis dosi : dosis) {
            if(dosi != null) //Grunden til det skal checks er at der kan godt ligge noget på pos 0,1 og 3. Hvilket betyder i check at 2 er tom.
                antalNuværendeDosis += dosi.getAntal(); //Det samme hvis 3 var tom.
        }

        if(antalNuværendeDosis == 4)
            throw new IllegalArgumentException("Der er allerede 4 dosis antal");

        else if(antalNuværendeDosis + antal > 4)
            throw new IllegalArgumentException("For mange antal er givet i dosis til sammen er skrevet ind (Maks 5)");

        for(int index = 0; index < dosis.length; index++)
            if(dosis[index] == null)
                dosis[index] = new Dosis(LocalTime.of(00,00),antal);

    }


    @Override
    public double samletDosis() {

        int periode = super.getSlutDato().compareTo(super.getStartDato());

        int antalDagligdosis = 0;

        for (Dosis dosi : dosis) {
            antalDagligdosis += dosi.getAntal();
        }

        //Sørger for at der ikke kan kom minus
        if(periode < 0)
            throw new IllegalArgumentException("Slutdato er før startdato");


        return antalDagligdosis*periode;
    }

    @Override
    public double doegnDosis() {
        int antalDagligdosis = 0;

        for (Dosis dosi : dosis) {
            antalDagligdosis += dosi.getAntal();
        }

        return antalDagligdosis;
    }

    @Override
    public String getType() {
        return super.getLaegemiddel().getNavn();
    }

    public Dosis[] getDoser() {
        return dosis;
    }
}
