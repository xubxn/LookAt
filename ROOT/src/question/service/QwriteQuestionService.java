package question.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import question.dao.QDAO;
import question.model.Question;

public class QwriteQuestionService {

	private QDAO qDAO=new QDAO();
	
	public int insert(Question question) {
		Connection conn=null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Question quest=toQuestion(question);
			Question savedQuestion=qDAO.Qwrite(conn,quest);
			if(savedQuestion==null) {
				throw new RuntimeException("fail to insert question");
			}
			conn.commit();
			
			return savedQuestion.getQA_no();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}catch(RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}

	private Question toQuestion(Question question) {
		Date now=new Date();
	return new Question(question.getQ_no(), question.getMember_id(),question.getQ_plus_file(),question.getQ_details(),now,question.getQ_title());
	}




}
