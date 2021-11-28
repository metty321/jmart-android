package MatthewJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import MatthewJmartFH.jmart_android.model.Account;
import MatthewJmartFH.jmart_android.request.LoginRequest;
import MatthewJmartFH.jmart_android.request.RegisterRequest;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText name = findViewById(R.id.name2);
        EditText email = findViewById(R.id.email2);
        EditText password = findViewById(R.id.password2);
        TextView signIn = findViewById(R.id.signin2);
        Button regbtn =findViewById(R.id.regbtn);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String>listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            System.out.println(response);
                            JSONObject obj = new JSONObject(response);
                            if(obj != null)
                            {
                                Toast.makeText(RegisterActivity.this,"Register Success",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }


                        }
                        catch(JSONException e){
                            e.printStackTrace();
                            System.out.println("input invalid!");
                            Toast.makeText(RegisterActivity.this,"Register Error",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,"Register Error",Toast.LENGTH_LONG);
                    }
                };

                RegisterRequest regReq = new RegisterRequest(name.getText().toString(),email.getText().toString(),password.getText()
                        .toString(),listener,null);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(regReq);
            }
        });
    }



}