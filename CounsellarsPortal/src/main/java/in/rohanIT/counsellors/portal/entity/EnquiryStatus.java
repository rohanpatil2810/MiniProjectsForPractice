package in.rohanIT.counsellors.portal.entity;

public enum EnquiryStatus {
    
    OPEN("Open"),
    IN_PROGRESS("In Progress"),
    FOLLOW_UP("Follow Up"),
    SUCCESS("Success"),
    LOSS("Loss"),
    CLOSED("Closed"),
    INVALID("Invalid");     // optional - for unexpected/old data

    private final String displayName;

    EnquiryStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Optional: helper method to get enum from string (case-insensitive)
    public static EnquiryStatus fromString(String text) {
        if (text == null) {
            return null;
        }
        
        String normalized = text.trim().toUpperCase().replace(" ", "_");
        
        for (EnquiryStatus status : EnquiryStatus.values()) {
            if (status.name().equals(normalized) || 
                status.displayName.equalsIgnoreCase(text.trim())) {
                return status;
            }
        }
        
        // or throw exception / return default
        return EnquiryStatus.INVALID;
        // throw new IllegalArgumentException("Unknown enquiry status: " + text);
    }
}