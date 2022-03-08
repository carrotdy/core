package hello.core.member;

public interface MemberRepository {

    void save(Member member);      //회원을 저장하는 기능
    Member findId(Long memberId);  //회원의 아이디를 찾는 기능
}
