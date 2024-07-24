package ZInvest.controller;

import ZInvest.domain.dto.InntektFormData;
import ZInvest.domain.dto.*;
import ZInvest.service.RegnskapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ZInvestDbController {

    RegnskapService regnskapService;

    @Autowired
    public ZInvestDbController(RegnskapService regnskapService) {
        this.regnskapService = regnskapService;
    }

    @CrossOrigin
    @PostMapping("/leggTilLeilighet")
    public boolean leggTilLeilighet(@RequestBody LeilighetRequest leilighetReq) {
        return regnskapService.leggTilLeilighet(leilighetReq);
    }

    @CrossOrigin
    @PostMapping("/leggTilInntekt")
    public boolean leggTilInntekt(@RequestBody InntektFormData inntektFormData) {
        return regnskapService.leggTilInntekt(inntektFormData);
    }

    @CrossOrigin
    @PostMapping("/leggTilUtgift")
    public boolean leggTilUtgift(@RequestBody UtgiftFormData utgiftFormData) {
        return regnskapService.leggTilUtgift(utgiftFormData);
    }

    @CrossOrigin
    @GetMapping("/hentInntektTyper")
    public List<InntektTypeRequest> hentInntektTyper() {
        return regnskapService.hentInntektTyper();
    }

    @CrossOrigin
    @GetMapping("/hentInntektRegnskap")
    public List<InntektRequest> hentInntektRegnskap(@RequestParam String leilighetId,
                                                    @RequestParam String aar) {
        List<InntektRequest> inntektRequests = regnskapService.hentInntektRegnskap(leilighetId, aar);
        return inntektRequests;
    }

    @CrossOrigin
    @GetMapping("/hentUtgiftTyper")
    public List<UtgiftTypeRequest> hentUtgiftTyper() {
        return regnskapService.hentUtgiftTyper();
    }

    @CrossOrigin
    @GetMapping("/hentLeiligheter")
    public List<LeilighetRequest> hentLeiligheter() {
        return regnskapService.hentLeiligheter();
    }

}
