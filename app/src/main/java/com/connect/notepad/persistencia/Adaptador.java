package com.connect.notepad.persistencia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.connect.notepad.R;
import com.connect.notepad.interfaces.Notepad;
import com.connect.notepad.modelo.Notes;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {
    private LayoutInflater inflater;
    private List<Notes> list;
    private OnItemClickListener onItemClickListener;

    public Adaptador(Context context, List<Notes> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final View view = inflater.inflate(R.layout.cards, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holders, int position) {
        final ViewHolder holder = holders;
        holder.cardView.setCardBackgroundColor(list.get(position).getColor());
        holder.title.setText(list.get(position).getTitle());
        holder.description.setText(list.get(position).getDescription());
        holder.published.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, description, published;
        OnItemClickListener onItemClickListener;
        CardView cardView;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);

            cardView = itemView.findViewById(R.id.cards);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            published = itemView.findViewById(R.id.published);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(inflater.getContext(), Notepad.class);
            intent.putExtra("id", String.valueOf(list.get(getAdapterPosition()).getId()));
            intent.putExtra("title",list.get(getAdapterPosition()).getTitle());
            intent.putExtra("description", list.get(getAdapterPosition()).getDescription());
            intent.putExtra("color", String.valueOf(list.get(getAdapterPosition()).getColor()));
            inflater.getContext().startActivity(intent);
        }
    }
}
