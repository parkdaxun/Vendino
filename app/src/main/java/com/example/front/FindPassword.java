package com.example.front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.front.domain.ChangePwForm;
import com.example.front.domain.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FindPassword extends AppCompatActivity {

    Retrofit retrofit = new APIClient().getRetrofit();
    Service service = retrofit.create(Service.class);
    EditText id;
    EditText pw;
    EditText newpw1;
    EditText newpw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

         id = findViewById(R.id.id2);
        newpw1 = findViewById(R.id.newPassword);
        newpw2 = findViewById(R.id.newPassword2);
    }


    public void ChangePassword(View view) {
        boolean flag = newpw1.getText().toString().equals(newpw2.getText().toString());
        if(!flag){
            return;
        }
        System.out.println("login");
        ChangePwForm cpf = new ChangePwForm();
        cpf.setId(id.getText().toString());
        cpf.setNewpw(newpw2.getText().toString());
        System.out.println(id.getText().toString()+"/"+newpw2.getText().toString());
        Call<JsonObject> call = service.changePw(cpf);
        try {
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.isSuccessful()){
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                }
            });

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }




    }
}