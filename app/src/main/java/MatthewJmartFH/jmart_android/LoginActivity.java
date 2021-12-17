package MatthewJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import MatthewJmartFH.jmart_android.model.Account;
import MatthewJmartFH.jmart_android.request.LoginRequest;

public class LoginActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    private static Account loggedAccount = null;
    public static final String PREF_SHARED = "pref_shared";
    public static final String EMAIL_KEY = "email_key";
    public static final String PASS_KEY  = "pass_key";

    public static Account getLoggedAccount()
    {
        return loggedAccount;
    }

    SharedPreferences preferences;
    String email_sharedPreference, pass_sharedPreference;

    @Override
    protected void onStart() {
        super.onStart();

        if(email_sharedPreference != null && pass_sharedPreference != null)
        {
            Response.Listener<String> listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        if(object != null)
                        {
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            loggedAccount = gson.fromJson(object.toString(),Account.class);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            LoginRequest loginRequest = new LoginRequest(email_sharedPreference.toString(),
                    pass_sharedPreference.toString(),listener,null);
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(loginRequest);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         EditText email = findViewById(R.id.email);
         EditText password = findViewById(R.id.password);
        TextView signIn = findViewById(R.id.signin);
        Button loginbtn =(Button)findViewById(R.id.loginbtn);
        EditText signup = findViewById(R.id.signupnow);

        preferences = getSharedPreferences(PREF_SHARED, Context.MODE_PRIVATE);

        email_sharedPreference = preferences.getString(EMAIL_KEY,null);
        pass_sharedPreference = preferences.getString(PASS_KEY,null);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String>listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject obj = new JSONObject(response);
                            if(obj != null)
                            {
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString(EMAIL_KEY,email.getText().toString());
                                editor.putString(PASS_KEY,password.getText().toString());

                                editor.apply();

                                Toast.makeText(LoginActivity.this,"Login Success",
                                        Toast.LENGTH_SHORT).show();
                                loggedAccount = gson.fromJson(obj.toString(),Account.class);
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(intent);
                            }

                        }
                        catch(JSONException e){
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,"Login Error",
                                    Toast.LENGTH_SHORT).show();
                                

                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_LONG);
                    }
                };

                LoginRequest loginReq = new LoginRequest(email.getText().toString(),password.getText()
                .toString(),listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginReq);
            }
        });
        signup.setInputType(InputType.TYPE_NULL);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }





}