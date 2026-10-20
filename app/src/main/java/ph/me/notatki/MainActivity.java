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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Notatki> slowa = new ArrayList<>();
    ArrayAdapter<Notatki> arrayadapter;
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
        listView = findViewById(R.id.Lista);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
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
                            Toast.makeText(MainActivity.this, "1"+response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        slowa = response.body();
                        Toast.makeText(MainActivity.this, "2"+slowa.get(0).tresc, Toast.LENGTH_SHORT).show();

                        arrayadapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, slowa);
                        listView.setAdapter(arrayadapter);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Notatki>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
        );




    }
}