package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override  //다형성에 의해 MemberRepository가 호출되는게 아니라 MemoryMemberRepository이 호출이 되어 save를 불러옴
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findId(memberId);
    }
}
