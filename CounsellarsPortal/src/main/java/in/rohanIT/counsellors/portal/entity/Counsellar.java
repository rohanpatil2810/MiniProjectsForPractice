package in.rohanIT.counsellors.portal.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "counsellor")  
public class Counsellar {   // ← plan to rename → Counsellor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String contact;

    private String address;   // ← renamed from adrs

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;   // ← renamed from pw – **hash it!**

    @OneToMany(mappedBy = "counsellar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enquiry> enquiries = new ArrayList<>();   // ← collection, not single entity

    // Very useful helper methods (prevents many bugs)
    public void addEnquiry(Enquiry enquiry) {
        enquiries.add(enquiry);
        enquiry.setCounsellar(this);
    }

    public void removeEnquiry(Enquiry enquiry) {
        enquiries.remove(enquiry);
        enquiry.setCounsellar(null);
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Enquiry> getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(List<Enquiry> enquiries) {
		this.enquiries = enquiries;
	}
    
}