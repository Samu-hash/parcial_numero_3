package com.example.parcialnumero3samuel.persistences;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.parcialnumero3samuel.Util.Util;
import com.example.parcialnumero3samuel.persistences.db.ConnectHelper;
import com.example.parcialnumero3samuel.persistences.models.Contacts;

import java.util.ArrayList;

public class RepositoryContacts  extends ConnectHelper {

    private Context context;

    public RepositoryContacts(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Contacts insertContact(Contacts c) {

        try {
            ConnectHelper dbHelper = new ConnectHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombres", c.getNames());
            values.put("apellidos", c.getLast());
            values.put("telefono", c.getPhone());
            values.put("correo_electronico", c.getEmail());
            int id = (int) db.insert(Util.TABLE_NAME, null, values);
            c.setIdentifier(id);
        } catch (Exception ex) {
            ex.toString();
            c.setIdentifier(0);
        }
        return c;
    }

    public Contacts viewContacts(int id) {

        ConnectHelper dbHelper = new ConnectHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contacts contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME
                + " WHERE identifier = " + id + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {
            contacto = new Contacts();
            contacto.setIdentifier(cursorContactos.getInt(0));
            contacto.setNames(cursorContactos.getString(1));
            contacto.setLast(cursorContactos.getString(2));
            contacto.setPhone(cursorContactos.getString(3));
            contacto.setEmail(cursorContactos.getString(4));
        }

        cursorContactos.close();

        return contacto;
    }

    public boolean updateContact(Contacts c) {

        boolean correcto = false;

        ConnectHelper dbHelper = new ConnectHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + Util.TABLE_NAME
                    + " SET nombres = '" + c.getNames()
                    + "', apellidos = '" + c.getLast()
                    + "', telefono = '" + c.getPhone()
                    + "', correo_electronico = '" + c.getEmail()
                    + "' WHERE identifier='" + c.getIdentifier() + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean deleteContact(int id) {

        boolean correcto = false;

        ConnectHelper dbHelper = new ConnectHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + Util.TABLE_NAME + " WHERE identifier = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public ArrayList<Contacts> viewContacts() {

        ConnectHelper dbHelper = new ConnectHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contacts> listaContactos = new ArrayList<>();
        Contacts contacto;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME + " ORDER BY nombres ASC", null);

        if (cursorContactos.moveToFirst()) {
            do {
                contacto = new Contacts();
                contacto.setIdentifier(cursorContactos.getInt(0));
                contacto.setNames(cursorContactos.getString(1));
                contacto.setLast(cursorContactos.getString(2));
                contacto.setPhone(cursorContactos.getString(3));
                contacto.setEmail(cursorContactos.getString(4));
                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listaContactos;
    }
}
