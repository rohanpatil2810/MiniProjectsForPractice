package in.rohanIT.counsellors.portal.service;

import java.util.List;

import in.rohanIT.counsellors.portal.entity.Enquiry;

public interface EnquiryService {

	 abstract boolean saveEnquiry(Enquiry enquiry, int counsellorId);
	 abstract List<Enquiry> getAllEnquiries();
	 
	
}
