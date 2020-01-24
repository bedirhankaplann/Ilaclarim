package com.bedirhankaplan.ilaclarim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button btnIlac, btnAlinan, btnVKI, btnKaydet;
    private ImageView btnHakkında;
    private TextView tvIlacAd, tvAdet, tvBilgi, tvBaslik, tvTarih, tvTarih2;
    private Spinner spinIlac, spinAdet;
    private String[] ilaclar, adet;
    private String ilac, tane, tarih;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIlac = findViewById(R.id.btnIlaclarim);
        btnAlinan = findViewById(R.id.btnAldiklarim);
        btnVKI =findViewById(R.id.btnVKI);
        btnKaydet = findViewById(R.id.btnKaydet);
        btnHakkında = findViewById(R.id.btnHakkinda);
        tvIlacAd = findViewById(R.id.tvIlacAd);
        tvBaslik = findViewById(R.id.tvBaslik);
        tvAdet = findViewById(R.id.tvAdet);
        tvBilgi = findViewById(R.id.tvBilgi);
        tvTarih = findViewById(R.id.tvTarih);
        tvTarih2 = findViewById(R.id.tvTarih2);

        date = new Date();
        SimpleDateFormat saat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tvTarih2.setText(saat.format(date));
        tarih = tvTarih2.getText().toString();

        spinIlac = findViewById(R.id.spinIlac);
        spinAdet = findViewById(R.id.spinAdet);

        ilaclar = getResources().getStringArray(R.array.ilaclar);
        ArrayAdapter<String> adapterIlac = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, ilaclar);
        adapterIlac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIlac.setAdapter(adapterIlac);

        adet = getResources().getStringArray(R.array.adet);
        ArrayAdapter<String> adapterAdet = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, adet);
        spinAdet.setAdapter(adapterAdet);

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setIcon(R.mipmap.pill);
                adb.setTitle("Onay Ekranı");
                adb.setMessage("İlacı Aldığınız Onaylıyor Musunuz?");
                adb.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();;
                    }
                });
                adb.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ilac = spinIlac.getSelectedItem().toString();
                        tane = spinAdet.getSelectedItem().toString();
                        Veritabani vt = new Veritabani(MainActivity.this);
                        vt.VeriEkle(ilac, tane, tarih);
                        Intent intent = new Intent(MainActivity.this, Aldiklarim.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "İlaç Alımınız Başarıyla Eklenmiştir", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = adb.create();
                alert.show();
            }

        });

        btnVKI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VKI.class);
                startActivity(intent);
            }
        });
        btnAlinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Aldiklarim.class);
                startActivity(intent);
            }
        });
        btnHakkında.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setIcon(R.mipmap.pill);
                adb.setTitle("Uygulama Hakkında");
                adb.setMessage("İlaçlarım Uygulaması\nBedirhan KAPLAN\nTarafından Yapılmıştır.\n\nSağlıklı Günler...");
                adb.setPositiveButton("Teşekkürler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();;
                    }
                });
                AlertDialog alert = adb.create();
                alert.show();
            }

        });
    }
}
