package alura.com.agenda.asyncktask;

import alura.com.agenda.database.dao.TelefoneDAO;
import alura.com.agenda.model.Aluno;
import alura.com.agenda.model.Telefone;
import android.os.AsyncTask;

import java.util.List;

public class BuscaTodosTelefonesDoAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {

    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TelefonesDoAlunoEncontradoListener listener;

    public BuscaTodosTelefonesDoAlunoTask(TelefoneDAO telefoneDAO,
                                          Aluno aluno,
                                          TelefonesDoAlunoEncontradoListener listener
    ) {

        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);
    }

    public interface TelefonesDoAlunoEncontradoListener {
        void quandoEncontrados(List<Telefone> telefones);
    }

}
