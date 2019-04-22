package com.example.edwin.turismo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.List;

public class Listacategorias extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {
    private String url = "";
    private RequestQueue rq;
    private JsonRequest jrq;

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
    private List<String> coordenadas=new ArrayList<String>();
    private String mensaje = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacategorias);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*para cambiarle el titulo a la barra */
this.setTitle(R.string.regresar);
/*para aser las peticiones*/
rq= Volley.newRequestQueue(getApplicationContext());
/*para seleccionar la categoria*/
selectCategoria(1);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return false;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"error de conexion a la ws",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        Toast.makeText(getApplicationContext(),"cantidad "+jsonArray.length(),Toast.LENGTH_LONG).show();
       try {
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

        /*llenando los array*/
                id_cate.add(jsonObject.optInt("id_promo"));
                fecha_promo.add(jsonObject.optString("fecha_promocion"));
                decuento_promo.add(jsonObject.optDouble("descuento"));
                titulo.add(jsonObject.optString("titulo_img"));
                descripcion.add(jsonObject.optString("descripcio_img"));
/*para convertir la img a bitmap*/
                String img = jsonObject.optString("img");
                byte[] bytedate = android.util.Base64.decode(img, android.util.Base64.DEFAULT);
                Bitmap bitmapimg = BitmapFactory.decodeByteArray(bytedate, 0, bytedate.length);
/*-----------------------------------------------------------------*/
                imagen.add(bitmapimg);
                nombre_contacto.add(jsonObject.optString("nombre_contacto"));
                nombre_comercio.add(jsonObject.optString("email_contacto"));
                telefono.add(jsonObject.optString("telefono_numbers"));
                nombre_comercio.add(jsonObject.optString("nombre_comercio"));
                coordenadas.add(jsonObject.optString("coordenadas"));

            }
           Toast.makeText(getApplicationContext(),"data "+ id_cate.get(0),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

    /*metodo para traer  todas las categorias */
    public void selectCategoria(int categoria) {
        switch (categoria) {

            case 1:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=1";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 2:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=2";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 3:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=3";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 4:
                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=4";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 5:
                url = " https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=5";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            case 6:

                url = "https://coadunate-thin.000webhostapp.com/webservice/crud/selectCateapp.php?accion=6";
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);
                break;
            default:
                mensaje = "No selecciono Categoria";

        }

    }
}