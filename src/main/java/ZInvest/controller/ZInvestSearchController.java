package ZInvest.controller;

import ZInvest.domain.dto.*;
import ZInvest.service.DataService;
import ZInvest.service.RegnskapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class ZInvestSearchController{

    private final DataService dataService;
    private final RegnskapService regnskapService;

    @Autowired
    public ZInvestSearchController(DataService dataService, RegnskapService regnskapService) {
        this.dataService = dataService;
        this.regnskapService = regnskapService;
    }

    @CrossOrigin
    @GetMapping("/hentInntekt")
    public List<InntektRequest> hentInntekt(@RequestParam String leilighetId,
                                            @RequestParam String inntektTypeId,
                                            @RequestParam String aar) {
        List<InntektRequest> inntektRequests = dataService.hentInntekt(leilighetId, inntektTypeId, aar);
        return inntektRequests;
    }

    @CrossOrigin
    @GetMapping("/hentInntektRegnskap")
    public List<InntektRequest> hentInntektRegnskap(@RequestParam String leilighetId,
                                                    @RequestParam String aar) {
        List<InntektRequest> inntektRequests = regnskapService.hentInntektRegnskap(leilighetId, aar);
        return inntektRequests;
    }

    @CrossOrigin
    @GetMapping("/hentInntektTyper")
    public List<InntektTypeRequest> hentInntektTyper() {
        return dataService.hentInntektTyper();
    }

    @CrossOrigin
    @GetMapping("/hentUtgift")
    public List<UtgiftRequest> hentUtgift(@RequestParam String leilighetId,
                                           @RequestParam String utgiftTypeId,
                                           @RequestParam String aar) {
        List<UtgiftRequest> utgiftRequests = dataService.hentUtgift(leilighetId, utgiftTypeId, aar);
        return utgiftRequests;
    }

    @CrossOrigin
    @GetMapping("/hentUtgiftTyper")
    public List<UtgiftTypeRequest> hentUtgiftTyper() {
        return dataService.hentUtgiftTyper();
    }

    @CrossOrigin
    @GetMapping("/hentLeiligheter")
    public List<LeilighetRequest> hentLeiligheter() {
        return dataService.hentLeiligheter();
    }
}
