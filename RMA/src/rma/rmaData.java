package rma;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Tamas Bayer
 */
public class rmaData {
    private final SimpleStringProperty RmaNummer;
    private final SimpleStringProperty CridNummer;
    private final SimpleStringProperty Kunde;
    private final SimpleStringProperty Datum;
    
    public rmaData(){
        this.RmaNummer = new SimpleStringProperty ("");
        this.CridNummer = new SimpleStringProperty ("");
        this.Kunde = new SimpleStringProperty ("");
        this.Datum = new SimpleStringProperty ("");
    }
    
    public rmaData(String rNummer, String cNummer, String kun, String dat){
        this.RmaNummer = new SimpleStringProperty (rNummer);
        this.CridNummer = new SimpleStringProperty (cNummer);
        this.Kunde = new SimpleStringProperty (kun);
        this.Datum = new SimpleStringProperty (dat);
    
    }
    
    public String getRmaNummer(){
        return RmaNummer.get();
    }
    public void setRmaNummer(String rNummer){
        RmaNummer.set(rNummer);
    }
    public String getCridNummer(){
        return CridNummer.get();
    }
    public void setCridNummer(String cNummer){
        CridNummer.set(cNummer);
    }
    public String getKunde(){
        return Kunde.get();
    }
    public void setKunde(String kun){
        Kunde.set(kun);
    }
    public String getDatum(){
        return Datum.get();
    }
    public void setDatum(String dat){
        Datum.set(dat);
    }
    
    
}