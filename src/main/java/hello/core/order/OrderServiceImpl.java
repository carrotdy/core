package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository ;
    private final DiscountPolicy discountPolicy;      //인터페이스에만 의존하도록 설계

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    DiscountPolicy 뿐만아니라 구체 클래스 FixDiscountPolicy도 의존하고 있음 -> DIP위반
    FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl 코드도 변경해야 하므로 -> OCP위반
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findId(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
