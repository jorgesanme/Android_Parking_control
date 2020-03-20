package com.jorge.crud_parking_control;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends  RecyclerView.Adapter<MiAdaptador.ViewHolder>  {
    protected View.OnClickListener onClickListener;

    private LayoutInflater inflador;
    //protected AlmacenUsuariosSW_PHP_MiHosting usuarios;
    //protected MiAdaptador(AlmacenUsuariosSW_PHP_MiHosting usuarios){
    //this.usuarios = usuarios;
    //}
    private ArrayList<Propietario> lista;

    public MiAdaptador(Context context, ArrayList<Propietario> lista) {
        super();
        this.lista = lista;
        inflador =(LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.elementos_lista, parent, false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        //holder.titulo.setText(lista.get(i));
        holder.titulo.setText(lista.get(i).getNombre());
        holder.subtitutlo.setText(lista.get(i).getApellidos());
        holder.id.setText(String.valueOf( lista.get(i).get_id()));

        //determinar eventos para los botones
        holder.setOnClickListeners();

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titulo, subtitutlo;
        public TextView id;
        public Button Bmodify;
        public Button Beliminar;
         Context context;

        ViewHolder(View itemView) {
            super(itemView);
            //Obteniendo referencia Context
            context=itemView.getContext();

            subtitutlo=itemView.findViewById(R.id.subtitulo);
            titulo = itemView.findViewById(R.id.titulo);
            id = itemView.findViewById(R.id.id);

            Bmodify= itemView.findViewById(R.id.modificar);
            Beliminar = itemView.findViewById(R.id.eliminar);

        }

        void setOnClickListeners(){
            Beliminar.setOnClickListener(this);
            Bmodify.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.eliminar:
                    Intent intentEli= new Intent(context,EliminarRegistro.class );
                    intentEli.putExtra("id",id.getText());

                    context.startActivity(intentEli);
                    break;

                case R.id.modificar:
                    Intent intentMod= new Intent(context, ModificarRegistro.class );
                    intentMod.putExtra("id",id.getText());
                    context.startActivity(intentMod);
                    break;
            }

        }
    }
    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
