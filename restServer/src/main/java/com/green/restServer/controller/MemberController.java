package com.green.restServer.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.restServer.dto.BoardDto;
import com.green.restServer.entity.Board;
import com.green.restServer.entity.Member;
import com.green.restServer.repository.BoardRepository;
import com.green.restServer.repository.MemberRepository;

import net.coobird.thumbnailator.Thumbnails;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	// @Autowired
	private BoardRepository boardRepository;
	// @Autowired
	private MemberRepository memberRepository;
	
	// 생성자 주입 방식 
	public MemberController(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	
	// 로그인이 끝난 상태 -> 서비스를 이용하는 단계 -> 게시물을 보거나 게시물을 등록하는 것.
	//@GetMapping("/main")
	public String main() {
		return "member/main";
	}
	
	
	//게시물 등록(등록 폼, 등록처리)
	//@GetMapping("/regBoard")
	public String regBoard() {
		return "member/regBoardForm";
	}
	
	@PostMapping("/regBoardProc")
	public @ResponseBody String regBoardProc(BoardDto boardDto) {
		// 엔티티의 컬럼과 게시글 등록시 입력받는 컬럼이 차이가 나서 Dto 를 만들어서 받는다.
		
		Board board = new Board();
		board.setTitle(boardDto.getTitle());
		board.setContent(boardDto.getContent());
		
		Member member = new Member();
		member.setUsername(boardDto.getWriter());
		board.setMember(member);
		
		// 파일 관련 멤버변수 세팅
		String originName = boardDto.getFileName();
		board.setOriginName(originName);
		String newName = UUID.randomUUID().toString() + originName; // 중복되지 않는 새이름 생성
		board.setNewName(newName);
		
		String thumbNailName = "";
		
		// 파일처리
		File file = new File(newName);
		
		try {
			boardDto.getFile().transferTo(file);
			System.out.println("파일 업로드 성공....");
			
			// 썸네일 생성
			String thumbnailSaveName = "s_" + newName;
			board.setThumbnailName(thumbnailSaveName);
			File thumbfile = new File(uploadPath + thumbnailSaveName);
			File ufile = new File(uploadPath + newName);
			
			Thumbnails.of(ufile).size(100, 100).toFile(thumbfile);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// db 에 board(엔티티) 를 저장
		System.out.println("===================================");
		System.out.println(board);
		Board b = boardRepository.save(board);
		if(b != null) {
			return "등록 성공.....";
		}else {
			return "등록 실패.....";
		}
			
	}
	
	//게시물 조회
	//@GetMapping("/getBoardList")
	public String boardList(Model model) {
		List<Board> list = boardRepository.findAll();
		
		model.addAttribute("boardList", list);
		return "member/boardList";
	}
	
	//@GetMapping("/detail")
	public String detail(@RequestParam("bno") int bno, Model model) {
		
		
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.get();
		
		model.addAttribute("board", board);
		return "member/getBoard";
	}
	
	
	
	
	
	
}
