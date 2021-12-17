package MatthewJmartFH.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        TextView product_name = findViewById(R.id.product_name_value);
        product_name.setText(ProductFragment.productClicked.name
        );
        TextView product_weight = findViewById(R.id.product_weight_value);
        product_weight.setText(String.valueOf(ProductFragment.productClicked.weight));

        TextView product_price = findViewById(R.id.product_price_value);
        product_price.setText(String.valueOf(ProductFragment.productClicked.price));

        TextView product_condition = findViewById(R.id.product_condition_value);
        product_condition.setText(ConditionUsedConverter(ProductFragment.productClicked.conditionUsed));

        TextView product_category= findViewById(R.id.product_category_value);
        product_category.setText(String.valueOf(ProductFragment.productClicked.category));

        TextView product_discount = findViewById(R.id.product_discount_value);
        product_discount.setText(String.valueOf(ProductFragment.productClicked.discount));

        TextView product_shipmentPlan = findViewById(R.id.product_shipmentPlan_value);
        product_shipmentPlan.setText(ShipmentPlansConverter(ProductFragment.productClicked.shipmentPlans));

        Button buy_btn = findViewById(R.id.buy_btn);

        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    public String ShipmentPlansConverter(byte shipmentPlans)
    {
        switch (shipmentPlans)
        {
            case 1 :
                return "INSTANT";
            case 2 :
                return "SAME DAY";
            case 4 :
                return "NEXT DAY";
            case 8 :
                return "REGULER";
            default:
                return "KARGO";
        }
    }

    public String ConditionUsedConverter(boolean conditionUsed)
    {
        if(conditionUsed)
        {
            return "USED";
        }

        else
        {
            return "NEW";
        }
    }

}