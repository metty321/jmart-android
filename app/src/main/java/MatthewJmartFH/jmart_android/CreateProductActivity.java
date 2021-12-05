package MatthewJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import MatthewJmartFH.jmart_android.model.ProductCategory;
import MatthewJmartFH.jmart_android.request.CreateProductRequest;

public class CreateProductActivity extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinner_2;
    private boolean conditionUsed;
    byte chosen_shipmentPlan;
    ProductCategory productCategory;
    private final String[] ProductCategory ={"ART CRAFT","AUTOMOTIVE","BOOK","CARPENTRY","COSMETICS","ELECTRONIC",
    "FASHION","FNB","FURNITURE","GADGET","GAMING","HEALTHCARE","JEWELRY"};
    private final String[] shipmentPlans = {"INSTANT","KARGO","NEXT DAY","REGULER","SAME DAY"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        EditText CreatedProduct  = findViewById(R.id.name_3);
        EditText productWeight   = findViewById(R.id.weight);
        EditText productDiscount = findViewById(R.id.discount);
        RadioButton new_Btn      = findViewById(R.id.new_tag_2);
        RadioButton used_Btn     = findViewById(R.id.used_tag_2);
        spinner = (Spinner) findViewById(R.id.spinner_category_2);
        spinner_2 = (Spinner) findViewById(R.id.shipment_plans);
        final ArrayAdapter<String>arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,ProductCategory);
        spinner.setAdapter(arrayAdapter);
        final ArrayAdapter<String>arrayAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,shipmentPlans);
        Button create_btn        = findViewById(R.id.create_btn);


        //Action When the create button is clicked
        create_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            conditionUsed = used_Btn.isChecked();
            chosen_shipmentPlan = shipmentplanChecker(spinner_2);
            productCategory = ProductCategoryChecker(spinner);
            Response.Listener<String>listener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        if(obj != null)
                        {
                            Toast.makeText(CreateProductActivity.this,"Store registration succcess!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateProductActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(CreateProductActivity.this,"Store registration failed!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(CreateProductActivity.this,"Store registration failed!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(CreateProductActivity.this,"Store registration failed!",
                            Toast.LENGTH_SHORT).show();
                }
            };

            CreateProductRequest productRequest = new CreateProductRequest(
                    LoginActivity.getLoggedAccount().id,
                    CreatedProduct.getText().toString(),
                    productWeight.getText().toString(),
                    productDiscount.getText().toString(),
                    String.valueOf(productCategory),
                    String.valueOf(chosen_shipmentPlan),
                    listener,errorListener);

            RequestQueue requestQueue = Volley.newRequestQueue(CreateProductActivity.this);
            requestQueue.add(productRequest);
        }
    });
    }


    protected byte shipmentplanChecker (Spinner shipmentplan)
    {
        if(shipmentplan.getSelectedItem()=="INSTANT")
        {
            return (byte)00000001;
        }
        if(shipmentplan.getSelectedItem()=="KARGO")
        {
            return (byte)00000010;
        }
        if(shipmentplan.getSelectedItem()=="NEXT DAY")
        {
            return (byte)00000100;
        }
        if(shipmentplan.getSelectedItem()=="REGULER")
        {
            return (byte)00000101;
        }
        if(shipmentplan.getSelectedItem()=="SAME DAY")
        {
            return (byte)00000110;
        }

        return 0;
    }

    protected ProductCategory ProductCategoryChecker (Spinner productcategory)
    {
        if(productcategory.getSelectedItem()=="BOOK")
        {
            return MatthewJmartFH.jmart_android.model.ProductCategory.BOOK;
        }
        if(productcategory.getSelectedItem()=="ART CRAFT")
        {
            return MatthewJmartFH.jmart_android.model.ProductCategory.ART_CRAFT;
        }
        if(productcategory.getSelectedItem()=="AUTOMOTIVE")
        {
            return MatthewJmartFH.jmart_android.model.ProductCategory.AUTOMOTIVE;
        }
        if(productcategory.getSelectedItem()=="CARPENTRY")
        {
            return MatthewJmartFH.jmart_android.model.ProductCategory.CARPENTRY;
        }
        if(productcategory.getSelectedItem()=="COSMETICS")
        {
            return MatthewJmartFH.jmart_android.model.ProductCategory.COSMETICS;
        }

        return null;
    }


}