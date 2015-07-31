package eflglobal.fakedooblo;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSocialVerify();
            }
        });
        mEditText = (EditText)findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchSocialVerify(){
        try {
            Intent inent = new Intent("eflglobal.socialverify.EFLGLOBAL_SOCIAL_VERIFY");
            inent.putExtra("id", Integer.parseInt(mEditText.getText().toString()) );
            startActivityForResult(inent, 42);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this,"NumberFormatException", Toast.LENGTH_LONG).show();
        } catch (ActivityNotFoundException e){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 42) {
            // Make sure the request was successful
            String msg = "";
            if(data!=null&&data.getExtras()!=null){
                msg = data.getStringExtra("MESSAGE");
            }
            switch (resultCode){
                case RESULT_OK:
                    Toast.makeText(this,"RESULT_OK \n"+ msg, Toast.LENGTH_LONG).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this,"RESULT_CANCELED", Toast.LENGTH_LONG).show();
                    break;
            }

        }
    }
}
