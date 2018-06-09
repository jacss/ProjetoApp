package projetoapp.projeto.com.projetoapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Agenda extends AppCompatActivity {
    private EditText textoNome;
    private EditText textoTelefone;
    private EditText textoEmail;
    private Button botaoAdicionar;
    private ListView listaContato;
    private SQLiteDatabase bancoDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        try {


            //Recuperando os componente da tela
            textoNome = findViewById(R.id.editNome_Id);
            textoTelefone = findViewById(R.id.editTelefone_Id);
            textoEmail = findViewById(R.id.editEmail_Id);
            botaoAdicionar = findViewById(R.id.botaoAgenda_Id);
            listaContato = findViewById(R.id.listView_Id);

            //Criando o banco de dados
            bancoDados = openOrCreateDatabase("bd_agenda", MODE_PRIVATE, null);
            //Criando a taleba com os atributos
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS contato (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome VARCHAR," +
                    "telefone VARCHAR," +
                    "email VARCHAR)");
            //Criando ação no botão adicionar
            botaoAdicionar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nomeContato = textoNome.getText().toString();
                    String telefoneContato = textoTelefone.getText().toString();
                    String emailContato = textoEmail.getText().toString();

                    //Inserindo dados no banco
                    // bancoDados.execSQL("INSERT INTO contato (nome,telefone,email) VALUES('" + nomeContato + "','" + telefoneContato + "','" + emailContato + "')");
                    salvarContato(nomeContato, telefoneContato, emailContato);

                }
            });
            //Recupera os contatos
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM contato", null);

            //Recupera os ids das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaTelefone = cursor.getColumnIndex("telefone");
            int indiceColunaEmail = cursor.getColumnIndex("email");

            //Listar os contatos
            cursor.moveToFirst();

            while (cursor != null) {

                Log.i("Resultado - nome: ", cursor.getString(indiceColunaNome));
                Log.i("Resultado - telefone: ", cursor.getString(indiceColunaTelefone));
                Log.i("Resultado - email: ", cursor.getString(indiceColunaEmail));
                cursor.moveToNext();

            }

        } catch (Exception erro) {
            erro.printStackTrace();
        }

    }

    private void salvarContato(String nomeCon, String telefoneCon, String emailCon) {
        try {
            if (nomeCon.equals("")|| telefoneCon.equals("")||emailCon.equals("")) {
                Toast.makeText(Agenda.this, "Os campos não pode ser vazio!!", Toast.LENGTH_SHORT).show();
            } else {
                bancoDados.execSQL("INSERT INTO contato (nome,telefone,email) VALUES('" + nomeCon + "','" + telefoneCon + "','" + emailCon + "')");
                limpaCampos();
                Toast.makeText(Agenda.this, "Contato salvo com sucesso", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception erro) {
            erro.printStackTrace();

        }


    }

    public void limpaCampos() {
        textoNome.setText("");
        textoTelefone.setText("");
        textoEmail.setText("");
    }

   
}
