package dserruya.sunchalespadelclub.sunchalespadelclub.Utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dserruya.sunchalespadelclub.sunchalespadelclub.DataBase.Tournaments;
import dserruya.sunchalespadelclub.sunchalespadelclub.R;

public class TournamentAdapter extends RecyclerView.Adapter<TournamentAdapter.TournamentViewHolder>{

    private ArrayList<Tournaments> data;

    public TournamentAdapter(ArrayList<Tournaments> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TournamentAdapter.TournamentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tournament, parent, false);
        TournamentViewHolder tvh = new TournamentViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TournamentAdapter.TournamentViewHolder holder, int position) {
        holder.tv_Position .setText(data.get(position).getArtista());
        holder.tv_TournamentName .setText(data.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class TournamentViewHolder extends RecyclerView.ViewHolder{

        TextView tv_TournamentName;
        TextView tv_Position;

        public TournamentViewHolder(View itemView) {
            super(itemView);
            tv_TournamentName = (TextView) itemView.findViewById(R.id.tv_TournamentName);
            tv_Position = (TextView) itemView.findViewById(R.id.tv_Position);
        }
    }
}