package id.ac.polman.astra.serojamatchmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.polman.astra.serojamatchmaker.entity.Event;
import id.ac.polman.astra.serojamatchmaker.entity.EventInput;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseGetEvent;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class monitoringActivity extends AppCompatActivity {

    Adapter mAdapter;
    APIService mAPIService;
    RecyclerView mRecyclerView;
    private List<Event> mEventInputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        mRecyclerView = (RecyclerView) findViewById(R.id.eventDataList);

        SearchView mSearch = (SearchView) findViewById(R.id.txtSearchEvent);

        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);

                //filter(newText);
                return false;
            }
        });


        /*mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                mAdapter.getFilter().filter(s);
            }
        });*/

        LinearLayout mbtnstart = (LinearLayout) findViewById(R.id.btnBackStart);
        mbtnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StartActivity.class);

                startActivity(intent);
                finish();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        search();

        //onCreateOptionsMenu(null);

    }

  /*  private void filter(String text) {
        ArrayList<Event> filteredList = new ArrayList<>();
        for (Event item : mEventInputs) {
            if (item.getEventName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }*/

    private void search(){
        mAPIService = APIUtils.getAPIService();
        Call<ResponseGetEvent> call = mAPIService.getEvent();

        call.enqueue(new Callback<ResponseGetEvent>() {
            @Override
            public void onResponse(Call<ResponseGetEvent> call, Response<ResponseGetEvent> response) {
                if (response.isSuccessful()) {
                    List<Event> posts = response.body().getData();
                    mAdapter = new Adapter(monitoringActivity.this, posts);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetEvent> call, Throwable t) {
                Log.e("Get Event Error : ", t.getMessage());
                Toast.makeText(monitoringActivity.this, "Gagal Get Data!", Toast.LENGTH_LONG).show();
            }
        });
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_monitoring, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }*/

   /* @Override
    public void onStartDetailEvent() {
        Fragment fragment = DetailEventFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.monitoring_activity, fragment)
                .addToBackStack(null)
                .commit();
    }*/
}