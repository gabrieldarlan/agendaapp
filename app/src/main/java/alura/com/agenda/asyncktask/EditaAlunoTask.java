package alura.com.agenda.asyncktask;

import alura.com.agenda.database.dao.AlunoDAO;
import alura.com.agenda.database.dao.TelefoneDAO;
import alura.com.agenda.model.Aluno;
import alura.com.agenda.model.Telefone;
import alura.com.agenda.model.TipoTelefone;

import java.util.List;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {


    private final AlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;
    private final List<Telefone> telefonesDoAluno;

    public EditaAlunoTask(AlunoDAO alunoDAO,
                          Aluno aluno,
                          Telefone telefoneFixo,
                          Telefone telefoneCelular,
                          TelefoneDAO telefoneDAO,
                          List<Telefone> telefonesDoAluno,
                          FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);

        return null;
    }


    private void atualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setAlunoId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }

}
