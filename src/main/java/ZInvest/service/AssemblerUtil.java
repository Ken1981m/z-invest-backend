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
                .beskrivelse(leilighetRequest.getBeskrivelse())
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
                        .beskrivelse(inntekt.getBeskrivelse() != null ? inntekt.getBeskrivelse() : "")
                        .build()
                )
        );
        return inntektRequests;
    }

    public static List<InntektRegnskapRequest> assembleInntektRegnskapRequest(List<Inntekt> inntektList) {
        List<InntektRegnskapRequest> inntektRegnskapRequests = new ArrayList<>();
        inntektList.forEach(inntekt ->
                inntektRegnskapRequests.add(new InntektRegnskapRequest.Builder()
                        .label(MaanedMap.hentMaaned(inntekt.getMnd()))
                        .belop(inntekt.getBelop())
                        .build()
                )
        );
        return inntektRegnskapRequests;
    }

    public static List<UtgiftRequest> assembleUtgiftRequest(List<Utgift> utgiftList) {
        List<UtgiftRequest> utgiftRequests = new ArrayList<>();
        utgiftList.forEach(utgift ->
                utgiftRequests.add(new UtgiftRequest.Builder()
                        .id(utgift.getId())
                        .mnd(utgift.getMnd())
                        .label(MaanedMap.hentMaaned(utgift.getMnd()))
                        .belop(utgift.getBelop())
                        .utgiftBeskrivelse(utgift.getBeskrivelse() != null ? utgift.getBeskrivelse() : "")
                        .mndUavhengig(utgift.getMndUavhengig())
                        .utgiftTypeId(utgift.getUtgiftTypeId())
                        .utgiftTypeNavn(utgift.getUtgiftTypeNavn())
                        .utgiftTypeBeskrivelse(utgift.getUtgiftTypeBeskrivelse())
                        .build()
                )
        );
        return utgiftRequests;
    }

    public static List<UtgiftRegnskapRequest> assembleUtgiftRegnskapRequest(List<Utgift> utgiftList) {
        List<UtgiftRegnskapRequest> utgiftRegnskapRequests = new ArrayList<>();
        utgiftList.forEach(utgift ->
                utgiftRegnskapRequests.add(new UtgiftRegnskapRequest.Builder()
                        .leilighetId(utgift.getLeilighetId())
                        .label(MaanedMap.hentMaaned(utgift.getMnd()))
                        .belop(utgift.getBelop())
                        .utgiftBeskrivelse(utgift.getBeskrivelse() != null ? utgift.getBeskrivelse() : "")
                        .utgiftTypeNavn(utgift.getUtgiftTypeNavn())
                        .build()
                )
        );
        return utgiftRegnskapRequests;
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

    public static List<UtgiftDetalj> assembleUtgiftDetalj(List<UtgiftRegnskapRequest> utgiftRegnskapRequestList) {
        List<UtgiftDetalj> utgiftDetaljer = new ArrayList<>();
        utgiftRegnskapRequestList.forEach(utgiftRegnskapRequest ->
            utgiftDetaljer.add(new UtgiftDetalj.Builder()
                    .navn(hentNavnOgBeskrivelse(utgiftRegnskapRequest))
                    .belop(utgiftRegnskapRequest.getBelop())
                    .build()
            )
        );

        return utgiftDetaljer;
    }

    private static String hentNavnOgBeskrivelse(UtgiftRegnskapRequest utgiftRegnskapRequest) {
        return utgiftRegnskapRequest.getLabel() != null
                ? (utgiftRegnskapRequest.getLabel() + (
                    !"".equals(utgiftRegnskapRequest.getUtgiftBeskrivelse()) ? " - " + utgiftRegnskapRequest.getUtgiftBeskrivelse() : ""
                ) )
                : utgiftRegnskapRequest.getUtgiftBeskrivelse();
    }

    public static LeilighetRequest assembleLeilighetRequest(Leilighet leilighet) {
        return new LeilighetRequest.Builder()
                .id(leilighet.getId())
                .navn(leilighet.getNavn())
                .adresse(leilighet.getAdresse())
                .postNr(leilighet.getPostNr())
                .postSted(leilighet.getPostSted())
                .beskrivelse(leilighet.getBeskrivelse())
                .build();
    }

    public static SkatteprosentRequest assembleSkatteprosentRequest(Skatteprosent skatteprosent) {
        return new SkatteprosentRequest.Builder()
                .id(skatteprosent.getId())
                .aar(skatteprosent.getAar())
                .skatteprosent(skatteprosent.getSkatteprosent())
                .build();
    }

    public static FaktiskBetaltSkattRequest assembleFaktiskBetaltSkattRequest(FaktiskBetaltSkatt faktiskBetaltSkatt) {
        return new FaktiskBetaltSkattRequest.Builder()
                .id(faktiskBetaltSkatt.getId())
                .aar(faktiskBetaltSkatt.getAar())
                .faktiskSkattBelopForUtleieUtfyltISkattemelding(faktiskBetaltSkatt.getFaktiskSkattBelopForUtleieUtfyltISkattemelding())
                .faktiskSkattBelopEtterUtleieUtfyltISkattemelding(faktiskBetaltSkatt.getFaktiskSkattBelopEtterUtleieUtfyltISkattemelding())
                .build();
    }
}
