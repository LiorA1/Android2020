package com.example.contactapp.model;

public class Contact
{
    private int _id;
    private String _name;
    private String _phoneNumber;

    public Contact()
    {

    }

    public Contact(int _id, String _name, String _phoneNumber) {
        this._id = _id;
        this._name = _name;
        this._phoneNumber = _phoneNumber;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phoneNumer() {
        return _phoneNumber;
    }

    public void set_phoneNumer(String _phoneNumer) {
        this._phoneNumber = _phoneNumer;
    }
}
