package MatthewJmartFH.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Matthew Eucharist
 * This is the class of Login Request that controls the Login request
 *
 */
public class LoginRequest extends StringRequest {
    public static final String URL = "http://10.0.2.2:6969/account/login";
    private  final  Map<String,String>  params;
    public LoginRequest(String email, String password, Response.Listener<String>listener,
                        Response.ErrorListener errorListener)
    {
        super(Method.POST,URL,listener,errorListener);
        params = new HashMap<>();
        params.put("email",email);
        params.put("password",password);
    }


    public Map<String,String> getParams()
    {
        return params;
    }
}
