package com.example.httppostfile;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private void doMultiPost(String url, List< NameValuePair> params){
	 HttpClient client=new DefaultHttpClient();
	 HttpPost post=new HttpPost(url);
	 try{
		  //setup multipart entity
		  MultipartEntity entity=new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
		      
		  File f = new File("/storage/sdcard/ic_launcher.png");
		  FileBody fileBody=new FileBody(f);
		  
		  entity.addPart("IM_TOKEN",new StringBody("a91d5c89_6582_11e4_a517_5254181238dc"));
		  entity.addPart("USER_GUID",new StringBody("3"));
		  entity.addPart("SESSION_GUID",new StringBody("1"));
		  entity.addPart("IM_FILE",fileBody);
		  
		  post.setEntity(entity);
		  
		  //create response handler
		  ResponseHandler< String> handler=new BasicResponseHandler();
		  //execute and get response
		  String response=new String(client.execute(post,handler).getBytes(),HTTP.UTF_8);
		  Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
		  
	  }catch(Exception e){
	   e.printStackTrace();
	  }     
	  
	}
	public void onSubmit(View v){
		List< NameValuePair> params = new ArrayList< NameValuePair>();
		doMultiPost("http://125.227.117.92/im/api/upload/index.php",params);
	}
	public void showDialog(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("½T©w", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	

}
