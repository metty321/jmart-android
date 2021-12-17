package MatthewJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static MatthewJmartFH.jmart_android.LoginActivity.getLoggedAccount;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import MatthewJmartFH.jmart_android.model.Product;
import MatthewJmartFH.jmart_android.request.CreatePaymentRequest;

/**
 * @author Matthew Eucharist
 * this class  controls the payment process to the REST Controller
 *
 */
public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        EditText shipment_address = findViewById(R.id.shipment_address_value);
        EditText productCount     = findViewById(R.id.product_count_value);
        String product_count_str  = productCount.getText().toString();

        TextView balance_preview  = findViewById(R.id.balance_preview);
        balance_preview.setText(String.valueOf(getLoggedAccount().balance));
        TextView total_price      =  findViewById(R.id.total_price_value);
        String original_price     = String.valueOf(ProductFragment.productClicked.price);

        Button confirm_btn        = findViewById(R.id.confirm_btn);
        Button cancel_btn         = findViewById(R.id.cancel_btn);
        Spinner shipment          = findViewById(R.id.spinner_shipment_confirmation);

        final String[] shipmentPlans = {"INSTANT","KARGO","NEXT DAY","REGULER","SAME DAY"};


        final ArrayAdapter<String> Adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,shipmentPlans);
        shipment.setAdapter(Adapter);

//        cancel_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent back_intent = new Intent(PaymentActivity.this, ProductDetailsActivity.class);
//                startActivity(back_intent);
//            }
//        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String>listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.println("test");
                            JSONObject object = new JSONObject(response);
                            System.out.println(response);
                            if(object != null )
                            {
//                                int prod_count            = Integer.parseInt(product_count_str);
//                                int TotalPrice            =(int)ProductFragment.productClicked.price * prod_count;
//                                total_price.setText(String.valueOf(TotalPrice));
                                Toast.makeText(PaymentActivity.this, "Payment success",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PaymentActivity.this,MainActivity.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            Toast.makeText(PaymentActivity.this, "Payment failed",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                };

                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PaymentActivity.this,"Error has occured",
                                Toast.LENGTH_SHORT).show();
                    }
                };

                CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest(String.valueOf(getLoggedAccount().id),
                        String.valueOf(ProductFragment.productClicked.id),
                        productCount.getText().toString(),
                        shipment_address.getText().toString(),
                        String.valueOf(shipmentConverted(shipment)),
                        listener,
                        errorListener);

                RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
                requestQueue.add(createPaymentRequest);
            }


        });


    }

    public byte shipmentConverted(Spinner shipment)
    {
        if(shipment.getSelectedItem()=="INSTANT")
        {
            return (byte)00000001;
        }
        if(shipment.getSelectedItem()=="KARGO")
        {
            return (byte)00010000;
        }
        if(shipment.getSelectedItem()=="NEXT DAY")
        {
            return (byte)00000100;
        }
        if(shipment.getSelectedItem()=="REGULER")
        {
            return (byte)00001000;
        }
        if(shipment.getSelectedItem()=="SAME DAY")
        {
            return (byte)00000010;
        }

        return 0;
    }
}