package MatthewJmartFH.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import MatthewJmartFH.jmart_android.LoginActivity;

public class CreateProductRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:6969/product/create";
    private static  Map<String,String> params = new HashMap<>();

    public CreateProductRequest(String name, String price,String weight,String conditionUsed, String discount,
                                String category,
                                String shipmentPlans,
                                Response.Listener<String>listener,
                                Response.ErrorListener errorListener)
    {
        super(Request.Method.POST,URL,listener,errorListener);
        Integer i = LoginActivity.getLoggedAccount().id;
        params.put("accountId", String.valueOf(i));
        params.put("name",name);
        params.put("price",price);
        params.put("weight",weight);
        params.put("conditionUsed",conditionUsed);
        params.put("discount",discount);
        params.put("category",category);
        params.put("shipmentPlans",shipmentPlans);
    }

    public Map<String,String> getParams()
    {
        return params;
    }
}
