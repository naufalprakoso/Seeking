package com.njy.seeking.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.njy.seeking.R;
import com.njy.seeking.VacancyDetailActivity;
import com.njy.seeking.data.KEY;
import com.njy.seeking.model.Vacancy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naufalprakoso on 12/05/18.
 */

public class VacancyAdapter extends RecyclerView.Adapter<VacancyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Vacancy> list;

    public ArrayList<Vacancy> getList() {
        return list;
    }

    public void setList(ArrayList<Vacancy> list) {
        this.list = list;
    }

    public VacancyAdapter(Context context) {

        this.context = context;
    }

    public VacancyAdapter(Context context, ArrayList<Vacancy> tempList){
        this.list = tempList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_vacancy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtSeat.setText(getList().get(position).getSeat() + " Seats left");
        holder.txtPosition.setText(getList().get(position).getPosition());
        holder.txtLocation.setText(getList().get(position).getLocation());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VacancyDetailActivity.class);
                i.putExtra(KEY.POSITION_KEY, getList().get(position).getPosition());
                i.putExtra(KEY.TYPE_KEY, getList().get(position).getType());
                i.putExtra(KEY.LOCATION_KEY, getList().get(position).getLocation());
                i.putExtra(KEY.SEAT_LEFT_KEY, getList().get(position).getSeat());
                i.putExtra(KEY.SALARY_KEY, "Rp " + getList().get(position).getSalary());
                i.putExtra(KEY.EXPERIENCE_KEY, getList().get(position).getExperience());
                i.putExtra(KEY.LANGUAGE_KEY, getList().get(position).getLanguage());
                i.putExtra(KEY.CERTIFICATION_KEY, getList().get(position).getCertification());
                i.putExtra(KEY.ADDITIONAL_KEY, getList().get(position).getAdditional());
                i.putExtra(KEY.JOB_DESCRIPTION_KEY, getList().get(position).getDescription());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtPosition, txtSeat, txtLocation;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            txtPosition = (TextView) itemView.findViewById(R.id.txt_position);
            txtSeat = (TextView) itemView.findViewById(R.id.txt_seat);
            txtLocation = (TextView) itemView.findViewById(R.id.txt_location);
            cardView = (CardView) itemView.findViewById(R.id.cv_item);
        }
    }
}
