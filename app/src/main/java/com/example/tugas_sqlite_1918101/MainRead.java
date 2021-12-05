package com.example.tugas_sqlite_1918101;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements
        AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Cactus> ListCactus = new
            ArrayList<Cactus>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);

        adapter_off = new CustomListAdapter(this, ListCactus);
        mListView = (ListView) findViewById(R.id.list_cactus);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListCactus.clear();

        List<Cactus> cactus = db.ReadCactus();
        for (Cactus cts : cactus) {
            Cactus daftar = new Cactus();
            daftar.set_id(cts.get_id());
            daftar.set_nama(cts.get_nama());
            daftar.set_jenis(cts.get_jenis());
            ListCactus.add(daftar);

            if ((ListCactus.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int
            i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Cactus detailCts = (Cactus) o;

        String Sid = detailCts.get_id();
        String Snama = detailCts.get_nama();
        String Sjenis = detailCts.get_jenis();

        Intent goUpdel = new Intent(MainRead.this,
                MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Ijenis", Sjenis);
        startActivity(goUpdel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListCactus.clear();
        mListView.setAdapter(adapter_off);

        List<Cactus> cactus = db.ReadCactus();
        for (Cactus cts : cactus) {
            Cactus daftar = new Cactus();
            daftar.set_id(cts.get_id());
            daftar.set_nama(cts.get_nama());
            daftar.set_jenis(cts.get_jenis());
            ListCactus.add(daftar);

            if ((ListCactus.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
