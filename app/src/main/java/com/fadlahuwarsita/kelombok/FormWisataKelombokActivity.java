package com.fadlahuwarsita.kelombok;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.fadlahuwarsita.kelombok.model.WisataKelombok;

public class FormWisataKelombokActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout tilNama, tilDaerah, tilDeskripsi;
    Spinner spnKategoriWisata;
    final String[] tipeWisata = {WisataKelombok.AIRTERJUN, WisataKelombok.BUDAYA, WisataKelombok.PANTAI, WisataKelombok.PEGUNUNGAN, WisataKelombok.PEMANDIAN, WisataKelombok.SEJARAH, WisataKelombok.DESA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_wisata_kelombok);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> simpan());
        tilNama = findViewById(R.id.til_nama_destinasi);
        tilDaerah = findViewById(R.id.til_daerah_lokasi);
        tilDeskripsi = findViewById(R.id.til_deskripsi);
        spnKategoriWisata = findViewById(R.id.spn_kategori);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tipeWisata
        );
        spnKategoriWisata.setAdapter(adapter);
        spnKategoriWisata.setSelection(0);
    }

    private void simpan() {
        if (isDataValid()) {
            WisataKelombok tr = new WisataKelombok();
            tr.setNamaDestinasi(tilNama.getEditText().getText().toString());
            tr.setDaerah(tilDaerah.getEditText().getText().toString());
            tr.setDeskripsi(tilDeskripsi.getEditText().getText().toString());
            tr.setKategori(spnKategoriWisata.getSelectedItem().toString());
            SharedPreferenceUtility.addWisata(this, tr);
            Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);


        }
    }

    private boolean isDataValid() {
        if (tilDeskripsi.getEditText().getText().toString().isEmpty() || tilNama.getEditText().getText().toString().isEmpty() || tilDaerah.getEditText().getText().toString().isEmpty() || spnKategoriWisata.getSelectedItem().toString().isEmpty()
        ) {
            Toast.makeText(this, "Data tidak bileh ada yang kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}