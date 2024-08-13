package ZInvest.service;

import ZInvest.domain.Leilighet;
import ZInvest.domain.UtgiftType;
import ZInvest.domain.dto.*;
import ZInvest.repository.ZInvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Autowired
    ZInvestRepository repository;

    public boolean leggTilLeilighet(LeilighetRequest leilighetReq) {
        try {
            Leilighet leilighet = AssemblerUtil.assembleLeilighet(leilighetReq);
            return repository.leggTilLeilighet(leilighet);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean leggTilInntektType(InntektTypeFormData inntektTypeFormData) {
        try {
            return repository.leggtilInntektType(inntektTypeFormData);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean oppdaterInntektType(InntektTypeFormData inntektTypeFormData) {
        try {
            return repository.oppdaterInntektType(inntektTypeFormData);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean slettInntektType(Integer id) {
        try {
            return repository.slettInntektType(id);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean leggTilInntekt(InntektFormData inntektFormData) {
        try {
            return repository.leggTilInntekt(inntektFormData);
        } catch (Exception e) {
            return false;
        }
    }
    public boolean oppdaterInntekt(InntektFormData inntektFormData) {
        try {
            return repository.oppdaterInntekt(inntektFormData);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean slettInntekt(Integer id) {
        try {
            return repository.slettInntekt(id);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean leggTilUtgiftType(UtgiftTypeFormData utgiftTypeFormData) {
        try {
            return repository.leggTilUtgiftType(utgiftTypeFormData);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean oppdaterUtgiftType(UtgiftTypeFormData utgiftTypeFormData) {
        try {
            return repository.oppdaterUtgiftType(utgiftTypeFormData);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean slettUtgiftType(Integer id) {
        try {
            return repository.slettUtgiftType(id);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean leggTilUtgift(UtgiftFormData utgiftFormData) {
        try {
            if (utgiftFormData.getMnd() != null && utgiftFormData.getMnd().intValue() == 13) {
                UtgiftType utgiftType = repository.hentUtgiftType(Integer.parseInt(utgiftFormData.getUtgiftTypeId()));
                if (utgiftType.isMndUavhengig()) {
                    return repository.leggTilUtgiftUtenMnd(utgiftFormData);
                }
            } else {
                return repository.leggTilUtgift(utgiftFormData);
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public boolean oppdaterUtgift(UtgiftFormData utgiftFormData) {
        try {
            return repository.oppdaterUtgift(utgiftFormData);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean slettUtgift(Integer id) {
        try {
            return repository.slettUtgift(id);
        } catch (Exception e) {
            return false;
        }
    }

    public List<InntektRequest> hentInntekt(String leilighetId, String inntektTypeId, String aar) {
        return AssemblerUtil.assembleInntektRequest(repository.hentInntekt(
                Integer.parseInt(leilighetId),
                Integer.parseInt(inntektTypeId),
                Integer.parseInt(aar)));
    }

    public List<InntektTypeRequest> hentInntektTyper() {
        return AssemblerUtil.assembleInntektTypeRequest(repository.hentInntektTyper());
    }

    public List<UtgiftRequest> hentUtgift(String leilighetId, Integer utgiftTypeId, String aar) {
        return AssemblerUtil.assembleUtgiftRequest(repository.hentUtgift(
                Integer.parseInt(leilighetId),
                utgiftTypeId,
                Integer.parseInt(aar)));
    }

    public List<UtgiftTypeRequest> hentUtgiftTyper() {
        return AssemblerUtil.assembleUtgiftTypeRequest(repository.hentUtgiftTyper());
    }

    public List<LeilighetRequest> hentLeiligheter() {
        return repository.hentLeiligheter().stream().map(leilighet ->
                AssemblerUtil.assembleLeilighetRequest(leilighet)).collect(Collectors.toList());
    }

    public String hentLeilighetNavn(String leilighetId) {
        return repository.hentLeilighet(Integer.parseInt(leilighetId)).getNavn();
    }

    public void setRepository(ZInvestRepository repository) {
        this.repository = repository;
    }

}
