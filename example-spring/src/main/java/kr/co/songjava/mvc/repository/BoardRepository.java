package kr.co.songjava.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.songjava.mvc.domain.Board;

/**
 * 게시판 Repository
 * @author 서동진
 */
@Repository
public interface BoardRepository {

	List<Board> getList(); //리스트
	
	Board get(int boardSeq); //단건
	
	void save(Board board); //등록
	
	void update(Board board); //업데이트
	
	void delete(int boardSeq); //삭제
}
