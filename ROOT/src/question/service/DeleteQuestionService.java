package question.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import question.dao.QDAO;
import question.model.Question;

public class DeleteQuestionService {

		QDAO questionDAO=new QDAO();
		
		public void delete(int QA_no) {
		Connection conn=null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
					
			Question question =questionDAO.getDetail(conn,QA_no);
			if(question==null) {
				throw new QuestionNotFoundException();
			}
			questionDAO.delete(conn,QA_no);
			conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		}
}
