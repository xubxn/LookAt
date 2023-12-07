package question.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import question.dao.QDAO;
import question.model.Question;

public class ListQuestionService {

	private QDAO qDAO=new QDAO();
		private int size=10;
		
		public QuestionPage getQuestionPage(int pageNum, String member_id) {
			try(Connection conn=ConnectionProvider.getConnection()){
				int total=qDAO.selectCount(conn,member_id);
				List<Question> content=qDAO.select(conn,member_id,(pageNum-1)*size,size);
				return new QuestionPage(total, pageNum, size, content);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
		public QuestionPage getQuestionPageAD(int pageNum) {
			try(Connection conn=ConnectionProvider.getConnection()){
				int total=qDAO.selectCountAD(conn);
				List<Question> content=qDAO.selectAD(conn,(pageNum-1)*size,size);
				return new QuestionPage(total, pageNum, size, content);
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
