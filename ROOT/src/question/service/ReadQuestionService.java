package question.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import question.dao.QDAO;
import question.model.Question;

public class ReadQuestionService {

	private QDAO qDAO=new QDAO();

	public Question getQuestion(int questionNum) {
		try(Connection conn=ConnectionProvider.getConnection()){
			Question question=qDAO.selectById(conn,questionNum);
			if(question==null) {
				throw new QuestionNotFoundException();
			}
			return question;
			}catch(SQLException e){
				throw new RuntimeException(e);
				
		}
	}

}
