package com.example.ujianandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edNamaDepan = (EditText) findViewById(R.id.edNamaDepan);
        EditText edNamaBelakang = (EditText) findViewById(R.id.edNamaBelakang);
        EditText edumur = (EditText) findViewById(R.id.edumur);
        Button btnSimpan = (Button) findViewById(R.id.btnSimpan);

        ArrayList<String> daftar_nama = new ArrayList<>();

        Intent intent_list = new Intent(MainActivity.this, ListActivity.class);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isian_nama_depan = edNamaDepan.getText().toString();
                String isian_nama_belakang = edNamaBelakang.getText().toString();
                int isian_umur = Integer.valueOf(edumur.getText().toString());
                String status = "";

                if(0 <= isian_umur && isian_umur <= 10)
                {
                    status = "ANAK";
                }
                else if(isian_umur >= 11 && isian_umur <= 20)
                {
                    status = "REMAJA";
                }
                else if(isian_umur >= 21 && isian_umur <= 30)
                {
                    status = "DEWASA";
                }
                else if(isian_umur > 30)
                {
                    status = "TUA";
                }

                if(isian_nama_depan.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Isian masih kosong", Toast.LENGTH_SHORT).show();
                }else {
                    String nama_lengkap = isian_nama_depan.concat(" ").concat(isian_nama_belakang);
                    daftar_nama.clear();

                    for (int i = 0; i <isian_umur; i += 2)
                    {
                        if (i % 2 == 0)
                        {
                            daftar_nama.add(i + ". " + nama_lengkap + ", status: " + status);
                        }
                    }
                    daftar_nama.add(nama_lengkap);
                    edNamaDepan.setText("");
                    edNamaBelakang.setText("");
                    intent_list.putStringArrayListExtra("daftar_nama", daftar_nama);
                    startActivity(intent_list);
                }
            }
        });
    }
}
