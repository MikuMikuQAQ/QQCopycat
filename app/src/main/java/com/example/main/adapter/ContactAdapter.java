package com.example.main.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.example.login.R;
import com.example.main.model.ContactDatabase;
import com.example.main.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;

    private List<ContactDatabase> databaseList = new ArrayList<>();

    public ContactAdapter(Context context, List<ContactDatabase> databaseList) {
        this.context = context;
        this.databaseList = databaseList;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder viewHolder, int i) {
        ContactDatabase database = databaseList.get(i);
        viewHolder.name.setText(database.getName());
        viewHolder.num.setText(database.getPhoneNum());
        Glide.with(viewHolder.view).load(R.drawable.bigmiku).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return databaseList != null?databaseList.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        AppCompatTextView name;

        AppCompatTextView num;

        RoundImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.contact_name);
            num = view.findViewById(R.id.contact_mun);
            imageView = view.findViewById(R.id.contact_image);
        }
    }
}
