package com.connect.notepad.interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.connect.notepad.R;
import com.connect.notepad.modelo.Notes;
import com.connect.notepad.persistencia.ApiRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.thebluealliance.spectrum.SpectrumPalette;

public class Notepad extends AppCompatActivity {
    EditText edit_title, edit_description;
    SpectrumPalette palette;
    private AdView mAdView;
    ApiRequest apiRequest;
    int color;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        edit_title = findViewById(R.id.edit_title);
        edit_description = findViewById(R.id.edit_description);
        palette = findViewById(R.id.palette);

        palette.setOnColorSelectedListener(
                new SpectrumPalette.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int clr) {
                        color = clr;
                    }
                }
        );

        palette.setSelectedColor(getResources().getColor(R.color.white));
        color = getResources().getColor(R.color.white);

        Intent intent = getIntent();

        if (intent != null){
            Bundle params = intent.getExtras();
            if (params != null){
                id = Integer.parseInt(params.getString("id"));
                edit_title.setText(params.getString("title"));
                edit_description.setText(params.getString("description"));
                palette.setSelectedColor(Integer.parseInt(params.getString("color")));
                color = Integer.parseInt(params.getString("color"));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                salvar();
                return true;

            case R.id.dell:
                deletar();
                return true;

            case R.id.update:
                update();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void salvar() {
        Notes notes = new Notes();
        notes.setTitle(edit_title.getText().toString());
        notes.setDescription(edit_description.getText().toString());
        notes.setColor(color);
        long id = apiRequest.insert(notes);

        Intent intent = new Intent(Notepad.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void update() {
        Notes notes = new Notes();
        notes.setId(id);
        notes.setTitle(edit_title.getText().toString());
        notes.setDescription(edit_description.getText().toString());
        notes.setColor(color);
        apiRequest.update(notes);

        Intent intent = new Intent(Notepad.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void deletar(){
        apiRequest.excluir(id);
        Intent intent = new Intent(Notepad.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Notepad.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
