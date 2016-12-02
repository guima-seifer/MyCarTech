package com.pti.MyCarTech.Main.Sessions;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pti.MyCarTech.R;

/**
 * Created by fernando on 02-12-2016.
 */

class ViewHolder extends RecyclerView.ViewHolder{
    public TextView getItemTitle() {
        return itemTitle;
    }

    public TextView getItemDate() {
        return itemDate;
    }

    public TextView itemTitle;
    public TextView itemDate;
    public TextView itemDistance;
    public TextView consumo_fixed;
    public TextView distance_fixed;
    public TextView itemConsumo;
    public TextView session_fixed;

    private Snackbar snackbar;

    public TextView getItemDistance() {
        return itemDistance;
    }

    public TextView getItemConsumo() {
        return itemConsumo;
    }

    public ViewHolder(View itemView) {
        super(itemView);
        itemTitle = (TextView)itemView.findViewById(R.id.item_id);
        session_fixed = (TextView)itemView.findViewById(R.id.session_id);
        itemConsumo = (TextView)itemView.findViewById(R.id.item_cons);
        consumo_fixed = (TextView)itemView.findViewById(R.id.item_cons_text);
        itemDate = (TextView)itemView.findViewById(R.id.item_date);
        itemDistance = (TextView)itemView.findViewById(R.id.item_dist);
        distance_fixed = (TextView)itemView.findViewById(R.id.item_dist_text);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                int position = getAdapterPosition() + 1;

                snackbar = Snackbar
                        .make(v, "Clicked on item "+ position, Snackbar.LENGTH_LONG)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.setActionTextColor(v.getResources().getColor(R.color.colorAccent));
                snackbar.show();
            }
        });
    }
}