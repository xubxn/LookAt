package question.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import question.dao.QDAO;
import question.model.Question;

public class ModifyQuestionService {

	private QDAO qDAO=new QDAO();
	
	public int modify(ModifyRequest modReq) throws Exception{
		
		Connection conn=null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Question question =qDAO.selectById(conn, modReq.getQA_no());
			if(question==null) {
				throw new QuestionNotFoundException();
			}
			/*if(!canModify(modReq.getMember_id(),question)) {
				throw new PermissionDeniedException();
			}*/
		qDAO.update(conn, modReq.getQ_no(),modReq.getQ_plus_file(),modReq.getQ_details(),modReq.getQ_title(),modReq.getQA_no());
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		return 0;
	}

/*private boolean canModify(String modfyingMember_id, Question question) {
		return question.getMember_id().equals(modfyingMember_id);
	}*/
	
}
