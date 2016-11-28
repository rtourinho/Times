package br.com.fiap.futebol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.futebol.adapter.TimeListAdapter;
import br.com.fiap.futebol.api.TimeAPI;
import br.com.fiap.futebol.model.Time;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<List<Time>>{

    private TimeListAdapter adapter;
    private RecyclerView rvTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTimes = (RecyclerView) findViewById(R.id.rvTimes);

        //rvTimes.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvTimes.setLayoutManager(new LinearLayoutManager(this));

        rvTimes.setItemAnimator(new DefaultItemAnimator());
        rvTimes.setHasFixedSize(true);
        //rvTimes.setAdapter(new TimeListAdapter(getBaseContext(), new ArrayList<Time>()));
        loadTimes();
    }

    private void loadTimes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TimeAPI api = retrofit.create(TimeAPI.class);
        Call<List<Time>> call = api.getTimes();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Time>> call, Response<List<Time>> response) {
        adapter = new TimeListAdapter(getBaseContext(), response.body());
        rvTimes.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<List<Time>> call, Throwable t) {
        Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();;
    }
}
