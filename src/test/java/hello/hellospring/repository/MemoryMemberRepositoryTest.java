package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { //TEST 케이스 작성하기 (정말 중요한 작업)
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { //clear 처리 필수 -> 각 개별 test 이후 clear 처리 해주기
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); //member1 회원 가입

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);  //member2 회원가입

        //test 진행
        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);


    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member2);

        //test 진행
        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
