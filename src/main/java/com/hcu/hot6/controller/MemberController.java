package com.hcu.hot6.controller;

import com.hcu.hot6.domain.Member;
import com.hcu.hot6.domain.request.MemberRequest;
import com.hcu.hot6.domain.response.MemberProfileResponse;
import com.hcu.hot6.domain.response.MemberResponse;
import com.hcu.hot6.repository.MemberRepository;
import com.hcu.hot6.service.MemberService;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    @PostMapping("/users")
    public ResponseEntity<MemberResponse> registerMember(
            @AuthenticationPrincipal OAuth2User user, @RequestBody @Valid MemberRequest form) {
        Member member = memberService.updateMember(user.getName(), form);
        return ResponseEntity.ok(new MemberResponse(member));
    }

    @GetMapping("/users/me")
    public ResponseEntity<MemberProfileResponse> getProfile(
            @AuthenticationPrincipal OAuth2User user) {
        Member member = memberRepository.findByEmail(user.getName()).orElseThrow();
        return ResponseEntity.ok(new MemberProfileResponse(member));
    }

    @PutMapping("/users/me")
    public ResponseEntity<MemberResponse> modifyProfile(
            @AuthenticationPrincipal OAuth2User user, @RequestBody MemberRequest form) {
        Member member = memberService.updateMember(user.getName(), form);
        return ResponseEntity.ok(new MemberResponse(member));
    }

    @DeleteMapping("/users/me")
    public ResponseEntity<String> deleteAccount(@AuthenticationPrincipal OAuth2User user) {
        return ResponseEntity.ok(memberService.deleteMember(user.getName()));
    }

    @GetMapping("/users/validation")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestParam String nickname) {
        boolean isPresent = memberService.isPresent(nickname);
        Map<String, Boolean> result = Collections.singletonMap("isPresent", isPresent);

        return ResponseEntity.ok(result);
    }
}
