package com.bedirhankaplan.ilaclarim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Aldiklarim extends AppCompatActivity {

    private Date date;
    private ListView VeriListele;
    private TextView tvTarih;
    private Button btnIlac, btnAlinan, btnVKI, btnSil, btnGuncelle, btnKaydet;
    private EditText etIlac, etAdet;
    private String tarih;
    private int idBul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aldiklarim);
        VeriListele = findViewById(R.id.listView);

        Listele();

        btnIlac = findViewById(R.id.btnIlaclarim);
        btnAlinan = findViewById(R.id.btnAldiklarim);
        btnVKI = findViewById(R.id.btnVKI);
        btnKaydet = findViewById(R.id.btnKaydet);
        btnSil = findViewById(R.id.btnSil);
        etIlac = findViewById(R.id.etIlac);
        etAdet = findViewById(R.id.etAdet);
        tvTarih = findViewById(R.id.tvTarih);

        date = new Date();
        SimpleDateFormat saat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tvTarih.setText(saat.format(date));
        tarih = tvTarih.getText().toString();

        VeriListele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Tıklanan Verileri Alıyoruz.
                String item = VeriListele.getItemAtPosition(position).toString();
                // - e ayırıyoruz
                String[] itembol = item.split(" - ");

                idBul = Integer.valueOf(itembol[0].toString());
                etIlac.setText(itembol[1].toString());
                etAdet.setText(itembol[2].toString());
                tvTarih.setText(itembol[3].toString());
            }
        });

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veritabani vt = new Veritabani(Aldiklarim.this);
                vt.VeriDuzenle(idBul,etIlac.getText().toString(), etAdet.getText().toString(), tvTarih.getText().toString());
                Toast.makeText(Aldiklarim.this, "İlaç Alımınız Başarıyla Güncellenmiştir.", Toast.LENGTH_SHORT).show();
                Listele();
                etIlac.setText("");
                etAdet.setText("");
            }
        });

        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veritabani vt = new Veritabani(Aldiklarim.this);
                vt.VeriSilme(idBul);
                Listele();
                etIlac.setText("");
                etAdet.setText("");

            }
        });
        btnIlac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Aldiklarim.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnVKI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Aldiklarim.this, VKI.class);
                startActivity(intent);
            }
        });
    }

    public void Listele() {
        Veritabani vt = new Veritabani(Aldiklarim.this);
        List<String> list = vt.VeriListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Aldiklarim.this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, list);
        VeriListele.setAdapter(adapter);
    }
}
