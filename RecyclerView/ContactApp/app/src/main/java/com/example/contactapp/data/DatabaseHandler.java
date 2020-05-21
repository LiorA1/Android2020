package com.example.contactapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.contactapp.MainActivity;
import com.example.contactapp.model.Contact;
import com.example.contactapp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper
{
    public DatabaseHandler(Context context)
    {
        super(context, Util._DATABASE_NAME, null, Util._DATABASE_VERSION);
    }

    public DatabaseHandler(@Nullable Context context,
                           @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory,
                           int version)
    {
        super(context, name, factory, version);


    }


    /**
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // we create our table(s)

        // CREATE TABLE tableName(id, name, phone)
        String createContactTable = "CREATE TABLE " + Util._TABLE_NAME + "("
                + Util._KEY_ID + " INTEGER PRIMARY KEY, " + Util._KEY_NAME + " TEXT, "
                + Util._KEY_PHONE_NUMBER + " TEXT" + ")";

        // creating our table:
        db.execSQL(createContactTable);


    }

    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String dropTable = "DROP TABLE IF EXISTS " + Util._DATABASE_NAME;

        db.execSQL(dropTable);
        //db.execSQL(dropTable, new String[]{Util._DATABASE_NAME});

        // create a table again
        onCreate(db);

    }

    // CRUD - Create, Read, Update, Delete.
    // Add contact -
    public void addContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Util._KEY_ID, "12");
        values.put(Util._KEY_NAME, contact.get_name());
        values.put(Util._KEY_PHONE_NUMBER, contact.get_phoneNumer());

        // Insert row
        db.insert(Util._TABLE_NAME, null, values);

        //Toast.makeText(MainActivity, "add contact: ",Toast.LENGTH_LONG).show();

        db.close();

    }

    // Get a contact
    public Contact getContact(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                Util._TABLE_NAME,
                new String[]{Util._KEY_ID, Util._KEY_NAME, Util._KEY_PHONE_NUMBER},
                Util._KEY_ID + "=?", new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }

        Contact contact = new Contact();
        contact.set_id(Integer.parseInt(cursor.getString(0)));
        contact.set_name(cursor.getString(1));
        contact.set_phoneNumer(cursor.getString(2));

        return contact;
    }

    // Get all Contacts
    public List<Contact> getAllContacts()
    {
        List<Contact> contactList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // Select all contacts
        String selectAll = "SELECT * FROM " + Util._TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll, null);

        // Loop through our data
        if(cursor.moveToFirst())
        {
            do
            {
                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_phoneNumer(cursor.getString(2));

                // add to the list
                contactList.add(contact);

            }while (cursor.moveToNext());

        }

        cursor.close();

        return contactList;
    }


    // CRUD: Update Contact -

    /**
     *
     * @param contact
     * @return int - the id of the updated row.
     */
    public int updateContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util._KEY_NAME, contact.get_name());
        values.put(Util._KEY_PHONE_NUMBER, contact.get_phoneNumer());

        // update the row
        return db.update(Util._TABLE_NAME, values,
                Util._KEY_ID + "=?",
                new String[]{String.valueOf(contact.get_id())});
    }

    // CRUD: Delete single contact -

    public void deleteContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util._TABLE_NAME, Util._KEY_ID + "=?",
                new String[]{String.valueOf(contact.get_id())});

        db.close();
    }

    // Get contacts count -
    public int getCount()
    {
        String countQuery = "SELECT * FROM " + Util._TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        db.close();
        
        return cursor.getCount();
    }


}
