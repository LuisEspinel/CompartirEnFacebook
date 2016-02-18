package luisespinel.compartirenfacebook;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
    }

    public void publicar(View view){
        try{
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle("prueba facebook")
                        .setContentDescription("esto es una prueba")
                        .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                        .build();

                shareDialog.show(linkContent);
            }
        }catch(Exception e){
            Toast.makeText(this,"algo paso : "+e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
