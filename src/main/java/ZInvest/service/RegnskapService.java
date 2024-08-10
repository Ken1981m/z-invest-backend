package ZInvest.service;

import ZInvest.domain.dto.*;
import ZInvest.repository.ZInvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class RegnskapService {

    @Autowired
    ZInvestRepository repository;

    @Autowired
    DataService dataService;


    public List<InntektRequest> hentInntektRegnskapForFlereAar(String leilighetId, String aarListe) {
        String[] aarArray = aarListe.split(";");

        List<InntektRequest> aktuelleAarList = new ArrayList<>();

        List<InntektRequest> inntektRegnskap = hentInntektRegnskap(leilighetId, aarArray[0]);
        if (!inntektRegnskap.isEmpty()) {
            aktuelleAarList.add(getAarRad(aarArray[0]));
        }

        inntektRegnskap.forEach(inntektRequest -> {
            if (inntektRequest.getBelopList() == null) {
                inntektRequest.setBelopList(new ArrayList<>());
            }
            inntektRequest.getBelopList().add(inntektRequest.getBelop());
        });

        for (int i=1; i< aarArray.length; i++) {
            List<InntektRequest> hentetRegnskap = hentInntektRegnskap(leilighetId, aarArray[i]);
            assembleInntektRegnskap(inntektRegnskap, hentetRegnskap);

            if (!hentetRegnskap.isEmpty()) {
                aktuelleAarList.add(getAarRad(aarArray[i]));
            }
        }

        List<InntektRequest> finalInntektRegnskap = new ArrayList<>(aktuelleAarList);
        finalInntektRegnskap.addAll(inntektRegnskap);

        List<InntektRequest> finalInntektRegnskapWithAarListeForUtgifter = populerAarListeForUtgifter(finalInntektRegnskap);

        return finalInntektRegnskapWithAarListeForUtgifter;
    }

    private List<InntektRequest> populerAarListeForUtgifter(List<InntektRequest> finalInntektRegnskap) {
        List<String> alleAarMedData = finalInntektRegnskap
                .stream()
                .map(InntektRequest::getAar)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        finalInntektRegnskap.forEach(inntektRequest -> {
            if (ConstantsMap.SUM_UTGIFTER.getString().equals(inntektRequest.getLabel())) {
                inntektRequest.setAarList(alleAarMedData);
            }
        });

        return finalInntektRegnskap;
    }

    private void assembleInntektRegnskap(List<InntektRequest> inntektRegnskap, List<InntektRequest> hentetRegnskap) {
        if (inntektRegnskap.isEmpty()) {
            inntektRegnskap.addAll(hentetRegnskap);
        }

        inntektRegnskap.forEach(inntektRequest -> {
            hentetRegnskap.forEach(hentetData -> {
                if (inntektRequest.getLabel().equals(hentetData.getLabel())) {
                    if (inntektRequest.getBelopList() == null) {
                        inntektRequest.setBelopList(new ArrayList<>());
                    }
                    inntektRequest.getBelopList().add(hentetData.getBelop());
                }
            });
        });
    }

    public List<UtgiftRequest> hentUtgiftRegnskap(String leilighetId, String aar) {
        List<UtgiftRequest> utgiftRegnskap = new ArrayList<>();

        List<UtgiftRequest> utgiftRequests = AssemblerUtil.assembleUtgiftRequest(
                repository.hentUtgift(Integer.parseInt(leilighetId), Integer.parseInt(aar))
        );

        if (!utgiftRequests.isEmpty()) {
            AtomicInteger index = new AtomicInteger(1);

            List<String> utgiftTypeNavnListe = utgiftRequests.stream()
                    .map(UtgiftRequest::getUtgiftTypeNavn)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            utgiftTypeNavnListe.forEach(utgiftTypeNavn -> {
                Double beregnetSumUtgift = beregnSumUtgifterPerUtgiftType(utgiftRequests, utgiftTypeNavn);
                utgiftRegnskap.add(new UtgiftRequest.Builder()
                        .id(index.getAndIncrement())
                        .label(utgiftTypeNavn)
                        .belop(beregnetSumUtgift)
                        .build());
            });
        }

        List<UtgiftRequest> finalUtgiftRegnskap = populerMedUtgiftDetaljer(utgiftRegnskap, utgiftRequests);

        return finalUtgiftRegnskap;
    }

    private List<UtgiftRequest> populerMedUtgiftDetaljer(
            List<UtgiftRequest> utgiftRegnskap,
            List<UtgiftRequest> utgiftRequests) {

        utgiftRegnskap.forEach(utgift -> {
            List<UtgiftRequest> detaljer = new ArrayList<>();
            utgiftRequests.forEach(utgiftRequest -> {
                if(utgift.getLabel().equals(utgiftRequest.getUtgiftTypeNavn())) {
                    detaljer.add(utgiftRequest);
                }
            });
            utgift.setUtgiftDetaljer(AssemblerUtil.assembleUtgiftDetalj(detaljer));
        });

        return utgiftRegnskap;
    }


    protected List<InntektRequest> hentInntektRegnskap(String leilighetId, String aar) {
        List<InntektRequest> inntektRequests = AssemblerUtil.assembleInntektRequest(
                repository.hentInntekt(Integer.parseInt(leilighetId), Integer.parseInt(aar)));

        List<UtgiftRequest> utgiftRequests = AssemblerUtil.assembleUtgiftRequest(
                repository.hentUtgift(Integer.parseInt(leilighetId), Integer.parseInt(aar))
        );

        if (!inntektRequests.isEmpty()) {
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

            Double alleUtgifter = beregnSumUtgifter(utgiftRequests);
            inntektRequests.add(new InntektRequest.Builder()
                    .id(index++)
                    .label(ConstantsMap.SUM_UTGIFTER.getString())
                    .belop(alleUtgifter)
                    .build());

            inntektRequests.add(new InntektRequest.Builder()
                    .id(index++)
                    .label(ConstantsMap.SUM_BRUTTO_INNTEKT_MINUS_ALLE_UTGIFTER.getString())
                    .belop(sumBruttoInntekt - alleUtgifter)
                    .build());

            addEmptyRow(inntektRequests, index++);

            Double estimertSkatt = (sumBruttoInntekt - alleUtgifter)  * 0.22;
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

            Double estimertNettoInntekt = sumBruttoInntekt - alleUtgifter - estimertSkatt;
            inntektRequests.add(new InntektRequest.Builder()
                    .id(index++)
                    .label(ConstantsMap.ESTIMERT_NETTO_INNTEKT.getString())
                    .belop(rundeAvBelop(estimertNettoInntekt))
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

    public Double beregnSumUtgifter(List<UtgiftRequest> utgifter) {
        return utgifter.stream().mapToDouble(utgift -> utgift.getBelop() != null ? utgift.getBelop() : 0.0).sum();
    }

    public Double beregnSumUtgifterPerUtgiftType(List<UtgiftRequest> utgifter, String utgiftTypeNavn) {
        return utgifter.stream()
                .filter(utgiftRequest -> utgiftRequest.getUtgiftTypeNavn().equals(utgiftTypeNavn))
                .mapToDouble(utgift -> utgift.getBelop() != null ? utgift.getBelop() : 0.0).sum();
    }

    private static void addEmptyRow(List<InntektRequest> inntektRequests, int index) {
        inntektRequests.add(new InntektRequest.Builder()
                .id(index)
                .label("")
                .build());
    }

    private InntektRequest getAarRad(String aar) {
        return new InntektRequest.Builder()
                .aar(aar)
                .build();
    }

    public Double rundeAvBelop(Double value) {
        return Math.round(value * 100.0) / 100.0;
    }


    public void setRepository(ZInvestRepository repository) {
        this.repository = repository;
    }

}
