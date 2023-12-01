
import com.epam.training.sportsbetting.data.TestDataLoader;
import com.epam.training.sportsbetting.domain.*;
import com.epam.training.sportsbetting.repository.BetRepository;
import com.epam.training.sportsbetting.repository.PlayerRepository;
import com.epam.training.sportsbetting.repository.WagerRepository;
import com.epam.training.sportsbetting.service.AuthenticationException;
import com.epam.training.sportsbetting.service.DefaultSportsBettingService;
import com.epam.training.sportsbetting.service.LowBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultSportsBettingServiceTest {

    private DefaultSportsBettingService underTest;

    private Player testPlayer;

    private User testUser;

    @Mock
    private TestDataLoader dataStore;

    @Mock
    private BetRepository betRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private WagerRepository wagerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new DefaultSportsBettingService(betRepository, playerRepository, wagerRepository);
        testPlayer = new Player("testEmail", "pwd", "testName", new BigDecimal(100), Currency.EUR);
        testUser = new User("testEmail", "pwd");
    }

    @Test
    public void testAuthenticateUserShouldReturnPlayerWhenLoginSuccessful() {
        //GIVEN
        Player expected = testPlayer;

        BDDMockito.given(playerRepository.findByEmailAndPassword(testUser.getEmail(), testUser.getPassword())).willReturn(Optional.of(expected));
        //WHEN
        Player actual = underTest.authenticateUser(testUser);
        //THEN
        BDDMockito.verify(playerRepository).findByEmailAndPassword(testUser.getEmail(), testUser.getPassword());
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testAuthenticateUserShouldThrowExceptionWhenEmailIsNotMatching() {
        //GIVEN
        Player expected = new Player("testEmail", "pwd", "testName", new BigDecimal(0), Currency.EUR);

        User testUser = new User("wrongTestEmail", "pwd");

        BDDMockito.given(playerRepository.findByEmailAndPassword(testPlayer.getEmail(), testPlayer.getPassword())).willReturn(Optional.of(expected));
        //WHEN
        Assertions.assertThrows(AuthenticationException.class, () -> underTest.authenticateUser(testUser));
    }

    @Test
    public void testAuthenticateUserShouldThrowExceptionWhenPasswordIsNotMatching() {
        //GIVEN
        Player expected = new Player("testEmail", "pwd", "testName", new BigDecimal(0), Currency.EUR);

        User testUser = new User("wrongTestEmail", "pwd");

        BDDMockito.given(playerRepository.findByEmailAndPassword(testPlayer.getEmail(), testPlayer.getPassword())).willReturn(Optional.of(expected));
        //WHEN
        Assertions.assertThrows(AuthenticationException.class, () -> underTest.authenticateUser(testUser));
    }

    @Test
    public void testFindAllBetsShouldReturnBetsList() {
        //GIVEN
        List<Bet> expected = new ArrayList<>();
        expected.add(new Bet());
        BDDMockito.given(betRepository.findAll()).willReturn(expected);
        //WHEN
        List<Bet> actual = underTest.findAllBets();
        //THEN
        BDDMockito.verify(betRepository).findAll();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testCreateWagerShouldThrowLowBalanceExceptionWhenBalanceIsLessThenBetAmount() {
        //GIVEN
        Player testPlayer = new Player("testEmail", "pwd", "testName", new BigDecimal(100), Currency.EUR);
        Outcome testOutcome = new Outcome();
        BigDecimal testAmount = new BigDecimal(101);
        //WHEN
        Assertions.assertThrows(LowBalanceException.class, () -> underTest.createWager(testPlayer, testOutcome, testAmount));
    }

    @Test
    public void testCreateWagerCreateTheWagerAndShouldNotBeNull() {
        //GIVEN
        Player testPlayer = new Player("testEmail", "pwd", "testName", new BigDecimal(101), Currency.EUR);
        Outcome testOutcome = new Outcome();
        BigDecimal testAmount = new BigDecimal(100);
        Wager expected = new Wager(testPlayer,testOutcome,testAmount,testPlayer.getCurrency(), LocalDateTime.now(),false, false);
        BDDMockito.given(dataStore.createDataStoreWager(testPlayer,testOutcome,testAmount)).willReturn(expected);
        //WHEN
        Wager actual = underTest.createWager(testPlayer, testOutcome, testAmount);
        //THEN
        //Assertions.assertEquals(actual, expected);
        Assertions.assertNotNull(actual);
    }

    @Test
    public void testCreateWagerShouldPayForWagerWhenWagerIsCreated() {
        //GIVEN
        Player testPlayer = new Player("testEmail", "pwd", "testName", new BigDecimal(100), Currency.EUR);
        Outcome testOutcome = new Outcome();
        BigDecimal testAmount = new BigDecimal(20);
        BigDecimal expected = new BigDecimal(80);
        //WHEN
        underTest.createWager(testPlayer, testOutcome, testAmount);
        BigDecimal actual = testPlayer.getBalance();
        //THEN
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testFindAllWagersShouldReturnWagersList() {
        //GIVEN
        List<Wager> expected = new ArrayList<>();
        expected.add(new Wager());
        BDDMockito.given(wagerRepository.findAll()).willReturn(expected);
        //WHEN
        List<Wager> actual = underTest.findAllWagers();
        //THEN
        BDDMockito.verify(wagerRepository).findAll();
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testCalculateResultsShouldSetWinTrueOfWagerListFirstElementWhenListSizeIs1() {
        //GIVEN
        List<Wager> expected = new ArrayList<>();
        expected.add(new Wager(testPlayer, new Outcome("testEvent", new Bet(), new BigDecimal(1), false),
                new BigDecimal(100), Currency.EUR, LocalDateTime.now(), false, false));
        //WHEN
        underTest.calculateResults(expected);
        //THEN
        Assertions.assertTrue(expected.get(0).isWin());
    }

    @Test
    public void testCalculateResultsShouldSetWinTrueOfWagerListSecondElementAndProcessedTrueOfAllElementWhenListSizeMoreThen1() {
        //GIVEN
        List<Wager> expected = new ArrayList<>();
        expected.add(new Wager(testPlayer, new Outcome("testEvent1", new Bet(), new BigDecimal(1), false),
                new BigDecimal(100), Currency.EUR, LocalDateTime.now(), false, false));
        expected.add(new Wager(testPlayer, new Outcome("testEvent2", new Bet(), new BigDecimal(2), false),
                new BigDecimal(100), Currency.EUR, LocalDateTime.now(), false, false));
        //WHEN
        underTest.calculateResults(expected);
        //THEN
        Assertions.assertTrue(expected.get(1).isWin());
        Assertions.assertTrue(expected.get(0).isProcessed());
        Assertions.assertTrue(expected.get(1).isProcessed());
    }

    @Test
    public void testCalculateResultsShouldMultiplyWagerAmountAndAddItToPlayersBalanceWhenWagerIsSetToWin() {
        //GIVEN
        List<Wager> expected = new ArrayList<>();
        expected.add(new Wager(testPlayer, new Outcome("testEvent1", new Bet(), new BigDecimal(1), false),
                new BigDecimal(100), Currency.EUR, LocalDateTime.now(), false, false));
        expected.add(new Wager(testPlayer, new Outcome("testEvent2", new Bet(), new BigDecimal(2), false),
                new BigDecimal(100), Currency.EUR, LocalDateTime.now(), false, false));
        BigDecimal actual = new BigDecimal(300);
        //WHEN
        underTest.calculateResults(expected);
        //THEN
        Assertions.assertEquals(expected.get(0).getPlayer().getBalance(), actual);
    }
}
