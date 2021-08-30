package dw.koo.android.firebase_messaging_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.firebase.iid.FirebaseInstanceId;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("onCreate", "FCM token: " + refreshedToken);
    }
}
