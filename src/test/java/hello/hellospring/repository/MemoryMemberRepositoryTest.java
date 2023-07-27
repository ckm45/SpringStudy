package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {
    
    MemberRepository repository = new MemoryMemberRepository();
    
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
        repository.save(member1);
        
        
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        
        Member result = repository.findByName("spring2").get();
        
        assertThat(result).isEqualTo(member1);
        
        
    }
}