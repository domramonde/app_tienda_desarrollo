package com.dronesolutions.appgamarra.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dronesolutions.appgamarra.R;
import com.dronesolutions.appgamarra.ui.models.Categoria;

import java.util.List;

/**
 * Created by VANESSA on 22/02/2017.
 */
public class CategoriaPadreAdapter extends RecyclerView.Adapter<CategoriaPadreAdapter.CatPadreViewHolder> {

    private List<Categoria> ListaRops;

    public static class CatPadreViewHolder extends RecyclerView.ViewHolder {
        //Campos respectivos del item
        public TextView txtCodigo;
        public TextView txtFechaCrea;

        public CatPadreViewHolder(View v){
            super(v);

            txtCodigo  = (TextView) v.findViewById(R.id.txt_card_view_cod_rop);
            txtFechaCrea  = (TextView) v.findViewById(R.id.txt_card_view_fec_crea_rop);

        }
    }

    public CategoriaPadreAdapter(List<Categoria> rops){
        this.ListaRops = rops;
    }

    @Override
    public int getItemCount(){
        return ListaRops.size();
    }

    @Override
    public CatPadreViewHolder onCreateViewHolder(ViewGroup viewGroup,int i){
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_rop,viewGroup,false);
        return new CatPadreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CatPadreViewHolder viewHolder,int i){
        viewHolder.txtCodigo.setText(String.valueOf(ListaRops.get(i).getId()) );
        viewHolder.txtFechaCrea.setText( ListaRops.get(i).getEventDate().toString() );
    }

}
