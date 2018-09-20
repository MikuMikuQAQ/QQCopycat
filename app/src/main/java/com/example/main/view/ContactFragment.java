package com.example.main.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.login.R;
import com.example.main.adapter.ContactAdapter;
import com.example.main.model.ContactDatabase;
import com.example.main.presenter.ContactPresenter;
import com.example.main.presenter.IContactPresenter;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment implements IContactFragment {

    private IContactPresenter presenter;

    private List<ContactDatabase> databaseList = new ArrayList<>();

    private ContactAdapter adapter;

    private RecyclerView recyclerView;

    public static ContactFragment newInstance(){
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact,container,false);
        presenter = new ContactPresenter(getContext(),this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[] {
                    Manifest.permission.READ_CONTACTS
            },1);
        }else {
            databaseList = presenter.readContact();
            //adapter.notifyDataSetChanged();
        }

        initView(view);
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.contact_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new ContactAdapter(view.getContext(),databaseList);
        recyclerView.setAdapter(adapter);
    }
}
