package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class DagligFast extends Ordination {

    private Dosis[] dosis= new Dosis[4];

    public DagligFast(LocalDate startDato, LocalDate slutDato, Laegemiddel laegemiddel) {
        super(startDato, slutDato, laegemiddel);
    }

    public void opretDosis(LocalTime tid, double antal ){

        int antalNuværendeDosis = 0;

        for (Dosis dosi : dosis) {
            if(dosi != null)
                antalNuværendeDosis += dosi.getAntal();
        }

        if(antalNuværendeDosis == 4) return;
        else if(antalNuværendeDosis + antal > 4) return;

        for(int index = 0; index < dosis.length; index++)
            if(dosis[index] == null)
                dosis[index] = new Dosis(tid,antal);

    }


    @Override
    public double samletDosis() {

        int periode = super.getSlutDato().compareTo(super.getStartDato());

        int antalDagligdosis = 0;

        for (Dosis dosi : dosis) {
            antalDagligdosis += dosi.getAntal();
        }

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
