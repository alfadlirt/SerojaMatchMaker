package id.ac.polman.astra.serojamatchmaker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.ac.polman.astra.serojamatchmaker.adapter.TeamInputAdapter;
import id.ac.polman.astra.serojamatchmaker.entity.EventInput;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseAddEvent;
import id.ac.polman.astra.serojamatchmaker.model.Event;
import id.ac.polman.astra.serojamatchmaker.model.TeamCardInput;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import id.ac.polman.astra.serojamatchmaker.utils.CustomLoading;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment {

    private APIService mAPIService;
    private EditText mEventName;
    private EditText mNumberOfTeam;
    private int numberOfLines = 0;
    Event event;
    CustomLoading loadingDialog;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TeamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamFragment newInstance(String param1, String param2) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new CustomLoading(getActivity());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            event = bundle.getParcelable("event");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_team, container, false);
        // Inflate the layout for this fragment

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new TeamInputAdapter(getContext(), initList(event.getNumber_of_team()));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        view.findViewById(R.id.btnSaveTeam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure the data is valid?")
                        .setContentText("You won't be able to change team after event created!")
                        .setConfirmText("Save")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                loadingDialog.startLoading("Creating Your Event..");
                                List<TeamCardInput> team_list_array = new ArrayList<>();
                                for (int i = 0; i < TeamInputAdapter.editModelArrayList.size(); i++){
                                    TeamCardInput teaminput = new TeamCardInput();
                                    teaminput.setTeam_name(TeamInputAdapter.editModelArrayList.get(i).getTeam_name());
                                    teaminput.setInstance(TeamInputAdapter.editModelArrayList.get(i).getInstance());
                                    team_list_array.add(teaminput);
                                }

                                ObjectMapper objectMapper = new ObjectMapper();
                                String team_list_json = "";
                                try {
                                    team_list_json = objectMapper.writeValueAsString(team_list_array);
                                } catch (JsonProcessingException e) {
                                    e.printStackTrace();
                                }
                                mAPIService = APIUtils.getAPIService();
                                Call<ResponseAddEvent> call = mAPIService.addEventAndTeam(
                                        new EventInput("USR0000005",event.getEvent_name(),event.getNumber_of_team(),event.getElimination_type(),team_list_json));

                                call.enqueue(new Callback<ResponseAddEvent>() {
                                    @Override
                                    public void onResponse(Call<ResponseAddEvent> call, Response<ResponseAddEvent> response) {
                                        if(response.isSuccessful()){
                                            loadingDialog.stopLoading();
                                            if(response.body().getSuccess()==true) {
                                                Bundle bundle = new Bundle();
                                                bundle.putParcelable("eventData", response.body().getData());
                                                ((MainActivity) getActivity()).callFragmentEventCreated(bundle);
                                                Toast.makeText(getActivity(), "Data saved successfully", Toast.LENGTH_LONG).show();
                                            }else{

                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseAddEvent> call, Throwable t) {
                                        Log.e("Update Error : ", t.getMessage());
                                        Toast.makeText(getActivity(), "Data gagal tersimpan!", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
        });
        return view;
    }

    private ArrayList<TeamCardInput> initList(int number_of_team){

        ArrayList<TeamCardInput> list = new ArrayList<>();

        for(int i = 0; i < number_of_team; i++){
            TeamCardInput teamCardInput = new TeamCardInput();
            list.add(teamCardInput);
        }

        return list;
    }


}