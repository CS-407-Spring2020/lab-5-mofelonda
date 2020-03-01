package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    static String usernameKey = "username";

    public void loginClick(View view) {
        EditText nameField = findViewById(R.id.nameField);
        String str = nameField.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply();
        goToActivity2();
    }

    public void goToActivity2() {
        Intent login = new Intent(this, Activity2.class);
        startActivity(login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            sharedPreferences.getString(usernameKey, "");
            Intent loggedIn = new Intent(this, Activity2.class);
            startActivity(loggedIn);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }
}