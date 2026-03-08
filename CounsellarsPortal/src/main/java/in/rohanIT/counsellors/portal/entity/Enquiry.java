package in.rohanIT.counsellors.portal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "enquiry")     // ← suggestion: use lowercase + no "_table" suffix
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;          // ← prefer private + getter/setter

    private String comment;
    private String studentName;
    private String status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="counsellor_id")
    Counsellar counsellar;
    
    

    public Counsellar getCounsellar() {
		return counsellar;
	}

	public void setCounsellar(Counsellar counsellar) {
		this.counsellar = counsellar;
	}

	// Constructors (optional but useful)
    public Enquiry() {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}