package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private  static long sequence = 0L; //sequence 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id에 setting하고
        store.put(member.getId(), member); //store에 id 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환될 가능성이 있으면 ofNullable 감싸기
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //name이 가져온 name이랑 같으면 찾기
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values = member들
    }

    public void clearStore() { //내용 clear 처리
        store.clear();
    }

}
