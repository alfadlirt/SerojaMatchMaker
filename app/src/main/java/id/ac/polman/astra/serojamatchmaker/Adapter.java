package id.ac.polman.astra.serojamatchmaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterHolder> {


    private Context mContext;
    private List<Event> mEvents;

    public Adapter(Context context, List<Resep> dataList){
    }

    @NonNull
    @Override
    public Adapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recipe_list, parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.AdapterHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {


        public AdapterHolder(@NonNull View itemView) {

        }
    }
}
