package ph.me.notatki;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

public class Notatki {
    @SerializedName("tytul")
    public String tytul;
    @SerializedName("tresc")
    public String tresc;
    @SerializedName("data")
    public String kalendarz;

    public Notatki(String tytul, String tresc, String kalendarz) {
        this.tytul = tytul;
        this.tresc = tresc;
        this.kalendarz = kalendarz;
    }

    public Notatki() {
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    @Override
    public String toString() {
        return "Tytuł notatki: " + tytul + " Tresc: " +  tresc + " Data: " +  kalendarz;
    }
}

