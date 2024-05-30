package com.green.security.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.green.security.entity.Member;

public class CustomUserDetails implements UserDetails{ // Security 전용 Session을 이용하기 위해서 절차가 복잡해짐
	
	private Member member;
	
	public CustomUserDetails(Member member) {
		this.member = member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // 제네릭 <? extends A> A를 상속받은 애만 올수있다.
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				
				return member.getRole();
			}
			
		});
		
		return collection; // 기본이 null 세팅인데 null 이면 안됨.
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}
	
	// 필요한거는 직접 메서드 만들어 쓰기
	public String getName() {
		return member.getName();
	}
	
	public String getRole() {
		return member.getRole();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
