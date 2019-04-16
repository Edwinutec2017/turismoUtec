package com.example.edwin.turismo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 15/04/2019.
 */

public class AdaptadorCategorias extends BaseAdapter{
private Context context;

    private List<String> tituloPromo=new ArrayList<String>();
    private List<Bitmap> imgPromo = new ArrayList<Bitmap>();
private TextView titulo;
private ImageView img;
View fila;

    public AdaptadorCategorias(Context context, List<String> tituloPromo, List<Bitmap> imgPromo) {
        this.context = context;
        this.tituloPromo = tituloPromo;
        this.imgPromo = imgPromo;
    }

    @Override
    public int getCount() {
        return tituloPromo.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view ==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            fila=new View(context);
            fila=layoutInflater.inflate(R.layout.llistadise,null);

        }else{fila=(View)view;
        }
        titulo=(TextView)fila.findViewById(R.id.txtTituloPromo);
        img=(ImageView)fila.findViewById(R.id.imgPromo);
        titulo.setText(tituloPromo.get(i));
        img.setImageBitmap(imgPromo.get(i));
        return fila;
    }
}
