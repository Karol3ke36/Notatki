package ph.me.notatki;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList<String> slowa = new ArrayList<>();
        ArrayAdapter<String> arrayadapter;
        listView = findViewById(R.id.Lista);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Karol3ke36/JSon-notatki/main/db.json")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ArrayList<Notatki>> call = jsonPlaceHolderApi.getNotatki();
        call.enqueue(
                new Callback<ArrayList<Notatki>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Notatki>> call,
                                           Response<ArrayList<Notatki>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        slowa = response.body();
                        Toast.makeText(MainActivity.this, slowa.get(0).trescPytania, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Notatki>> call, Throwable t) {

                    }
                }
        );



        slowa.add("XXX");
        arrayadapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, slowa);
        listView.setAdapter(arrayadapter);
    }
}