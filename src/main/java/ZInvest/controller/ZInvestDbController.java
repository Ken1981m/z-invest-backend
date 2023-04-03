package ZInvest.controller;

import ZInvest.domain.Inntekt;
import ZInvest.domain.InntektType;
import ZInvest.domain.Leilighet;
import ZInvest.domain.UtgiftType;
import ZInvest.domain.dto.InntektRequest;
import ZInvest.domain.dto.InntektTypeRequest;
import ZInvest.domain.dto.LeilighetRequest;
import ZInvest.domain.dto.UtgiftTypeRequest;
import ZInvest.repository.ZInvestRepository;
import ZInvest.service.AssemblerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ZInvestDbController {

    @Autowired
    ZInvestRepository repository;


    @CrossOrigin
    @PostMapping("/leggTilLeilighet")
    public ResponseEntity<String> leggTilLeilighet(@RequestBody LeilighetRequest leilighetReq) {
        Leilighet leilighet = AssemblerUtil.assembleLeilighet(leilighetReq);
        repository.leggTilLeilighet(leilighet);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PostMapping("/leggTilInntekt")
    public ResponseEntity<String> leggTilInntekt(@RequestParam String leilighetId,
                                                 @RequestParam String inntektTypeId,
                                                 @RequestParam String formatertDato,
                                                 @RequestParam Double belop) {
        repository.leggTilInntekt(Integer.parseInt(leilighetId),
                                  Integer.parseInt(inntektTypeId),
                                  formatertDato.substring(0, formatertDato.indexOf("-")),
                                  formatertDato.substring(formatertDato.indexOf("-")+1),
                                  belop);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PostMapping("/leggTilUtgift")
    public ResponseEntity<String> leggTilUtgift(@RequestParam String leilighetId,
                                                 @RequestParam String utgiftTypeId,
                                                 @RequestParam String formatertDato,
                                                 @RequestParam Double belop) {
        repository.leggTilUtgift(Integer.parseInt(leilighetId),
                Integer.parseInt(utgiftTypeId),
                formatertDato.substring(0, formatertDato.indexOf("-")),
                formatertDato.substring(formatertDato.indexOf("-")+1),
                belop);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/hentInntektTyper")
    public List<InntektTypeRequest> hentInntektTyper() {
        List<InntektType> inntektTypeList = repository.hentInntektTyper();
        return AssemblerUtil.assembleInntektTypeRequest(inntektTypeList);
    }

    @CrossOrigin
    @GetMapping("/hentInntekt")
    public List<InntektRequest> hentInntekt(@RequestParam String leilighetId,
                                            @RequestParam String aar) {
        List<Inntekt> inntektList = repository.hentInntekt(Integer.parseInt(leilighetId), Integer.parseInt(aar));
        return AssemblerUtil.assembleInntektRequest(inntektList);
    }

    @CrossOrigin
    @GetMapping("/hentUtgiftTyper")
    public List<UtgiftTypeRequest> hentUtgiftTyper() {
        List<UtgiftType> utgiftTypeList = repository.hentUtgiftTyper();
        return AssemblerUtil.assembleUtgiftTypeRequest(utgiftTypeList);
    }

    @CrossOrigin
    @GetMapping("/hentLeiligheter")
    public List<LeilighetRequest> hentLeiligheter() {
        List<Leilighet> leilighetList = repository.hentLeiligheter();
        return AssemblerUtil.assembleLeilighetRequest(leilighetList);
    }

}
