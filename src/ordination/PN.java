package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination{

    private double antalEnheder;
    private ArrayList<LocalDate> datoerGivet = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, Laegemiddel laegemiddel, double vaegt) {

        super(startDato, slutDato, laegemiddel);

        if(slutDato.isBefore(startDato))
            throw new IllegalArgumentException("Slutdato er før startdato");

        if(vaegt < 0)
            throw new IllegalArgumentException("Du kan ikke have minus vægt");

        this.antalEnheder = laegemiddel.anbefaletDosisPrDoegn((int)vaegt) * vaegt;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givetDato
     * Returnerer true hvis givetDato er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givetDato ignoreres
     * @param givetDato
     * @return
     */
    public boolean givDosis(LocalDate givetDato) {

        if((givetDato.isBefore(super.getSlutDato()) && givetDato.isAfter(super.getStartDato()))
            || givetDato.equals(super.getStartDato()) || givetDato.equals(super.getSlutDato())
        ){
            datoerGivet.add(givetDato);
            return true;
        }

        return false;
    }

    public double doegnDosis() {

        int periode = super.getSlutDato().compareTo(super.getStartDato());

        return ((this.antalEnheder * datoerGivet.size())/periode);
    }


    public double samletDosis() { return this.antalEnheder * datoerGivet.size(); }

    @Override
    public String getType() {
        return super.getLaegemiddel().getNavn();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return datoerGivet.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }



}
