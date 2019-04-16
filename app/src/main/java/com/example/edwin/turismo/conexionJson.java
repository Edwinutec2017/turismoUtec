package com.example.edwin.turismo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Edwin on 16/04/2019.
 */

public class conexionJson implements Response.Listener<JSONObject>, Response.ErrorListener {
    private String url = "";
    private RequestQueue rq;
    private JsonRequest jrq;
    private Context context;
    /*array para traer los datos*/
    private List<Integer> id_cate = new ArrayList<Integer>();
    private List<String> fecha_promo = new ArrayList<String>();
    private List<Double> decuento_promo = new ArrayList<Double>();
    private List<String> titulo = new ArrayList<String>();
    private List<String> descripcion = new ArrayList<String>();
    private List<Bitmap> imagen = new ArrayList<Bitmap>();
    private List<String> nombre_contacto = new ArrayList<String>();
    private List<String> email = new ArrayList<String>();
    private List<String> telefono = new ArrayList<String>();
    private List<String> nombre_comercio = new ArrayList<String>();
    private String mensaje = "";

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /*-*******************************************************----*/
    public conexionJson(Context context, int idcat) {
        this.context = context;
        rq = Volley.newRequestQueue(context.getApplicationContext());
        selectCategoria(idcat);
    }

    /*metodo para traer  todas las categorias */
    public void selectCategoria(int categoria) {
        switch (categoria) {
            case 1:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCate.php?accion=1";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 2:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCate.php?accion=2";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 3:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCate.php?accion=3";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 4:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCate.php?accion=4";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 5:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCate.php?accion=5";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 6:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCate.php?accion=6";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            default:
                mensaje = "No selecciono Categoria";

        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        mensaje = "Error no se  puede moestrar la categoria";
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
        /*llenando los array*/
                this.id_cate.add(jsonObject.optInt("id_promo"));
                this.fecha_promo.add(jsonObject.optString("fecha_promocion"));
                this.decuento_promo.add(jsonObject.optDouble("descuento"));
                this.titulo.add(jsonObject.optString("titulo_img"));
                this.descripcion.add(jsonObject.optString("descripcion_img"));
/*para convertir la img a bitmap*/
                String img = jsonObject.optString("imagen");
                byte[] bytedate = android.util.Base64.decode(img, android.util.Base64.DEFAULT);
                Bitmap bitmapimg = BitmapFactory.decodeByteArray(bytedate, 0, bytedate.length);
/*-----------------------------------------------------------------*/
                this.imagen.add(bitmapimg);
                this.nombre_contacto.add(jsonObject.optString("nombre_contacto"));
                this.nombre_comercio.add(jsonObject.optString("email_Contacto"));
                this.telefono.add(jsonObject.optString("telefono_numbers"));
                this.nombre_comercio.add(jsonObject.optString("nombre_comercio"));

            }
        } catch (JSONException e) {
            e.printStackTrace();

        }

    }

    public List<Integer> getId_cate() {
        return id_cate;
    }

    public void setId_cate(List<Integer> id_cate) {
        this.id_cate = id_cate;
    }

    public List<String> getFecha_promo() {
        return fecha_promo;
    }

    public void setFecha_promo(List<String> fecha_promo) {
        this.fecha_promo = fecha_promo;
    }

    public List<Double> getDecuento_promo() {
        return decuento_promo;
    }

    public void setDecuento_promo(List<Double> decuento_promo) {
        this.decuento_promo = decuento_promo;
    }

    public List<String> getTitulo() {
        return titulo;
    }

    public void setTitulo(List<String> titulo) {
        this.titulo = titulo;
    }

    public List<String> getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(List<String> descripcion) {
        this.descripcion = descripcion;
    }

    public List<Bitmap> getImagen() {
        return imagen;
    }

    public void setImagen(List<Bitmap> imagen) {
        this.imagen = imagen;
    }

    public List<String> getNombre_contacto() {
        return nombre_contacto;
    }

    public void setNombre_contacto(List<String> nombre_contacto) {
        this.nombre_contacto = nombre_contacto;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getTelefono() {
        return telefono;
    }

    public void setTelefono(List<String> telefono) {
        this.telefono = telefono;
    }

    public List<String> getNombre_comercio() {
        return nombre_comercio;
    }

    public void setNombre_comercio(List<String> nombre_comercio) {
        this.nombre_comercio = nombre_comercio;
    }
}
