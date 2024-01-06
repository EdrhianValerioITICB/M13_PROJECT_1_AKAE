package cat.iticbcn.demo.controllers;

import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.bean.UserStudent;
import cat.iticbcn.demo.service.OfferService;
import cat.iticbcn.demo.service.UserStudentService;
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
public class UserStudentController {

    private UserStudentService userStudentService;

    private OfferService offerService;

    public UserStudentController(UserStudentService userStudentService, OfferService offerService) {
        this.userStudentService = userStudentService;
        this.offerService = offerService;
    }

    @GetMapping("/student/offers")
    List<Offer> findAllStudentOffers(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        Optional<UserStudent> userStudent = userStudentService.findByUsername(username);

        return userStudentService.findAllSelectedOffers(userStudent);
    }

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
