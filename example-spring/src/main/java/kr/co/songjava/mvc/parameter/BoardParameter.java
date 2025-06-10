package kr.co.songjava.mvc.parameter;

import java.util.Date;

import lombok.Data;

@Data //롬복 선언
public class BoardParameter {
	
	private int boardSeq;
	private String title;
	private String contents;
}
