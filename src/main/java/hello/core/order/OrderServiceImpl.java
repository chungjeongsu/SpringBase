package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository;
/**  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();    discountPolicy의 사용되는 구현체를 RateDiscountPolicy로 바꾼다.
   * SRP(구현을 직접 의존주입), OCP(OrderServiceImpl 자체를 변경), DIP(FixDiscountPolicy라는 구현체에도 의존하고 있다.)
 */
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
