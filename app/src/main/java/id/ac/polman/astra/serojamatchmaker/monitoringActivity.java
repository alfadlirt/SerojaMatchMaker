package id.ac.polman.astra.serojamatchmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import id.ac.polman.astra.serojamatchmaker.entity.EventInput;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class monitoringActivity extends AppCompatActivity {

    Adapter mAdapter;
    APIService mAPIService;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        mAPIService = APIUtils.getAPIService();
        Call<List<EventInput>> call = mAPIService.getEvent();

        call.enqueue(new Callback<List<EventInput>>() {
            @Override
            public void onResponse(Call<List<EventInput>> call, Response<List<EventInput>> response) {
                if (response.isSuccessful()) {
                    List<EventInput> posts = response.body();
                    mAdapter = new Adapter(monitoringActivity.this, posts);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<EventInput>> call, Throwable t) {
                Log.e("Get Event Error : ", t.getMessage());
                Toast.makeText(monitoringActivity.this, "Gagal Get Data!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
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
    }
}