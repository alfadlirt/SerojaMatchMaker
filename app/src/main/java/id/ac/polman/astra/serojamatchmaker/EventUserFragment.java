package id.ac.polman.astra.serojamatchmaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    SharedPreferences sharedPreferences;
    Adapter2 mAdapter;
    APIService mAPIService;
    RecyclerView mRecyclerView;

    private final static String APP_NAME = "serojamatchmaker";
    private final static String UNAME = "username";
    private final static String NAMA = "name";
    private final static String ID = "id";
    private final static String PASSWORD = "password";

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

        sharedPreferences = this.getActivity().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        //Cek SharedPreferences
        String unamesp = sharedPreferences.getString(UNAME, null);
        String namesp = sharedPreferences.getString(NAMA, null);
        String idsp = sharedPreferences.getString(ID, null);
        String passsp = sharedPreferences.getString(PASSWORD, null);

        Log.e("shared pref: ", idsp);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.eventDataList);
        LinearLayout mBtnEvent = (LinearLayout) view.findViewById(R.id.btnAddEvent);
        mBtnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).callFragmentEvent();
            }
        });

        SearchView mSearch = (SearchView) view.findViewById(R.id.txtSearchUserEvent);
        //mSearch.setQuery(idsp, true);

        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //mAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //search();
                return true;
            }
        });

        /*EditText mSearch = (EditText) view.findViewById(R.id.txtSearchUserEvent);
        mSearch.setText(idsp);

        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                mSearch.setText(idsp);
                mAdapter.getFilter().filter(s);
            }
        });*/

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        search();

        return view;
    }

    private void search(){
        sharedPreferences = this.getActivity().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        //Cek SharedPreferences
        String unamesp = sharedPreferences.getString(UNAME, null);
        String namesp = sharedPreferences.getString(NAMA, null);
        String idsp = sharedPreferences.getString(ID, null);
        String passsp = sharedPreferences.getString(PASSWORD, null);

        mAPIService = APIUtils.getAPIService();
        Call<ResponseGetEvent> call = mAPIService.getEventByUser(idsp);

        call.enqueue(new Callback<ResponseGetEvent>() {
            @Override
            public void onResponse(Call<ResponseGetEvent> call, Response<ResponseGetEvent> response) {
                if (response.isSuccessful()) {
                    /*for(int i = 0; i<response.body().getData().size(); i++){
                        String libId = response.body().getData().get(i).getUserId();
                        if(idsp == libId){
                            List<Event> posts = response.body().getData();
                            mAdapter = new Adapter2(getActivity(), posts);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }*/

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
