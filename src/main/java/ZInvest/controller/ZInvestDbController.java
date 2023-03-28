package ZInvest.controller;

import ZInvest.domain.InntektType;
import ZInvest.domain.Leilighet;
import ZInvest.domain.dto.InntektTypeRequest;
import ZInvest.domain.dto.LeilighetRequest;
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
    @GetMapping("/hentInntektTyper")
    public List<InntektTypeRequest> hentInntektTyper() {
        List<InntektType> inntektTypeList = repository.hentInntektTyper();
        return AssemblerUtil.assembleInntektTypeRequest(inntektTypeList);
    }

}
