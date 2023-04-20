package com.example.parcialnumero3samuel.Util;

public class Util {

    public static final int VERSION_SQL = 1;
    public static final String DB_NAME = "parcial3.db";
    public static final String TABLE_NAME = "contactos";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"(identifier integer primary key autoincrement, " +
            "nombres text not null, apellidos text not null, telefono text not null, correo_electronico text)";

    public static final String DROP_TABLE = "DROP TABLE "+TABLE_NAME;

}
