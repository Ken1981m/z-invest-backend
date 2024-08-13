package ZInvest.service;

import ZInvest.domain.Leilighet;
import ZInvest.domain.dto.InntektRegnskapRequest;
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

    DataService dataService;
    RegnskapService regnskapService;
    ZInvestRepository repository;

    @BeforeEach
    public void setup() {
        repository = mock(ZInvestRepository.class);
        dataService = new DataService();
        dataService.setRepository(repository);
        regnskapService = new RegnskapService();
        regnskapService.setRepository(repository);
    }

    @Test
    public void hentLeiligheter() {
        List<Leilighet> leilighet = hentTestLeilighet();
        when(repository.hentLeiligheter()).thenReturn(leilighet);

        List<LeilighetRequest> leilighetRequests = dataService.hentLeiligheter();

        assertThat(leilighet.get(0).getNavn() == leilighetRequests.get(0).getNavn());
    }

    @Test
    public void summerInntekt() {
        List<InntektRegnskapRequest> inntekter = hentTestInntekt();
        Double sumInntekt = regnskapService.beregnSumInntekt(inntekter);
        assertThat(inntekter.get(0).getBelop() + inntekter.get(1).getBelop()).isEqualTo(sumInntekt);
    }

    @Test
    public void testRundeAvBelop() {
        Double tall = 123.43533535;
        Double res = regnskapService.rundeAvBelop(tall);
        assertThat(res).isEqualTo(123.44);
    }

    private List<InntektRegnskapRequest> hentTestInntekt() {
        InntektRegnskapRequest inntekt1 = new InntektRegnskapRequest.Builder()
                .belop(10.5)
                .build();

        InntektRegnskapRequest inntekt2 = new InntektRegnskapRequest.Builder()
                .belop(1.5)
                .build();

        List<InntektRegnskapRequest> inntektRegnskapRequestList = new ArrayList<>();
        inntektRegnskapRequestList.add(inntekt1);
        inntektRegnskapRequestList.add(inntekt2);
        return inntektRegnskapRequestList;
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