package alura.com.agenda.asyncktask;

import alura.com.agenda.database.dao.AlunoDAO;
import alura.com.agenda.model.Aluno;
import alura.com.agenda.ui.adapter.ListaAlunosAdapter;
import android.os.AsyncTask;

import java.util.List;

public class BuscaAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;

    public BuscaAlunosTask(AlunoDAO dao, ListaAlunosAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);
    }
}
