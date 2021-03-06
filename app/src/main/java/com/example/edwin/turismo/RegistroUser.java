package com.example.edwin.turismo;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroUser extends AppCompatActivity {
private Button btnCancelarReg,btnRegistrarser,btnCanceVenta,btnConfirmar;
private EditText editRegPass1,editRegPass2,editRegEmail;
private ProgressBar progressBar;
private ObjectAnimator ani;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private int reps;
private Dialog dgConfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_user);
        /**/
        getSupportActionBar().hide();
        /**/
        /*asignacion de variables*/
        btnCancelarReg=findViewById(R.id.btnRegCancelar);
        editRegPass1=findViewById(R.id.editPassReg1);
        editRegPass2=findViewById(R.id.editPassReg2);
        editRegEmail=findViewById(R.id.editEmailReg);
        btnRegistrarser=findViewById(R.id.btnRegUser);
        /**/
               /*FIREBASE*/
        FirebaseApp.initializeApp(this);
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            }
        };
        /*accion de boton para calcelar un registro de usuario*/
        btnCancelarReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /*accion del boton registrar usuario*/
        btnRegistrarser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validacion_campos_vacios validarVacios=new Validacion_campos_vacios();
                if(validarVacios.validarVaciosReg(editRegEmail,editRegPass1,editRegPass2)){
                    editRegPass2.requestFocus();
                    editRegPass2.setError("Campo Requerido");
                    editRegPass1.requestFocus();
                    editRegPass1.setError("Campo Requerido");
                    editRegEmail.requestFocus();
                    editRegEmail.setError("Campo Requerido");
                }else if(validarVacios.validarCorreo(editRegEmail)){
                    if(validarVacios.compararPassword(editRegPass1,editRegPass2)){
if(editRegPass1.length()>=6){
    ventanaEmergente();

}else{
    editRegPass1.requestFocus();
    editRegPass1.setError("Password debe contener  6 dijitos");

}

                    }else{
                        editRegPass2.requestFocus();
                        editRegPass2.setError("Password no es igual");
                        editRegPass2.setText("");
                    }

                }else{
                    editRegEmail.requestFocus();
                    editRegEmail.setError("Email Incorrecto formato");
                }
            }
        });
    }
    /*para crear un nuevo usuario*/
    public int crearUser(){

        firebaseAuth.createUserWithEmailAndPassword(editRegEmail.getText().toString(), editRegPass1.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            /*insertado*/
                       reps =1;
                        } else {
                            /*no insertado*/
                        reps = 0;
                        }
                    }
                });
      return  reps;
    }
    /*para mostrar ventana emergente*/
    public void ventanaEmergente(){
        dgConfirmar=new Dialog(this,R.style.Theme_Dialog_Translucent);
        dgConfirmar.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dgConfirmar.setCancelable(false);
dgConfirmar.setContentView(R.layout.ventanaemergente);
dgConfirmar.show();
btnCanceVenta=dgConfirmar.findViewById(R.id.btnCancVent);
btnConfirmar=dgConfirmar.findViewById(R.id.btnGuarVent);
progressBar =dgConfirmar.findViewById(R.id.barraGuardar);
progressBar.setVisibility(View.GONE);
/*animacion de el progreso*/
ani=ObjectAnimator.ofInt(progressBar,"progress",0,200);

/*------*/
/*cancelar el registro*/
btnCanceVenta.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dgConfirmar.dismiss();
    }
});
/*para confirmar el registro del usuario*/
btnConfirmar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
progressBar.setVisibility(View.VISIBLE);
btnCanceVenta.setVisibility(View.INVISIBLE);
btnConfirmar.setVisibility(View.INVISIBLE);
if(crearUser()==1){
    Toast.makeText(getApplication(),"Usuario Registrado con exito",Toast.LENGTH_SHORT).show();
    dgConfirmar.dismiss();
    finish();

}else{
    progressBar.setVisibility(View.GONE);
    btnConfirmar.setVisibility(View.VISIBLE);
  btnCanceVenta.setVisibility(View.VISIBLE);
    Toast.makeText(getApplication(),"Email ya esta registrado",Toast.LENGTH_SHORT).show();
}

    }
});
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
