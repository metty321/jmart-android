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
import MatthewJmartFH.jmart_android.request.TopUpRequest;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        Account loggedAccount = LoginActivity.getLoggedAccount();

        //Card View 1
        TextView registered_name  = findViewById(R.id.name_value);
        registered_name.setText(loggedAccount.name);
        TextView registered_email = findViewById(R.id.email_value);
        registered_email.setText(loggedAccount.email);
        TextView topUpBalance     = findViewById(R.id.balance_value);
        topUpBalance.setText(String.valueOf(loggedAccount.balance));

        CardView register_card = findViewById(R.id.card_view_2);
        CardView info_card     = findViewById(R.id.card_view_3);
        Button openreg_button  = findViewById(R.id.button);
        Button register_button = findViewById(R.id.regBtn);
        Button cancel_button   = findViewById(R.id.cancelBtn);



        //balance
        TextView balance_value = findViewById(R.id.balance_value);
        balance_value.setText(String.valueOf(loggedAccount.balance));
        Button topUp_btn       = findViewById(R.id.TopUp_btn);




        if((loggedAccount.store == null))
        {
            openreg_button.setVisibility(View.VISIBLE);

        }

        else
        {
            info_card.setVisibility(View.VISIBLE);
            TextView store_name = findViewById(R.id.store_name_value);
            store_name.setText(loggedAccount.store.name);
            TextView store_address = findViewById(R.id.address_value);
            store_address.setText(loggedAccount.store.address);
            TextView store_phoneNumber = findViewById(R.id.phone_value);
            store_phoneNumber.setText(loggedAccount.store.phoneNumber);
        }

        topUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText input_balance = findViewById(R.id.amount);
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj= new JSONObject(response);

                            if(obj != null)
                            {
                                Toast.makeText(AboutMeActivity.this,
                                        "Top Up success!",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AboutMeActivity.this,
                                    "Top Up success",Toast.LENGTH_SHORT).show();

                            e.printStackTrace();

                        }

                    }
                };

                Response.ErrorListener error_listener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AboutMeActivity.this,
                                "listener failed!",Toast.LENGTH_SHORT).show();
                    }
                };
                TopUpRequest topUpRequest = new TopUpRequest(loggedAccount.id,input_balance.getText().toString(),
                        listener,error_listener);

                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(topUpRequest);
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openreg_button.setVisibility(View.VISIBLE);
                register_card.setVisibility(View.GONE);

            }
        });

        openreg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openreg_button.setVisibility(View.GONE);
                register_card.setVisibility(View.VISIBLE);
                EditText input_name = findViewById(R.id.Name_register);
                EditText input_address    = findViewById(R.id.address_register);
                EditText input_phoneNumber= findViewById(R.id.phone_register);
                Button regBtn = findViewById(R.id.regBtn);

                regBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Response.Listener<String> listener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try{
                                    JSONObject object = new JSONObject(response);
                                    System.out.println(response);
                                    if(object != null)
                                    {
                                        Toast.makeText(AboutMeActivity.this,"Register success!",
                                                Toast.LENGTH_LONG).show();
                                    }

                                    else
                                    {
                                        Toast.makeText(AboutMeActivity.this,"Register failed!",
                                                Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(AboutMeActivity.this,"Register failed!",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        };

                        Response.ErrorListener errorListener = new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(AboutMeActivity.this,"Register failed!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        };

                        CreateStoreRequest storeRequest = new CreateStoreRequest(loggedAccount.id,
                                input_name.getText().toString(),
                                input_address.getText().toString(),
                                input_phoneNumber.getText().toString(),
                                listener,
                                errorListener);
                        RequestQueue queue =Volley.newRequestQueue(AboutMeActivity.this);
                        queue.add(storeRequest);
                    }
                });

            }
        });
    }
}