package ZInvest.service;

import ZInvest.domain.*;
import ZInvest.domain.dto.*;

import java.util.ArrayList;
import java.util.List;

public class AssemblerUtil {

    public static Leilighet assembleLeilighet(LeilighetRequest leilighetRequest) {
        return new Leilighet.Builder()
                .navn(leilighetRequest.getNavn())
                .adresse(leilighetRequest.getAdresse())
                .postNr(leilighetRequest.getPostnr())
                .postSted(leilighetRequest.getPoststed())
                .build();
    }

    public static List<InntektTypeRequest> assembleInntektTypeRequest(List<InntektType> inntektTypeList) {
        List<InntektTypeRequest> inntektTypeRequests = new ArrayList<>();
        inntektTypeList.forEach(inntektType ->
             inntektTypeRequests.add(new InntektTypeRequest.Builder()
                     .id(inntektType.getId())
                     .navn(inntektType.getNavn())
                     .beskrivelse(inntektType.getBeskrivelse())
                     .build()
             )
        );
        return inntektTypeRequests;
    }

    public static List<InntektRequest> assembleInntektRequest(List<Inntekt> inntektList) {
        List<InntektRequest> inntektRequests = new ArrayList<>();
        inntektList.forEach(inntekt ->
                inntektRequests.add(new InntektRequest.Builder()
                        .id(inntekt.getId())
                        .mnd(inntekt.getMnd())
                        .label(MaanedMap.hentMaaned(inntekt.getMnd()))
                        .belop(inntekt.getBelop())
                        .beskrivelse(inntekt.getBeskrivelse())
                        .build()
                )
        );
        return inntektRequests;
    }

    public static List<UtgiftRequest> assembleUtgiftRequest(List<Utgift> utgiftList) {
        List<UtgiftRequest> utgiftRequests = new ArrayList<>();
        utgiftList.forEach(utgift ->
                utgiftRequests.add(new UtgiftRequest.Builder()
                        .id(utgift.getId())
                        .mnd(utgift.getMnd())
                        .label(MaanedMap.hentMaaned(utgift.getMnd()))
                        .belop(utgift.getBelop())
                        .beskrivelse(utgift.getBeskrivelse())
                        .build()
                )
        );
        return utgiftRequests;
    }

    public static List<UtgiftTypeRequest> assembleUtgiftTypeRequest(List<UtgiftType> utgiftTypeList) {
        List<UtgiftTypeRequest> utgiftTypeRequests = new ArrayList<>();
        utgiftTypeList.forEach(utgiftType ->
                utgiftTypeRequests.add(new UtgiftTypeRequest.Builder()
                        .id(utgiftType.getId())
                        .navn(utgiftType.getNavn())
                        .beskrivelse(utgiftType.getBeskrivelse())
                        .mndUavhengig(utgiftType.isMndUavhengig())
                        .build()
                )
        );
        return utgiftTypeRequests;
    }

    public static LeilighetRequest assembleLeilighetRequest(Leilighet leilighet) {
        return new LeilighetRequest.Builder()
                .id(leilighet.getId())
                .navn(leilighet.getNavn())
                .adresse(leilighet.getAdresse())
                .postNr(leilighet.getPostNr())
                .postSted(leilighet.getPostSted())
                .build();
    }
}
