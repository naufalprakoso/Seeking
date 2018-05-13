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

import com.njy.seeking.seeker.CertificationDetailActivity;
import com.njy.seeking.R;
import com.njy.seeking.data.KEY;
import com.njy.seeking.model.Certification;

import java.util.ArrayList;

/**
 * Created by naufalprakoso on 12/05/18.
 */

public class CertificationAdapter extends RecyclerView.Adapter<CertificationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Certification> certifications;

    public ArrayList<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(ArrayList<Certification> certifications) {
        this.certifications = certifications;
    }

    public CertificationAdapter(Context context, ArrayList<Certification> certifications) {
        this.context = context;
        this.certifications = certifications;
    }

    @NonNull
    @Override
    public CertificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_certification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CertificationAdapter.ViewHolder holder, final int position) {
        holder.txtName.setText(getCertifications().get(position).getName());
        holder.txtOrganize.setText(getCertifications().get(position).getOrganize());
        holder.txtPrice.setText(getCertifications().get(position).getPrice());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CertificationDetailActivity.class);
                i.putExtra(KEY.NAME_CERTIFICATION_KEY, "" + getCertifications().get(position).getName());
                i.putExtra(KEY.ORGANIZE_CERTIFICATION_KEY, "" + getCertifications().get(position).getOrganize());
                i.putExtra(KEY.PRICE_CERTIFICATION_KEY, "Rp " + getCertifications().get(position).getPrice());
                i.putExtra(KEY.TEST_DATE_CERTIFICATION_KEY, "" + getCertifications().get(position).getTestdate());
                i.putExtra(KEY.TEST_DESCRIPTION_CERTIFICATION_KEY, "" + getCertifications().get(position).getTestdescription());
                i.putExtra(KEY.VENUE_CERTIFICATION_KEY, "" + getCertifications().get(position).getVenue());
                i.putExtra(KEY.LANGUAGE_CERTIFICATION_KEY, "" + getCertifications().get(position).getLanguage());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getCertifications().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtOrganize, txtPrice;
        ImageView imgCompany;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price);
            txtOrganize = (TextView) itemView.findViewById(R.id.txt_organize);
            imgCompany = (ImageView) itemView.findViewById(R.id.img_company);
            cardView = (CardView) itemView.findViewById(R.id.cv_item);
        }
    }
}
