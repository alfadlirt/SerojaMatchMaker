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

import id.ac.polman.astra.serojamatchmaker.remote.APIService;

public class Dashboard extends Fragment{
    SharedPreferences sharedPreferences;
    private APIService mAPIService;
    private final static String APP_NAME = "serojamatchmaker";
    private final static String UNAME = "username";
    private final static String NAMA = "name";
    private final static String ID = "id";
    private final static String PASSWORD = "password";

    private TextView mId;
    private EditText mNama;
    private EditText mUsername;

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

        //Cek SharedPreferences
        String unamesp = sharedPreferences.getString(UNAME, null);
        String namesp = sharedPreferences.getString(NAMA, null);
        String idsp = sharedPreferences.getString(ID, null);
        String passsp = sharedPreferences.getString(PASSWORD, null);


        setNameAndUname(view, unamesp, namesp);

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

        SharedPreferences preferences = this.getActivity().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        LinearLayout logout = (LinearLayout) view.findViewById(R.id.logoutbtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return view;
    }

    public void setNameAndUname(View view, String uname, String name){
        TextView m_name = (TextView) view.findViewById(R.id.nameUser);
        m_name.setText(name);

        TextView m_username = (TextView) view.findViewById(R.id.username);
        m_username.setText(uname);
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
