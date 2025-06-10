package kr.co.songjava.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.songjava.configuration.http.BaseResponse;
import kr.co.songjava.mvc.domain.Board;
import kr.co.songjava.mvc.parameter.BoardParameter;
import kr.co.songjava.mvc.repository.BoardRepository;
import kr.co.songjava.mvc.service.BoardService;


/**
 * 게시판 컨트롤러
 * @author 서동진
 */
@RestController
@RequestMapping("/board")
@Tag(name = "게시판 API", description = "게시판 관련 기능을 제공하는 컨트롤러")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	/**
	 * 목록 리턴.
	 * @return
	 */
	@GetMapping
	@Operation(summary = "목록 조회", description = "게시물 목록 정보를 조회할 수 있습니다.")
	public BaseResponse<List<Board>> getList(){
		return new BaseResponse<List<Board>>(boardService.getList());
	}
	
	/**
	 * 상세 정보 리턴.
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("{boardSeq}")
	@Operation(summary = "상세 조회", description = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@Parameters({
	    @Parameter(name = "boardSeq", description = "게시물 번호", example = "1"),
	})
	public BaseResponse<Board> get(@PathVariable int boardSeq) {
		return new BaseResponse<Board>(boardService.get(boardSeq));
	}
	
	/**
	 * 등록/수정 처리.
	 * @param board
	 */
	@PutMapping("/save")
	@Operation(summary = "등록 / 수정 처리", description = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
	@Parameters({
	    @Parameter(name = "boardSeq", description = "게시물 번호", example = "1"),
	    @Parameter(name = "title", description = "제목", example = "spring"),
	    @Parameter(name = "contents", description = "내용", example = "spring 강좌"),
	})
	public BaseResponse<Integer> save(BoardParameter parameter) {
		boardService.save(parameter);
		return new BaseResponse<Integer>(parameter.getBoardSeq());
	}
		
	/**
	 * 삭제 처리.
	 * @param board
	 */
	@DeleteMapping("/delete/{boardSeq}")
	@Operation(summary = "삭제 처리", description = "게시물 번호에 해당하는 정보 삭제합니다.")
	@Parameters({
	    @Parameter(name = "boardSeq", description = "게시물 번호", example = "1"),
	})
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			return new BaseResponse<Boolean>(false);
		}
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}
}
