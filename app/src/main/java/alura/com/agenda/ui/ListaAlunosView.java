package alura.com.agenda.ui;

import alura.com.agenda.asyncktask.BuscaAlunosTask;
import alura.com.agenda.asyncktask.RemoveAlunoTask;
import alura.com.agenda.database.AgendaDatabase;
import alura.com.agenda.database.dao.AlunoDAO;
import alura.com.agenda.model.Aluno;
import alura.com.agenda.ui.adapter.ListaAlunosAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        dao = AgendaDatabase.getInstance(context)
                .getRoomAlunoDao();
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog
                .Builder(context).
                setTitle("Removendo Aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        new BuscaAlunosTask(dao,adapter).execute();

    }

    private void remove(Aluno aluno) {
        new RemoveAlunoTask(dao, adapter, aluno).execute();

    }

    public void configuraAdapter(ListView listaDeAlunos) {

        listaDeAlunos.setAdapter(adapter);
    }

}
