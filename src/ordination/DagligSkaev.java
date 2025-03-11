package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    private ArrayList<Dosis> dosis = new ArrayList<>();

    public DagligSkaev(LocalDate startDato, LocalDate slutDato, Laegemiddel laegemiddel) {
        super(startDato, slutDato, laegemiddel);
    }

    public void opretDosis(LocalTime tid, double antal ){

        dosis.add(new Dosis(tid, antal));

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

    public ArrayList<Dosis> getDoser() {
        return dosis;
    }
}
