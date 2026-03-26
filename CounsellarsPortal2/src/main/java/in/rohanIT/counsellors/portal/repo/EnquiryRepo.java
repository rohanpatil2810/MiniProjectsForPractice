package in.rohanIT.counsellors.portal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.rohanIT.counsellors.portal.entity.Enquiry;

public interface EnquiryRepo extends JpaRepository<Enquiry, Integer>{

//	List<Enquiry> findByCounsellar(int id);

	List<Enquiry> findByCounsellarId(Integer id);
}
