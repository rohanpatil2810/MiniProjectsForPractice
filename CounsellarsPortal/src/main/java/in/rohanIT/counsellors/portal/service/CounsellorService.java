package in.rohanIT.counsellors.portal.service;

import org.springframework.stereotype.Service;

import in.rohanIT.counsellors.portal.entity.Counsellar;
import in.rohanIT.counsellors.portal.model.DashboardResponse;

@Service
public interface CounsellorService {
	
	abstract String registerCounsellar(Counsellar counsellar);
	
	abstract  Counsellar login(String email, String Pw);
	
	abstract DashboardResponse dashboard(Counsellar counsellar);

}
