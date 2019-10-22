package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wolfsoft.kcab.R;

import java.util.ArrayList;

import model.InRideModel;

public class InRideAdapter extends RecyclerView.Adapter<InRideAdapter.ViewHolder> {
    Context context;

    public ArrayList<InRideModel> getRidehistoryModelArrayList() {
        return inRideModelArrayList;
    }

    private ArrayList<InRideModel> inRideModelArrayList;
    private AdapterView.OnItemClickListener listener;

    public InRideAdapter(Context context, ArrayList<InRideModel> inRideModelArrayList, AdapterView.OnItemClickListener listener) {
        this.context = context;
        this.inRideModelArrayList = inRideModelArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InRideAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_in__ride_icab,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InRideAdapter.ViewHolder holder, int position) {
        holder.duration.setText(inRideModelArrayList.get(position).getDuration());
        holder.time.setText(inRideModelArrayList.get(position).getTime());
    }


    @Override
    public int getItemCount() {
        return inRideModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time,duration;

        public ViewHolder(View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.time);
            duration=itemView.findViewById(R.id.duration);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", String.valueOf(getAdapterPosition()));
                }
            });
        }
    }
}
