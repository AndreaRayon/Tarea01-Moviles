package com.iteso.tarea01;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Activity_Main extends AppCompatActivity {
    Alumno alumno;
    AutoCompleteTextView autoCompleteTextView;
    Spinner spinner;
    RadioButton radioButton;
    EditText name, phone;
    CheckBox checkBox;

    public class Alumno {
        AutoCompleteTextView actv;
        Spinner spinner;
        RadioButton rb;
        EditText name, phone;
        CheckBox cb;

        public Alumno(AutoCompleteTextView autoCompleteTextView, Spinner spinner, RadioButton radioButton, EditText name, EditText phone, CheckBox checkBox) {
            this.actv = autoCompleteTextView;
            this.spinner = spinner;
            this.rb = radioButton;
            this.name = name;
            this.phone = phone;
            this.cb = checkBox;
        }

        public String toString() {
            return "Nombre: " + name.getText() +
                    "\nTelefono: " + phone.getText() +
                    "\nEscolaridad: " + spinner.getSelectedItem().toString() +
                    "\nGénero : " + rb.getText() +
                    (actv.getText().length() > 0 ? "Libro Favorito:" + actv.getText() : "") +
                    "\nPractica Deporte: " + (cb.isChecked() ? "Si" : "No");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        checkBox = (CheckBox) findViewById(R.id.sport);
        radioButton = (RadioButton) findViewById(R.id.fem);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.escolaridad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        autoCompleteTextView = findViewById(R.id.activity_main_books);
        String[] libros = getResources().getStringArray(R.array.book_list);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, libros);
        autoCompleteTextView.setAdapter(adapter2);
    }

    public void limpiar() {
        autoCompleteTextView.setText("");
        autoCompleteTextView.setText("");
        radioButton = findViewById(R.id.fem);
        radioButton.setChecked(true);
        spinner.setSelection(0);
        name.setText("");
        phone.setText("");
        checkBox.setChecked(false);
    }

    public void onButtonClicked(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage( "¿Desea limpiar?");
        alertDialog.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                limpiar();
            }
        });

        alertDialog.create();
        alertDialog.show();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.fem:
                if (checked)
                    radioButton = findViewById(R.id.fem);
                break;
            case R.id.man:
                if (checked)
                    radioButton = findViewById(R.id.man);
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
        
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Alumno alumno = new Alumno(autoCompleteTextView, spinner, radioButton, name, phone, checkBox);
        if (alumno.name.getText().length() > 0 && alumno.phone.getText().length() > 0) {
            Toast.makeText(this, alumno.toString(), Toast.LENGTH_LONG).show();
            limpiar();
        } else Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
        return true;
    }

    public class SpinnerActivity extends Activity
            implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}

