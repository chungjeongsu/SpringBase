package hello.core.member;

public class MemberServiceImpl implements MemberService {
     private final MemberRepository memberRepository = new MemoryMemberRepository();
     /**  인터페이스와 구현까지 의존하고 있음
     *  인터페이스의 구현체가 바뀔 때, 구현체와 관계없는 이 클래스 또한 바뀌어야 함
     *  MemoryMemberRepository를 생성해주고, 주입해주니, SRP 원칙에 맞지 않다.
     */


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
