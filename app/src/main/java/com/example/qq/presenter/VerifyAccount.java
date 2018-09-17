package com.example.qq.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.example.database.model.DbUser;
import com.example.qq.IMainView;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class VerifyAccount implements IVerifyAccount {

    private Context context;

    private IMainView mainView;

    public VerifyAccount(IMainView mainView) {
        this.mainView = mainView;
    }

    public VerifyAccount(Context context, IMainView mainView) {
        this.context = context;
        this.mainView = mainView;
    }

    @Override
    public void verfyAccount(final String user, final String pwd) {
                Log.e("TAG", "run: " );
                if (user != null && !user.isEmpty()) {
                    Log.e("TAG", "run: " );
                    if (pwd != null && !pwd.isEmpty()) {
                        Log.e("TAG", "run: " );
                        List<DbUser> dbUser = LitePal.where("username = ?",user).find(DbUser.class);
                        if (dbUser.size() == 0) {
                            Log.e("TAG", "run: " );
                            DbUser dbUser1 = new DbUser();
                            dbUser1.setUsername(user);
                            dbUser1.setPassword(pwd);
                            dbUser1.setStatus(1);
                            if (dbUser1.save()){
                                Log.e("TAG", "run: " );
                                mainView.callBack(1);
                            } else {
                                Log.e("TAG", "run: " );
                                mainView.callBack(2);
                            }
                        } else {
                            Log.e("TAG", "run: " );
                            if (dbUser.get(0).getPassword().equals(pwd)){
                                Log.e("TAG", "run: " );
                                ContentValues values = new ContentValues();
                                values.put("status","1");
                                LitePal.updateAll(DbUser.class,values,"username = ?",user);
                                mainView.callBack(3);
                            } else {
                                Log.e("TAG", "run: " );
                                mainView.callBack(4);
                            }
                        }
                    }
                }else {

                }
    }

    @Override
    public String queryAccount(String user) {
        return null;
    }
}
