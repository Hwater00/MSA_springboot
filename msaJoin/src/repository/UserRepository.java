package repository;

import entity.User;

import java.util.List;

public interface UserRepository {
    // 레파지토리의 종류를 가리지 않고 클라이언트들이 메서드만 호출하면 같은 정보를 얻을 수 있다는 보장이 된다.

    public List<User> getAllUser();

    public User getUserByUserId(int userId);

}
