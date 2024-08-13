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


    public List<InntektRegnskapRequest> hentInntektRegnskapForFlereAar(String leilighetIds, String aarListe) {
        Integer[] leilighetIdArray = Arrays.stream(leilighetIds.split(","))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
        String[] aarArray = aarListe.split(";");

        List<InntektRegnskapRequest> aktuelleAarList = new ArrayList<>();

        List<InntektRegnskapRequest> inntektRegnskap = hentInntektRegnskap(leilighetIdArray, aarArray[0]);
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
            List<InntektRegnskapRequest> hentetRegnskap = hentInntektRegnskap(leilighetIdArray, aarArray[i]);
            assembleInntektRegnskap(inntektRegnskap, hentetRegnskap);

            if (!hentetRegnskap.isEmpty()) {
                aktuelleAarList.add(getAarRad(aarArray[i]));
            }
        }

        List<InntektRegnskapRequest> finalInntektRegnskap = new ArrayList<>(aktuelleAarList);
        finalInntektRegnskap.addAll(inntektRegnskap);

        List<InntektRegnskapRequest> finalInntektRegnskapWithAarListeForUtgifter = populerAarListeForUtgifter(finalInntektRegnskap);

        return finalInntektRegnskapWithAarListeForUtgifter;
    }

    private List<InntektRegnskapRequest> populerAarListeForUtgifter(List<InntektRegnskapRequest> finalInntektRegnskap) {
        List<String> alleAarMedData = finalInntektRegnskap
                .stream()
                .map(InntektRegnskapRequest::getAar)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        finalInntektRegnskap.forEach(inntektRequest -> {
            if (ConstantsMap.SUM_UTGIFTER.getString().equals(inntektRequest.getLabel())) {
                inntektRequest.setAarList(alleAarMedData);
            }
        });

        return finalInntektRegnskap;
    }

    private void assembleInntektRegnskap(List<InntektRegnskapRequest> inntektRegnskap, List<InntektRegnskapRequest> hentetRegnskap) {
        if (inntektRegnskap.isEmpty()) {
            inntektRegnskap.addAll(hentetRegnskap);
        }

        inntektRegnskap.forEach(inntektRegnskapRequest -> {
            hentetRegnskap.forEach(hentetData -> {
                if (inntektRegnskapRequest.getLabel().equals(hentetData.getLabel())) {
                    if (inntektRegnskapRequest.getBelopList() == null) {
                        inntektRegnskapRequest.setBelopList(new ArrayList<>());
                    }
                    inntektRegnskapRequest.getBelopList().add(hentetData.getBelop());
                }
            });
        });
    }

    public Map<String, List<UtgiftRegnskapRequest>> hentUtgiftRegnskap(String leilighetIds, String aar) {
        Map<String, List<UtgiftRegnskapRequest>> utgiftRegnskapMap = new HashMap<>();

        Integer[] leilighetIdArray = Arrays.stream(leilighetIds.split(","))
                .map(Integer::valueOf)
                .toArray(Integer[]::new);

        List<UtgiftRegnskapRequest> utgiftRegnskapRequests = AssemblerUtil.assembleUtgiftRegnskapRequest(
                repository.hentUtgift(leilighetIdArray, Integer.parseInt(aar))
        );

        Arrays.asList(leilighetIds.split(",")).forEach(leilighetId -> {

            List<UtgiftRegnskapRequest> utgiftRegnskap = new ArrayList<>();

            if (!utgiftRegnskapRequests.isEmpty()) {
                List<String> utgiftTypeNavnListe = utgiftRegnskapRequests.stream()
                        .map(UtgiftRegnskapRequest::getUtgiftTypeNavn)
                        .distinct()
                        .sorted()
                        .collect(Collectors.toList());

                utgiftTypeNavnListe.forEach(utgiftTypeNavn -> {
                    Double beregnetSumUtgift = beregnSumUtgifterPerUtgiftType(leilighetId, utgiftRegnskapRequests, utgiftTypeNavn);
                    utgiftRegnskap.add(new UtgiftRegnskapRequest.Builder()
                            .label(utgiftTypeNavn)
                            .belop(beregnetSumUtgift)
                            .build());
                });
            }

            List<UtgiftRegnskapRequest> finalUtgiftRegnskap = populerMedUtgiftDetaljer(leilighetId, utgiftRegnskap, utgiftRegnskapRequests);

            utgiftRegnskapMap.put(dataService.hentLeilighetNavn(leilighetId), finalUtgiftRegnskap);
        });

        return utgiftRegnskapMap;
    }

    private List<UtgiftRegnskapRequest> populerMedUtgiftDetaljer(
            String leilighetId,
            List<UtgiftRegnskapRequest> utgiftRegnskap,
            List<UtgiftRegnskapRequest> utgiftRegnskapRequests) {

        utgiftRegnskap.forEach(utgift -> {
            List<UtgiftRegnskapRequest> detaljer = new ArrayList<>();
            utgiftRegnskapRequests.forEach(utgiftRegnskapRequest -> {
                if(utgift.getLabel().equals(utgiftRegnskapRequest.getUtgiftTypeNavn()) &&
                   utgiftRegnskapRequest.getLeilighetId().intValue() == Integer.parseInt(leilighetId)) {
                    detaljer.add(utgiftRegnskapRequest);
                }
            });
            utgift.setUtgiftDetaljer(AssemblerUtil.assembleUtgiftDetalj(detaljer));
        });

        return utgiftRegnskap;
    }


    protected List<InntektRegnskapRequest> hentInntektRegnskap(Integer[] leilighetIdArray, String aar) {
        List<InntektRegnskapRequest> inntektRegnskapRequests = AssemblerUtil.assembleInntektRegnskapRequest(
                repository.hentInntekt(leilighetIdArray, Integer.parseInt(aar)));

        List<UtgiftRequest> utgiftRequests = AssemblerUtil.assembleUtgiftRequest(
                repository.hentUtgift(leilighetIdArray, Integer.parseInt(aar))
        );

        if (!inntektRegnskapRequests.isEmpty()) {
            addEmptyRow(inntektRegnskapRequests);

            Double sumBruttoInntekt = beregnSumInntekt(inntektRegnskapRequests);
            inntektRegnskapRequests.add(new InntektRegnskapRequest.Builder()
                    .label(ConstantsMap.SUM_BRUTTO_INNTEKT.getString())
                    .belop(sumBruttoInntekt)
                    .build());

            Double alleUtgifter = beregnSumUtgifter(utgiftRequests);
            inntektRegnskapRequests.add(new InntektRegnskapRequest.Builder()
                    .label(ConstantsMap.SUM_UTGIFTER.getString())
                    .belop(alleUtgifter)
                    .build());

            inntektRegnskapRequests.add(new InntektRegnskapRequest.Builder()
                    .label(ConstantsMap.SUM_BRUTTO_INNTEKT_MINUS_ALLE_UTGIFTER.getString())
                    .belop(sumBruttoInntekt - alleUtgifter)
                    .build());

            addEmptyRow(inntektRegnskapRequests);

            Double estimertSkatt = (sumBruttoInntekt - alleUtgifter)  * 0.22;
            inntektRegnskapRequests.add(new InntektRegnskapRequest.Builder()
                    .label(ConstantsMap.ESTIMERT_SKATT.getString())
                    .belop(estimertSkatt)
                    .build());

            inntektRegnskapRequests.add(new InntektRegnskapRequest.Builder()
                    .label(ConstantsMap.FAKTISK_SKATT.getString())
                    .belop(0.0)
                    .build());

            addEmptyRow(inntektRegnskapRequests);

            Double estimertNettoInntekt = sumBruttoInntekt - alleUtgifter - estimertSkatt;
            inntektRegnskapRequests.add(new InntektRegnskapRequest.Builder()
                    .label(ConstantsMap.ESTIMERT_NETTO_INNTEKT.getString())
                    .belop(rundeAvBelop(estimertNettoInntekt))
                    .build());

            inntektRegnskapRequests.add(new InntektRegnskapRequest.Builder()
                    .label(ConstantsMap.FAKTISK_NETTO_INNTEKT.getString())
                    .belop(0.0)
                    .build());
        }

        return inntektRegnskapRequests;
    }

    public Double beregnSumInntekt(List<InntektRegnskapRequest> inntekter) {
        return inntekter.stream().mapToDouble(inntekt -> inntekt.getBelop() != null ? inntekt.getBelop() : 0.0).sum();
    }

    public Double beregnSumUtgifter(List<UtgiftRequest> utgifter) {
        return utgifter.stream().mapToDouble(utgift -> utgift.getBelop() != null ? utgift.getBelop() : 0.0).sum();
    }

    public Double beregnSumUtgifterPerUtgiftType(String leilighetId,
                                                 List<UtgiftRegnskapRequest> utgifter,
                                                 String utgiftTypeNavn) {
        return utgifter.stream()
                .filter(utgiftRegnskapRequest ->
                        utgiftRegnskapRequest.getUtgiftTypeNavn().equals(utgiftTypeNavn) &&
                        utgiftRegnskapRequest.getLeilighetId().intValue() == Integer.parseInt(leilighetId))
                .mapToDouble(utgift -> utgift.getBelop() != null ? utgift.getBelop() : 0.0).sum();
    }

    private static void addEmptyRow(List<InntektRegnskapRequest> inntektRequests) {
        inntektRequests.add(new InntektRegnskapRequest.Builder()
                .label("")
                .build());
    }

    private InntektRegnskapRequest getAarRad(String aar) {
        return new InntektRegnskapRequest.Builder()
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
