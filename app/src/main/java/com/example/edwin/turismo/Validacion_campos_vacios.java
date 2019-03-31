package com.example.edwin.turismo;

import android.util.Patterns;
import android.widget.EditText;

/**
 * Created by Edwin on 26/3/2019.
 */

public class Validacion_campos_vacios {


    public Validacion_campos_vacios() {
    }
    /*valida los cammpos vacios del login*/
    public boolean validarVaciosLogin(EditText email,EditText password){
        if(email.getText().toString().trim().isEmpty() && password.getText().toString().trim().isEmpty()){
            return true;
        }else{

            return false;
        }
    }
    /*valida formato de correo*/
    public boolean validarCorreo(EditText email){
        if(Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            return true;
        }else{
            return false;
        }
    }

    /*validar campos de registros de usuarios */
    public boolean validarVaciosReg(EditText email,EditText password,EditText repPass){
        if(email.getText().toString().trim().isEmpty() && password.getText().toString().trim().isEmpty() && repPass.getText().toString().trim().isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    /*para comparar contraseñas iguales*/
    public boolean compararPassword(EditText pass1,EditText pass2){
        if(pass1.getText().toString().equals(pass2.getText().toString())){
            return true;
        }else
        {
            return false;
        }

    }
}
