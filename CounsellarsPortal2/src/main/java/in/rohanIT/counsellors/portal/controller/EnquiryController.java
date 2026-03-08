package in.rohanIT.counsellors.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.rohanIT.counsellors.portal.entity.Enquiry;
import in.rohanIT.counsellors.portal.service.EnquiryService;

@RestController
@RequestMapping("/api/enquiry")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @PostMapping("/add/{counsellorId}")
    public ResponseEntity<String> addEnquiry(@RequestBody Enquiry enquiry,
                                             @PathVariable int counsellorId) {

        boolean saved = enquiryService.saveEnquiry(enquiry, counsellorId);

        if(saved) {
            return ResponseEntity.ok("Enquiry Added Successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to Add Enquiry");
        }
    }
}