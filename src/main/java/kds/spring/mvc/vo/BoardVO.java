package kds.spring.mvc.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BoardVO {
	private int bno;
	private String title;
	private String userid;
	private String regdate;
	private String views;
	private String contents;
	
}
