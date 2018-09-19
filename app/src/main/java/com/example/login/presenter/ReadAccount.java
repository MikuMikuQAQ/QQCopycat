package com.example.login.presenter;

import android.content.Context;
import com.example.database.model.DbUser;
import com.example.login.ILoginView;
import org.litepal.LitePal;

import java.util.List;

public class ReadAccount implements IReadAccount {

    private Context context;

    private ILoginView mainView;

    public ReadAccount(ILoginView mainView) {
        this.mainView = mainView;
    }

    public ReadAccount(Context context, ILoginView mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    @Override
    public void readStatus() {
        List<DbUser> dbUsers = LitePal.where("status = ?","1").find(DbUser.class);
        if (dbUsers.size() == 0) {

        } else {
            mainView.getReadStatus(dbUsers.get(0).getUsername(),dbUsers.get(0).getPassword());
        }
    }
}
