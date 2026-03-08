package in.rohanIT.counsellors.portal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.rohanIT.counsellors.portal.entity.Counsellar;
import in.rohanIT.counsellors.portal.entity.Enquiry;
import in.rohanIT.counsellors.portal.repo.CounsellorRepo;
import in.rohanIT.counsellors.portal.repo.EnquiryRepo;
import in.rohanIT.counsellors.portal.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    @Autowired
    private CounsellorRepo counsellorRepo;

    @Autowired
    private EnquiryRepo enquiryRepo;

    @Override
    public boolean saveEnquiry(Enquiry enquiry, int counsellorId) {

        Counsellar counsellar = counsellorRepo.findById(counsellorId).orElse(null);

        if (counsellar != null) {

            enquiry.setCounsellar(counsellar); // VERY IMPORTANT

            Enquiry saved = enquiryRepo.save(enquiry);

            return saved != null;
        }

        return false;
    }

    @Override
    public List<Enquiry> getAllEnquiries() {
        return enquiryRepo.findAll();
    }
}