package alura.com.agenda.database.dao;

import alura.com.agenda.model.Aluno;
import androidx.room.*;

import java.util.List;

@Dao
public interface AlunoDAO {
    @Insert
    Long salva(Aluno aluno);

    @Query("SELECT * FROM aluno")
    List<Aluno> todos();

    @Delete
    void remove(Aluno aluno);

    @Update
    void edita(Aluno aluno);
}
