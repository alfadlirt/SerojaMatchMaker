package id.ac.polman.astra.serojamatchmaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Dashboard extends Fragment{
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

        Intent intent = getActivity().getIntent();
        String uname = intent.getStringExtra("Username");
        String sname = intent.getStringExtra("Name");

        setUsernameAndPassword(view, uname, sname);

        LinearLayout start = (LinearLayout) view.findViewById(R.id.homebtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).callFragment2();
            }
        });

        return view;
    }

    public interface Callbacks{
        public void onStartDashboard();
    }

    public void setUsernameAndPassword(View view, String uname, String name){
        TextView m_name = (TextView) view.findViewById(R.id.nameUser);
        m_name.setText(name);

        TextView m_username = (TextView) view.findViewById(R.id.username);
        m_username.setText(uname);
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
