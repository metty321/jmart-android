package MatthewJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
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
import MatthewJmartFH.jmart_android.request.CreateStoreRequest;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        Account loggedAccount = LoginActivity.getLoggedAccount();

        //1st Cardview
        TextView topUpBalance = findViewById(R.id.balance_value);
        topUpBalance.setText(String.valueOf(loggedAccount.balance));
        TextView preview_name = findViewById(R.id.name_value);
        preview_name.setText(loggedAccount.name);
        TextView preview_email = findViewById(R.id.email_value);
        preview_email.setText(loggedAccount.email);


        //2st CardView
        CardView registerPage = findViewById(R.id.card_view_2);
        TextView registerName = findViewById(R.id.Name_register);
        registerName.setText(loggedAccount.store.name);
        TextView registerAddress = findViewById(R.id.address_register);
         registerAddress.setText(loggedAccount.store.address);
         TextView registerPhone = findViewById(R.id.phone_register);
         registerPhone.setText((loggedAccount.store.phoneNumber));
         //

        //3rd cardview
        CardView viewAccount= findViewById(R.id.card_view_3);


        Button register_btn = findViewById(R.id.button);
        if(loggedAccount.store == null)
        {
            register_btn.setVisibility(View.VISIBLE);

        }
        else
        {
            viewAccount.setVisibility(View.VISIBLE);
            TextView show_name = findViewById(R.id.store_name_value);
            TextView show_address = findViewById(R.id.address_value);
            TextView show_phone = findViewById(R.id.phone_value);
            show_name.setText(loggedAccount.store.name);
            show_address.setText(loggedAccount.store.address);
            show_phone.setText(loggedAccount.store.phoneNumber);

        }

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPage.setVisibility(View.VISIBLE);
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(obj != null)
                            {
                                Toast.makeText(AboutMeActivity.this,"Store registered!",Toast.LENGTH_SHORT).show();
                            }

                            else{
                                Toast.makeText(AboutMeActivity.this,"Store failed to registered!",Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(AboutMeActivity.this,"Store failed to registered!",Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                Response.ErrorListener errorListener =new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AboutMeActivity.this,"Store failed to registered!",Toast.LENGTH_SHORT).show();
                    }
                };
                CreateStoreRequest createStoreRequest = new CreateStoreRequest(LoginActivity.getLoggedAccount().id,
                        registerName.getText().toString(),
                        registerPhone.getText().toString(),registerAddress.getText().toString(),listener,errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(createStoreRequest);
            }
        });
    }
}