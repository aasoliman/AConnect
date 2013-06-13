package cgu.edu.ist380.solimana.Project.AConnect;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	final int   REQ_CODE_PICK_IMAGE = 1;
	Button choose;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		choose =  (Button) findViewById(R.id.choose);
		
		choose.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				 Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
			       photoPickerIntent.setType("image/*");
			       startActivityForResult(photoPickerIntent, REQ_CODE_PICK_IMAGE); 
				
			}
			
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, 
		       Intent imageReturnedIntent) {
		    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

		    switch(requestCode) { 
		    case REQ_CODE_PICK_IMAGE:
		        if(resultCode == RESULT_OK){  
		            Uri selectedImage = imageReturnedIntent.getData();
//		            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//		            Cursor cursor = getContentResolver().query(
//		                               selectedImage, filePathColumn, null, null, null);
//		            cursor.moveToFirst();
//
//		            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//		            String filePath = cursor.getString(columnIndex);
//		            cursor.close();

		           try {
					Bitmap b = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
					Log.d("image","got an image from the gallery "+selectedImage.getLastPathSegment());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        }
		    }
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
