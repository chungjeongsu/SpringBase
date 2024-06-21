package hello.core.singleton;

public class SingletonService {
    //자기 자신을 static에 올린다.
    private static final SingletonService instance = new SingletonService();

    //조회
    public static SingletonService getInstance() {
        return instance;
    }

    //싱글톤을 보장 자기 클래스 이외에서는 자기 자신을 생성할 수 없다.
    private SingletonService(){}

    //로직 사용
    public void logic(){
        System.out.printf("싱글톤 객체 로직 호출");
    }

    public static void main(String[] args) {

    }

}
