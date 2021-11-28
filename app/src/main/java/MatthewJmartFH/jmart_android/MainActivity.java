package MatthewJmartFH.jmart_android;

import static MatthewJmartFH.jmart_android.LoginActivity.getLoggedAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView name = findViewById(R.id.greet);
        name.setText("Hello "+getLoggedAccount().name);
    }

}