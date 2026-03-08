package in.rohanIT.counsellors.portal.serviceimpl;

import in.rohanIT.counsellors.portal.entity.Counsellar;
import in.rohanIT.counsellors.portal.entity.Enquiry;
import in.rohanIT.counsellors.portal.model.DashboardResponse;
import in.rohanIT.counsellors.portal.repo.CounsellorRepo;
import in.rohanIT.counsellors.portal.repo.EnquiryRepo;
import in.rohanIT.counsellors.portal.service.CounsellorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounserllorServiceImpl implements CounsellorService {   // ← rename to CounsellorServiceImpl

    private final CounsellorRepo counsellorRepo;
    private final EnquiryRepo enquiryRepo;

    // Constructor injection (best practice)
    public CounserllorServiceImpl(CounsellorRepo counsellorRepo, EnquiryRepo enquiryRepo) {
        this.counsellorRepo = counsellorRepo;
        this.enquiryRepo = enquiryRepo;
    }

    @Override
    public String registerCounsellar(Counsellar counsellar) {
        if (counsellar == null || counsellar.getEmail() == null) {
            return "Invalid data";
        }
        if (counsellorRepo.findByEmail(counsellar.getEmail()) != null) {
            return "Email already exists";
        }
        // TODO: hash password here (see previous messages about BCryptPasswordEncoder)
        // counsellar.setPassword(passwordEncoder.encode(counsellar.getPassword()));
        List<Enquiry> enquiries = counsellar.getEnquiries();

        if(enquiries != null) {
            for(Enquiry e : enquiries) {
                e.setCounsellar(counsellar);
            }
        }
        counsellorRepo.save(counsellar);
        return "Registration Successful";
    }

    @Override
    public Counsellar login(String email, String pw) {
        Counsellar counsellar = counsellorRepo.findByEmail(email);
        if (counsellar != null) {
            // TODO: use passwordEncoder.matches(pw, counsellar.getPassword())
            if (counsellar.getPassword().equals(pw)) {   // temporary – unsafe
                return counsellar;
            }
        }
        return null;
    }

    @Override
    public DashboardResponse dashboard(Counsellar counsellar) {
        if (counsellar == null || counsellar.getId() == null) {
            throw new IllegalArgumentException("Counsellor required");
        }

        // Better: use Optional properly or orElseThrow
        Counsellar counsellarFromDB = counsellorRepo.findById(counsellar.getId())
                .orElseThrow(() -> new RuntimeException("Counsellor not found"));

        // Use the correct query method (adjust name after you rename field)
        List<Enquiry> enquiries = enquiryRepo.findByCounsellarId(counsellarFromDB.getId());

        int totalEnquiries = enquiries.size();

        long successCount = enquiries.stream()
                .filter(e -> "Success".equalsIgnoreCase(e.getStatus()))
                .count();

        long lossCount = enquiries.stream()
                .filter(e -> "Loss".equalsIgnoreCase(e.getStatus()))
                .count();

        DashboardResponse response = new DashboardResponse();
        response.setTotalEnquiries(totalEnquiries);
        // Assuming you have fields for success/loss – adjust names accordingly
         response.setOpenEnquiries((int) successCount);
         response.setClosedEnquiries((int) lossCount);

        return response;
    }
}