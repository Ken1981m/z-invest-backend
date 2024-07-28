package ZInvest.service;

import ZInvest.domain.dto.*;
import ZInvest.repository.ZInvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegnskapService {

    @Autowired
    ZInvestRepository repository;


    public List<InntektRequest> hentInntektRegnskap(String leilighetId, String aar) {
        List<InntektRequest> inntektRequests = AssemblerUtil.assembleInntektRequest(
                repository.hentInntekt(Integer.parseInt(leilighetId), Integer.parseInt(aar)));

        if (inntektRequests.size() > 0) {
            int index = inntektRequests.stream()
                    .mapToInt(InntektRequest::getId)
                    .max().orElse(1) + 1;

            addEmptyRow(inntektRequests, index++);

            Double sumBruttoInntekt = beregnSumInntekt(inntektRequests);
            inntektRequests.add(new InntektRequest.Builder()
                    .id(index++)
                    .label(ConstantsMap.SUM_BRUTTO_INNTEKT.getString())
                    .belop(sumBruttoInntekt)
                    .build());

            addEmptyRow(inntektRequests, index++);

            Double estimertSkatt = sumBruttoInntekt * 0.22;
            inntektRequests.add(new InntektRequest.Builder()
                    .id(index++)
                    .label(ConstantsMap.ESTIMERT_SKATT.getString())
                    .belop(estimertSkatt)
                    .build());

            inntektRequests.add(new InntektRequest.Builder()
                    .id(index++)
                    .label(ConstantsMap.FAKTISK_SKATT.getString())
                    .belop(0.0)
                    .build());

            addEmptyRow(inntektRequests, index++);

            Double estimertNettoInntekt = sumBruttoInntekt - estimertSkatt;
            inntektRequests.add(new InntektRequest.Builder()
                    .id(index++)
                    .label(ConstantsMap.ESTIMERT_NETTO_INNTEKT.getString())
                    .belop(estimertNettoInntekt)
                    .build());

            inntektRequests.add(new InntektRequest.Builder()
                    .id(index++)
                    .label(ConstantsMap.FAKTISK_NETTO_INNTEKT.getString())
                    .belop(0.0)
                    .build());
        }

        return inntektRequests;
    }

    public Double beregnSumInntekt(List<InntektRequest> inntekter) {
        return inntekter.stream().mapToDouble(inntekt -> inntekt.getBelop() != null ? inntekt.getBelop() : 0.0).sum();
    }

    private static void addEmptyRow(List<InntektRequest> inntektRequests, int index) {
        inntektRequests.add(new InntektRequest.Builder()
                .id(index)
                .label("")
                .build());
    }


    public void setRepository(ZInvestRepository repository) {
        this.repository = repository;
    }

}
