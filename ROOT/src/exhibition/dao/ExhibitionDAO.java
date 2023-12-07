package exhibition.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exhibition.model.Exhibition;
import exhibition.model.ExhibitionJoin;
import jdbc.JdbcUtil;

public class ExhibitionDAO {

	public int selectCountByname(Connection conn, String name) throws SQLException {
		String sql = "SELECT COUNT(*) FROM exhibition " +
							  "where title like concat('%',?,'%') ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			int totalCNT = 0; 			
			if (rs.next()) {
				totalCNT = rs.getInt(1);
			}
			System.out.println("totalCNT = "+totalCNT);
			return totalCNT;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}

	public List<Exhibition> getListByName(Connection conn, String name, int startRow, int size) throws SQLException {
			String sql = "select e.exhibition_no,e.title,e.open_date,e.end_date,e.PLACE,e.thumbnail,e.details_img,e.introduction, p.price_no, p.price_adult, p.price_student, p.price_baby, l.loc_no, l.loc, l.details_place " +
								  "from exhibition e,price p,location l " +
								  "where e.exhibition_no = p.exhibition_no " +
								  "and e.PLACE = l.place " +
								  "and e.title like concat('%',?,'%' )" +
								  "order by exhibition_no desc LIMIT ?,?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, size);
				rs = pstmt.executeQuery();
				List<Exhibition> result = new ArrayList<Exhibition>();
				while (rs.next()) {
					result.add(convertExhibition(rs)); 
				} 
				return result;
			} finally { // 5.자원반납
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}

	// 전시회 등록
	public int insert(Connection conn, Exhibition exhibition) throws SQLException {
		System.out.println("insert DAO 진입");
		System.out.println("[insert DAO] exhibition 데이터: " + exhibition.toString());

		String sql_location = "INSERT INTO location (loc, place, details_place) VALUES (?, ?, ?)";
		String sql_exhibition = "INSERT INTO exhibition (title, open_date, end_date, thumbnail, details_img, introduction, PLACE) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sql_price = "INSERT INTO price (price_adult, price_student, price_baby, exhibition_no) VALUES (?, ?, ?, ?)";
		PreparedStatement stmt_location = null;
		PreparedStatement stmt_exhibition = null;
		PreparedStatement stmt_price = null;
		ResultSet rs_exhibition = null;

		try {
			conn.setAutoCommit(false);  // 트랜잭션 시작

			// location 테이블에 삽입
			stmt_location = conn.prepareStatement(sql_location);
			stmt_location.setString(1, exhibition.getLoc());
			stmt_location.setString(2, exhibition.getPlace());
			stmt_location.setString(3, exhibition.getDetails_place());
			stmt_location.executeUpdate();

			// exhibition 테이블에 삽입
			stmt_exhibition = conn.prepareStatement(sql_exhibition, Statement.RETURN_GENERATED_KEYS);
			stmt_exhibition.setString(1, exhibition.getTitle());
			stmt_exhibition.setDate(2, exhibition.getOpen_date());
			stmt_exhibition.setDate(3, exhibition.getEnd_date());
			stmt_exhibition.setString(4, exhibition.getThumbnail());
			stmt_exhibition.setString(5, exhibition.getDetails_img());
			stmt_exhibition.setString(6, exhibition.getIntroduction());
			stmt_exhibition.setString(7, exhibition.getPlace());
			stmt_exhibition.executeUpdate();

			// 생성된 exhibition_no 가져오기
			rs_exhibition = stmt_exhibition.getGeneratedKeys();
			if (rs_exhibition.next()) {
				int generated_exhibition_no = rs_exhibition.getInt(1);

				// price 테이블에 삽입
				stmt_price = conn.prepareStatement(sql_price);
				stmt_price.setInt(1, exhibition.getPrice_adult());
				stmt_price.setInt(2, exhibition.getPrice_student());
				stmt_price.setInt(3, exhibition.getPrice_baby());
				stmt_price.setInt(4, generated_exhibition_no);
				stmt_price.executeUpdate();
			} else {
				throw new SQLException("Creating exhibition failed, no ID obtained.");
			}

			conn.commit();

			return rs_exhibition.getInt(1);  // 직전에 작성된 글번호 리턴
		} catch (SQLException e) {
			conn.rollback();  
			throw e;
		} finally {
			JdbcUtil.close(stmt_price);
			JdbcUtil.close(rs_exhibition);
			JdbcUtil.close(stmt_exhibition);
			JdbcUtil.close(stmt_location);
		}
	}




