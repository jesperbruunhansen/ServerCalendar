package Model.Forecast;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by danielfranch on 16/10/14.
 * Constructor til ForecastModel Arraylist
 */
public class ForecastClass {

    private String date;
    private String celsius;
    private String desc;
    private Date dateDate;

    // Funktion som setter dato, grader og beskrivelse til Forecast objektet
    public ForecastClass (String date, String celsius, String desc) {
        this.date = date;
        this.celsius = celsius;
        this.desc = desc;
    }

    public ForecastClass(){};


    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {this.dateDate = dateDate;}
    // Settere og gettere for Forecast klassen
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    // Returnere vejrudsigten som en json tekststreng
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", celsius='" + celsius + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
