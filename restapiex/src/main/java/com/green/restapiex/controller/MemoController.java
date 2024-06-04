package com.green.restapiex.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.green.restapiex.dto.MemoDto;
import com.green.restapiex.entity.Memo;
import com.green.restapiex.repository.MemoRepository;

@RestController
@RequestMapping("/memoapp")
public class MemoController {
	
	@Autowired
	private MemoRepository memoRepository;
	
	
	@RequestMapping("/main")
	public String root() {
		return "hello memo app";
	}
	
	
	// GET 메소드 (서버에 자료 요청할 때 - 어떤 자료인지 식별할 수 있는 파라미터가 있겠다.)
	// http://localhost:8092/memoapp/memo?mno=1
	@GetMapping("/memo")
	public Memo getMemo(@RequestParam("mno") Integer mno) {
		System.out.println("getMemo ....... mno : " + mno);
		
		Optional<Memo> result = memoRepository.findById(mno);
		Memo memo = result.get();
		
		return memo;
	}
	
	// POST 메소드 (서버에 자료 새로 등록할 때 - ajax로 json 데이터가 파라미터로 넘어온다.)
	// http://localhost:8092/memoapp/memo
	// { "title" : "test title..", "content" : "test content", "writer" : "hgd" }
	@PostMapping("/memo")
	public Memo postMemo(@RequestBody MemoDto memoDto) { // -> form 으로 넘어오면 커맨드 객체로 받기가능. @RequestBody 를 앞에 붙이면 json 으로 넘어오는 자료 받을 수 있음
		System.out.println("postMemo ..... memoDto : " + memoDto);
		
		Memo memo = new Memo();
		memo.setMno(memoDto.getMno());
		memo.setTitle(memoDto.getTitle());
		memo.setContent(memoDto.getContent());
		memo.setWriter(memoDto.getWriter());
		
		Memo result = memoRepository.save(memo);
		
		return result;
	}
	
	// PUT 메소드 (서버에 자료 재 등록할 때)
	@PutMapping("/memo")
	public String putMemo( MemoDto memoDto) {
		System.out.println("postMemo ..... memoDto : " + memoDto);
		
		Memo memo = new Memo();
		memo.setMno(memoDto.getMno());
		memo.setTitle(memoDto.getTitle());
		memo.setContent(memoDto.getContent());
		memo.setWriter(memoDto.getWriter());
		
		Memo result = memoRepository.save(memo);
		
		return result.toString();
	}
	
	// DELETE 메소드 (서버의 자료 삭제할 때 - 삭제할 자료를 식별할 수 있는 파라미터가 넘어온다.)
	@DeleteMapping("/memo/{mno}")
	public String deleteMemo(@PathVariable("mno") Integer mno) {
		System.out.println("deleteMemo ........ mno : " + mno);
		
		memoRepository.deleteById(mno);
		
		return "deleteMemo ........ mno : " + mno;
	}
	
	
	
}
