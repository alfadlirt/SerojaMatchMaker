package id.ac.polman.astra.serojamatchmaker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Date;

import id.ac.polman.astra.serojamatchmaker.model.Event;
import id.ac.polman.astra.serojamatchmaker.fragment.BracketsFragment;
import id.ac.polman.astra.serojamatchmaker.model.EventParcelData;
import id.ac.polman.astra.serojamatchmaker.utils.CustomLoading;

public class DetailEvent extends AppCompatActivity {

    RecyclerView mRecyclerView;

    TextView mName, mNOT, mType, mStatus, mLastM;

    private Event mEvent;
    CustomLoading loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_event);

/*
        String name = getIntent().getStringExtra("event_name");
        mName.setText(name);

        String team = getIntent().getStringExtra("number_of_team");
        mNOT.setText(team);

        String status = ;
        mStatus.setText(status);

        String modif = ;
        mLastM.setText(modif);
*/

        Bundle bundle = new Bundle();
        bundle.putString("id_event",getIntent().getStringExtra("id_event"));
        //loadingDialog.startLoading("Updating Score");
        BracketsFragment fragment2 = new BracketsFragment();
        fragment2.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_detail, fragment2)
                .addToBackStack(null)
                .commit();
        //loadingDialog.stopLoading();
    }


   /* public interface Callbacks{
        public void onStartDetailEvent();
    }

    private DetailEventFragment.Callbacks mCallbacks = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallbacks = (DetailEventFragment.Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }*/
}