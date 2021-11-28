package MatthewJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    public static Account getLoggedAccount()
    {
        return loggedAccount;
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