package com.ingsw.fitnessapp.activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ingsw.fitnessapp.R;

import java.util.List;

public class NuovoEsercizioActivity extends AppCompatActivity {

    MaterialToolbar toolbar ;

    Spinner spinner_tipologia;
    TextInputEditText nome,peso;
    AutoCompleteTextView unita_di_misura;
    TextView ripetizioni;
    Button btn_piu, btn_meno;
    ChipGroup gruppo_chips;

    TextInputLayout layout_peso,layout_udm;
    RelativeLayout relativeLayout_ripetizioni;
    LinearLayout linearLayout_gruppi;
    ExtendedFloatingActionButton fab;

    int reps = 0;
    int chipId = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuovo_eseicizio);
        toolbar = findViewById(R.id.id_nuovoesercizio_appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_tipologia = findViewById(R.id.id_nuovoesercizio_spinner);
        nome = findViewById(R.id.id_nuovoesercizio_nome);
        peso = findViewById(R.id.id_nuovoesercizio_peso);
        unita_di_misura = findViewById(R.id.id_nuovoesercizio_udm);
        ripetizioni = findViewById(R.id.id_nuovoesercizio_ripetizioni);
        btn_piu = findViewById(R.id.id_nuovoesercizio_piu);
        btn_meno = findViewById(R.id.id_nuovoesercizio_meno);
        layout_peso = findViewById(R.id.id_nuovoesercizio_layoutinput_peso);
        layout_udm = findViewById(R.id.id_nuovoesercizio_layout_udm);
        relativeLayout_ripetizioni = findViewById(R.id.id_nuovoesercizio_layoutrelative);
        linearLayout_gruppi = findViewById(R.id.id_nuovoesercizio_layout_gruppi);
        gruppo_chips = findViewById(R.id.id_nuovoesercizio_chipgroup);

        fab = findViewById(R.id.id_nuovoesercizio_fab);


        spinner_tipologia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case (0):{
                        layout_peso.setVisibility(View.VISIBLE);
                        layout_udm.setVisibility(View.VISIBLE);
                        relativeLayout_ripetizioni.setVisibility(View.VISIBLE);
                        linearLayout_gruppi.setVisibility(View.VISIBLE);
                        gruppo_chips.setVisibility(View.VISIBLE);

                    }break;
                    case (1):{
                        layout_peso.setVisibility(View.GONE);
                        layout_udm.setVisibility(View.GONE);
                        relativeLayout_ripetizioni.setVisibility(View.GONE);
                        linearLayout_gruppi.setVisibility(View.GONE);
                        gruppo_chips.setVisibility(View.GONE);
                    }break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_piu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reps >= 0){
                    reps++;
                }
                ripetizioni.setText(String.valueOf(reps).toString());
            }
        });

        btn_meno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reps > 0){
                    reps--;
                }
                ripetizioni.setText(String.valueOf(reps).toString());
            }
        });


        gruppo_chips.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controllaCampi(spinner_tipologia.getSelectedItemPosition());
            }
        });
    }

    private void controllaCampi(int selectedItemPosition) {
        switch (selectedItemPosition){
            case (0):{


            }break;
            case (1):{


            }break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

