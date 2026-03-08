package in.rohanIT.counsellors.portal.repo;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.rohanIT.counsellors.portal.entity.Counsellar;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellar, Integer>{

	Optional<Counsellar> findById(Integer id);
	Counsellar findByEmail(String email);
}
