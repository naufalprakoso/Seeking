package com.njy.seeking.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njy.seeking.R;
import com.njy.seeking.data.KEY;
import com.njy.seeking.model.Vacancy;
import com.njy.seeking.seeker.VacancyDetailSeekerActivity;

import java.util.ArrayList;

/**
 * Created by naufalprakoso on 12/05/18.
 */

public class VacancySeekerAdapter extends RecyclerView.Adapter<VacancySeekerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Vacancy> vacancies;

    public VacancySeekerAdapter(Context context, ArrayList<Vacancy> vacancies) {
        this.context = context;
        this.vacancies = vacancies;
    }

    public ArrayList<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(ArrayList<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_vacancy_seeker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtSeat.setText(getVacancies().get(position).getSeat() + " Seats left");
        holder.txtPosition.setText(getVacancies().get(position).getPosition());
        holder.txtLocation.setText(getVacancies().get(position).getName());

//        Glide.with(context)
//                .load(getVacancies().get(position).)
//                .into(holder.imgCompany);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VacancyDetailSeekerActivity.class);
                i.putExtra(KEY.POSITION_KEY, getVacancies().get(position).getPosition());
                i.putExtra(KEY.TYPE_KEY, getVacancies().get(position).getType());
                i.putExtra(KEY.LOCATION_KEY, getVacancies().get(position).getLocation());
                i.putExtra(KEY.SEAT_LEFT_KEY, getVacancies().get(position).getSeat());
                i.putExtra(KEY.SALARY_KEY, "Rp " + getVacancies().get(position).getSalary());
                i.putExtra(KEY.EXPERIENCE_KEY, getVacancies().get(position).getExperience());
                i.putExtra(KEY.LANGUAGE_KEY, getVacancies().get(position).getLanguage());
                i.putExtra(KEY.CERTIFICATION_KEY, getVacancies().get(position).getCertification());
                i.putExtra(KEY.ADDITIONAL_KEY, getVacancies().get(position).getAdditional());
                i.putExtra(KEY.JOB_DESCRIPTION_KEY, getVacancies().get(position).getDescription());
                i.putExtra(KEY.VACANCY_ID_KEY, getVacancies().get(position).getVacancyid());
                i.putExtra(KEY.NAME_GET_COMPANY_KEY, getVacancies().get(position).getName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getVacancies().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtPosition, txtSeat, txtLocation;
        CardView cardView;
        ImageView imgCompany;

        public ViewHolder(View itemView) {
            super(itemView);

            txtPosition = (TextView) itemView.findViewById(R.id.txt_position);
            txtSeat = (TextView) itemView.findViewById(R.id.txt_seat);
            txtLocation = (TextView) itemView.findViewById(R.id.txt_location);
            cardView = (CardView) itemView.findViewById(R.id.cv_item);
            imgCompany = (ImageView) itemView.findViewById(R.id.img_company);
        }
    }
}
