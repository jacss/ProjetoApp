package projetoapp.projeto.com.projetoapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button calculadoraBasica;
private Button botaoAgenda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculadoraBasica = findViewById(R.id.calBasica_Id);
        botaoAgenda = findViewById(R.id.agendaTel_Id);


        calculadoraBasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Calculadora.class);
                startActivity(intent);

            }
        });

        botaoAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Agenda.class);
                startActivity(intent);

            }
        });


    }
}
