package id.ac.polman.astra.serojamatchmaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.ac.polman.astra.serojamatchmaker.entity.Event;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseChangePassword;
import id.ac.polman.astra.serojamatchmaker.entity.User;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import retrofit2.Call;

import static android.content.ContentValues.TAG;

public class Dashboard extends Fragment{
    SharedPreferences sharedPreferences;
    private APIService mAPIService;
    private final static String APP_NAME = "serojamatchmaker";
    private final static String UNAME = "username";
    private final static String NAMA = "name";
    private final static String ID = "id";
    private final static String PASSWORD = "password";

    private TextView mId, mOnGoing, mFinished;
    private EditText mNama;
    private EditText mUsername;

    private

    TextView username, name;
    public static Dashboard newInstance(){
        return new Dashboard();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.seroja_main, viewGroup, false);

        sharedPreferences = this.getActivity().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        mId = (TextView) view.findViewById(R.id.txtEditIdUser);
        mNama= (EditText) view.findViewById(R.id.txtEditName);
        mUsername = (EditText) view.findViewById(R.id.txtEditUname);
        mOnGoing = (TextView) view.findViewById(R.id.txtCountOngoing);
        mFinished = (TextView) view.findViewById(R.id.txtCountFinished);

        //Cek SharedPreferences
        String unamesp = sharedPreferences.getString(UNAME, null);
        String namesp = sharedPreferences.getString(NAMA, null);
        String idsp = sharedPreferences.getString(ID, null);
        String passsp = sharedPreferences.getString(PASSWORD, null);

        mAPIService = APIUtils.getAPIService();
        Call<Integer> callOn = mAPIService.getCountEventOngoing();

        setCountDashboard(view, callOn);


        setNameAndUname(view, namesp);

        LinearLayout start = (LinearLayout) view.findViewById(R.id.homebtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).callFragment2();
            }
        });

        LinearLayout event = (LinearLayout) view.findViewById(R.id.eventBtn);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).callFragmentEvent();
            }
        });

        LinearLayout getProfile = (LinearLayout) view.findViewById(R.id.userbtn);
        getProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).callFragmentProfil();
            }
        });

/*
        LinearLayout logoutButton = (LinearLayout) view.findViewById(R.id.logoutbtn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((MainActivity) getActivity()).callFragmentEvent();
                //((MainActivity) getActivity()).callDetailFragment();
            }
        });
*/


        SharedPreferences preferences = this.getActivity().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        LinearLayout logout = (LinearLayout) view.findViewById(R.id.logoutbtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(getActivity(), StartActivity.class));
            }
        });

        return view;
    }


    public void setCountDashboard(View view, Call<Integer> status){
        TextView mOnGoing = (TextView) view.findViewById(R.id.txtCountOngoing);
        mOnGoing.setText(status.toString());

        /*TextView mFinished = (TextView) view.findViewById(R.id.txtCountFinished);
        mFinished.setText(finished);*/
    }

    public void setNameAndUname(View view, String name){
        TextView m_name = (TextView) view.findViewById(R.id.nameUser);
        m_name.setText(name);
    }

    public interface Callbacks{
        public void onStartDashboard();
    }

    private Callbacks mCallbacks = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
}
