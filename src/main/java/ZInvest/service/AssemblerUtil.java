package ZInvest.service;

import ZInvest.domain.InntektType;
import ZInvest.domain.Leilighet;
import ZInvest.domain.dto.InntektTypeRequest;
import ZInvest.domain.dto.LeilighetRequest;

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
}
