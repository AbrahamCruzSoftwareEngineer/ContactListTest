package com.evolutiondso.www.contactlisttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.evolutiondso.www.contactlisttest.Model.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.StartService_Btn)
    public Button BTN_startService;
    @BindView(R.id.rv)
    public RecyclerView rv;

    public List<Result> persons = new ArrayList<>();
    RecyclerViewAdapter adapter;


    public static final String ENDPOINT_URL = "https://s3.amazonaws.com/";
    private static final String TAG = "MyTag_:";
    public MyService myService;
    //instancia de retrofit
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myService = retrofit.create(MyService.class);

        //startService = (Button) findViewById(R.id.StartService_Btn);
           BTN_startService.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               obtenerDatos();
           }
        });

        //For the Recycler View
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);


    }

    private void obtenerDatos(){
        Call<List<Result>> resultCall = myService.all();
        resultCall.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                Log.d(TAG, "SUCCES: RESPONSE!");
                displayResult(response.body());
            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Log.d(TAG, "ERROR: NOT RESOLVING, ERROR: "+t.toString());
            }
        });
    }

    private void displayResult(List<Result> res) {
        String str = null;
        Integer cont = 0;
        if (res != null) {
            for (Result r : res){
                cont++;
                str += r.getName();
                Log.d(TAG, "Id: "+cont+" Name: "+str);
            }
            persons = res;
            adapter = new RecyclerViewAdapter(persons, this);
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if (res == null){
            Toast.makeText(this, "Result is NULL", Toast.LENGTH_SHORT).show();
        }
    }
}
