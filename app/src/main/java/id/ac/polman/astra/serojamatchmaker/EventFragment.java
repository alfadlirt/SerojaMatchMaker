package id.ac.polman.astra.serojamatchmaker;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import id.ac.polman.astra.serojamatchmaker.entity.ResponseLogin;
import id.ac.polman.astra.serojamatchmaker.model.Event;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFragment extends Fragment {

    private APIService mAPIService;
    private EditText mEventName;
    private EditText mNumberOfTeam;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventFragment() {
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
    public static EventFragment newInstance(String param1, String param2) {
        EventFragment fragment = new EventFragment();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_event, container, false);
        // Inflate the layout for this fragment
        mEventName = (EditText) view.findViewById(R.id.txtEventName);
        mNumberOfTeam = (EditText) view.findViewById(R.id.txtNumberTeam);
        view.findViewById(R.id.btnSaveEvent2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEvent();
            }
        });

        view.findViewById(R.id.btnSaveText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEvent();
            }
        });

        return view;
    }

    private void createEvent(){
        String eventName = String.valueOf(mEventName.getText());
        String numberOfTeam = String.valueOf(mNumberOfTeam.getText());

        if (eventName.isEmpty()) {
            mEventName.setError("Harap isi Field Name!");
            mEventName.requestFocus();
            return;
        } else if (numberOfTeam.isEmpty()) {
            mNumberOfTeam.setError("Harap isi Field Username!");
            mNumberOfTeam.requestFocus();
            return;
        }else{
            Event event = new
                    Event(eventName, Integer.parseInt(numberOfTeam),"KNO","ONGOING", 0);

            Bundle bundle = new Bundle();
            bundle.putParcelable("event", event);



            ((MainActivity) getActivity()).callFragmentTeam(bundle);

        }
    }

}