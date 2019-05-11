package com.example.edwin.turismo;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


@SuppressLint("ValidFragment")
public class Detalle extends Fragment {


private  String titulo,fecha,descuento,descripcion,contacto,telefono,comercio,email ;
private Bitmap img;
    public Detalle(String titul,String fecha,String descuento,String decrip,String conta,String tele,String comer,String email,Bitmap img) {
        this.titulo=titul;
        this.fecha=fecha;
        this.descuento=descuento;
        this.descripcion=decrip;
        this.contacto=conta;
        this.telefono=tele;
        this.comercio=comer;
        this.email=email;
        this.img=img;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

       TextView txttitulo=(TextView)view.findViewById(R.id.txtTitulo);
        txttitulo.setText(titulo);
        TextView txtFecha=(TextView)view.findViewById(R.id.txtFecha);
        txtFecha.setText(fecha);
        TextView txtDesc=(TextView)view.findViewById(R.id.txtDescuento);
        txtDesc.setText(descuento);
        TextView txtdescrip=(TextView)view.findViewById(R.id.txtDescripDetalle);
        txtdescrip.setText(descripcion);
        TextView txtcontacto=(TextView)view.findViewById(R.id.txtContacto);
        txtcontacto.setText(contacto);
        TextView txttele=(TextView)view.findViewById(R.id.txtTelefono);
txttele.setText(telefono);
TextView txtcomer=(TextView)view.findViewById(R.id.txtComercio);
txtcomer.setText(comercio);
TextView txtemail=(TextView)view.findViewById(R.id.txtEmail);
txtemail.setText(email);
        ImageView imgDes=(ImageView)view.findViewById(R.id.imgDetalle);
        if(img!=null){
            imgDes.setImageBitmap(img);

        }else{
            imgDes.setImageResource(R.drawable.bannerprincipal);

        }

        // Inflate the layout for this fragment
        return view;
    }

}
