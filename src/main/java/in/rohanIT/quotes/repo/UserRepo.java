package in.rohanIT.quotes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.rohanIT.quotes.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);

}
