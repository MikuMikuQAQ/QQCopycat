package com.example.main.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import com.example.login.R;

import java.util.Calendar;
import java.util.Date;

public class SetFragment extends Fragment implements View.OnClickListener {

    private AppCompatTextView timeView;
    private AppCompatButton timeButton;

    private DatePickerDialog dialog;

    private String timeString = "";
    private int year;
    private int day;
    private int month;

    private Calendar calendar;

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            timeView.setText(changeDate(year, month + 1, dayOfMonth));
        }
    };

    public static SetFragment newInstance() {
        SetFragment fragment = new SetFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
        initView(view);

        timeView.setText(getDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_button:
                dialog.show();
                break;
            default:
                break;
        }
    }

    private void initView(View view) {
        timeView = view.findViewById(R.id.time_view);
        timeButton = view.findViewById(R.id.time_button);

        dialog = new DatePickerDialog(view.getContext(), dateSetListener, year, month - 1, day);

        timeButton.setOnClickListener(this);
    }

    private void setData() {
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    private String getDate() {
        setData();
        timeString = year + "年" + month + "月" + day + "日";
        return timeString;
    }

    private String changeDate(int year, int month, int dayOfMonth) {
        timeString = year + "年" + month + "月" + dayOfMonth + "日";
        return timeString;
    }
}
