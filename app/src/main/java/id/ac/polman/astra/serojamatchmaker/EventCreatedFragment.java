package id.ac.polman.astra.serojamatchmaker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.ac.polman.astra.serojamatchmaker.adapter.TeamInputAdapter;
import id.ac.polman.astra.serojamatchmaker.entity.AddEventReturnData;
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
 * Use the {@link EventCreatedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventCreatedFragment extends Fragment {
    AddEventReturnData eventData;
    CustomLoading loadingDialog;
    TextView mEventID;
    TextView mEventName;
    TextView mEventTeam;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventCreatedFragment() {
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
    public static EventCreatedFragment newInstance(String param1, String param2) {
        EventCreatedFragment fragment = new EventCreatedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            this.eventData = bundle.getParcelable("eventData");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_created, container, false);
        // Inflate the layout for this fragment
        mEventID = (TextView) view.findViewById(R.id.txt_EventID);
        mEventName = (TextView) view.findViewById(R.id.txt_EventName);
        mEventTeam = (TextView) view.findViewById(R.id.txt_NumberOfTeam);
        mEventID.setText(eventData.getEvent().getId());
        mEventName.setText(eventData.getEvent().getEventName());
        mEventTeam.setText(eventData.getEvent().getNumberOfTeam());

        view.findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).onStartDashboard();
            }
        });
        return view;
    }



}