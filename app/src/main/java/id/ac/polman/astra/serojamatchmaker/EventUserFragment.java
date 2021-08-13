package id.ac.polman.astra.serojamatchmaker;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.polman.astra.serojamatchmaker.entity.Event;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseGetEvent;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventUserFragment extends Fragment {

    Adapter2 mAdapter;
    APIService mAPIService;
    RecyclerView mRecyclerView;

    public static EventUserFragment newInstance(){
        return new EventUserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.user_event, viewGroup, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.eventDataList);

        EditText mSearch = (EditText) view.findViewById(R.id.txtSearchUserEvent);

        mSearch.addTextChangedListener(new TextWatcher() {
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
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        search();

        return view;
    }

    private void search(){
        mAPIService = APIUtils.getAPIService();
        Call<ResponseGetEvent> call = mAPIService.getEvent();

        call.enqueue(new Callback<ResponseGetEvent>() {
            @Override
            public void onResponse(Call<ResponseGetEvent> call, Response<ResponseGetEvent> response) {
                if (response.isSuccessful()) {
                    List<Event> posts = response.body().getData();
                    mAdapter = new Adapter2(getActivity(), posts);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetEvent> call, Throwable t) {
                Log.e("Get Event Error : ", t.getMessage());
                Toast.makeText(getActivity(), "Gagal Get Data!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
