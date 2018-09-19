package com.example.login.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.example.database.model.DbUser;
import com.example.login.ILoginView;
import org.litepal.LitePal;

import java.util.List;

public class VerifyAccount implements IVerifyAccount {

    private Context context;

    private ILoginView mainView;

    public VerifyAccount(ILoginView mainView) {
        this.mainView = mainView;
    }

    public VerifyAccount(Context context, ILoginView mainView) {
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
                        List<DbUser> dbUsers = LitePal.where("username = ?",user).find(DbUser.class);
                        if (dbUsers.size() == 0) {
                            Log.e("TAG", "run: " );
                            List<DbUser> dbUsers1 = LitePal.where("status = ?","1").find(DbUser.class);
                            if (dbUsers1.size() == 0){
                            }else {
                                ContentValues values = new ContentValues();
                                values.put("status","0");
                                LitePal.updateAll(DbUser.class,values,"username = ?",dbUsers1.get(0).getUsername());
                            }
                            DbUser dbUser = new DbUser();
                            dbUser.setUsername(user);
                            dbUser.setPassword(pwd);
                            dbUser.setStatus(1);
                            if (dbUser.save()){
                                Log.e("TAG", "run: " );
                                mainView.callBack(1);
                            } else {
                                Log.e("TAG", "run: " );
                                mainView.callBack(2);
                            }
                        } else {
                            Log.e("TAG", "run: " );
                            if (dbUsers.get(0).getPassword().equals(pwd)){
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
