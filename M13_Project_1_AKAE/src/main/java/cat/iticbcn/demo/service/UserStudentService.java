package cat.iticbcn.demo.service;

import cat.iticbcn.demo.Exception.CompanyNotFoundException;
import cat.iticbcn.demo.bean.Offer;
import cat.iticbcn.demo.bean.UserAuthority;
import cat.iticbcn.demo.bean.UserStudent;
import cat.iticbcn.demo.dto.UserRegisterDTO;
import cat.iticbcn.demo.repository.UserStudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserStudentService {

    private final UserStudentRepository repository;

    private final OfferService offerService;
    private final PasswordEncoder passwordEncoder;

    public UserStudentService(UserStudentRepository repository, OfferService offerService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.offerService = offerService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Offer> findAllSelectedOffers(Optional<UserStudent> userStudent){
        return userStudent.map(UserStudent::getOffers).orElseThrow(() -> new CompanyNotFoundException(1L));
    }

    public Optional<UserStudent> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    public UserStudent save(UserRegisterDTO userDTO) {
        UserStudent user = new UserStudent(
                null,
                userDTO.username(),
                passwordEncoder.encode(userDTO.password()),
                userDTO.email(),
                List.of(UserAuthority.READ),
                Collections.emptyList()
        );
        return this.repository.save(user);
    }

    public Optional<Offer> enrollStudentInOffer(Optional<UserStudent> studentOptional, Long offerId) {
        Optional<Offer> optionalOffer = offerService.findById(offerId);
        if (optionalOffer.isPresent()) {
            UserStudent student = studentOptional.get();
            Offer offer = optionalOffer.get();

            if (!student.getOffers().contains(offer)) {
                student.getOffers().add(offer);
                repository.save(student);
            }
        }
        return optionalOffer;
    }
}