	// 전시회 목록
	public List<Exhibition> getList(Connection conn, int startRow, int size) throws SQLException {
		String sql = "select e.exhibition_no,e.title,e.open_date,e.end_date,e.PLACE,e.thumbnail,e.details_img,e.introduction, p.price_no, p.price_adult, p.price_student, p.price_baby, l.loc_no, l.loc, l.details_place " +
							  "from exhibition e,price p,location l " +
							  "where e.exhibition_no = p.exhibition_no " +
							  "and e.PLACE = l.place " +
							  "order by exhibition_no desc LIMIT ?,?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, startRow);
			stmt.setInt(2, size);
			rs = stmt.executeQuery();

			List<Exhibition> result = new ArrayList<Exhibition>();

			while (rs.next()) {
				result.add(convertExhibition(rs)); 
			} // while
			return result;
		} finally { // 5.자원반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// 전시회 상세조회
	public Exhibition getDetail (Connection conn, int no) throws SQLException {
		String sql = "select e.exhibition_no,e.title,e.open_date,e.end_date,e.PLACE,e.thumbnail,e.details_img,e.introduction, p.price_no, p.price_adult, p.price_student, p.price_baby, l.loc_no, l.loc, l.details_place " + 
							  "from exhibition e,price p,location l " + 
							  "where e.exhibition_no = p.exhibition_no " + 
							  "and e.PLACE = l.place " + 
							  "and e.exhibition_no=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			rs = stmt.executeQuery();
			Exhibition detailData = null;
			if (rs.next()) {
				detailData = convertExhibition(rs);
			}
			return detailData;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public ExhibitionJoin getDetail2(Connection conn, int no) throws SQLException {
		String sql ="select e.exhibition_no,e.title,e.open_date,e.end_date,e.PLACE,e.thumbnail,e.details_img,e.introduction, p.price_adult, p.price_student, p.price_baby, l.details_place " + 
				"from exhibition e,price p,location l " + 
				"where e.exhibition_no = p.exhibition_no " + 
				"and e.PLACE = l.place " + 
				"and e.exhibition_no=?";
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1,no);
				rs = stmt.executeQuery();
			ExhibitionJoin detailData = null;
			if (rs.next()) {
				detailData = new ExhibitionJoin();
				detailData.setExhibition_no(rs.getInt("exhibition_no"));
				detailData.setTitle(rs.getString("title"));
				detailData.setOpen_date(rs.getDate("open_date"));
				detailData.setEnd_date(rs.getDate("end_date"));
				detailData.setPlace(rs.getString("place"));
				detailData.setThumbnail(rs.getString("thumbnail"));
				detailData.setDetails_img(rs.getString("details_img"));
				detailData.setIntroduction(rs.getString("introduction"));
				detailData.setPrice_adult(rs.getInt("price_adult"));
				detailData.setPrice_student(rs.getInt("price_student"));
				detailData.setPrice_baby(rs.getInt("price_baby"));
				detailData.setDetails_place(rs.getString("details_place"));
			
			//콘솔출력
			System.out.println("ExhibitionDAO - getDetail() ExhibitionJoin detailData= " + detailData);
			}
			return detailData;
			} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			}
	}

	// 전시회 수정
	public int update(Connection conn, Exhibition exhibition) throws SQLException {
		
		System.out.println("update DAO진입");
		System.out.println("[update DAO]exhibition 데이터: " + exhibition.toString());

		String sql_location = "update location set loc=?, place=?, details_place=? where loc_no=?";
		String sql_exhibition = "update exhibition set title = ?, open_date = ?, end_date = ?, thumbnail = ?, details_img = ?, introduction = ? where exhibition_no = ?";
		String sql_price = "update price set price_adult = ?, price_student = ?, price_baby = ? where price_no = ?";
		PreparedStatement stmt_location = null;
		PreparedStatement stmt_exhibition = null;
		PreparedStatement stmt_price = null;

		try {
			stmt_location = conn.prepareStatement(sql_location);
			stmt_location.setString(1, exhibition.getLoc());
			stmt_location.setString(2, exhibition.getPlace());
			stmt_location.setString(3, exhibition.getDetails_place());
			stmt_location.setInt(4, exhibition.getLoc_no());
			int location_result = stmt_location.executeUpdate();
			
			stmt_price = conn.prepareStatement(sql_price);
			stmt_price.setInt(1, exhibition.getPrice_adult());
			stmt_price.setInt(2, exhibition.getPrice_student());
			stmt_price.setInt(3, exhibition.getPrice_baby());
			stmt_price.setInt(4, exhibition.getPrice_no());
			int price_result = stmt_price.executeUpdate();
			
			stmt_exhibition = conn.prepareStatement((sql_exhibition));
			stmt_exhibition.setString(1, exhibition.getTitle());
			stmt_exhibition.setDate(2, exhibition.getOpen_date());
			stmt_exhibition.setDate(3, exhibition.getEnd_date());
			stmt_exhibition.setString(4, exhibition.getThumbnail());
			stmt_exhibition.setString(5, exhibition.getDetails_img());
			stmt_exhibition.setString(6, exhibition.getIntroduction());
			stmt_exhibition.setInt(7, exhibition.getExhibition_no());
			int exhibition_result = stmt_exhibition.executeUpdate();
			
			System.out.println("exhibition.getPrice_no()" + exhibition.getPrice_no());
			System.out.println("exhibition.getLoc_no()" + exhibition.getLoc_no());
			System.out.println("exhibition.getExhibition_no():" + exhibition.getExhibition_no() );
			
			//update가 성공되면 1리턴, 실패시 0리턴
			//exhibition, price, location 전부 성공해야 성공한 것
			int result = 0;
			if (exhibition_result==1 && price_result==1 && location_result==1){
				result=1;
			}
			System.out.println("DAO - update() 결과: " + result);
			return result;
		}catch (SQLException e) {
			conn.rollback();  // Roll back transaction in case of error
			throw e;
		}finally {
			JdbcUtil.close(stmt_price);
			JdbcUtil.close(stmt_location);
			JdbcUtil.close(stmt_exhibition);
		}
	}

	// 전시회 삭제
	public int delete(Connection conn, int no) throws SQLException {
		
		System.out.println("ExhibitionDAO-delete() 진입");
		
		String sql = "delete from exhibition where exhibition_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			return stmt.executeUpdate();
			//delete가 성공되면 1리턴, 실패시 0리턴
		}finally {
			JdbcUtil.close(stmt);
		}
	}

	// 전시회 Model 객체 생성을 위한 메소드
	public Exhibition convertExhibition(ResultSet rs) throws SQLException {
		Exhibition exhibition = new Exhibition();
		exhibition.setExhibition_no(rs.getInt("exhibition_no"));
		exhibition.setTitle(rs.getString("title"));
		exhibition.setOpen_date(rs.getDate("open_date"));
		exhibition.setEnd_date(rs.getDate("end_date"));
		exhibition.setThumbnail(rs.getString("thumbnail"));
		exhibition.setDetails_img(rs.getString("details_img"));
		exhibition.setIntroduction(rs.getString("introduction"));
		exhibition.setPrice_no(rs.getInt("price_no"));
		exhibition.setPrice_adult(rs.getInt("price_adult"));
		exhibition.setPrice_student(rs.getInt("price_student"));
		exhibition.setPrice_baby(rs.getInt("price_baby"));
		exhibition.setLoc_no(rs.getInt("loc_no"));
		exhibition.setPlace(rs.getString("place"));
		exhibition.setLoc(rs.getString("loc"));
		exhibition.setDetails_place(rs.getString("details_place"));
		return exhibition;
	}
	
	
	//게시물 수 조회하는 메소드
	public int selectCount(Connection conn) throws SQLException {
		String sql = "SELECT COUNT(*) FROM exhibition";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int totalCNT = 0; //총 게시물수를 저장하기 위한 변수 선언 및 초기화
			
			if (rs.next()) {
				totalCNT = rs.getInt(1);
			}
			return totalCNT;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		
	} //selectCount();


	//필터검색
	//StringBuilder : String은 더하기 연산자를 사용할 때마다 새로운 객체를 생성하여 메모리를 할당하고 그에 따라 성능도 저하된다고한다. StringBuilder는 더하기 연산자를 사용해도 새로운 String 객체를 생성하지 않는다.
	public List<Exhibition> filterExhibition(Connection conn, String[] locations, String yearMonth) throws NumberFormatException, SQLException {
		StringBuilder sql = new StringBuilder( "SELECT e.exhibition_no,e.title,e.open_date,e.end_date, e.thumbnail,e.details_img,e.introduction, " + 
								"p.price_no, p.price_adult, p.price_student, p.price_baby, " + 
								"l.loc_no, l.loc, l.place, l.details_place " + 
								"from exhibition e,price p,location l " + 
								"where e.exhibition_no = p.exhibition_no " +  
								"and e.PLACE = l.place ");  
								
								//if문을 두개로 하여 지역만 검색했을 때, 달만 검색했을 때, 지역과 달 모두 검색하였을 때 세가지 방법을 고려한 코드 
								if (locations != null && locations.length > 0) {
							        sql.append(" AND loc IN (");
							        for (int i = 0; i < locations.length; i++) {
							            sql.append("?");
							            if (i < locations.length - 1) {
							                sql.append(", ");
							            }
							        }
							            sql.append(")");
							        }
								//시작일과 종료일 둘 중 하나의 조건에 해당한다면 출력
								if (yearMonth != null) {
						            sql.append(" AND ((YEAR(open_date) = ? AND MONTH(open_date) = ?) "
						            		+ "OR (YEAR(end_date) = ? AND MONTH(end_date) = ?))");
						        }
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql.toString());
			
			//location for문으로 여러개 선택 가능하게 하기
			int index = 1;
            if (locations != null) {
            	for(int i =0; i<=locations.length; i++) {
                    stmt.setString(i+1, locations[i]);
                    System.out.println(locations[i]);
                }
            }
            if (yearMonth != null) {
                String[] parts = yearMonth.split("-"); //"-" 기호를 기준으로 자르기
                stmt.setInt(index++, Integer.parseInt(parts[0]));  // year //open_date의 year 
                stmt.setInt(index++, Integer.parseInt(parts[1]));  // month //open_date의 month
                stmt.setInt(index++, Integer.parseInt(parts[0]));  // year //end_date의 year
                stmt.setInt(index++, Integer.parseInt(parts[1]));  // month end_date의 month
            }
            rs = stmt.executeQuery();

			List<Exhibition> result = new ArrayList<Exhibition>();

			while (rs.next()) {
				result.add(convertExhibition(rs)); 
			} // while
			return result;
		} finally { // 5.자원반납
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		}




	public List<Exhibition> filterExhibition01(Connection conn, String location, String open_date, String end_date) throws SQLException {
		String sql = "SELECT e.exhibition_no,e.title,e.open_date,e.end_date, e.thumbnail,e.details_img,e.introduction, " + 
				"p.price_no, p.price_adult, p.price_student, p.price_baby, " + 
				"l.loc_no, l.loc, l.place, l.details_place " + 
				"from exhibition e,price p,location l " + 
				"where e.exhibition_no = p.exhibition_no " +  
				"and e.PLACE = l.place "+
				"and loc=?  and month(open_date) >= ? and month(end_date) <= ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Exhibition> result = new ArrayList<Exhibition>();
		try {
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, location);
		stmt.setString(2, open_date);
		stmt.setString(3, end_date);
		
		rs = stmt.executeQuery();
		while(rs.next()) {
			result.add(convertExhibition(rs)); 
		}
		return result;
	} finally { // 5.자원반납
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
	}
	}

	public Exhibition getInfo(Connection conn, int no) throws SQLException {
		String sql = "select exhibition_no, title, PLACE " + 
					"from exhibition " + 
					"where exhibition_no=?";		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Exhibition exhibition = new Exhibition();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				int exhibition_no = rs.getInt("exhibition_no");
				String title = rs.getString("title");
				String place = rs.getString("PLACE");				
				exhibition.setExhibition_no(exhibition_no);
				exhibition.setTitle(title);
				exhibition.setPlace(place);
			}
			return exhibition;
		}finally { // 5.자원반납
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);
	}
	}

	public int deleteLoc(Connection conn, String place) throws SQLException {
		String sql ="delete from location " + 
					"where place=?";
		Statement stmt = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			stmt.execute("SET FOREIGN_KEY_CHECKS=0");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,place);
			result = pstmt.executeUpdate();
			stmt.execute("SET FOREIGN_KEY_CHECKS=1");
		return result;
		}finally { // 5.자원반납
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
			
		}
	}

	public int deletePrice(Connection conn, int exhibition_no) throws SQLException {
		String sql ="delete from price " + 
					"where exhibition_no=?";
		Statement stmt = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			stmt.execute("SET FOREIGN_KEY_CHECKS=0");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,exhibition_no);
			result = pstmt.executeUpdate();
			stmt.execute("SET FOREIGN_KEY_CHECKS=1");
		return result;
		}finally { 
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	public int deleteExhibition(Connection conn, int exhibition_no) throws SQLException {
		String sql ="delete from exhibition " + 
					"where exhibition_no=?";
		Statement stmt = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			stmt = conn.createStatement();
			stmt.execute("SET FOREIGN_KEY_CHECKS=0");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,exhibition_no);
			result = pstmt.executeUpdate();
			stmt.execute("SET FOREIGN_KEY_CHECKS=1");
		return result;
		}finally { 
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	
	
}