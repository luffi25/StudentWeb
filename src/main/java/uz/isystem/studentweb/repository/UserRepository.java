package uz.isystem.studentweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.isystem.studentweb.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByUserIdAndDeletedAtNull(Long userId);

    Optional<User> findByUserIdAndPhoneAndDeletedAtIsNull(Long userId, String phone);

    Optional<User> findByIdAndDeletedAtNull(Integer userId);

    List<User> findAllByDeletedAtIsNull();

    @Query(value = "FROM User where deletedAt is null and id = :id")
    @Transactional
    Optional<User> findUser(@Param("id") Integer id);

    Optional<User> findByPhoneAndDeletedAtIsNull(String phone);

    Optional<User> findByPasswordAndPhone(String password, String phone);

    @Query("from User where password = :password and phone = :phone")
    @Transactional
    Optional<User> authorize(@Param("password") String password, @Param("phone") String phone);

}
