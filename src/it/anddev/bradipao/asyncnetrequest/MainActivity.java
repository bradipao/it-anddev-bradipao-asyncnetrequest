package it.anddev.bradipao.asyncnetrequest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;

public class MainActivity extends Activity {

   // text example URL : http://www.anddev.it
   // image example URL : http://www.android.com/images/whatsnew/jb-new-logo.png
   
   // views
   EditText et1;
   Button btn1,btn2;
   TextView tv1;
   ImageView iv1;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.ac_main);
      
      // get views
      et1 = (EditText) findViewById(R.id.et1);
      btn1 = (Button) findViewById(R.id.btn1);
      btn2 = (Button) findViewById(R.id.btn2);
      tv1 = (TextView) findViewById(R.id.tv1);
      iv1 = (ImageView) findViewById(R.id.iv1);
      
      // button btn1 listener
      btn1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            
            AsyncHttpClient client = new AsyncHttpClient();
            String url = et1.getText().toString();
            client.get(url,new AsyncHttpResponseHandler() {
               @Override
               public void onSuccess(String response) {
                  tv1.setText("http GET request successful");
                  Log.d("AREQ","http GET request successful");
               }
               @Override
               public void onFailure(Throwable e,String response) {
                  tv1.setText("http GET request failed");
                  Log.d("AREQ","http GET request failed");
               }
            });

         }
      });
      
      // button btn2 listener
      btn2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            
            AsyncHttpClient client = new AsyncHttpClient();
            String url = et1.getText().toString();
            String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };
            client.get(url,new BinaryHttpResponseHandler(allowedContentTypes) {
               @Override
               public void onSuccess(byte[] imageData) {
                  Bitmap bitmap = BitmapFactory.decodeByteArray(imageData,0,imageData.length);
                  iv1.setImageBitmap(bitmap);
                  Log.d("AREQ","http IMAGE request successful");
               }
               @Override
               public void onFailure(Throwable e, byte[] imageData) {
                  tv1.setText("http IMAGE request failed");
                  Log.d("AREQ","http IMAGE request failed");
               }
            });

         }
      });
      
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}
