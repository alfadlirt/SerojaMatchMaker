package id.ac.polman.astra.serojamatchmaker;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.ac.polman.astra.serojamatchmaker.adapter.BracketListAdapter;
import id.ac.polman.astra.serojamatchmaker.adapter.TeamInputAdapter;
import id.ac.polman.astra.serojamatchmaker.entity.BracketResponse;
import id.ac.polman.astra.serojamatchmaker.entity.EventInput;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseAddEvent;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseBracketGet;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseBracketPut;
import id.ac.polman.astra.serojamatchmaker.model.BracketCard;
import id.ac.polman.astra.serojamatchmaker.model.Event;
import id.ac.polman.astra.serojamatchmaker.model.TeamCardInput;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import id.ac.polman.astra.serojamatchmaker.utils.CustomLoading;
import id.ac.polman.astra.serojamatchmaker.utils.UpdateScoreModal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InputScoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InputScoreFragment extends Fragment {
    private static final String TAG = "InputScoreFragment";
    private APIService mAPIService;
    Event event;
    CustomLoading loadingDialog;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<BracketCard> list = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InputScoreFragment() {
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
    public static InputScoreFragment newInstance(String param1, String param2) {
        InputScoreFragment fragment = new InputScoreFragment();
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
        View view = inflater.inflate(R.layout.layout_update_score, container, false);
        // Inflate the layout for this fragment

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());



        mAPIService = APIUtils.getAPIService();
        Call<ResponseBracketGet> call = mAPIService.getEventBracket("EVT0000001");

        call.enqueue(new Callback<ResponseBracketGet>() {
            @Override
            public void onResponse(Call<ResponseBracketGet> call, Response<ResponseBracketGet> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()==true) {
                        int lastStage = response.body().getData().get(0).getStageNumber();
                        for(int i = 0; i <response.body().getData().size(); i++){

                            BracketCard bracketcard = new BracketCard();
                            bracketcard.setId_bracket(response.body().getData().get(i).getId());
                            bracketcard.setStatus(response.body().getData().get(i).getStatus());
                            bracketcard.setStage_type(response.body().getData().get(i).getStageType());
                            if(response.body().getData().get(i).getIsWo()==null){
                                bracketcard.setIs_wo(false);
                            }
                            else if(response.body().getData().get(i).getIsWo()==1){
                                bracketcard.setIs_wo(true);
                            }
                            else{
                                bracketcard.setIs_wo(false);
                            }

                            if(response.body().getData().get(i).getIsWoMoved()==null){
                                bracketcard.setIs_wo_moved(false);
                            }
                            else if(response.body().getData().get(i).getIsWoMoved()==1){
                                bracketcard.setIs_wo_moved(true);
                            }
                            else if(response.body().getData().get(i).getIsWoMoved()==0){
                                bracketcard.setIs_wo_moved(false);
                            }

                            if(response.body().getData().get(i).getIsEnd()==1){
                                bracketcard.setTeam_a_name(response.body().getData().get(i).getTeamA());
                                bracketcard.setTeam_b_name(response.body().getData().get(i).getTeamB());
                                if(response.body().getData().get(i).getStatus().equals("FINISHED")){
                                    bracketcard.setSkor_a_name(response.body().getData().get(i).getSkorA());
                                    bracketcard.setSkor_b_name(response.body().getData().get(i).getSkorB());
                                }
                            }
                            else if(response.body().getData().get(i).getIsWo()==0&&
                                    response.body().getData().get(i).getIsEnd()==0){
                                bracketcard.setTeam_a_name(response.body().getData().get(i).getTeamA());
                                bracketcard.setTeam_b_name(response.body().getData().get(i).getTeamB());
                                if(response.body().getData().get(i).getStatus().equals("FINISHED")){
                                    bracketcard.setSkor_a_name(response.body().getData().get(i).getSkorA());
                                    bracketcard.setSkor_b_name(response.body().getData().get(i).getSkorB());
                                }
                            }
                            else{
                                if(response.body().getData().get(i).getTeamA()==null){
                                    bracketcard.setTeam_b_name(response.body().getData().get(i).getTeamB());
                                }
                                else{
                                    bracketcard.setTeam_a_name(response.body().getData().get(i).getTeamA());
                                }
                            }


                            list.add(bracketcard);
                        }

                        mAdapter = new BracketListAdapter(getContext(), list);
                        mRecyclerView.setLayoutManager(mLayoutManager);
                        mRecyclerView.setAdapter(mAdapter);
                    }else{

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBracketGet> call, Throwable t) {
                Log.e("Update Error : ", t.getMessage());
                Toast.makeText(getActivity(), "Data gagal tersimpan!", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }


    public void InputScore(BracketCard brckt) {
        //Toast.makeText(getActivity(), "Data " + brckt.getId_bracket() +
        // brckt.getSkor_a_name() + " " + brckt.getSkor_b_name(), Toast.LENGTH_LONG).show();
        loadingDialog.startLoading("Updating Score");
        mAPIService = APIUtils.getAPIService();
        Call<ResponseBracketPut> call = mAPIService.updatescore(brckt.getId_bracket(), brckt.getSkor_a_name(), brckt.getSkor_b_name());

        call.enqueue(new Callback<ResponseBracketPut>() {
            @Override
            public void onResponse(Call<ResponseBracketPut> call, Response<ResponseBracketPut> response) {
                if(response.isSuccessful()){

                        //BracketResponse rtr = (BracketResponse) response.body().getData();
                    ((MainActivity) getActivity()).refreshInputScore();
                    loadingDialog.stopLoading();
                    Toast.makeText(getActivity(), "Data saved successfully", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBracketPut> call, Throwable t) {
                loadingDialog.stopLoading();
                Log.e("Update Error : ", t.getMessage());
                Toast.makeText(getActivity(), "Data gagal tersimpan!", Toast.LENGTH_LONG).show();
            }
        });
    }
}