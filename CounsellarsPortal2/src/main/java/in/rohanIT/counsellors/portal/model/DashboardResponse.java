package in.rohanIT.counsellors.portal.model;

public class DashboardResponse {
	
	Integer totalEnquiries;
	Integer openEnquiries;
	Integer ClosedEnquiries;
	public Integer getTotalEnquiries() {
		return totalEnquiries;
	}
	public void setTotalEnquiries(Integer totalEnquiries) {
		this.totalEnquiries = totalEnquiries;
	}
	public Integer getOpenEnquiries() {
		return openEnquiries;
	}
	public void setOpenEnquiries(Integer openEnquiries) {
		this.openEnquiries = openEnquiries;
	}
	public Integer getClosedEnquiries() {
		return ClosedEnquiries;
	}
	public void setClosedEnquiries(Integer closedEnquiries) {
		ClosedEnquiries = closedEnquiries;
	}
	

}
