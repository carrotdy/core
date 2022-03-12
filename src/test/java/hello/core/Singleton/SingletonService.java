package hello.core.Singleton;

public class SingletonService {

    //static final:변하지 않는다는 의미는 final에 의해서 적용시킬 수 있고, static은 객체마다 가질 필요가 없는 공용으로 사용하는 필드 혹은 인스턴스 필드를 포함하지 않는 메소드
    //1.static 영역에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    //2.public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance(){
        return instance;
    }

    //3.생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
