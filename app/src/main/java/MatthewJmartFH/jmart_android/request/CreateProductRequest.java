package MatthewJmartFH.jmart_android.request;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

public class CreateProductRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:6969/account/%d/ProductRegister";
    private  static Map<String,String> params;

    public CreateProductRequest(int id,String name, String weight, String discount,
                                String product_category,
                                String shipment_plans,
                                Response.Listener<String>listener,
                                Response.ErrorListener errorListener)
    {
        super(Request.Method.POST,String.format(URL,id),listener,errorListener);
        params.put("id", String.valueOf(id));
        params.put("name",name);
        params.put("weight",weight);
        params.put("discount",discount);
        params.put("product category",product_category);
        params.put("shipment plans",shipment_plans);
    }

    public Map<String,String> getParams()
    {
        return params;
    }
}
