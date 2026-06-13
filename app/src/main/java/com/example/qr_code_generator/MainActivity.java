package com.example.qr_code_generator;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class MainActivity extends AppCompatActivity {
    private String mytext;
    private ImageView show;
    private BitMatrix matrix;
    private EditText catchText;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //
        catchText = findViewById(R.id.editTextText);
        show = findViewById(R.id.image);
        catchText = findViewById(R.id.editTextText);
        show = findViewById(R.id.image);
        //
        catchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                MultiFormatWriter ground = new MultiFormatWriter();
                BarcodeEncoder encoder = new BarcodeEncoder();
                //
                mytext = catchText.getText().toString();
                //
                try {
                    if (!mytext.isEmpty()){
                        matrix = ground.encode(mytext,BarcodeFormat.QR_CODE,250,250);
                        Bitmap picture = encoder.createBitmap(matrix);
                        //
                        show.setImageBitmap(picture);

                    }
                    else {
                        int how = Toast.LENGTH_SHORT;
                        Toast.makeText(getBaseContext(),"Set Text!",how).show();
                    }
                    //
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
            }
            //
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            //
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            });
        //
        }
    }
