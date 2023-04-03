package ZInvest.service;

import ZInvest.domain.Inntekt;
import ZInvest.domain.InntektType;
import ZInvest.domain.Leilighet;
import ZInvest.domain.UtgiftType;
import ZInvest.domain.dto.InntektRequest;
import ZInvest.domain.dto.InntektTypeRequest;
import ZInvest.domain.dto.LeilighetRequest;
import ZInvest.domain.dto.UtgiftTypeRequest;

import java.util.ArrayList;
import java.util.List;

public class AssemblerUtil {

    public static Leilighet assembleLeilighet(LeilighetRequest leilighetRequest) {
        return new Leilighet.Builder()
                .navn(leilighetRequest.getNavn())
                .adresse(leilighetRequest.getAdresse())
                .postNr(leilighetRequest.getPostNr())
                .postSted(leilighetRequest.getPostSted())
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
                        .leilighet(inntekt.getLeilighet())
                        .inntektType(inntekt.getInntektType())
                        .belop(inntekt.getBelop())
                        .aar(inntekt.getAar())
                        .mnd(inntekt.getMnd())
                        .build()
                )
        );
        return inntektRequests;
    }



    public static List<UtgiftTypeRequest> assembleUtgiftTypeRequest(List<UtgiftType> utgiftTypeList) {
        List<UtgiftTypeRequest> utgiftTypeRequests = new ArrayList<>();
        utgiftTypeList.forEach(inntektType ->
                utgiftTypeRequests.add(new UtgiftTypeRequest.Builder()
                        .id(inntektType.getId())
                        .navn(inntektType.getNavn())
                        .beskrivelse(inntektType.getBeskrivelse())
                        .build()
                )
        );
        return utgiftTypeRequests;
    }

    public static List<LeilighetRequest> assembleLeilighetRequest(List<Leilighet> leilighetList) {
        List<LeilighetRequest> leilighetRequests = new ArrayList<>();
        leilighetList.forEach(leilighet ->
                leilighetRequests.add(new LeilighetRequest.Builder()
                        .id(leilighet.getId())
                        .navn(leilighet.getNavn())
                        .adresse(leilighet.getAdresse())

                        .build()
                )
        );
        return leilighetRequests;
    }
}
