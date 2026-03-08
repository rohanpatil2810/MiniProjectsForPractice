package in.rohanIT.counsellors.portal.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.rohanIT.counsellors.portal.entity.Counsellar;
import in.rohanIT.counsellors.portal.model.DashboardResponse;
import in.rohanIT.counsellors.portal.service.CounsellorService;

@RestController
@RequestMapping("/api")  // Better base path: /api (industry standard)
public class CounsellorController {  // Better name: CounsellorController

    private final CounsellorService counsellorService;

    // Constructor injection → clean & testable
    public CounsellorController(CounsellorService counsellorService) {
        this.counsellorService = counsellorService;
    }

    // POST /api/register
    @PostMapping("/register")
    public ResponseEntity<String> registerCounsellor(@RequestBody Counsellar counsellar) {
        if (counsellar == null || counsellar.getEmail() == null || counsellar.getPassword() == null) {
            return ResponseEntity.badRequest().body("Invalid registration data");
        }

        String result = counsellorService.registerCounsellar(counsellar);

        if (result.contains("already exists") || result.contains("Invalid")) {
            return ResponseEntity.badRequest().body(result);
        }

        // 201 Created is better for successful resource creation
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // POST /api/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        Counsellar loggedInCounsellor = counsellorService.login(email, password);

        if (loggedInCounsellor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // In real app → return JWT token here instead of full entity
        // For now returning minimal info (never return password!)
        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "counsellorId", loggedInCounsellor.getId(),
                "name", loggedInCounsellor.getName(),
                "email", loggedInCounsellor.getEmail()
        ));
    }

    // GET /api/dashboard/{counsellorId}
    // In real app → use @AuthenticationPrincipal or JWT to get current user
    @GetMapping("/dashboard/{counsellorId}")
    public ResponseEntity<DashboardResponse> getDashboard(@PathVariable Integer counsellorId) {
        if (counsellorId == null || counsellorId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        // Create a dummy Counsellar just for ID (service will validate)
        Counsellar dummy = new Counsellar();
        dummy.setId(counsellorId);

        DashboardResponse dashboard = counsellorService.dashboard(dummy);

        if (dashboard == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dashboard);
    }
}