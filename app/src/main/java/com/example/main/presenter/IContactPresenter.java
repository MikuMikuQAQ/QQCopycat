package com.example.main.presenter;

import com.example.main.model.ContactDatabase;

import java.util.List;

public interface IContactPresenter {
    List<ContactDatabase> readContact();
}
