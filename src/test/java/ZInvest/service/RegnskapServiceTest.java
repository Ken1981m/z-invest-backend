package ZInvest.service;

import ZInvest.domain.Leilighet;
import ZInvest.domain.dto.InntektRequest;
import ZInvest.domain.dto.LeilighetRequest;
import ZInvest.repository.ZInvestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegnskapServiceTest {

    RegnskapService regnskapService;
    ZInvestRepository repository;

    @BeforeEach
    public void setup() {
        repository = mock(ZInvestRepository.class);
        regnskapService = new RegnskapService();
        regnskapService.setRepository(repository);
    }

    @Test
    public void hentLeiligheter() {
        List<Leilighet> leilighet = hentTestLeilighet();
        when(repository.hentLeiligheter()).thenReturn(leilighet);

        List<LeilighetRequest> leilighetRequests = regnskapService.hentLeiligheter();

        assertThat(leilighet.get(0).getNavn() == leilighetRequests.get(0).getNavn());
    }

    @Test
    public void summerInntekt() {
        List<InntektRequest> inntekter = hentTestInntekt();
        Double sumInntekt = regnskapService.beregnSumInntekt(inntekter);
        assertThat(inntekter.get(0).getBelop() + inntekter.get(1).getBelop()).isEqualTo(sumInntekt);
    }


    private List<InntektRequest> hentTestInntekt() {
        InntektRequest inntekt1 = new InntektRequest.Builder()
                .belop(10.5)
                .build();

        InntektRequest inntekt2 = new InntektRequest.Builder()
                .belop(1.5)
                .build();

        List<InntektRequest> inntektRequestList = new ArrayList<>();
        inntektRequestList.add(inntekt1);
        inntektRequestList.add(inntekt2);
        return inntektRequestList;
     }

    private List<Leilighet> hentTestLeilighet() {
        List<Leilighet> leilighetList = new ArrayList<>();
        Leilighet leilighet1 = new Leilighet.Builder()
                .id(1)
                .navn("Andeby1")
                .adresse("Duck1")
                .postNr("0223")
                .postSted("Antonby")
                .build();

        Leilighet leilighet2 = new Leilighet.Builder()
                .id(2)
                .navn("Andeby2")
                .adresse("Duck2")
                .postNr("0224")
                .postSted("Antonby")
                .build();

        leilighetList.add(leilighet1);
        leilighetList.add(leilighet2);
        return leilighetList;
    }
}