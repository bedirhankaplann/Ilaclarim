package com.bedirhankaplan.ilaclarim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class VKI extends AppCompatActivity {

    private TextView tvCinsiyet, tvKilo, tvBoy, tvSonuc;
    private EditText etBoy, etKilo;
    private CheckBox cbErkek, cbKadin;
    private Button btnHesapla, btnIlac, btnAlinan, btnVKI;
    private float sonuc;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vki);

        tvBoy = findViewById(R.id.tvBoy);
        tvKilo = findViewById(R.id.tvKilo);
        tvCinsiyet = findViewById(R.id.tvCinsiyet);
        tvSonuc = findViewById(R.id.tvSonuc);

        etBoy = findViewById(R.id.etBoy);
        etKilo = findViewById(R.id.etKilo);

        cbErkek = findViewById(R.id.cbErkek);
        cbKadin = findViewById(R.id.cbKadin);

        btnHesapla = findViewById(R.id.btnHesapla);
        btnIlac = findViewById(R.id.btnIlaclarim);
        btnAlinan = findViewById(R.id.btnAldiklarim);
        btnVKI = findViewById(R.id.btnVKI);

        btnAlinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VKI.this, Aldiklarim.class);
                startActivity(intent);
            }
        });

        btnIlac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VKI.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnVKI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VKI.this, VKI.class);
                startActivity(intent);
            }
        });

        btnHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float boy = Float.parseFloat(etBoy.getText().toString());
                float kilo = Float.parseFloat(etKilo.getText().toString());
                sonuc = ( kilo / ( boy * boy )) * 10000;
                String mesaj ="";
                String cinsiyet = "";

                if ( sonuc <= 15)
                    mesaj = "Çok Ciddi Derecede Düşük Kilolusunuz";
                else if ( sonuc <= 16 )
                    mesaj = "Ciddi Derecede Düşük Kilolusunuz";
                else if ( sonuc <= 18.5)
                    mesaj = "Düşük Kilolusunuz";
                else if ( sonuc <= 25)
                    mesaj = "Normal Kilolusunuz";
                else if ( sonuc <= 30)
                    mesaj = "Fazla Kilolusunuz";
                else if ( sonuc <= 35)
                    mesaj = "1.Derecede (Hafif) Obezitesiniz";
                else if ( sonuc <= 40)
                    mesaj = "2.Derecede (Orta) Obezitesiniz";
                else if ( sonuc <= 100)
                    mesaj = "3.Derecede (Hafif) Obezitesiniz";
                else
                    mesaj = "Aralık Dışı Değer";

                if (cbErkek.isChecked())
                    cinsiyet = cbErkek.getText().toString();
                else if ( cbKadin.isChecked())
                    cinsiyet = cbKadin.getText().toString();
                else if (cbErkek.isChecked() && cbKadin.isChecked())
                    cinsiyet = "Seçilmemiştir";
                else
                    cinsiyet = "Seçilmemiştir";

                AlertDialog.Builder adb = new AlertDialog.Builder(context);
                adb.setIcon(R.mipmap.scales);
                adb.setTitle("Sonuç");
                adb.setMessage("\nCinsiyet: " +cinsiyet+"\nSonuç = " + sonuc + "\n" + mesaj);
                adb.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = adb.create();
                alertDialog.show();

                tvSonuc.setText("\nSonuç = " + sonuc + "\n" + mesaj);
            }
        });
    }
}
