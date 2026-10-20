package ph.me.notatki;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("Karol3ke36/JSon-notatki/main/db.json")
    public Call<ArrayList<Notatki>> getNotatki();
}