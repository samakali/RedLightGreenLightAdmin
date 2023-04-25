package com.soulsight.redlightgreenlightadmin.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shashank.sony.fancytoastlib.FancyToast;


public class PrograssHandler {

    private static KProgressHUD progressHUD;
    public static void showProgras( Context context)
    {

        if (progressHUD==null) {
            progressHUD = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("loading...")
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        }
    }
    public static void hidePrograss()
    {
        if (progressHUD!=null) {
            if (progressHUD.isShowing()) {
                progressHUD.dismiss();
                progressHUD=null;
            }
        }
    }

    public static void showTostSuccess(Context context,String message)
    {
        FancyToast.makeText(context,message,FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
    }

    public static void showTostError(Context context,String message)
    {
        FancyToast.makeText(context,message,FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
    }

    public static void imageIuploader( Uri uri,PrograsHandlerEvents prograsHandlerEvents)
    {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child(""+TimeHandler.currentDateInMills());
        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if (task.isSuccessful())
                {
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            prograsHandlerEvents.imageUplaodSuccess(uri.toString());

                        }
                    });
                }
                else {
                    prograsHandlerEvents.imageUploadFailed();
                }
            }
        });
    }

    public static void pickSingleImage(Activity activity, int code)
    {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent,code);
    }


    public interface PrograsHandlerEvents{
        void imageUplaodSuccess(String imagePath);
        void imageUploadFailed();
    }

}
