package com.soulsight.redlightgreenlightadmin.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class SaveFile {

    private Bitmap imageToSave;
    private View imageView;
    private Context context;
    private String shareAbleFile;
    public SaveFile(View imageView, Context context, String path) {

        this.context=context;
        this.imageView = imageView;
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        this.imageToSave = imageView.getDrawingCache();

        try {
            saveImageToStorage(imageToSave,path);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("samak",e.getMessage());
        }
        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();

        this.imageView.setDrawingCacheEnabled(false);

    }



    private void saveImageToStorage(Bitmap bitmap,String path) throws IOException {
        OutputStream imageOutStream;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            String fileName=System.currentTimeMillis()+".png";
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.DISPLAY_NAME,
                    fileName);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
            values.put(MediaStore.Images.Media.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES  + "/"+ path);

            Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            values);

            File dir=new File(String.valueOf(uri));
            shareAbleFile= Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES) + "/"+
                    path+"/"+fileName;

            if (!dir.exists())
            {
                dir.mkdir();
            }
            imageOutStream = context.getContentResolver().openOutputStream(uri);

        } else {


            String imagesDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES) + "/"+path;
            File dir=new File(imagesDir);
            if (!dir.exists())
            {
                dir.mkdir();
            }


            File image = new File(imagesDir, System.currentTimeMillis()+".png");
            shareAbleFile= String.valueOf(image);
            imageOutStream = new FileOutputStream(image);
        }


        bitmap.compress(CompressFormat.PNG, 100, imageOutStream);
        imageOutStream.close();

        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE",
                Uri.fromFile(new File(shareAbleFile))));

    }


}
