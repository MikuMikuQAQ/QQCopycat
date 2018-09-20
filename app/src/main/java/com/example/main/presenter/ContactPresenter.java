package com.example.main.presenter;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.example.main.model.ContactDatabase;
import com.example.main.view.IContactFragment;

import java.util.ArrayList;
import java.util.List;

public class ContactPresenter implements IContactPresenter {

    private Context context;

    private IContactFragment fragment;

    private List<ContactDatabase> databaseList = new ArrayList<>();

    public ContactPresenter(Context context, IContactFragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @Override
    public List<ContactDatabase> readContact() {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    ContactDatabase database = new ContactDatabase(name, num);
                    databaseList.add(database);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return databaseList;
    }
}
