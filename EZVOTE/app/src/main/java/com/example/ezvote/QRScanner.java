package com.example.ezvote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class QRScanner extends AppCompatActivity {

    CodeScanner CodeScanner;
    CodeScannerView codeScannerView;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_scanner);

        codeScannerView = (CodeScannerView)findViewById(R.id.ScannerView);
        text = findViewById(R.id.textView);
        CodeScanner = new CodeScanner(this,codeScannerView);

        CodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(result.getText());
                    }
                });
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        requestCamera();
    }

    private void requestCamera() {
        CodeScanner.startPreview();
    }
}