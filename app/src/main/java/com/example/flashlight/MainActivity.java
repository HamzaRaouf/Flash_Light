package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 private ImageView togglebtn;
 boolean flashOn=false;
 boolean hasCamerFlash=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        togglebtn=findViewById(R.id.imageView);

        hasCamerFlash=getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        togglebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasCamerFlash)
                {
                    if(flashOn)
                    {
                        flashOn=false;
                        togglebtn.setImageResource(R.drawable.power_off);
                        try {
                            flashLightOff();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        flashOn=true;
                        togglebtn.setImageResource(R.drawable.power_on);
                        try {
                            flashLigtOn();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No Flash available on your Device", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void flashLightOff() throws CameraAccessException {
        CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraID=cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(cameraID,false);
        Toast.makeText(this, "Off", Toast.LENGTH_SHORT).show();

    }

    private void flashLigtOn() throws CameraAccessException {
        CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraID=cameraManager.getCameraIdList()[0];
        cameraManager.setTorchMode(cameraID,true);
        Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show();
    }
}