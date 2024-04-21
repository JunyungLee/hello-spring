package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public  MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 회원 가입
     * **/
    public Long join(Member member) { //rule : 중복 회원 제거 
        //같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { //Method로 따로 추출
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //optional 형태이기 때문에 사용 가능 //Null일 가능성이 있다면 Opitonal 로 감싸서 반환해준다.
           throw new IllegalStateException("이미 존재하는 회원입니다.");
       });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Optional<Member>  findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

