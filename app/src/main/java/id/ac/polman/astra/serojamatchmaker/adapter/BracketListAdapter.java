package id.ac.polman.astra.serojamatchmaker.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.polman.astra.serojamatchmaker.InputScoreFragment;
import id.ac.polman.astra.serojamatchmaker.MainActivity;
import id.ac.polman.astra.serojamatchmaker.R;
import id.ac.polman.astra.serojamatchmaker.model.BracketCard;
import id.ac.polman.astra.serojamatchmaker.model.TeamCardInput;
import id.ac.polman.astra.serojamatchmaker.utils.UpdateScoreModal;

public class BracketListAdapter extends RecyclerView.Adapter<BracketListAdapter.BracketListHolder>{
    private LayoutInflater inflater;
    public static ArrayList<BracketCard> editModelArrayList;
    private static Context mContext;

    public static class BracketListHolder extends RecyclerView.ViewHolder {
        protected TextView mTeamA;
        protected TextView mSkorA;
        protected TextView mTeamB;
        protected TextView mSkorB;
        protected TextView mBracketID;
        protected TextView mStageType;
        protected LinearLayout mBtn;
        protected LinearLayout rootView;

        public LinearLayout.LayoutParams params;
        public BracketListHolder(View view) {
            super(view);
            //mImageView = itemView.findViewById(R.id.imageView);
            //mTextView1 = itemView.findViewById(R.id.textView);
            //mTextView2 = itemView.findViewById(R.id.textView2);

            mTeamA = (TextView) view.findViewById(R.id.team_a_textview);
            mTeamB = (TextView) view.findViewById(R.id.team_b_textview);
            mSkorA = (TextView) view.findViewById(R.id.skor_a_textview);
            mSkorB = (TextView) view.findViewById(R.id.skor_b_textview);

            mBracketID = (TextView) view.findViewById(R.id.bracket_id_textview);
            mStageType = (TextView) view.findViewById(R.id.stage_type_textview);

            mBtn = (LinearLayout) view.findViewById(R.id.btnInputSkor);
            params = new LinearLayout.LayoutParams(0, 0);
            rootView =(LinearLayout) view.findViewById(R.id.root_card);

            mBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //((MainActivity)mContext).openDialog();

                    FragmentActivity activity = (FragmentActivity)(mContext);
                    FragmentManager fm = activity.getSupportFragmentManager();

                    InputScoreFragment frg = (InputScoreFragment) fm.findFragmentByTag("InputScoreFragment");
                    BracketCard brckt = new BracketCard();
                    brckt.setId_bracket(mBracketID.getText().toString());
                    brckt.setTeam_a_name(mTeamA.getText().toString());
                    brckt.setTeam_b_name(mTeamB.getText().toString());
                    UpdateScoreModal exampleDialog = new UpdateScoreModal(brckt);
                    exampleDialog.setTargetFragment(frg,1);
                    exampleDialog.show(fm, "example dialog");

                }
            });
        }
    }

    public BracketListAdapter(Context ctx, ArrayList<BracketCard> editModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        mContext = ctx;
        this.editModelArrayList = editModelArrayList;
    }
    @Override
    public BracketListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.bracket_card, parent, false);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bracket_card, parent, false);
        BracketListHolder evh = new BracketListHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(BracketListHolder holder, int position) {


        BracketCard currentItem = editModelArrayList.get(position);
        if(currentItem.getIs_wo_moved()){
            holder.rootView.setLayoutParams(holder.params);
        }
        else {
            holder.mTeamA.setText(currentItem.getTeam_a_name());
            holder.mTeamB.setText(currentItem.getTeam_b_name());

            holder.mBracketID.setText(currentItem.getId_bracket());
            holder.mStageType.setText(currentItem.getStage_type());
            if (currentItem.getStatus().equals("FINISHED")) {
                holder.mSkorA.setText(Integer.toString(currentItem.getSkor_a_name()));
                holder.mSkorB.setText(Integer.toString(currentItem.getSkor_b_name()));
            } else {
                holder.mSkorA.setText("-");
                holder.mSkorB.setText("-");
            }

            if (currentItem.getIs_wo() || currentItem.getStatus().equals("UNASSIGNED")
                    || currentItem.getStatus().equals("FINISHED")) {
                holder.mBtn.setVisibility(View.INVISIBLE);
            }
        }

    }
    @Override
    public int getItemCount() {
        return editModelArrayList.size();
    }
    public ArrayList<BracketCard> getArrayList() {
        return editModelArrayList;
    }
}
