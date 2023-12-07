package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

		//Connection 반환
		public static void close (Connection conn) {
			try { 
			if(conn!=null) {conn.close();}
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}	
		
		//Statement 반환
		public static void close (Statement stmt) {
			try {
				if(stmt!=null) {stmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//preparedstatement 반환 
		public static void close (PreparedStatement pstmt) {
			try {
				if(pstmt!=null) {pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//Resultset 반환
		public static void close (ResultSet rs) { 
			try { 
				if(rs!=null) {rs.close();}			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//rollback 기능
		public static void rollback (Connection conn) { 
			try { 
				if(conn!=null) {conn.rollback();}			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
