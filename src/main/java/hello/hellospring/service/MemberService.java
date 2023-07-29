package hello.hellospring.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;


public class MemberService {


    private final MemberRepository memberRepository;
    
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원x
        validateDuplicateMember(member);// 중복 회원 검증
        memberRepository.save(member);
        return member.getId();


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
            // ifPresent : null이 아니면, 값이 있다면
            // Optional이라고 감싸면 Optional 안에 member 객체가 있는것 그래서 Optional 통해서
            // 여러 메소드를 쓴다
            // 과게에는 if null이면 이라고 코딩했으나 이제는 null 가능성이 있다면 Optional로 감싸서 반환
            // 덕분에 ifPresent 와 같은 메소드를 사용할 수 있게 됐다
        });
    }
   
    //전체회원 조회
   public List<Member> findMembers(){
       return memberRepository.findAll();
       
   }
   
   //회원 한명 조회 
   public Optional<Member > findOne(Long memberId){
       return memberRepository.findById(memberId);
   }
   
}
