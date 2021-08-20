package id.ac.polman.astra.serojamatchmaker.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;

import id.ac.polman.astra.serojamatchmaker.InputScoreFragment;
import id.ac.polman.astra.serojamatchmaker.R;
import id.ac.polman.astra.serojamatchmaker.model.BracketCard;


public class UpdateScoreModal extends DialogFragment {
    private EditText txtTeamA;
    private EditText txtTeamB;
    private TextView id;
    private TextInputLayout labelA;
    private TextInputLayout labelB;
    private BracketCard bracketData;
    public UpdateScoreModal(BracketCard brckt) {
        this.bracketData = brckt;
    }

    public interface OnInputScore{
        void sendInput(BracketCard updatedBracket);
    }
    public OnInputScore mOnInputScore;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.modal_update_score, null);


        id = view.findViewById(R.id.bracket_id_textview);
        txtTeamA = view.findViewById(R.id.txt_input_skor_a);
        txtTeamB = view.findViewById(R.id.txt_input_skor_b);
        labelA = view.findViewById(R.id.txt_label_team_a);
        labelB = view.findViewById(R.id.txt_label_team_b);
        id.setText(this.bracketData.getId_bracket());
        labelA.setHelperText(this.bracketData.getTeam_a_name());
        labelB.setHelperText(this.bracketData.getTeam_b_name());


        builder.setView(view)
                .setTitle("Input Score")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Integer skorA = Integer.parseInt(txtTeamA.getText().toString());
                        Integer skorB = Integer.parseInt(txtTeamB.getText().toString());
                        if(skorA==skorB){
                            txtTeamA.setError("Same Value!");
                            txtTeamB.setError("Same Value!");
                            txtTeamB.requestFocus();
                            return;
                        }else{
                            BracketCard brckt = new BracketCard();
                            brckt.setId_bracket(id.getText().toString());
                            brckt.setSkor_a_name(skorA);
                            brckt.setSkor_b_name(skorB);
                            mOnInputScore.sendInput(brckt);
                        }
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mOnInputScore = (OnInputScore) context;
            //listener = (UpdateScoreModalListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

}
