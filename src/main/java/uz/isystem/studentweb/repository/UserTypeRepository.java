package uz.isystem.studentweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.isystem.studentweb.model.UserType;

import java.util.List;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
    List<UserType> findAllByName(String name);

    List<UserType> findAllByDeletedAtNull();
}
