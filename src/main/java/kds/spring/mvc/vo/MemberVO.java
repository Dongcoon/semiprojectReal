package kds.spring.mvc.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class MemberVO {
	private String mno;
	private String userid;
	private String passwd;
	private String name;
	private String email;
	private String regdate;

}
