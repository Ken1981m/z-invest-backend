package ZInvest.service;

import ZInvest.domain.dto.InntektFormData;
import ZInvest.domain.Leilighet;
import ZInvest.domain.dto.*;
import ZInvest.repository.ZInvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegnskapService {

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

    public boolean leggTilInntekt(InntektFormData inntektFormData) {
        try {
            return repository.leggTilInntekt(
                    Integer.parseInt(inntektFormData.getInntektTypeId()),
                    Integer.parseInt(inntektFormData.getInntektTypeId()),
                    Integer.parseInt(inntektFormData.getFormatertDato()
                            .substring(0, inntektFormData.getFormatertDato().indexOf("-"))),
                    Integer.parseInt(inntektFormData.getFormatertDato()
                            .substring(inntektFormData.getFormatertDato().indexOf("-")+1)),
                    inntektFormData.getBelop());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean leggTilUtgift(UtgiftFormData utgiftFormData) {
        try {
            return repository.leggTilUtgift(
                    Integer.parseInt(utgiftFormData.getLeilighetId()),
                    Integer.parseInt(utgiftFormData.getUtgiftTypeId()),
                    Integer.parseInt(utgiftFormData.getFormatertDato()
                            .substring(0, utgiftFormData.getFormatertDato().indexOf("-"))),
                    Integer.parseInt(utgiftFormData.getFormatertDato()
                            .substring(utgiftFormData.getFormatertDato().indexOf("-") + 1)),
                    utgiftFormData.getBelop());
        } catch (Exception e) {
            return false;
        }
    }

    public List<InntektTypeRequest> hentInntektTyper() {
        return AssemblerUtil.assembleInntektTypeRequest(repository.hentInntektTyper());
    }

    public List<UtgiftTypeRequest> hentUtgiftTyper() {
        return AssemblerUtil.assembleUtgiftTypeRequest(repository.hentUtgiftTyper());
    }

    public List<LeilighetRequest> hentLeiligheter() {
       return repository.hentLeiligheter().stream().map(leilighet ->
               AssemblerUtil.assembleLeilighetRequest(leilighet)).collect(Collectors.toList());
    }

    public List<InntektRequest> hentInntektRegnskap(String leilighetId, String aar) {
        List<InntektRequest> inntektRequests = AssemblerUtil.assembleInntektRequest(
                repository.hentInntekt(Integer.parseInt(leilighetId), Integer.parseInt(aar)));

        if (inntektRequests.size() > 0) {
            addEmptyRow(inntektRequests);

            Double sumBruttoInntekt = beregnSumInntekt(inntektRequests);
            inntektRequests.add(new InntektRequest.Builder()
                    .label(ConstantsMap.SUM_BRUTTO_INNTEKT.getString())
                    .belop(sumBruttoInntekt)
                    .build());

            addEmptyRow(inntektRequests);

            Double estimertSkatt = sumBruttoInntekt * 0.22;
            inntektRequests.add(new InntektRequest.Builder()
                    .label(ConstantsMap.ESTIMERT_SKATT.getString())
                    .belop(estimertSkatt)
                    .build());

            inntektRequests.add(new InntektRequest.Builder()
                    .label(ConstantsMap.FAKTISK_SKATT.getString())
                    .belop(0.0)
                    .build());

            addEmptyRow(inntektRequests);

            Double estimertNettoInntekt = sumBruttoInntekt - estimertSkatt;
            inntektRequests.add(new InntektRequest.Builder()
                    .label(ConstantsMap.ESTIMERT_NETTO_INNTEKT.getString())
                    .belop(estimertNettoInntekt)
                    .build());

            inntektRequests.add(new InntektRequest.Builder()
                    .label(ConstantsMap.FAKTISK_NETTO_INNTEKT.getString())
                    .belop(0.0)
                    .build());
        }

        return inntektRequests;
    }

    public Double beregnSumInntekt(List<InntektRequest> inntekter) {
        return inntekter.stream().mapToDouble(inntekt -> inntekt.getBelop() != null ? inntekt.getBelop() : 0.0).sum();
    }

    private static void addEmptyRow(List<InntektRequest> inntektRequests) {
        inntektRequests.add(new InntektRequest.Builder()
                .label("")
                .build());
    }


    public void setRepository(ZInvestRepository repository) {
        this.repository = repository;
    }
}
