package ZInvest.service;

import ZInvest.domain.Inntekt;
import ZInvest.domain.Leilighet;
import ZInvest.domain.Utgift;
import ZInvest.domain.dto.InntektRegnskapRequest;
import ZInvest.domain.dto.InntektRequest;
import ZInvest.domain.dto.LeilighetRequest;
import ZInvest.domain.dto.UtgiftRegnskapRequest;
import ZInvest.repository.ZInvestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.postgresql.hostchooser.HostRequirement.any;

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
        Long sumInntekt = Math.round(regnskapService.beregnSumInntekt(inntekter));
        assertThat(inntekter.get(0).getBelop() + inntekter.get(1).getBelop()).isEqualTo(sumInntekt);
    }


    @Test
    void testHentInntektRegnskapForFlereAar() {
        String leilighetIds = "1,2";
        String aarListe = "2021;2022";
        when(repository.hentSkatteprosent(anyInt())).thenReturn(22L);
        when(repository.hentInntekt(any(Integer[].class), eq(2021))).thenReturn(hentTestInntektForRegnskapFor2021());
        when(repository.hentInntekt(any(Integer[].class), eq(2022))).thenReturn(hentTestInntektForRegnskapFor2022());
        when(repository.hentUtgift(any(Integer[].class), eq(2021))).thenReturn(hentTestUtgiftForRegnskapFor2021());
        when(repository.hentUtgift(any(Integer[].class), eq(2022))).thenReturn(hentTestUtgiftForRegnskapFor2022());

        List<InntektRegnskapRequest> regnskap = regnskapService.hentInntektRegnskapForFlereAar(leilighetIds, aarListe);

        assertThat(regnskap.get(0).getAar()).isEqualTo("2021");
        assertThat(regnskap.get(1).getAar()).isEqualTo("2022");
        assertThat(regnskap.get(2).getLabel()).isEqualTo("Januar");
        assertThat(regnskap.get(2).getBelopList().get(0)).isEqualTo(1000L);
        assertThat(regnskap.get(2).getBelopList().get(1)).isEqualTo(1500L);
        assertThat(regnskap.get(3).getLabel()).isEqualTo("Februar");
        assertThat(regnskap.get(3).getBelopList().get(0)).isEqualTo(2000L);
        assertThat(regnskap.get(3).getBelopList().get(1)).isEqualTo(2500L);
        assertThat(regnskap.get(5).getLabel()).isEqualTo("Sum brutto inntekt (føres i skattemeldingen)");
        assertThat(regnskap.get(5).getBelopList().get(0)).isEqualTo(3000L);
        assertThat(regnskap.get(5).getBelopList().get(1)).isEqualTo(4000L);
        assertThat(regnskap.get(6).getLabel()).isEqualTo("Sum utgifter");
        assertThat(regnskap.get(6).getBelopList().get(0)).isEqualTo(500L);
        assertThat(regnskap.get(6).getBelopList().get(1)).isEqualTo(800L);
        assertThat(regnskap.get(6).getAarList().get(0)).isEqualTo("2021");
        assertThat(regnskap.get(6).getAarList().get(1)).isEqualTo("2022");
        assertThat(regnskap.get(7).getLabel()).isEqualTo("Sum brutto inntekt (etter trekk av alle utgifter)");
        assertThat(regnskap.get(7).getBelopList().get(0)).isEqualTo(2500L);
        assertThat(regnskap.get(7).getBelopList().get(1)).isEqualTo(3200L);
        assertThat(regnskap.get(9).getLabel()).isEqualTo("Estimert skatt");
        assertThat(regnskap.get(9).getBelopList().get(0)).isEqualTo(550L);
        assertThat(regnskap.get(9).getBelopList().get(1)).isEqualTo(704L);
        assertThat(regnskap.get(12).getLabel()).isEqualTo("Estimert netto inntekt (brutto inntekt trukket fra alle utgifter og estimert skatt)");
        assertThat(regnskap.get(12).getBelopList().get(0)).isEqualTo(1950L);
        assertThat(regnskap.get(12).getBelopList().get(1)).isEqualTo(2496L);
    }

    private List<Inntekt> hentTestInntektForRegnskapFor2021() {
        Inntekt inntekt1 = new Inntekt.Builder()
                .mnd(1)
                .belop(1000L)
                .build();

        Inntekt inntekt2 = new Inntekt.Builder()
                .mnd(2)
                .belop(2000L)
                .build();

        List<Inntekt> inntektList = new ArrayList<>();
        inntektList.add(inntekt1);
        inntektList.add(inntekt2);
        return inntektList;
    }

    private List<Inntekt> hentTestInntektForRegnskapFor2022() {
        Inntekt inntekt1 = new Inntekt.Builder()
                .mnd(1)
                .belop(1500L)
                .build();

        Inntekt inntekt2 = new Inntekt.Builder()
                .mnd(2)
                .belop(2500L)
                .build();

        List<Inntekt> inntektList = new ArrayList<>();
        inntektList.add(inntekt1);
        inntektList.add(inntekt2);
        return inntektList;
    }

    private List<Utgift> hentTestUtgiftForRegnskapFor2021() {
        Utgift utgift1 = new Utgift.Builder()
                .id(1)
                .belop(200L)
                .aar(2021)
                .mnd(1)
                .beskrivelse("beskrivelse1")
                .utgiftTypeId(1)
                .utgiftTypeBeskrivelse("Fast husleie hver måned")
                .utgiftTypeNavn("FELLESKOSTNADER")
                .build();

        Utgift utgift2 = new Utgift.Builder()
                .id(2)
                .belop(300L)
                .aar(2021)
                .mnd(2)
                .beskrivelse("beskrivelse2")
                .utgiftTypeId(4)
                .utgiftTypeBeskrivelse("Provisjon for megler per måned")
                .utgiftTypeNavn("PROVISJON MEGLER")
                .build();

        List<Utgift> utgiftList = new ArrayList<>();
        utgiftList.add(utgift1);
        utgiftList.add(utgift2);
        return utgiftList;
    }

    private List<Utgift> hentTestUtgiftForRegnskapFor2022() {
        Utgift utgift1 = new Utgift.Builder()
                .id(1)
                .belop(350L)
                .aar(2022)
                .mnd(1)
                .beskrivelse("beskrivelse1")
                .utgiftTypeId(1)
                .utgiftTypeBeskrivelse("Fast husleie hver måned")
                .utgiftTypeNavn("FELLESKOSTNADER")
                .build();

        Utgift utgift2 = new Utgift.Builder()
                .id(2)
                .belop(450L)
                .aar(2022)
                .mnd(2)
                .beskrivelse("beskrivelse2")
                .utgiftTypeId(4)
                .utgiftTypeBeskrivelse("Provisjon for megler per måned")
                .utgiftTypeNavn("PROVISJON MEGLER")
                .build();

        List<Utgift> utgiftList = new ArrayList<>();
        utgiftList.add(utgift1);
        utgiftList.add(utgift2);
        return utgiftList;
    }


    private List<InntektRegnskapRequest> hentTestInntekt() {
        InntektRegnskapRequest inntekt1 = new InntektRegnskapRequest.Builder()
                .belop(11L)
                .build();

        InntektRegnskapRequest inntekt2 = new InntektRegnskapRequest.Builder()
                .belop(2L)
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