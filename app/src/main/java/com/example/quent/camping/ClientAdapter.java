package com.example.quent.camping;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by quent on 05/12/2016.
 */

public class ClientAdapter extends BaseAdapter {
    private ArrayList<Client> listClient;
    private LayoutInflater inflater;

    public ClientAdapter(Context context, ArrayList<Client> listClient) {
        inflater = LayoutInflater.from(context);
        this.listClient = listClient;
    }

    @Override
    public int getCount() {
        return listClient.size();
    }

    @Override
    public Object getItem(int position) {
        return listClient.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder; //APPEL de la classe interne
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.vue_un_client,null);
            holder.vnom = (TextView)convertView.findViewById(R.id.textViewNom);
            holder.vprenom = (TextView)convertView.findViewById(R.id.textViewPrenom);
            holder.vdatenaiss = (TextView)convertView.findViewById(R.id.textViewDateNaissance);
            holder.vnumPort = (TextView)convertView.findViewById(R.id.textViewNumPortable);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//Affectation des éléments de la liste au holder

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        holder.vnom.setText(listClient.get(position).getNom());
        holder.vprenom.setText(listClient.get(position).getPrenom());
        holder.vdatenaiss.setText(sdf.format(listClient.get(position).getDateNais()));

        String s=listClient.get(position).getNumPortable();

        if (s.length() > 10) s="Numéro invalide";
        else s=String.format("%s.%s.%s.%s.%s", s.substring(0, 2), s.substring(2, 4), s.substring(4, 6),s.substring(6, 8),s.substring(8, 10));

        holder.vnumPort.setText(s);

        if(position % 2 == 0){
            convertView.setBackgroundColor(Color.rgb(238, 233, 233));
        }
        else {
            convertView.setBackgroundColor(Color.rgb(255, 255, 255));
        }

        return convertView;

    }

    private class ViewHolder {
        TextView vnom, vprenom, vdatenaiss, vnumPort;
    }
}
