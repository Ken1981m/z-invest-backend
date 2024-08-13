package ZInvest.controller;

import ZInvest.domain.dto.*;
import ZInvest.service.DataService;
import ZInvest.service.RegnskapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<InntektRegnskapRequest> hentInntektRegnskap(@RequestParam String leilighetIds,
                                                    @RequestParam String aarliste) {
        List<InntektRegnskapRequest> inntektRegnskapRequest = regnskapService.hentInntektRegnskapForFlereAar(leilighetIds, aarliste);
        return inntektRegnskapRequest;
    }

    @CrossOrigin
    @GetMapping("/hentUtgiftRegnskap")
    public Map<String, List<UtgiftRegnskapRequest>> hentUtgiftRegnskap(@RequestParam String leilighetIds,
                                                  @RequestParam String aar) {
        Map<String, List<UtgiftRegnskapRequest>> utgiftRegnskap = regnskapService.hentUtgiftRegnskap(leilighetIds, aar);
        return utgiftRegnskap;
    }

    @CrossOrigin
    @GetMapping("/hentInntektTyper")
    public List<InntektTypeRequest> hentInntektTyper() {
        return dataService.hentInntektTyper();
    }

    @CrossOrigin
    @GetMapping("/hentUtgift")
    public List<UtgiftRequest> hentUtgift(@RequestParam String leilighetId,
                                           @RequestParam Integer utgiftTypeId,
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
