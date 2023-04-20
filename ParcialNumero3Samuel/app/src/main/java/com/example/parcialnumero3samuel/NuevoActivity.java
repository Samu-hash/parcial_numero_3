package com.example.parcialnumero3samuel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parcialnumero3samuel.persistences.RepositoryContacts;
import com.example.parcialnumero3samuel.persistences.models.Contacts;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreoElectronico, txtApellido;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("")) {

                    RepositoryContacts dbContactos = new RepositoryContacts(NuevoActivity.this);
                    Contacts c = new Contacts();
                    c.setNames(txtNombre.getText().toString());
                    c.setLast(txtApellido.getText().toString());
                    c.setPhone(txtTelefono.getText().toString());
                    c.setEmail(txtCorreoElectronico.getText().toString());
                    c = dbContactos.insertContact(c);

                    if (c.getIdentifier() > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }
}