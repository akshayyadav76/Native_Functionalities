package com.ak.nativefunctionalities

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_alert_layout.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //=======for permission
      btnCameraPermission.setOnClickListener {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA,) == PackageManager.PERMISSION_GRANTED &&
          ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION,) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"you Already have Permission",Toast.LENGTH_SHORT).show()

        }else{
                //request permission
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),Camera_Permission_Code)
           }
        }


      //========for snackBar
        btnSnackBar.setOnClickListener {view->
            Snackbar.make(view,"this is snackBar",Snackbar.LENGTH_SHORT).show()
        }


        //==========for Alert Dialog
        btnAlertDialog.setOnClickListener {view->
            // Use the builder class for convenient dialog construction
            val builder =AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("App Dialog")
            //set message for alert dialog
            builder.setMessage("this is alert dialog.which show alert")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //perform positive action
            builder.setPositiveButton("yes"){ dialogInterface,which->
                Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }
            // perform cancel action
            builder.setNeutralButton("Cancel"){dialogInterface,which->
                Toast.makeText(applicationContext,"cancel",Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()

            }

            builder.setNegativeButton("No"){dialogInterface,which->
                Toast.makeText(applicationContext,"No",Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()

            }
            // create the alertDialog
            val alertDialog: AlertDialog = builder.create()
            //set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()

        }


        //==========for custom dialog
        btnCustomDialog.setOnClickListener {view ->
            val customDialg = Dialog(this)
            /* set the screen content from a layout resource
            ths resource wil be inflated, adding alll top-level views to the screen */
            customDialg.setContentView(R.layout.custom_alert_layout)
            customDialg.tv_submit.setOnClickListener {
                Toast.makeText(applicationContext, // you can also use this here
                    "clicked submit",Toast.LENGTH_SHORT).show()
                customDialg.dismiss()
            }
            customDialg.tv_cancle.setOnClickListener {
                Toast.makeText(applicationContext,"cancel",Toast.LENGTH_SHORT).show()
                customDialg.dismiss()
            }
            customDialg.show()
        }


        //=========for custom progress bar
        btnProgressBar.setOnClickListener {
            val progressBar = Dialog(this)


            progressBar.setContentView(R.layout.custom_progress_dialog_layout)
            progressBar.show()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == Camera_Permission_Code){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show()
            }
        }
    }


    // an data class which only contain variables
    companion object {
        private  const val Camera_Permission_Code =1
        private const val Fine_Location_Permission_Code =2
        private const val Camera_and_Fine_Location_Permission_Code =3

    }



}