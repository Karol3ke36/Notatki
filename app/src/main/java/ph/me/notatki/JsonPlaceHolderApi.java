package ph.me.notatki;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET
    public Call<ArrayList<Notatki>> getNotatki();
}