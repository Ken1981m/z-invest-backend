package ZInvest.controller;

import ZInvest.domain.dto.*;
import ZInvest.service.DataService;
import ZInvest.service.RegnskapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persist")
public class ZInvestPersistenceController {

    private final DataService dataService;
    private final RegnskapService regnskapService;

    @Autowired
    public ZInvestPersistenceController(DataService dataService, RegnskapService regnskapService) {
        this.dataService = dataService;
        this.regnskapService = regnskapService;
    }

    @CrossOrigin
    @PostMapping("/leggTilLeilighet")
    public boolean leggTilLeilighet(@RequestBody LeilighetRequest leilighetReq) {
        return dataService.leggTilLeilighet(leilighetReq);
    }

    @CrossOrigin
    @PostMapping("/leggTilInntektType")
    public boolean leggTilInntektType(@RequestBody InntektTypeFormData inntektTypeFormData) {
        return dataService.leggTilInntektType(inntektTypeFormData);
    }

    @CrossOrigin
    @PostMapping("/oppdaterInntektType")
    public boolean oppdaterInntektType(@RequestBody InntektTypeFormData inntektTypeFormData) {
        return dataService.oppdaterInntektType(inntektTypeFormData);
    }

    @CrossOrigin
    @PostMapping("/slettInntektType")
    public boolean slettInntektType(@RequestParam Integer id) {
        return dataService.slettInntektType(id);
    }

    @CrossOrigin
    @PostMapping("/leggTilInntekt")
    public boolean leggTilInntekt(@RequestBody InntektFormData inntektFormData) {
        return dataService.leggTilInntekt(inntektFormData);
    }

    @CrossOrigin
    @PostMapping("/oppdaterInntekt")
    public boolean oppdaterInntekt(@RequestBody InntektFormData inntektFormData) {
        return dataService.oppdaterInntekt(inntektFormData);
    }

    @CrossOrigin
    @PostMapping("/slettInntekt")
    public boolean slettInntekt(@RequestParam Integer id) {
        return dataService.slettInntekt(id);
    }

    @CrossOrigin
    @PostMapping("/leggTilUtgiftType")
    public boolean leggTilUtgiftType(@RequestBody UtgiftTypeFormData utgiftTypeFormData) {
        return dataService.leggTilUtgiftType(utgiftTypeFormData);
    }

    @CrossOrigin
    @PostMapping("/oppdaterUtgiftType")
    public boolean oppdaterUtgiftType(@RequestBody UtgiftTypeFormData utgiftTypeFormData) {
        return dataService.oppdaterUtgiftType(utgiftTypeFormData);
    }

    @CrossOrigin
    @PostMapping("/slettUtgiftType")
    public boolean slettUtgiftType(@RequestParam Integer id) {
        return dataService.slettUtgiftType(id);
    }

    @CrossOrigin
    @PostMapping("/leggTilUtgift")
    public boolean leggTilUtgift(@RequestBody UtgiftFormData utgiftFormData) {
        return dataService.leggTilUtgift(utgiftFormData);
    }

    @CrossOrigin
    @PostMapping("/oppdaterUtgift")
    public boolean oppdaterUtgift(@RequestBody UtgiftFormData utgiftFormData) {
        return dataService.oppdaterUtgift(utgiftFormData);
    }

    @CrossOrigin
    @PostMapping("/slettUtgift")
    public boolean slettUtgift(@RequestParam Integer id) {
        return dataService.slettUtgift(id);
    }
}
