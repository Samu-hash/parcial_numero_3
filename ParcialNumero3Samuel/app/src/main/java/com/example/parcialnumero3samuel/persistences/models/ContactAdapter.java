package com.example.parcialnumero3samuel.persistences.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.example.parcialnumero3samuel.R;
import com.example.parcialnumero3samuel.VerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactoViewHolder>{


    ArrayList<Contacts> listaContactos;
    ArrayList<Contacts> listaOriginal;

    public ContactAdapter(ArrayList<Contacts> listaContactos) {
        this.listaContactos = listaContactos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaContactos);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(listaContactos.get(position).getNames());
        holder.viewApellido.setText(listaContactos.get(position).getLast());
        holder.viewTelefono.setText(listaContactos.get(position).getPhone());
        holder.viewCorreo.setText(listaContactos.get(position).getEmail());
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaContactos.clear();
            listaContactos.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contacts> collecion = listaContactos.stream()
                        .filter(i -> i.getNames().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaContactos.clear();
                listaContactos.addAll(collecion);
            } else {
                for (Contacts c : listaOriginal) {
                    if (c.getNames().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaContactos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono, viewCorreo, viewApellido;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellido = itemView.findViewById(R.id.viewApellido);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaContactos.get(getAdapterPosition()).getIdentifier());
                    context.startActivity(intent);
                }
            });
        }
    }
}
