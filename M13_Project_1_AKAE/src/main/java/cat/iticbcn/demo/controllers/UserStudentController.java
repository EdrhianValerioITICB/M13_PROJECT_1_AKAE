package cat.iticbcn.demo.controllers;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.bean.UserStudent;
import cat.iticbcn.demo.service.OfferService;
import cat.iticbcn.demo.service.UserStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Student Controller", description = "Controller for student users")
public class UserStudentController {

    private UserStudentService userStudentService;

    private OfferService offerService;

    public UserStudentController(UserStudentService userStudentService, OfferService offerService) {
        this.userStudentService = userStudentService;
        this.offerService = offerService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved user offers")
    })
    @Operation(summary = "Find all user offers", description = "Retrieves all offers from a logged user")
    @GetMapping("/student/offers")
    List<Offer> findAllStudentOffers(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Optional<UserStudent> userStudent = userStudentService.findByUsername(username);

        return userStudentService.findAllSelectedOffers(userStudent);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrolled to the offer")
    })
    @Operation(summary = "Enroll to an offer", description = "Enrolls the current user to the offer with a specific id")
    @PostMapping("/student/offers/enroll")
    public ResponseEntity<Optional<Offer>> enrollStudentInOffer(@RequestParam Long offerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<UserStudent> student = userStudentService.findByUsername(username);
        Optional<Offer> offer = offerService.findById(offerId);

        if (student.isPresent() && offer.isPresent()) {
            userStudentService.enrollStudentInOffer(student, offerId);
            return ResponseEntity.ok(offer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
