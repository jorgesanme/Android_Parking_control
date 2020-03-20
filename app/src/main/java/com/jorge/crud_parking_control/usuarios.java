package com.jorge.crud_parking_control;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class usuarios extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MiAdaptador adaptador;
    private View.OnClickListener listener;
    private FloatingActionButton fab;
    private  Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuarios);
        recyclerView = findViewById(R.id.recyclerView);
        context= getApplicationContext();

        adaptador = new MiAdaptador(this,
                MainActivity.usuarios.listaUsuarios(10));

        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fab= findViewById(R.id.Boton_flotante);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    fab.show();
                }


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if( dy> 0 || dy < 0 && fab.isShown()){
                    fab.hide();
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent((android.content.Context) context, MainActivity.class);
                startActivity(i);

            }
        });
        // para mostrar un valor
        adaptador.setOnItemClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                Propietario s = MainActivity.usuarios.listaUsuarios(10).get(pos);
                Toast.makeText(usuarios.this, "Selección: \t" + pos
                        + "\nNombre: \t" + s.getNombre()+" "+s.getApellidos(), Toast.LENGTH_LONG).show();



            }

        });
    }
}