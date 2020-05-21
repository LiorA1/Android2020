package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactapp.adapter.RecyclerViewAdapter;
import com.example.contactapp.data.DatabaseHandler;
import com.example.contactapp.model.Contact;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
{

    private static final String TAG = "MainActivity";
    private RecyclerView _recyclerView;
    private RecyclerViewAdapter _recyclerViewAdapter;
    private ArrayList<Contact> _contactArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _recyclerView = findViewById(R.id.RecyclerView);
        _recyclerView.setHasFixedSize(true);
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));

        _contactArrayList = new ArrayList<>();


        // init the handler:
        DatabaseHandler dbHandler = new DatabaseHandler(MainActivity.this);


        //dbHandler.addContact(new Contact(0253, "Gil", "0522-963258"));
        //dbHandler.addContact(new Contact(0255, "Dani", "0522-956258"));

        //Contact toBeDeleted = dbHandler.getContact(2);
        //dbHandler.deleteContact(toBeDeleted);

        List<Contact> contactList = dbHandler.getAllContacts();

        for(Contact contact: contactList)
        {
            _contactArrayList.add(contact);
            Log.d(TAG, "onCreate:" + contact.get_name() + ", id: " + contact.get_id());
        }

        // setup an Adapter
        _recyclerViewAdapter = new RecyclerViewAdapter(
                MainActivity.this,
                _contactArrayList);

        // connect the view to the Adapter.
        _recyclerView.setAdapter(_recyclerViewAdapter);
    }
}
